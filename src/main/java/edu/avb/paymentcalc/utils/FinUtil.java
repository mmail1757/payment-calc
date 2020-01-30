package edu.avb.paymentcalc.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FinUtil {

    public static List<CreditPayment> calcAnnuityPayments
            (final BigDecimal creditAmount,
             final int durationInMonths,
             Date date,
             final BigDecimal rate) {
        ArrayList<CreditPayment> payments = new ArrayList<>();


        BigDecimal monthlyRate = rate.divide(
                new BigDecimal(CalendarUtil.NUMBER_OF_MONTHS),
                Constants.CALC_SCALE,
                Constants.ROUNDING_MODE
        );

        BigDecimal pow = monthlyRate.add(new BigDecimal(1)).pow(-durationInMonths, MathContext.DECIMAL64);

        BigDecimal denominator = new BigDecimal(1);


        denominator = denominator.subtract(pow);

        BigDecimal amount = creditAmount.multiply(monthlyRate).divide(
                denominator,
                Constants.CALC_SCALE,
                Constants.ROUNDING_MODE
        );

        BigDecimal withCommAmount = amount;

        BigDecimal base = creditAmount;

        for (int i = 0; i < durationInMonths; i++) {

            BigDecimal interest = base.multiply(monthlyRate);

            base = base.add(interest);

            date = CalendarUtil.nextMonthDate(date);

            CreditPayment payment = new CreditPaymentImpl(withCommAmount.setScale(
                    Constants.OUTPUT_AMOUNT_SCALE, Constants.ROUNDING_MODE), date, i + 1);

            payment.setDebt(amount.subtract(interest).setScale(
                    Constants.OUTPUT_AMOUNT_SCALE,
                    Constants.ROUNDING_MODE
                    )
            );

            payment.setInterest(
                    interest.setScale(Constants.OUTPUT_AMOUNT_SCALE,
                            Constants.ROUNDING_MODE)
            );

            base = base.subtract(amount);


            payment.setTotalLeft(base.setScale(
                    Constants.OUTPUT_AMOUNT_SCALE,
                    Constants.ROUNDING_MODE)
            );

            payments.add(payment);
        }
        return payments;
    }
}