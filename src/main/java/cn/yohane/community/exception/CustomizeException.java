package cn.yohane.community.exception;

/**
 * Created by SuwaKanan on 2020/06/19
 */
public class CustomizeException extends RuntimeException  {
    private String message;
    private Integer code;

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public CustomizeException(String message) {
        this.message = message;
    }
}
