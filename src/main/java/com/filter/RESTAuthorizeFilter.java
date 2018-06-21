package com.filter;

import com.constattribute.RequestVaribleName;
import com.entity.TeacherEntity;
import com.service.AuthorizeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * 用于对调用REST API的请求进行token验证，同时生成对应用户身份
 */
@Component
public class RESTAuthorizeFilter extends HandlerInterceptorAdapter {

    private final AuthorizeService authorizeService;
    private Logger logger = LogManager.getLogger(RESTAuthorizeFilter.class);

    @Autowired
    public RESTAuthorizeFilter(AuthorizeService authorizeService) {
        this.authorizeService = authorizeService;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.preHandle(request, response, handler);

        /*
         *  获得Token并进行比对
         *  如果Token有效则将对应Teacher信息和Administrator信息放入Session中
         *  如果Token无效则返回401代码要求登陆
         */
        logger.debug("Authorize filter is triggered");

        Optional<String> authorization1 = Optional.ofNullable(request.getHeader("Authorization"));
        if (authorization1.isPresent()) {
            Optional<TeacherEntity> tokenIdentity = authorizeService.getTokenIdentity(authorization1.get());
            if (tokenIdentity.isPresent()) {
                logger.debug("Authorize find identity: ", tokenIdentity.get());
                request.setAttribute(RequestVaribleName.IDENTITY, tokenIdentity.get());
                return true;
            }
        }

        response.setStatus(401);
        return false;
    }
}
