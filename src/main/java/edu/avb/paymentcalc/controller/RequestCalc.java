package edu.avb.paymentcalc.controller;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class RequestCalc {

    @Range(min = 100000, max = 5000000)
    String amount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date startDate;
    Byte rate;
    @Range(min = 12, max = 60)
    String duration;

    public RequestCalc() {
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Byte getRate() {
        return rate;
    }

    public void setRate(Byte rate) {
        this.rate = rate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
