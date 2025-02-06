package user.service;

import exception.BusinessLogicException;
import exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import user.entity.User;
import user.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User craeteUser(User user) {
        // 1. 중복된 nickName인지 검증
        verifyExistsNickName(user.getNickName());
        // 2. Repository에 저장

        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return null;
    }

    public User getUser(long userId) {
        return null;
    }

    // Page에 담아서 Controller에 넘김
    public Page<User> getUsers(int page, int size) {
        return null;
    }

    public void deleteUser(long userId) {
        // delete時 회원 상태 변경
        // 1. 회원이 존재하는지 확인
        User findUser = findVertifiedUser(userId);
        // 2. 존재하면 User의 상테를 변경
        findUser.setUserStatus(User.UserStatus.USER_QUIT);
        // save는 데이터를 저장할 때 PK가 같으면 덮어씌움, PK가 없거나 같지않으면 새로 저장
        userRepository.save(findUser);
    }

    // 가입 할 때 이미 존재하는 nickName인지 검증하는 메서드
    public void verifyExistsNickName (String nickName) {
        Optional<User> user = userRepository.findByNickName(nickName);
//        User findUser = user.orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_EXISTS));
        if (user.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.USER_EXISTS);
        }

    }

    public User findVertifiedUser (long userId) {
        // repository에서 해당하는 유저 있으면 반환 없으면 예외 발생
        Optional<User> findUser = userRepository.findById(userId);
        // orElseThrow -> Null값이면 Exception 발생시킴. Null이 아니면 Optional을 벗겨서 User 객체러 반환
        User user = findUser.orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND)
        );
        return user;
    }
}
