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

    private final String dateTime;
    private final int httpStatusCode;
    private final String error;
    private final Set<String> messages = new LinkedHashSet<>();

    public ErrorResponseBody(HttpStatus httpStatus) {
        this.dateTime = LocalDateTime.now().format(CUSTOM_FORMATTER);
        this.httpStatusCode = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }
}
