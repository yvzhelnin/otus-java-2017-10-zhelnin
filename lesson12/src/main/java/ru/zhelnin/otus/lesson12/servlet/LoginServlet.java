package ru.zhelnin.otus.lesson12.servlet;

import ru.zhelnin.otus.lesson12.servlet.abstraction.AbstractCacheServlet;
import ru.zhelnin.otus.lesson12.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends AbstractCacheServlet {

    private final String userName;
    private final String password;

    public LoginServlet(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestUserName = request.getParameter(Constants.USER_NAME_PARAMETER);
        String requestPassword = request.getParameter(Constants.PASSWORD_PARAMETER);
        if (requestUserName != null && requestPassword != null && checkAccount(requestUserName, requestPassword)) {
            saveToSession(request, requestUserName, requestPassword);
            setOK(response);
        } else {
            setError(response);
        }
    }

    private void saveToSession(HttpServletRequest request, String requestUserName, String requestPassword) {
        request.getSession().setAttribute(Constants.USER_NAME_PARAMETER, requestUserName);
        request.getSession().setAttribute(Constants.PASSWORD_PARAMETER, requestPassword);
    }

    private boolean checkAccount(String requestUserName, String requestPassword) {
        return requestUserName.equals(userName) && requestPassword.equals(password);
    }

    private void setOK(HttpServletResponse response) {
        response.setContentType("text/plain");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
