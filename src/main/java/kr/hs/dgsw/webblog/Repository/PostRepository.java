package kr.hs.dgsw.webblog.Repository;

import kr.hs.dgsw.webblog.Domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
