package com.example.ssc9.security;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Component
public class CustomCsrfTokenRepository implements CsrfTokenRepository {

    static final String DEFAULT_CSRF_PARAMETER_NAME = "_csrf";
    static final String DEFAULT_CSRF_HEADER_NAME = "X-XSRF-TOKEN";

    private String parameterName = DEFAULT_CSRF_PARAMETER_NAME;

    private String headerName = DEFAULT_CSRF_HEADER_NAME;

    private String cookieName = "USER_INFO";

    private static final String DEFAULT_CSRF_TOKEN_ATTR_NAME = CustomCsrfTokenRepository.class
            .getName().concat(".CSRF_TOKEN");

    private final String sessionAttributeName = DEFAULT_CSRF_TOKEN_ATTR_NAME;

    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, this.cookieName);
        if (cookie == null) {
            return new DefaultCsrfToken(this.headerName, this.parameterName,
                    createNewToken());
        }
        String cookieValue = cookie.getValue();
        String token = cookieValue.split("\\|")[0];
        if (!StringUtils.hasLength(token)) {
            return new DefaultCsrfToken(this.headerName, this.parameterName,
                    createNewToken());
        }
        return new DefaultCsrfToken(this.headerName, this.parameterName, token);
    }

    @Override
    public void saveToken(CsrfToken token, HttpServletRequest request,
                          HttpServletResponse response) {
        if (token == null) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.removeAttribute(this.sessionAttributeName);
            }
        }
        else {
            HttpSession session = request.getSession();
            session.setAttribute(this.sessionAttributeName, token);
        }
    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }
        return (CsrfToken) session.getAttribute(this.sessionAttributeName);
    }

    private String createNewToken() {
        return UUID.randomUUID().toString();
    }
}
