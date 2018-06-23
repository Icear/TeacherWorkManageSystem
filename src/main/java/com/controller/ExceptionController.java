package com.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ExceptionController {

    private static Logger logger = LogManager.getLogger(ExceptionController.class);
//    /**
//     * 处理对象属性校验失败异常
//     *
//     * @param e 异常
//     * @param request http请求
//     * @param redirectAttributes 重定向属性
//     * @return 重定向的地址
//     */
//    @ExceptionHandler(BindException.class)
//    public String getBindException(
//            BindException e,
//            HttpServletRequest request,
//            RedirectAttributes redirectAttributes) {
//        StringBuilder strBuilder = new StringBuilder();
//        e.getFieldErrors().forEach(er -> {
//            strBuilder.append(er.getDefaultMessage() + "; ");
//        });
//        redirectAttributes.addFlashAttribute("exception", strBuilder.toString());
//        return "redirect:" + request.getHeader("referer");
//    }
//
//    @ExceptionHandler(ConstraintViolationException.class)
//    public String getConstraintViolationException(
//            ConstraintViolationException e,
//            HttpServletRequest request,
//            RedirectAttributes redirectAttributes) {
//        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
//        StringBuilder strBuilder = new StringBuilder();
//        for (ConstraintViolation<?> violation : violations) {
//            strBuilder.append(violation.getMessage() + "; ");
//        }
//        redirectAttributes.addFlashAttribute("exception", strBuilder.toString());
//        return "redirect:" + request.getHeader("referer");
//    }

    /**
     * 没有找到对应的实体
     * @param response 传进来的参数
     * @return 设置好的respone.status = 404
     */
    @ExceptionHandler(NoResultException.class)
    public HttpServletResponse getNoResultException(HttpServletResponse response) {
        logger.debug("未找到对应的实体");
        response.setStatus(404);
        return response;
    }

    //    @ResponseStatus(value = HttpStatus.NOT_FOUND)
//    public void getNoResultException(NoResultException e) {
//        logger.debug("未找到对应的实体");
//    }

}
