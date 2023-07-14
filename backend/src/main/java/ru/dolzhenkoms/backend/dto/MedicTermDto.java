package ru.dolzhenkoms.backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicTermDto {

    private String name;

    private String history;

    private String application;

    private byte[] photo;
}
