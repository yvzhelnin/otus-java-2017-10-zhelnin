package ru.zhelnin.otus.lesson8.builder;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.junit.Assert;
import org.junit.Test;
import ru.zhelnin.otus.lesson8.experimental.TestObject;

import java.util.Arrays;
import java.util.List;

public class ObjectReaderTest {

    private final int primitive = 1;
    private final Integer[] array = {1, 2, 3, 4};
    private final List<Integer> collection = Arrays.asList(array);
    private final TestObject[] objectArray = {new TestObject(5, "firstObject", false), new TestObject(3, "secondObject", true)};

    @Test
    public void readPrimitiveTest() throws IllegalAccessException {
        JsonObject root = new Gson().fromJson(ObjectReader.makeJson(primitive, "primitive"), JsonObject.class);
        Assert.assertEquals(primitive, root.get("primitive").getAsInt());
    }

    @Test
    public void readArrayTest() throws IllegalAccessException {
        Assert.assertArrayEquals(array, parseArray(new Gson().fromJson(ObjectReader.makeJson(array, "array"), JsonObject.class), "array"));
    }

    @Test
    public void readCollectionTest() throws IllegalAccessException {
        Assert.assertEquals(collection, Arrays.asList(parseArray(new Gson().fromJson(ObjectReader.makeJson(collection, "collection"), JsonObject.class), "collection")));
    }

    @Test
    public void readObjectTest() throws IllegalAccessException {
        JsonObject root = new Gson().fromJson(ObjectReader.makeJson(new TestObject(5, "firstObject", false)), JsonObject.class);
        Assert.assertEquals(new TestObject(5, "firstObject", false), new Gson().fromJson(root.get("testObject"), TestObject.class));
    }

    @Test
    public void readObjectArrayTest() throws IllegalAccessException {
        Assert.assertArrayEquals(objectArray, parseObjectArray(new Gson().fromJson(ObjectReader.makeJson(objectArray, "objectArray"), JsonObject.class), "objectArray"));
    }

    @Test
    public void readObjectCollectionTest() throws IllegalAccessException {
        Assert.assertEquals(Arrays.asList(objectArray), Arrays.asList(parseObjectArray(new Gson().fromJson(ObjectReader.makeJson(Arrays.asList(objectArray), "objectCollection"), JsonObject.class), "objectCollection")));
    }

    private Integer[] parseArray(JsonObject root, String name) {
        Integer[] result = new Integer[4];
        int i = 0;
        for (JsonElement element : root.get(name).getAsJsonArray()) {
            result[i++] = element.getAsInt();
        }
        return result;
    }

    private TestObject[] parseObjectArray(JsonObject root, String name) {
        TestObject[] result = new TestObject[2];
        int i = 0;
        for (JsonElement element : root.get(name).getAsJsonArray()) {
            result[i++] = new Gson().fromJson(element, TestObject.class);
        }
        return result;
    }
}
