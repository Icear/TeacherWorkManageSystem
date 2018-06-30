package com.controller;

import com.constattribute.RequestPathName;
import com.entity.TeacherEntity;
import com.service.AuthorizeService;
import com.service.TeacherService;
import com.util.PasswordUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 * 用于处理认证请求
 */

@Controller
@Validated
public class AuthorizeController {

    private static Logger logger = LogManager.getLogger(AuthorizeController.class);
    private final AuthorizeService authorizeService;
    private final TeacherService teacherService;

    @Autowired
    public AuthorizeController(AuthorizeService authorizeService, TeacherService teacherService) {
        this.authorizeService = authorizeService;
        this.teacherService = teacherService;
    }

    @GetMapping(path = RequestPathName.AUTHORIZE)
    @PostMapping(path = RequestPathName.AUTHORIZE)
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody
    Map<String, String> authorize(
            @RequestAttribute @NotNull @Max(value = 18) @Min(value = 6)
                    String account,
            @RequestAttribute @NotNull @Max(value = 255) @Min(value = 6)
                    String password) {
        Map<String, String> responseBodyMap = new HashMap<>();

        logger.debug("account: " + account + ", password: " + password);

        Optional<TeacherEntity> teacher = teacherService.findTeacher(account);

        //查找教师身份并核对密码，通过则返回成功
        if (teacher.isPresent() && PasswordUtil.compare(password, teacher.get().getPassword())) {
            responseBodyMap.put("isSuccess", "true");
            responseBodyMap.put("token", authorizeService.applyToken(teacher.get()));
            logger.info("login success for account " + account);
        } else {
            responseBodyMap.put("isSuccess", "false");
            responseBodyMap.put("reason", "account or password error");
            logger.info("login failed for account " + account);
        }

        return responseBodyMap;
    }

    @DeleteMapping(path = RequestPathName.AUTHORIZE)
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public @ResponseBody
    Map<String, String> removeToken(
            @RequestAttribute
            @NotNull
                    String token) {
        authorizeService.destroyToken(token);
        logger.info("token removed");
        logger.debug("token " + token + " removed");
        return new HashMap<>();
    }

}
