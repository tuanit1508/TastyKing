package com.example.TastyKing.dto.response;


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
public class VoucherResponse {
    private String voucherId;
    private String voucherTitle;
    private int voucherDiscount;
    private int voucherQuantity;
    private int numberVoucherUsed;
    private Double voucherExchangePoint;
    @OpenDateConstraint(message = "OPEN_DATE_INVALID")
    private LocalDateTime voucherStartDate;
    private LocalDateTime voucherDueDate;
    private String voucherImage;
    private String voucherDescribe;
    private boolean isExpired;
}

