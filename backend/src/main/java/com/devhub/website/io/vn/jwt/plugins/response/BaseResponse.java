package com.devhub.website.io.vn.jwt.plugins.response;

import lombok.Data;

@Data
public class BaseResponse {
    private int statusCode = 200;
    private String message;
    private Object data;
}