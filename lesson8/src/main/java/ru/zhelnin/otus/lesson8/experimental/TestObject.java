package ru.zhelnin.otus.lesson8.experimental;

import lombok.Data;
import ru.zhelnin.otus.lesson8.builder.annotations.JsonClass;
import ru.zhelnin.otus.lesson8.builder.annotations.JsonProperty;

@SuppressWarnings("WeakerAccess")
@JsonClass("testObject")
@Data
public class TestObject {

    @JsonProperty
    private final int count;

    @JsonProperty
    protected final String name;

    @JsonProperty
    public final boolean isError;

    @JsonProperty
    private final InnerObject innerObject = new InnerObject("user", "*********");

    public TestObject(int count, String name, boolean isError) {
        this.count = count;
        this.name = name;
        this.isError = isError;
    }
}
