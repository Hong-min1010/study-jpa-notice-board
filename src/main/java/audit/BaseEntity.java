package audit;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
// 상속받는 애들의 부모클래스로 상속
@MappedSuperclass
// entity 변경을 실시간으로 감지 -> BaseEntity를 상속 받게되면 상속받은 Entity가 변경사항이 있나 감시함
@EntityListeners(AuditingEntityListener.class)
// 추상 클래스이기때문에 abstract를 붙여줘야함
public abstract class BaseEntity {

    @CreatedDate
    private LocalDateTime createdAt;

    // 마지막 수정된 시간을 자동으로 바꿔주는 애너테이션
    @LastModifiedDate
    private LocalDateTime modifiedAt;

}
