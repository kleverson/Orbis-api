package com.orbis.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiError {
    private Instant timestamp;
    private int status;
    private String message;
    private List<String> errors;
}
