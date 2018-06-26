package com.aop;

import com.annotation.Authority;
import com.constattribute.RequestVaribleName;
import com.entity.TeacherEntity;
import com.identity.Identity;
import com.service.TeacherService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


/**
 * Controller鉴权AOP组件
 */
@Aspect
@Component
public class AOPAuthorizeHandler {

    private TeacherService teacherService;
    private HttpServletRequest httpServletRequest;

    @Autowired
    public AOPAuthorizeHandler(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @ModelAttribute
    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Around(value = "@within(com.annotation.Authority)|| @annotation(com.annotation.Authority)")
    public Object checkAuthorityAroundProcess(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //获得方法和类上标记的所有权限
        Class<?> targetClass = proceedingJoinPoint.getTarget().getClass();
        Method targetMethod = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
        Set<Identity> identities = getTargetAuthorityTags(targetClass, targetMethod);

        //获得当前操作者身份
        Identity identity = identifyOperatorIdentity();

        //判断身份是否在允许访问权限内，允许则执行，否则抛出非法访问异常
        if (identities.contains(identity)) {
            return proceedingJoinPoint.proceed();
        } else {
            throw new IllegalAccessException("illegal access from identity " + identity.getName());
        }
    }

    /**
     * 获得目标上所有的权限标记
     *
     * @param targetClass  目标类
     * @param targetMethod 目标方法
     * @return 被标记的身份集合
     */
    private @NotNull Set<Identity> getTargetAuthorityTags(@NotNull Class targetClass, @NotNull Method targetMethod) {
        Set<Identity> identities = new HashSet<>();
        Set<Annotation> annotations = new HashSet<>();

        //拿到Class中的权限注解
        Collections.addAll(annotations, targetClass.getAnnotationsByType(Authority.class));
        //拿到Method中的权限注解
        Collections.addAll(annotations, targetMethod.getAnnotationsByType(Authority.class));

        //提取出身份
        for (Annotation annotation : annotations) {
            Authority authority = (Authority) annotation;
            identities.add(authority.role());
        }
        return identities;
    }

    /**
     * 检查当前操作者身份
     *
     * @return 操作者身份
     */
    private @NotNull Identity identifyOperatorIdentity() {
        //检查request中身份位是否有数据，有数据检查是否为管理员，无数据识别为游客
        Optional<TeacherEntity> teacherEntity = Optional.ofNullable((TeacherEntity) httpServletRequest.getAttribute(RequestVaribleName.IDENTITY));
        if (teacherEntity.isPresent()) {
            return teacherService.isAdministrator(teacherEntity.get()) ? Identity.Administrator : Identity.User;
        } else {
            return Identity.Guest;
        }
    }

}
