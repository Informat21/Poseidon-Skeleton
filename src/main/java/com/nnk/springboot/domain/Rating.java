package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "rating")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @NotBlank(message = "Moodys rating is required")
    @Column(name = "moodysRating")
    private String moodysRating;

    @NotBlank(message = "SandP rating is required")
    @Column(name = "sandPRating")
    private String sandPRating;

    @NotBlank(message = "Fitch rating is required")
    @Column(name = "fitchRating")
    private String fitchRating;

    @NotNull(message = "Order number is required")
    @Column(name = "orderNumber")
    @PositiveOrZero(message = "Order number must be zero or positive")
    private Integer orderNumber;
}
