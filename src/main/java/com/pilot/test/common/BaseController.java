package com.pilot.test.common;

import org.wf.jwtp.provider.Token;
import org.wf.jwtp.util.SubjectUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller基类
 */
public class BaseController {

    /**
     * 获取当前登录的userId
     */
    public Integer getLoginUserId(HttpServletRequest request) {
        Token token = getLoginToken(request);
        return token == null ? null : Integer.parseInt(token.getUserId());
    }

    public Token getLoginToken(HttpServletRequest request) {
        return SubjectUtil.getToken(request);
    }

}
