package ru.zhelnin.otus.lesson12.servlet;

import com.google.gson.Gson;
import ru.zhelnin.otus.lesson12.cache.ZCache;
import ru.zhelnin.otus.lesson12.cache.model.CacheData;
import ru.zhelnin.otus.lesson12.servlet.abstraction.AuthCacheServlet;
import ru.zhelnin.otus.lesson12.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CacheServlet extends HttpServlet implements AuthCacheServlet {

    private final ZCache cache;

    public CacheServlet(ZCache cache) {
        this.cache = cache;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getSession().getAttribute(Constants.USER_NAME_PARAMETER).toString();
        String password = request.getSession().getAttribute(Constants.PASSWORD_PARAMETER).toString();
        if (checkAccount(userName, password)) {
            setJson(response, new CacheData(cache.getMaxSize(), cache.getCurrentSize(), cache.getHitsCounter(), cache.getMissesCounter()));
        } else {
            setError(response);
        }
    }

    private boolean checkAccount(String requestUserName, String requestPassword) {
        return requestUserName != null && requestPassword != null && requestUserName.equals(Constants.USER_NAME) && requestPassword.equals(Constants.PASSWORD);
    }

    private void setJson(HttpServletResponse response, CacheData cacheData) throws IOException {
        String json = new Gson().toJson(cacheData);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
