package com.attendancecheck.sample.utils;

import lombok.Getter;
import lombok.Setter;

/**
 * UserContext
 */
@Getter
@Setter
public class UserContext {

    /**
     * tmx-correlation-id 헤더 키
     */
    public static final String CORRELATION_ID = "tmx-correlation-id";

    /**
     * Authorization 헤더 키
     */
    public static final String AUTH_TOKEN = "Authorization";

    /**
     * tmx-correlation-id
     */
    private String correlationId = "";

    /**
     * Authorization
     */
    private String authToken = "";

}
