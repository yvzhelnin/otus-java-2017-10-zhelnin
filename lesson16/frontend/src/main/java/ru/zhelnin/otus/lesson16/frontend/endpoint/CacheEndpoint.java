package ru.zhelnin.otus.lesson16.frontend.endpoint;

import com.google.gson.Gson;
import ru.zhelnin.otus.lesson16.core.message.RequestMessage;
import ru.zhelnin.otus.lesson16.core.model.CacheData;
import ru.zhelnin.otus.lesson16.core.util.BaseConstants;
import ru.zhelnin.otus.lesson16.frontend.config.FrontendWebAppInitializer;
import ru.zhelnin.otus.lesson16.frontend.socket.FrontendSocketClient;

import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/getCacheData/{userName}/{password}")
public class CacheEndpoint {

    private static FrontendSocketClient frontendSocketClient = FrontendWebAppInitializer.getContext().getBean(FrontendSocketClient.class);
    private static CacheData cacheData;

    @OnOpen
    public void onOpen(Session session,
                       @PathParam(BaseConstants.USER_NAME_PARAMETER) String requestUserName,
                       @PathParam(BaseConstants.PASSWORD_PARAMETER) String requestPassword) throws IOException {
        if (requestUserName == null || requestPassword == null || !accountIsValid(requestUserName, requestPassword)) {
            session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT, "Неправильное имя пользователя или пароль"));
        }
    }

    private boolean accountIsValid(String requestUserName, String requestPassword) {
        return requestUserName.equals(BaseConstants.USER_NAME) && requestPassword.equals(BaseConstants.PASSWORD);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException, EncodeException {
        frontendSocketClient.sendMessage(
                new RequestMessage(RequestMessage.class, BaseConstants.FRONTEND_CACHE_DATA_REQUEST, BaseConstants.DB_ADDRESS));
             session.getBasicRemote().sendText(new Gson().toJson(cacheData != null ? cacheData : ""));
    }

    @OnError
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        session.getBasicRemote().sendText("Session closed");
    }

    public static void updateCacheData(CacheData data) {
        cacheData = data;
    }
}
