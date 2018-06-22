package com.controller;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@ControllerAdvice
public class ExceptionController {

    /**
     * 处理对象属性校验失败异常
     *
     * @param e
     * @param request
     * @param redirectAttributes
     * @return
     */
    @ExceptionHandler(BindException.class)
    public String getBindException(
            BindException e,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes) {
        StringBuilder strBuilder = new StringBuilder();
        e.getFieldErrors().forEach(er -> {
            strBuilder.append(er.getDefaultMessage() + "; ");
        });
        redirectAttributes.addFlashAttribute("exception", strBuilder.toString());
        return "redirect:" + request.getHeader("referer");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public String getConstraintViolationException(
            ConstraintViolationException e,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder strBuilder = new StringBuilder();
        for (ConstraintViolation<?> violation : violations) {
            strBuilder.append(violation.getMessage() + "; ");
        }
        redirectAttributes.addFlashAttribute("exception", strBuilder.toString());
        return "redirect:" + request.getHeader("referer");
    }
}
