package com.example.java31.service;

import com.example.java31.model.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>(); // 用户列表
    // 初始测试数据
    {
        users.add(new User(1L, "Shelby", 25));
        users.add(new User(2L, "tong", 30));
        users.add(new User(3L, "duo", 28));
    }

    // 1. 获取用户列表
    public List<User> getAllUsers() {
        return new ArrayList<>(users); // 返回副本，避免外部修改
    }

    // 2. 添加用户（id不重复）
    public User addUser(User user) {
        if (users.stream().anyMatch(u -> u.getId().equals(user.getId()))) {
            throw new IllegalArgumentException("User ID already exists");
        }
        users.add(user);
        return user;
    }

    // 3. 根据id删除用户
    public void deleteUser(Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }

    // 4. 按年龄升序排序
    public List<User> getSortedByAge() {
        return users.stream()
                .sorted(Comparator.comparingInt(User::getAge))
                .collect(Collectors.toList());
    }

    // 5. 按name长度升序排序
    public List<User> getSortedByNameLength() {
        return users.stream()
                .sorted(Comparator.comparingInt(u -> u.getName().length()))
                .collect(Collectors.toList());
    }

    // 6. 去除重复name（保留第一个出现）
    public List<User> getUniqueNames() {
        return users.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toMap(User::getName, u -> u, (u1, u2) -> u1),
                        map -> new ArrayList<>(map.values())
                ));
    }

    // 7. 根据name查询用户（返回所有匹配项）
    public List<User> findByName(String name) {
        return users.stream()
                .filter(user -> user.getName().equals(name))
                .collect(Collectors.toList());
    }

    // 8. 提取name列表
    public List<String> extractNames() {
        return users.stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }

    // 9. 年龄大于指定值过滤
    public List<User> filterByAgeGreaterThan(Integer age) {
        return users.stream()
                .filter(user -> user.getAge() > age)
                .collect(Collectors.toList());
    }

    // 10. 根据id修改用户的name和age
    public User updateUser(Long id, String newName, Integer newAge) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .map(user -> {
                    user.setName(newName);
                    user.setAge(newAge);
                    return user;
                })
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    // 11. 根据id获取两个用户列表的交集
    public List<User> getIntersection(List<User> anotherUsers) {
        return users.stream()
                .filter(user -> anotherUsers.stream()
                        .anyMatch(anotherUser -> anotherUser.getId().equals(user.getId())))
                .collect(Collectors.toList());
    }
}