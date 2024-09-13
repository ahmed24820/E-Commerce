package com.my_project.e_commerce.Exceptions;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.Map;
@Component
public class CustomErrorFields extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> getErrorAttributes=  super.getErrorAttributes(webRequest, options);

        getErrorAttributes.put("success",Boolean.FALSE);
        getErrorAttributes.put("Status",getErrorAttributes.get("error"));
        getErrorAttributes.put("message",getErrorAttributes.get("message"));
        getErrorAttributes.put("details", Arrays.asList(getErrorAttributes.get("message")));
        getErrorAttributes.remove("error");
        getErrorAttributes.remove("path");

        return getErrorAttributes;
}
}
