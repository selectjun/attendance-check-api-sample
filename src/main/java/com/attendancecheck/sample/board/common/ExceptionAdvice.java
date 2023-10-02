package com.attendancecheck.sample.board.common;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Locale;

/**
 * Exception Advice
 */
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {

    /**
     * MessageSource
     */
    private final MessageSource messageSource;

    /**
     * 유효성 에러
     * @param bindingResult BindingResult
     * @param locale Locale
     * @return 에러 결과
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> test(BindingResult bindingResult, Locale locale) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("success", false);
        if (bindingResult.hasErrors()) {
            ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().orElse(null);
            if (objectError != null && objectError.getDefaultMessage() != null) {
                body.put("message", messageSource.getMessage(objectError.getDefaultMessage(), null, locale));
                return ResponseEntity.badRequest().body(body);
            }
        }
        body.put("message", messageSource.getMessage("sample.validation.message0000", null, locale));
        return ResponseEntity.internalServerError().body(body);
    }

    /**
     * 공통 에러
     * @param locale Locale
     * @return 에러 결과
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> othersError(Locale locale) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("success", false);
        body.put("message", messageSource.getMessage("sample.validation.message0000", null, locale));
        return ResponseEntity.internalServerError().body(body);
    }

}
