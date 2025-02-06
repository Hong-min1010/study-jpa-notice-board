package user.entity;

import audit.BaseEntity;
import board.entity.Board;
import comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
    @Id
    // Key가 생성이 되면 자동으로 Table과 같이 Mapping
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // long이 아니라 Long을 사용하는 이유 : 레퍼런스 타입을 사용해야
    // 영속성 확인할 때, null값으로 확인할 수 있기 때문
    private Long userId;

    @Column(nullable = false)
    private String nickName;

    // enum -> Class이다. 컴파일 時 Class로 변환

    // 초기화를 하지않으면 기본값이 null로 들어가서 NPE가 뜸
    // ORDINAL을 사용하면 enum class의 필드에 순서가 섞인다.(필드값이 순서대로 번호가 매겨져 있기 때문)

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private UserStatus userStatus = UserStatus.USER_ACTIVE;


    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<Comment> comments = new ArrayList<>();


    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<Board> boards = new ArrayList<>();

    // public static final 이 생략된거임 -> 변할 수 없는 값(상수)이기 때문

    public enum UserStatus {
        USER_ACTIVE,
        USER_SLEEP,
        USER_QUIT;
    }

    //    @OneToMany
//    private List<Like> likes = new ArrayList<>();
}
// List가 레퍼런스 타입이기때문 기본값 null인데
// new ArrayList를 하지 않으면 add를 해도 null에 값이 추가가 안되기 때문에 NPE가 뜸
// String = "", Long = 0