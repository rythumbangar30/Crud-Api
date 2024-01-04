package com.springboot.web.apiUser;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping
public class UserController {
    @GetMapping("/Users")
    public static List<User> getAllUsers(){
        return UserService.getAllUsers();
    }

    @GetMapping("/User/{userId}")
    public User getUserById(@PathVariable int userId){
        return UserService.getUserById(userId);
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){
        return UserService.addUser(user);
    }

    @PutMapping("/updateUser/{userId}")
    public User updateUser(@PathVariable int userId,@RequestBody User user){
        return UserService.updateUser(userId,user);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public User deleteUser(@PathVariable int userId){
        return UserService.deleteUser(userId);
    }
}
