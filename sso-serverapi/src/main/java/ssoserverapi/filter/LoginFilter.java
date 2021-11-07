package ssoserverapi.filter;

import ssoserverapi.constant.Auth;
import ssoserverapi.entity.TokenInfo;
import ssoserverapi.util.JwtUtil;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginFilter implements Filter {

    private FilterConfig config;

    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        System.out.println("进入过滤器");

        String token = null;
        Cookie[] cookies = ((HttpServletRequest) req).getCookies();
        if (null == cookies){
            // cookies判空
            System.out.println("token解析失败");
            String loginUrl = config.getInitParameter(Auth.LOGIN_URL);
            ((HttpServletResponse)resp).sendRedirect(loginUrl+"?" + Auth.CLIENT_URL + "="+ ((HttpServletRequest) req).getRequestURL());
            return;
        }
        for (Cookie cookie : cookies) {
            if (Auth.TOKEN.equals(cookie.getName())){
                token = cookie.getValue();
                break;
            }
        }

        if (token != null) {
            TokenInfo decode = JwtUtil.decode(token, TokenInfo.class);
            if (decode != null) {   // jwt解析成功
                System.out.println("认证成功");
                chain.doFilter(req, resp);
                return;
            }
        }

        System.out.println("token解析失败");
        String loginUrl = config.getInitParameter(Auth.LOGIN_URL);
        ((HttpServletResponse)resp).sendRedirect(loginUrl+"?" + Auth.CLIENT_URL + "="+ ((HttpServletRequest) req).getRequestURL());
    }

    public void destroy() {

    }
}
