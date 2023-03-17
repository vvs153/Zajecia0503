package com.example.zajecia0503.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCarRequest {
    private String nrRejestracyjny;
    private Integer iloscDrzwi;
    private Double pojemnoscSilnika;
}

