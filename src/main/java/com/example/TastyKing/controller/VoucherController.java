package com.example.TastyKing.controller;

import com.example.TastyKing.dto.request.UpdateVoucherRequest;
import com.example.TastyKing.dto.request.VoucherRequest;
import com.example.TastyKing.dto.response.ApiResponse;
import com.example.TastyKing.dto.response.VoucherResponse;
import com.example.TastyKing.service.VoucherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/voucher")
@RequiredArgsConstructor
@CrossOrigin("*")
public class VoucherController {

    @Autowired
    private VoucherService voucherService;

    @PostMapping
    public ApiResponse<VoucherResponse> createVoucher(@ModelAttribute @Valid VoucherRequest request) throws IOException {
        return ApiResponse.<VoucherResponse>builder()
                .result(voucherService.createVoucher(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<VoucherResponse>> getAllVoucher(){
        return ApiResponse.<List<VoucherResponse>>builder()
                .result(voucherService.getAllVoucher())
                .build();
    }

    @DeleteMapping("/{voucherID}")
    public ApiResponse<String> deleteVoucher(@PathVariable("voucherID") String voucherID){
        return ApiResponse.<String>builder()
                .result(voucherService.deleteVoucher(voucherID))
                .build();
    }

    @PutMapping("/{voucherID}")
    public ApiResponse<VoucherResponse> updateVoucher(@PathVariable("voucherID") String voucherID, @ModelAttribute @Valid UpdateVoucherRequest request) throws IOException {
        return ApiResponse.<VoucherResponse>builder()
                .result(voucherService.updateVoucher(voucherID, request))
                .build();
    }

    @GetMapping("/{voucherID}")
    public ApiResponse<VoucherResponse> getVoucherByVoucherID(@PathVariable("voucherID") String voucherID){
        return ApiResponse.<VoucherResponse>builder()
                .result(voucherService.getVoucherByVoucherID(voucherID))
                .build();
    }
}
