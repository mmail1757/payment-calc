package edu.avb.paymentcalc.controller;

import edu.avb.paymentcalc.utils.Constants;
import edu.avb.paymentcalc.utils.CreditPayment;
import edu.avb.paymentcalc.utils.FinUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class PaymentCalcController {

    @GetMapping("/calc")
    public String main(Map<String, Object> model) {
        return "calc";
    }

    @PostMapping("/calc")
        public String calc(@Valid RequestCalc requestCalc,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                    fieldError -> fieldError.getField() + "Error",
                    FieldError::getDefaultMessage
            );
            Map<String, String> errorMap = bindingResult.getFieldErrors().stream().collect(collector);
            model.mergeAttributes(errorMap);
        } else {
            List<CreditPayment> payments = FinUtil.calcAnnuityPayments(BigDecimal.valueOf(Integer.valueOf(requestCalc.amount)),
                    Integer.valueOf(requestCalc.duration), requestCalc.startDate, BigDecimal.valueOf(requestCalc.rate).divide(BigDecimal.valueOf(100), Constants.CALC_SCALE,
                            Constants.ROUNDING_MODE));
            model.addAttribute("payments", payments);
        }
        return "calc";
    }

}