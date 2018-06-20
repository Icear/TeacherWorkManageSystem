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
        return false;

//        if (!"".equals(token) && userService.checkTokenEffective(token)) {
//            //预设User环境数据
//            long userId = userService.getUserIdByToken(token);
//            UserInfo user = userService.findUserById(userId);
//            request.setAttribute("userInfo", user);
//            logger.debug("Authorized request " + requestContext + " from user: " + user.getUsername());
//        } else {
//            boolean authorization = false;
//            for (String context : authorization_ignore_context) {
//                if (context.equals(requestContext)) {
//                    logger.debug("Unauthorized request but match authorization-ignore rule: " + requestContext + ", access granted");
//                    authorization = true;
//                }
//            }
//
//            if (!"GET".equals(request.getMethod()) || !authorization) {
//                logger.debug("Unauthorized request: " + requestContext + ", send code 401");
//                response.setStatus(401);//未授权的操作
//                return;
//            }
//            //当请求方法为GET且在忽略授权列表中时允许访问
//        }
//
//        return true;
    }
}
