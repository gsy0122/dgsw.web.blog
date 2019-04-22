package kr.hs.dgsw.webblog.Controller;

import kr.hs.dgsw.webblog.Domain.User;
import kr.hs.dgsw.webblog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/user/create")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }
    @PutMapping("/user/update/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }
    @DeleteMapping("/user/delete/{id}")
    public boolean delete(@PathVariable Long id) {
        return userService.delete(id);
    }
    @GetMapping("/user/read/{id}")
    public User read(@PathVariable Long id) {
        return userService.read(id);
    }
    @GetMapping("/user/read")
    public List<User> readAll() {
        return userService.readAll();
    }
}
