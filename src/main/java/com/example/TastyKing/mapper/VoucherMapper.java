package com.example.TastyKing.mapper;

import com.example.TastyKing.dto.request.VoucherRequest;
import com.example.TastyKing.dto.response.VoucherResponse;
import com.example.TastyKing.entity.Voucher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.web.multipart.MultipartFile;

@Mapper(componentModel = "spring")
public interface VoucherMapper {

    @Mapping(source = "voucherImage", target = "voucherImage", qualifiedByName = "mapImage")
    Voucher toVoucher(VoucherRequest request);

    VoucherResponse toVoucherResponse(Voucher voucher);

    @Named("mapImage")
    default String mapImage(MultipartFile voucherImage) {
        if (voucherImage != null && !voucherImage.isEmpty()) {
            return voucherImage.getOriginalFilename();
        }
        return null;
    }
}