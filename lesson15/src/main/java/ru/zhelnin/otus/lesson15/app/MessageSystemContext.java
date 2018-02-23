package ru.zhelnin.otus.lesson15.app;

import ru.zhelnin.otus.lesson15.message.Address;
import ru.zhelnin.otus.lesson15.message.MessageSystem;

public class MessageSystemContext {

    private final MessageSystem messageSystem;

    private Address frontAddress = new Address("Frontend");
    private Address cacheAddress = new Address("Cache");

    public MessageSystemContext(MessageSystem messageSystem) {
        this.messageSystem = messageSystem;
    }

    public MessageSystem getMessageSystem() {
        return messageSystem;
    }

    public Address getFrontAddress() {
        return frontAddress;
    }

    public void setFrontAddress(Address frontAddress) {
        this.frontAddress = frontAddress;
    }

    public Address getCacheAddress() {
        return cacheAddress;
    }

    public void setCacheAddress(Address cacheAddress) {
        this.cacheAddress = cacheAddress;
    }
}
