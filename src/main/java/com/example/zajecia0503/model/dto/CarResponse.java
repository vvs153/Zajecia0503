package com.example.zajecia0503.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarResponse {
    private Long id;
    private String nrRejestracyjny;
    private LocalDate dataRejestracji;
    private Integer iloscDrzwi;
    private Double pojemnoscSilnika;


}
