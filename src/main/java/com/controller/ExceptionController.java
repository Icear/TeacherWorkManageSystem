package com.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@ControllerAdvice
public class ExceptionController {

    private static Logger logger = LogManager.getLogger(ExceptionController.class);
    /**
     * 处理对象属性校验失败异常
     *
     * @param e 异常
     * @param request http请求
     * @param redirectAttributes 重定向属性
     * @return 重定向的地址
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

    /**
     * 没有找到对应的实体
     *
     * @param e 异常
     */
    @ExceptionHandler(NoResultException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public void getNoResultException(NoResultException e) {
        logger.debug("未找到对应的实体");
    }

}
