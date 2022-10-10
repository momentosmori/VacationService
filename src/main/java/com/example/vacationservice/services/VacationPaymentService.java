package com.example.vacationservice.services;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class VacationPaymentService {
    public BigDecimal getPayByDaysNum(BigDecimal avgSalary, long numDays) {
        if(avgSalary.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Average salary can not be negative");
        }

        if(numDays < 0) {
            throw new IllegalArgumentException("Number of day can not be negative");
        }

        return avgSalary
                .multiply(new BigDecimal(12))
                .multiply(new BigDecimal(numDays))
                .divide(new BigDecimal(365), 2, RoundingMode.FLOOR);
    }
    public BigDecimal getPayByDateRange(BigDecimal avgSalary, LocalDate startDate, LocalDate finishDate) {
        return getPayByDaysNum(avgSalary, ChronoUnit.DAYS.between(startDate, finishDate.plusDays(1)));
    }
}
