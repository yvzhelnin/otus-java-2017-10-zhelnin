package ru.zhelnin.otus.lesson12.servlet.abstraction;

import javax.servlet.http.HttpServletResponse;

public interface AuthCacheServlet {

    default void setError(HttpServletResponse response) {
        response.setContentType("text/plain");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
