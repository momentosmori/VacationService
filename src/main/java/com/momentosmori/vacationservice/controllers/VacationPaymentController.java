package com.momentosmori.vacationservice.controllers;

import com.momentosmori.vacationservice.controllers.responses.PaymentNumResponse;
import com.momentosmori.vacationservice.services.VacationPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;
import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
@RequestMapping(value = "/vacationpayment", produces = MediaType.APPLICATION_JSON_VALUE)
public class VacationPaymentController {

    @Autowired
    public VacationPaymentService vacationPaymentService;

    @GetMapping(path = "bydaysnum")
    public @ResponseBody ResponseEntity<PaymentNumResponse> getPayByDaysNum(@PathParam("avgSalary") BigDecimal avgSalary,
                                                                            @PathParam("numDays") int numDays) {
        BigDecimal amount = vacationPaymentService.getPayByDaysNum(avgSalary, numDays);
        return new ResponseEntity(new PaymentNumResponse(amount), HttpStatus.OK);
   }

    @GetMapping(path = "bydaterange")
    public @ResponseBody ResponseEntity<PaymentNumResponse> getPayByDateRange(
            @PathParam("avgSalary") BigDecimal avgSalary,
            @PathParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @PathParam("finishDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate finishDate) {

        BigDecimal amount = vacationPaymentService.getPayByDateRange(avgSalary, startDate, finishDate);
        return new ResponseEntity(new PaymentNumResponse(amount), HttpStatus.OK);
    }
}
