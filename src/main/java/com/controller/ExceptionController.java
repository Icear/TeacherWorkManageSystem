package com.controller;

import com.exception.IdentityNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.PessimisticLockException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.*;

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


    //这个异常应该自己在dao层定义
//    /**
//     * 没有找到对应的实体
//     * @param e 未找到结果异常
//     */
//    @ExceptionHandler(NoResultException.class)
//    @ResponseStatus(value = HttpStatus.NOT_FOUND)
//    public void getNoResultException(NoResultException e) {
//        logger.info("未找到对应的实体", e);
//    }

    //    public HttpServletResponse getNoResultException(HttpServletResponse response) {
//        logger.debug("未找到对应的实体");
//        response.setStatus(404);
//        return response;
//    }

    /**
     * 身份未找到
     *
     * @param e 身份未找到异常
     */
    @ExceptionHandler(IdentityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public void getIdentityNotFoundException(IdentityNotFoundException e) {
        logger.error("身份未找到", e);
    }

    /**
     * 设置的参数无效
     *
     * @param e 无效参数异常
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public void getIllegalArgumentException(IllegalArgumentException e) {
        logger.info("设置的参数无效", e);
    }

    /**
     * 本次回话超时，这次语句声明回滚
     *
     * @param e 超时异常
     */
    @ExceptionHandler(QueryTimeoutException.class)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void getQueryTimeoutException(QueryTimeoutException e) {
        logger.error("本次回话超时，这次语句声明回滚", e);
    }

//    /**
//     * 暂时无事务
//     *
//     * @param e 需要事务异常
//     */
//    @ExceptionHandler(TransactionRequiredException.class)
//    @ResponseStatus(value = HttpStatus.FORBIDDEN)
//    public void getTransactionRequiredException(TransactionRequiredException e) {
//        logger.error("暂时无事务", e);
//    }

    /**
     * 悲观锁异常，整个事务回滚
     *
     * @param e 悲观锁异常
     */
    @ExceptionHandler(PessimisticLockException.class)
    @ResponseStatus(value = HttpStatus.LOCKED)
    public void getPessimisticLockException(PessimisticLockException e) {
        logger.error("悲观锁异常，整个事务回滚", e);
    }

    /**
     * 悲观锁异常，这次语句声明回滚
     *
     * @param e 悲观锁异常
     */
    @ExceptionHandler(LockTimeoutException.class)
    @ResponseStatus(value = HttpStatus.LOCKED)
    public void getLockTimeoutException(LockTimeoutException e) {
        logger.error("悲观锁异常，这次语句声明回滚", e);
    }



    /**
     * 未找到实体
     *
     * @param e 未找到实体异常
     */
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public void getEntityNotFoundException(EntityNotFoundException e) {
        logger.error("未找到实体", e);
    }



    /**
     * 得到多个实体
     *
     * @param e 非单个结果异常
     */
    @ExceptionHandler(NonUniqueResultException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public void getNonUniqueResultException(NonUniqueResultException e) {
        logger.info("得到多个实体", e);
    }

//    /**
//     * if called for a Java Persistence query language UPDATE or DELETE statement
//     * 状态非法 不知道用什么Http状态码表示
//     * 非法状态异常
//     *
//     * @param e 非法状态异常
//     */
//    @ExceptionHandler(IllegalStateException.class)
//    @ResponseStatus(value = HttpStatus.NOT_FOUND)
//    public void getIllegalStateException(IllegalStateException e) {
//        logger.error("非法状态异常", e);
//    }

    //todo 暂时用302？？？ 等麻瓜开
    /**
     * 实体已存在
     *
     * @param e 实体已存在异常
     */
    @ExceptionHandler(EntityExistsException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public void getEntityExistsException(EntityExistsException e) {
        logger.error("实体已存在", e);
    }

    //todo 拆分成多总情况，查
//        /**
//     * 持久化异常，本次事务回滚
//     * 403不知道怎么描述这种异常，就404吧
//     *
//     * @param e 持久化异常
//     */
//    @ExceptionHandler(PersistenceException.class)
//    @ResponseStatus(value = HttpStatus.NOT_FOUND)
//    public void getPersistenceException(PersistenceException e) {
//        logger.error("持久化异常，本次事务回滚", e);
//    }

}
