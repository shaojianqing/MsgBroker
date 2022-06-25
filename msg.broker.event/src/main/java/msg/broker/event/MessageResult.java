package msg.broker.event;

import java.io.Serializable;

public class MessageResult implements Serializable {

    private boolean success;

    private Object data;

    private String message;

    public MessageResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public MessageResult(boolean success, Object data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
