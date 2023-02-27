package com.lhstore.productcatalogservice.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
public class ErrorResponseBody {

    private static final DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final String timestamp;
    private final int httpStatusCode;
    private final String error;
    private final Set<String> messages = new LinkedHashSet<>();
    private final String path;

    public ErrorResponseBody(HttpStatus httpStatus, String urlPath) {
        timestamp = LocalDateTime.now().format(CUSTOM_FORMATTER);
        httpStatusCode = httpStatus.value();
        error = httpStatus.getReasonPhrase();
        path = urlPath;
    }

    public void addMessage(String message) {
        messages.add(message);
    }
}
