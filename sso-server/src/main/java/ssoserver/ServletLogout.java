package ssoserver;

import constant.Auth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/logout")
public class ServletLogout extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        deleteCookie(Auth.TOKEN, response);
        deleteCookie("username", response);
        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void deleteCookie(String name, HttpServletResponse response){
        Cookie token = new Cookie(name, null);
        token.setMaxAge(0);
        token.setPath("/");
        response.addCookie(token);
    }
}
