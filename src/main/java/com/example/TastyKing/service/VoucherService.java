package com.example.TastyKing.service;

import com.example.TastyKing.dto.request.UpdateVoucherRequest;
import com.example.TastyKing.dto.request.VoucherRequest;
import com.example.TastyKing.dto.response.VoucherResponse;
import com.example.TastyKing.entity.Combo;
import com.example.TastyKing.entity.Voucher;
import com.example.TastyKing.exception.AppException;
import com.example.TastyKing.exception.ErrorCode;
import com.example.TastyKing.mapper.VoucherMapper;
import com.example.TastyKing.repository.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VoucherService {
    private static final String UPLOAD_DIR = "D:\\FPT U\\FU-SU24\\SWP391\\TastyKing-FE\\img\\";

    @Autowired
    private VoucherRepository voucherRepository;
    @Autowired
    private VoucherMapper voucherMapper;

    @PreAuthorize("hasRole('ADMIN')")
    public VoucherResponse createVoucher(VoucherRequest request) throws IOException {
        String relativeImagePath = saveImage(request.getVoucherImage());

        if (voucherRepository.existsById(request.getVoucherId())){
            throw new AppException(ErrorCode.VOUCHER_HAS_EXISTED);
        }
        Voucher voucher = voucherMapper.toVoucher(request);
        voucher.setNumberVoucherUsed(0);
        voucher.setVoucherImage(relativeImagePath);
        voucher.setExpired(false);
        Voucher newVoucher = voucherRepository.save(voucher);
        return voucherMapper.toVoucherResponse(newVoucher);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public String deleteVoucher(String voucherID){
        voucherRepository.deleteById(voucherID);
        return "Voucher deleted successfull";
    }

    public List<VoucherResponse> getAllVoucher(){
        List<Voucher> vouchers = voucherRepository.findAll();
        return vouchers.stream()
                .map(voucherMapper::toVoucherResponse)
                .collect(Collectors.toList());
    }

    public VoucherResponse getVoucherByVoucherID(String voucherID){
        Voucher voucher = voucherRepository.findById(voucherID).orElseThrow(() -> new AppException(ErrorCode.VOUCHER_NOT_EXIST));
        return voucherMapper.toVoucherResponse(voucher);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public VoucherResponse updateVoucher(String voucherID, UpdateVoucherRequest request ) throws IOException {
        String relativeImagePath = saveImage(request.getVoucherImage());
        Voucher voucher = voucherRepository.findById(voucherID).orElseThrow(() -> new AppException(ErrorCode.VOUCHER_NOT_EXIST));
        voucher.setVoucherTitle(request.getVoucherTitle());
        voucher.setVoucherDiscount(request.getVoucherDiscount());
        voucher.setVoucherQuantity(request.getVoucherQuantity());
        voucher.setVoucherExchangePoint(request.getVoucherExchangePoint());
        voucher.setVoucherStartDate(request.getVoucherStartDate());
        voucher.setVoucherDueDate(request.getVoucherDueDate());
        voucher.setVoucherImage(relativeImagePath);

        Voucher voucherUpdate = voucherRepository.save(voucher);

        return voucherMapper.toVoucherResponse(voucherUpdate);
    }



    private String saveImage(MultipartFile image) throws IOException {
        if (image != null && !image.isEmpty()) {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            String imagePath = UPLOAD_DIR + image.getOriginalFilename();
            Files.write(Paths.get(imagePath), image.getBytes());
            return "img/" + image.getOriginalFilename();
        }
        return null;
    }


    @Scheduled(cron = "0 0 0 * * ?") // Run daily at midnight
    public void updateExpiredVouchers() {
        List<Voucher> vouchers = voucherRepository.findAll();
        LocalDateTime now = LocalDateTime.now();
        for (Voucher voucher : vouchers) {
            if ((voucher.getVoucherDueDate().isBefore(now) || voucher.getNumberVoucherUsed() > voucher.getVoucherQuantity()) && !voucher.isExpired()) {
                voucher.setExpired(true);
                voucherRepository.save(voucher);
            }
        }
    }
}
