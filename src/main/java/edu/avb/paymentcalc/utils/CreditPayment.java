package edu.avb.paymentcalc.utils;

import java.math.BigDecimal;
import java.util.Date;

public interface CreditPayment {
    
    Integer getId();

    Date getDate();

    BigDecimal getAmount();
    
    BigDecimal getDebt();
    
    BigDecimal getInterest();
    
    BigDecimal getTotalLeft();

    CreditPayment setDebt(BigDecimal debt);

    CreditPayment setInterest(BigDecimal interest);

    CreditPayment setTotalLeft(BigDecimal totalLeft);
}