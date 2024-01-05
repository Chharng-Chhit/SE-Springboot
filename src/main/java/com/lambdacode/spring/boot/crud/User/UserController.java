package com.lambdacode.spring.boot.crud.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.lambdacode.spring.boot.crud.Course.Course;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}/courses")
    public ResponseEntity<List<Course>> getCoursesManagedByUser(@PathVariable Integer userId) {
        User user = userService.getUser(userId);

        if (user != null) {
            List<Course> userCourses = userService.getCoursesManagedByUser(userId);
            return ResponseEntity.ok(userCourses);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public String addUser(@RequestBody User user) {
        userService.addUser(user);

        return "success add user";
    }

    /**
     * get users as list
     */

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    /**
     * get user by id
     */

    @GetMapping("/get")
    public User getUser(@RequestParam Integer id) {
        return userService.getUser(id);
    }

    /**
     * update user
     */

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Integer id, @RequestBody User user) {
        userService.updateUser(id, user);

        return ResponseEntity.noContent().build();
    }

    /**
     * delete user
     */

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

    /**
     * update name
     */

    @PatchMapping("/update-name/{id}")
    public ResponseEntity<Void> updateName(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        userService.updateName(id, userDTO);

        return ResponseEntity.noContent().build();
    }

}
