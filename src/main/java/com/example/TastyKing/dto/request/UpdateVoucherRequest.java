package com.example.TastyKing.dto.request;

import com.example.TastyKing.validate.OpenDateConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UpdateVoucherRequest {

    private String voucherTitle;
    private int voucherDiscount;
    private int voucherQuantity;
    private Double voucherExchangePoint;
    @OpenDateConstraint(message = "OPEN_DATE_INVALID")
    private LocalDateTime voucherStartDate;
    private LocalDateTime voucherDueDate;
    private MultipartFile voucherImage;
    private String voucherDescribe;
}
