package ru.zhelnin.otus.lesson9.model;

public class UserData {

    private Long id;
    private String name;
    private Integer age;

    public UserData(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public UserData(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public UserData() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
