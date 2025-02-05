package user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
