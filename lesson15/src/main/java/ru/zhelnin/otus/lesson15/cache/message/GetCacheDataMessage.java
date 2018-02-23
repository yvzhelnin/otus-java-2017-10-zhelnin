package ru.zhelnin.otus.lesson15.cache.message;

import ru.zhelnin.otus.lesson15.app.CacheService;
import ru.zhelnin.otus.lesson15.app.MessageToCache;
import ru.zhelnin.otus.lesson15.cache.model.CacheData;
import ru.zhelnin.otus.lesson15.message.Address;
import ru.zhelnin.otus.lesson15.message.MessageSystem;

public class GetCacheDataMessage extends MessageToCache {

    private final MessageSystem messageSystem;

    public GetCacheDataMessage(MessageSystem messageSystem, Address from, Address to) {
        super(from, to);
        this.messageSystem = messageSystem;
    }

    @Override
    public void exec(CacheService service) {
        CacheData data = service.getCacheData();
        messageSystem.sendMessage(new GetCacheDataMessageAnswer(getTo(), getFrom(), data));
    }
}
