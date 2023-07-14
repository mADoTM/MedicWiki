package ru.dolzhenkoms.backend.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ArticleFeedDto {

    private String name;

    private int viewsCount;

    private LocalDate createdAt;
}
