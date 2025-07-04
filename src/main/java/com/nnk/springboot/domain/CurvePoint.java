package com.nnk.springboot.domain;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;


@Entity
@Table(name = "curvepoint")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurvePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "curve_id")
    @NotNull(message = "CurveId is mandatory")
    private Integer curveId;

    @Column(name = "as_of_date")
    private Timestamp asOfDate;

    @Column(name = "term")
    @NotNull(message = "Term is mandatory")
    @PositiveOrZero(message = "Term must be zero or positive")
    private Double term;

    @Column(name = "value")
    @NotNull(message = "Value is mandatory")
    @PositiveOrZero(message = "Value must be zero or positive")
    private Double value;

    @Column(name = "creation_date")
    private Timestamp creationDate;
}
