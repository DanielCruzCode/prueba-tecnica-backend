package com.danielcruz.pruebatecnica.controllers.users.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter
@Setter
public class PayFeesReqDto {

    private int feesToPay;
    private boolean payAllFees;

    public PayFeesReqDto create(int feesToPay, boolean payAllFees) {
        PayFeesReqDto payFees = new PayFeesReqDto();
        payFees.setFeesToPay(feesToPay);
        payFees.setPayAllFees(payAllFees);

        return payFees;
    }

    @Override
    public String toString() {
        return "PayFeesReqDto{" +
                "feesToPay=" + feesToPay +
                ", payAllFees=" + payAllFees +
                '}';
    }
}
