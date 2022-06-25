package msg.broker.ecxeption;

import org.apache.commons.lang3.StringUtils;

public class BizValidateException extends Exception {

    private static final long serialVersionUID = 1L;

    public BizValidateException(String message) {
        super(message);
    }
}
