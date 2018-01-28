package ru.zhelnin.otus.lesson13.servlet.abstraction;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseServlet extends HttpServlet {

    protected void setError(HttpServletResponse response) {
        response.setContentType("text/plain");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
