package com.lhstore.productcatalogservice.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

public class RestUtils {

    private RestUtils() {}

    public static Optional<HttpServletRequest> getCurrentHttpServletRequest(){
        final RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (!(requestAttributes instanceof ServletRequestAttributes)) {
            return Optional.empty();
        }

        final HttpServletRequest httpServletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
        return Optional.of(httpServletRequest);
    }

    public static String getCurrentUriPath() {
        final Optional<HttpServletRequest> currentHttpServletRequestOptional = getCurrentHttpServletRequest();
        if (currentHttpServletRequestOptional.isEmpty()) {
            return null;
        }

        final HttpServletRequest currentHttpServletRequest = currentHttpServletRequestOptional.get();
        return currentHttpServletRequest.getRequestURI();
    }
}
