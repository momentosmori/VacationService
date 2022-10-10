package com.momentosmori.vacationservice.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class VacationPaymentServicesTest
{
    @Autowired
    VacationPaymentService vacationPaymentService ;

    @Test
    public void getPayByDaysNumTest() {
        assertEquals(vacationPaymentService.getPayByDaysNum(new BigDecimal(10000), 10), new BigDecimal("3287.67"));
        assertEquals(vacationPaymentService.getPayByDaysNum(new BigDecimal(0), 10), new BigDecimal("0.00"));
        assertEquals(vacationPaymentService.getPayByDaysNum(new BigDecimal(10000), 0), new BigDecimal("0.00"));
        assertEquals(vacationPaymentService.getPayByDaysNum(new BigDecimal(0), 0), new BigDecimal("0.00"));
    }

    @Test
    public void getPayByDaysNumTest_Errors() {
        assertThrows(
                IllegalArgumentException.class,
                () -> { vacationPaymentService.getPayByDaysNum(new BigDecimal(-300), 0); }
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> { vacationPaymentService.getPayByDaysNum(new BigDecimal(300), -10); }
        );
    }

    @Test
    public void getPayByDateRangeTest() {
        assertEquals(new BigDecimal("493.15"), vacationPaymentService
                .getPayByDateRange(new BigDecimal(1000), LocalDate.of(2022, 9, 1), LocalDate.of(2022, 9, 15) ));

        assertEquals(new BigDecimal("1183.56"), vacationPaymentService
                .getPayByDateRange(new BigDecimal(1000), LocalDate.of(2022, 9, 10), LocalDate.of(2022, 10, 15) ));

        assertEquals(new BigDecimal("4076.71"), vacationPaymentService
                .getPayByDateRange(new BigDecimal(1000), LocalDate.of(2022, 9, 10), LocalDate.of(2023, 1, 11) ));
    }

    @Test
    public void getPayByDateRangeTest_Errors() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    vacationPaymentService
                            .getPayByDateRange(new BigDecimal(1000), LocalDate.of(2022, 9, 10), LocalDate.of(2022, 1, 11));
                }
        );
    }
}
