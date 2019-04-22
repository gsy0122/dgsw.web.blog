package kr.hs.dgsw.webblog.Domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime created;
    @UpdateTimestamp
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime modified;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String imagePath;
}
