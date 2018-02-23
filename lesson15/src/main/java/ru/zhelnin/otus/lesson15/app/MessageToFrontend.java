package ru.zhelnin.otus.lesson15.app;

import ru.zhelnin.otus.lesson15.message.Address;
import ru.zhelnin.otus.lesson15.message.Addressee;
import ru.zhelnin.otus.lesson15.message.Message;

public abstract class MessageToFrontend extends Message {

    public MessageToFrontend(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Addressee addressee) {
        if (addressee instanceof FrontendService) {
            exec((FrontendService) addressee);
        }
    }

    public abstract void exec(FrontendService frontendService);
}
