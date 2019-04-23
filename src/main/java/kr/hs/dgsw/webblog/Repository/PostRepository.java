package kr.hs.dgsw.webblog.Repository;

import kr.hs.dgsw.webblog.Domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findTopByUserIdOrderByIdDesc(Long userId);
}
