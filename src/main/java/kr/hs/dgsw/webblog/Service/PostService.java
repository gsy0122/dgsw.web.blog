package kr.hs.dgsw.webblog.Service;

import kr.hs.dgsw.webblog.Domain.Post;

import java.util.List;

public interface PostService {
    Post create(Post post);
    Post read(Long id);
    Post readByUserId(Long userId);
    Post update(Long id, Post post);
    boolean delete(Long id);
    List<Post> readAll();
}
