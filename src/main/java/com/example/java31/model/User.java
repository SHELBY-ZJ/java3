package com.example.java31.model;

import lombok.Getter;
import lombok.Setter;
import java.util.Objects;

@Setter
@Getter
public class User {
    // Getter & Setter
    private Long id;        // 主键，不可重复
    private String name;    // 名称
    private Integer age;    // 年龄

    public User(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}