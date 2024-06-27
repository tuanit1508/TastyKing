package com.example.TastyKing.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class Voucher {
    @Id
    @Column(name = "VoucherId", length = 50)
    private String voucherId;

    @Column(name = "VoucherTitle", nullable = false, length = 100)
    private String voucherTitle;

    @Column(name = "VoucherDiscount", nullable = false)
    private int voucherDiscount;

    @Column(name = "VoucherQuantity", nullable = false)
    private int voucherQuantity;

    @Column(name = "VoucherUsed", nullable = false)
    private int numberVoucherUsed;

    @Column(name = "VoucherExchangeValue", nullable = false)
    private Double voucherExchangePoint;

    @Column(name = "VoucherStartDate", nullable = false)
    private LocalDateTime voucherStartDate;

    @Column(name = "VoucherDueDate", nullable = false)
    private LocalDateTime voucherDueDate;

    @Column(name = "VoucherImage", length = 2000)
    private String voucherImage;

    @Column(name = "VoucherDescribe", columnDefinition = "TEXT")
    private String voucherDescribe;

    @Column(name = "IsExpired")
    private boolean isExpired;
}
