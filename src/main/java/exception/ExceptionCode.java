package exception;

import lombok.Getter;

public enum ExceptionCode {
    USER_NOT_FOUND(404, "User not found"),
    USER_EXISTS(409, "User exists"),
    BOARD_NOT_FOUND(404, "Board not found"),
    COMMENT_NOT_FOUND(404, "Comment not found"),
    CANNOT_CHANGE_COMMENT(403, "Comment can not change"),
    NOT_IMPLEMENTATION(501, "Not Implementation"),
    INVALID_USER_STATUS(400, "Invalid User status");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
