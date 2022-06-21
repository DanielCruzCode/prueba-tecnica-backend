package com.danielcruz.pruebatecnica.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "banks")
@Getter @Setter
@NoArgsConstructor
public class Bank implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    @OneToMany(mappedBy = "bank")
    private Set<Debt> debts;

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", debts=" + debts.toString() +
                '}';
    }
}
