package com.example.vacationservice.controllers.responses;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;

public class PaymentNumResponse {
    @JsonFormat(shape=JsonFormat.Shape.STRING)
    private BigDecimal amount;

    public PaymentNumResponse(BigDecimal amount) {
        this.amount = amount;
    }
}
