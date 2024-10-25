
package com.devhub.website.io.vn.jwt.plugins.exception;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenException extends RuntimeException{
    private String message;
}