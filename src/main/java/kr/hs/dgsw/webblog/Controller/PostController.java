package kr.hs.dgsw.webblog.Controller;

import kr.hs.dgsw.webblog.Domain.Post;
import kr.hs.dgsw.webblog.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/post/create")
    public Post create(@RequestBody Post post) {
        return postService.create(post);
    }
    @PutMapping("/post/update/{id}")
    public Post update(@PathVariable Long id, @RequestBody Post post) {
        return postService.update(id, post);
    }
    @DeleteMapping("/post/delete/{id}")
    public boolean delete(@PathVariable Long id) {
        return postService.delete(id);
    }
    @GetMapping("/post/read/{id}")
    public Post read(@PathVariable Long id) {
        return postService.read(id);
    }
    @GetMapping("/post/read")
    public List<Post> readAll() {
        return postService.readAll();
    }
}
