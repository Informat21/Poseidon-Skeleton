package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Entity
@Table(name = "trade")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trade_id")
    private Integer tradeId;


    @NotBlank(message = "Account is mandatory")
    @Column(name = "account")
    private String account;


    @NotBlank(message = "Type is mandatory")
    @Column(name = "type")
    private String type;


    @Column(name = "buyQuantity")
    @PositiveOrZero(message = "Buy quantity must be zero or positive")
    private Double buyQuantity;


    @Column(name = "sellQuantity")
    @PositiveOrZero(message = "Sell quantity must be zero or positive")
    private Double sellQuantity;


    @Column(name = "buyPrice")
    @PositiveOrZero(message = "Buy price must be zero or positive")
    private Double buyPrice;


    @Column(name = "sellPrice")
    @PositiveOrZero(message = "Sell price must be zero or positive")
    private Double sellPrice;


    @Column(name = "tradeDate")
    private Timestamp tradeDate;


    @Column(name = "security")
    private String security;


    @Column(name = "status")
    private String status;


    @Column(name = "trader")
    private String trader;


    @Column(name = "benchmark")
    private String benchmark;


    @Column(name = "book")
    private String book;


    @Column(name = "creationName")
    private String creationName;


    @Column(name = "creationDate")
    private Timestamp creationDate;


    @Column(name = "revisionName")
    private String revisionName;


    @Column(name = "revisionDate")
    private Timestamp revisionDate;


    @Column(name = "dealName")
    private String dealName;


    @Column(name = "dealType")
    private String dealType;


    @Column(name = "sourceListId")
    private String sourceListId;


    @Column(name = "side")
    private String side;
}
