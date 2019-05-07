package kr.hs.dgsw.webblog.Service;

import kr.hs.dgsw.webblog.Domain.Post;
import kr.hs.dgsw.webblog.Domain.User;
import kr.hs.dgsw.webblog.Repository.PostRepository;
import kr.hs.dgsw.webblog.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void init(){
        User user = userRepository.save(new User("gsy0122", "1234", "강서연", "gsy0122@dgsw.hs.kr",
                "010-8366-9272", "https://cdn2.iconfinder.com/data/icons/4web-3/139/header-account-image-line-512.png"));
        postRepository.save(new Post(user.getId(), "안녕하세요.", "첫 번째 게시물입니다."));
    }

    @Override
    public Post create(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post read(Long id) {
        return postRepository.findById(id).isPresent() ? postRepository.findById(id).get() : null;
    }

    public Post readByUserId(Long userId) {
        return postRepository
                .findTopByUserIdOrderByIdDesc(userId)
                .orElse(null);
    }

    @Override
    public Post update(Long id, Post post) {
        return postRepository.findById(id)
                .map(found -> {
                    found.setTitle(Optional.ofNullable(post.getTitle()).orElse(found.getTitle()));
                    found.setContent(Optional.ofNullable(post.getContent()).orElse(found.getContent()));
                    found.setPictures(Optional.ofNullable(post.getPictures()).orElse(found.getPictures()));
                    return postRepository.save(found);
                })
                .orElse(null);
    }

    @Override
    public boolean delete(Long id) {
        try {
            postRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Post> readAll() {
        return postRepository.findAll();
    }
}
