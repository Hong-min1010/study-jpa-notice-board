package comment.entity;

import audit.BaseEntity;
import board.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import user.entity.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private String text;

    @ManyToOne
    // 多 인쪽은 무조건 JoinColum (name = "多의 ID")
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
