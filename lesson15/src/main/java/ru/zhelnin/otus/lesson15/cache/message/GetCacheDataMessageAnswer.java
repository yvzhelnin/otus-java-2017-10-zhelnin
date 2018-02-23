package ru.zhelnin.otus.lesson15.cache.message;

import ru.zhelnin.otus.lesson15.app.FrontendService;
import ru.zhelnin.otus.lesson15.app.MessageToFrontend;
import ru.zhelnin.otus.lesson15.cache.model.CacheData;
import ru.zhelnin.otus.lesson15.message.Address;

public class GetCacheDataMessageAnswer extends MessageToFrontend {

    private final CacheData data;

    GetCacheDataMessageAnswer(Address from, Address to, CacheData data) {
        super(from, to);
        this.data = data;
    }

    @Override
    public void exec(FrontendService frontendService) {
        frontendService.updateCacheData(data);
    }
}
