package com.example.java31.controller;

import com.example.java31.model.User;
import com.example.java31.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 1. 获取所有用户
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // 2. 添加用户
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        try {
            User newUser = userService.addUser(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 3. 删除用户
    @PostMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // 4. 按年龄排序
    @GetMapping("/sort/age")
    public List<User> getSortedByAge() {
        return userService.getSortedByAge();
    }

    // 5. 按name长度排序
    @GetMapping("/sort/name-length")
    public List<User> getSortedByNameLength() {
        return userService.getSortedByNameLength();
    }

    // 6. 去重name
    @GetMapping("/unique-names")
    public List<User> getUniqueNames() {
        return userService.getUniqueNames();
    }

    // 7. 按name查询
    @GetMapping("/search")
    public List<User> findByName(@RequestParam String name) {
        return userService.findByName(name);
    }

    // 8. 提取name列表
    @GetMapping("/names")
    public List<String> extractNames() {
        return userService.extractNames();
    }

    // 9. 过滤年龄大于指定值
    @GetMapping("/filter/age")
    public List<User> filterByAgeGreaterThan(@RequestParam Integer age) {
        return userService.filterByAgeGreaterThan(age);
    }

    // 10. 修改用户
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam Integer age
    ) {
        try {
            User updatedUser = userService.updateUser(id, name, age);
            return ResponseEntity.ok(updatedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 11. 获取交集（模拟另一个服务类的列表，此处通过请求体传入）
    @PostMapping("/intersection")
    public List<User> getIntersection(@RequestBody List<User> anotherUsers) {
        return userService.getIntersection(anotherUsers);
    }
}