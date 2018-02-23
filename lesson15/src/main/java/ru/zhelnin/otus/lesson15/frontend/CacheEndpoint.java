package ru.zhelnin.otus.lesson15.frontend;

import com.google.gson.Gson;
import ru.zhelnin.otus.lesson15.app.FrontendService;
import ru.zhelnin.otus.lesson15.app.MessageSystemContext;
import ru.zhelnin.otus.lesson15.cache.message.GetCacheDataMessage;
import ru.zhelnin.otus.lesson15.cache.model.CacheData;
import ru.zhelnin.otus.lesson15.config.WebAppInitializer;
import ru.zhelnin.otus.lesson15.message.Address;
import ru.zhelnin.otus.lesson15.message.Addressee;
import ru.zhelnin.otus.lesson15.message.Message;
import ru.zhelnin.otus.lesson15.util.Constants;

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
public class CacheEndpoint implements FrontendService, Addressee {

    private static MessageSystemContext context = WebAppInitializer.getContext().getBean(MessageSystemContext.class);

    private CacheData data;

    public CacheEndpoint() {
        context.getMessageSystem().addAddressee(this);
    }

    @OnOpen
    public void onOpen(Session session,
                       @PathParam(Constants.USER_NAME_PARAMETER) String requestUserName,
                       @PathParam(Constants.PASSWORD_PARAMETER) String requestPassword) throws IOException {
        if (requestUserName == null || requestPassword == null || !accountIsValid(requestUserName, requestPassword)) {
            session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT, "Неправильное имя пользователя или пароль"));
        }
    }

    private boolean accountIsValid(String requestUserName, String requestPassword) {
        return requestUserName.equals(Constants.USER_NAME) && requestPassword.equals(Constants.PASSWORD);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException, EncodeException {
        Message cacheDataMessage = new GetCacheDataMessage(context.getMessageSystem(), getAddress(), context.getCacheAddress());
        context.getMessageSystem().sendMessage(cacheDataMessage);
        session.getBasicRemote().sendText(new Gson().toJson(data != null ? data : ""));
    }

    @OnError
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        session.getBasicRemote().sendText("Session closed");
    }

    @Override
    public void updateCacheData(CacheData data) {
        this.data = data;
    }

    @Override
    public Address getAddress() {
        return context.getFrontAddress();
    }
}
