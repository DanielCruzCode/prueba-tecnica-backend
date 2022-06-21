package com.danielcruz.pruebatecnica.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "debts")
@Getter @Setter
@NoArgsConstructor
public class Debt implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Float total;
    @Column(name = "total_payed")
    private Float totalPayed;
    private Integer fees;
    @Column(name = "remaining_fees")
    private Integer remainingFees;
    @Column(name = "total_remaining")
    private Float totalRemaining;
    private String currency;
    @Column(name = "is_canceled")
    private Boolean isCanceled;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    @ManyToOne
    @JoinColumn(name = "bank_id")
    @JsonIgnore
    private Bank bank;

    @Override
    public String toString() {
        return "Debt{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", total=" + total +
                ", totalPayed=" + totalPayed +
                ", fees=" + fees +
                ", remainingFees=" + remainingFees +
                ", totalRemaining=" + totalRemaining +
                ", currency='" + currency + '\'' +
                ", isCanceled=" + isCanceled +
                ", user=" + user.getId() +
                ", bank=" + bank.getId() +
                '}';
    }

    public Float calculateTotalPayed(int feesToPay) {
        float feesAmountInMoney = this.total / this.fees;
        float totalFeesToPayInMoney = feesAmountInMoney * feesToPay;
        this.totalPayed = this.totalPayed + totalFeesToPayInMoney;

        return this.totalPayed;
    }

    public Float calculateTotalRemaining() {
        this.totalRemaining = this.total - this.totalPayed;

        return this.totalRemaining;
    }

    public Boolean calculateDebtCancellation() {
        if (this.total - this.totalPayed == 0f) {
            this.isCanceled = Boolean.TRUE;
            return true;
        }
        this.isCanceled = Boolean.FALSE;
        return false;
    }
}
