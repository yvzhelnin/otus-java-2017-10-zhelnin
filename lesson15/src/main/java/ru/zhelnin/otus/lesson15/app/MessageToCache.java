package ru.zhelnin.otus.lesson15.app;

import ru.zhelnin.otus.lesson15.message.Address;
import ru.zhelnin.otus.lesson15.message.Addressee;
import ru.zhelnin.otus.lesson15.message.Message;

public abstract class MessageToCache extends Message {

    public MessageToCache(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Addressee addressee) {
        if (addressee instanceof CacheService) {
            exec((CacheService) addressee);
        }
    }

    public abstract void exec(CacheService service);
}
