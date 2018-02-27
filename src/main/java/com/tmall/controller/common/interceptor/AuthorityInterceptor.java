package com.tmall.controller.common.interceptor;

import com.google.common.collect.Maps;
import com.tmall.common.Const;
import com.tmall.common.ServerResponse;
import com.tmall.controller.backend.UserManageController;
import com.tmall.pojo.User;
import com.tmall.util.CookieUtil;
import com.tmall.util.JsonUtil;
import com.tmall.util.RedisShardedPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/**
 * @author qiuxin
 */
@Slf4j
@SuppressWarnings("unchecked")
public class AuthorityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle");

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        String methodName = handlerMethod.getMethod().getName();
        String className = handlerMethod.getBean().getClass().getSimpleName();

        if (StringUtils.equals(className, "UserManageController") && StringUtils.equals(methodName, "login")) {
            log.info("拦截到登录请求，className:{}, methodName:{}", className, methodName);
            return true;
        }

        User user = null;
        String loginToken = CookieUtil.readLoginToken(request);
        if (StringUtils.isNotEmpty(loginToken)) {
            String userJsonStr = RedisShardedPoolUtil.get(loginToken);
            user = JsonUtil.string2Obj(userJsonStr, User.class);
        }

        if (user == null || (user.getRole() != Const.Role.ROLE_ADMIN)) {
            // getWriter() has already been called for this response
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();

            if (user == null) {
                if (StringUtils.equals(className, "ProductManageController") && StringUtils.equals(methodName, "richtextImgUpload")) {
                    Map resultMap = Maps.newHashMap();
                    resultMap.put("success", false);
                    resultMap.put("msg", "请登录管理员账户");
                    out.print(JsonUtil.obj2String(resultMap));
                } else {
                    out.print(JsonUtil.obj2String(ServerResponse.createByErrorMessage("拦截器拦截，用户未登录")));
                }
            } else {
                if (StringUtils.equals(className, "ProductManageController") && StringUtils.equals(methodName, "richtextImgUpload")) {
                    Map resultMap = Maps.newHashMap();
                    resultMap.put("success", false);
                    resultMap.put("msg", "无权限操作");
                    out.print(JsonUtil.obj2String(resultMap));
                } else {
                    out.print(JsonUtil.obj2String(ServerResponse.createByErrorMessage("拦截器拦截，用户未登录")));
                }
            }
            out.flush();
            out.close();
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion");
    }
}