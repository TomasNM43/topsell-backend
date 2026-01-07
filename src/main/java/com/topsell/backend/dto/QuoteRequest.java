package com.topsell.backend.dto;

import lombok.Data;
import java.util.List;

@Data
public class QuoteRequest {
    private List<QuoteItemDto> items;
}