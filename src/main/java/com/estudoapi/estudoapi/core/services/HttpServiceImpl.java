package com.estudoapi.estudoapi.core.services;

import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerMapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class HttpServiceImpl implements HttpService{
    private final ObjectMapper objectMapper;
    private final HttpServletRequest request; 

    @Override
    public <T> Optional<T> getPathVariable(String name, Class<T> type) {
        var attrs = request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        var getPathVariable = objectMapper.convertValue(
            attrs,
            new TypeReference<Map<String, String>>() {}
        );
        var value = getPathVariable.getOrDefault(name,null);
        if (value == null) {
            return Optional.empty();
        }
        return Optional.of(objectMapper.convertValue(value, type));
    }

}


   

