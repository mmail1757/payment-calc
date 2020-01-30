package edu.avb.paymentcalc.utils;

import java.math.BigDecimal;
import java.util.Date;

class CreditPaymentImpl implements CreditPayment {

    private final Integer id;
    private final BigDecimal amount;
    private final Date date;
    private BigDecimal debt;

    private BigDecimal totalLeft;
    private BigDecimal interest;

    CreditPaymentImpl(final BigDecimal amount, final Date date, final Integer id) {
        this.id = id;
        this.amount = amount;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public BigDecimal getDebt() {
        return debt;
    }

    @Override
    public BigDecimal getInterest() {
        return interest;
    }

    @Override
    public BigDecimal getTotalLeft() {
        return totalLeft;
    }

    @Override
    public CreditPayment setDebt(final BigDecimal debt) {
        this.debt = debt;
        return this;
    }

    @Override
    public CreditPayment setInterest(final BigDecimal interest) {
        this.interest = interest;
        return this;
    }

    @Override
    public CreditPayment setTotalLeft(final BigDecimal totalLeft) {
        this.totalLeft = totalLeft;
        return this;
    }
}
