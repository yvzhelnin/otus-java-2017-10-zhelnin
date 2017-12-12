package ru.zhelnin.otus.lesson8.experimental;

import lombok.Data;
import ru.zhelnin.otus.lesson8.builder.annotations.JsonProperty;

@SuppressWarnings("WeakerAccess")
@Data
public class InnerObject {

    @JsonProperty
    private final String user;

    @JsonProperty
    protected final String password;

    public InnerObject(String user, String password) {
        this.user = user;
        this.password = password;
    }
}
