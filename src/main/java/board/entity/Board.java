package board.entity;

import audit.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import user.entity.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
// ★ JPA일 때 -> private으로 field를 생성 時 자동으로 생성이 안될수도 있다. (build 애너테이션을 사용할때도 같이 써야함 builder, NoArgsConstructor 세트)
// 그렇게 때문에 @NoArgsConstructor(기본생성자 생성)을 사용함
// 필드에 final이 있을 時 무조건 초기화를 시켜줘야 하기때문에 (force = ture)를 @NoArgsConstructor 옆에 써야함
@NoArgsConstructor
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "board")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "board")
    private List<Like> likes = new ArrayList<>();

}
