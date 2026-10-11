package com.microservice.callreceiver.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
public class ExceptionResponse {
    private String message;
    private OffsetDateTime timestamp;
}
