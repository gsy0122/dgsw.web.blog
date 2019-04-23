package kr.hs.dgsw.webblog.Controller;

import kr.hs.dgsw.webblog.Domain.User;
import kr.hs.dgsw.webblog.Protocol.ResponseFormat;
import kr.hs.dgsw.webblog.Protocol.ResponseType;
import kr.hs.dgsw.webblog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/user/create")
    public ResponseFormat create(@RequestBody User user) {
        if (userService.create(user) != null) {
            return new ResponseFormat(
                    ResponseType.USER_ADD,
                    userService.create(user),
                    user.getId()
            );
        } else {
            return new ResponseFormat(
                    ResponseType.FAIL,
                    null
            );
        }
    }
    @PutMapping("/user/update/{id}")
    public ResponseFormat update(@PathVariable Long id, @RequestBody User user) {
        if (userService.update(id, user) != null) {
            return new ResponseFormat(
                    ResponseType.USER_UPDATE,
                    userService.update(id, user),
                    user.getId()
            );
        } else {
            return new ResponseFormat(
                    ResponseType.FAIL,
                    null
            );
        }
    }
    @DeleteMapping("/user/delete/{id}")
    public ResponseFormat delete(@PathVariable Long id) {
        if (userService.delete(id)) {
            return new ResponseFormat(
                    ResponseType.USER_DELETE,
                    userService.delete(id),
                    id
            );
        } else {
            return new ResponseFormat(
                    ResponseType.FAIL,
                    null
            );
        }
    }
    @GetMapping("/user/read/{id}")
    public ResponseFormat read(@PathVariable Long id) {
        if (userService.read(id) != null) {
            return new ResponseFormat(
                    ResponseType.USER_GET,
                    userService.read(id),
                    id
            );
        } else {
            return new ResponseFormat(
                    ResponseType.FAIL,
                    null
            );
        }
    }
    @GetMapping("/user/read")
    public ResponseFormat readAll() {
        if (userService.readAll() != null) {
            return new ResponseFormat(
                    ResponseType.USER_GET_ALL,
                    userService.readAll()
            );
        } else {
            return new ResponseFormat(
                    ResponseType.FAIL,
                    null
            );
        }
    }
}
