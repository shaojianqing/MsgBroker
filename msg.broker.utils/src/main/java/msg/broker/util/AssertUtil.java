package msg.broker.util;

import msg.broker.ecxeption.BizValidateException;
import org.apache.commons.lang3.StringUtils;

public class AssertUtil {

    public static void isNotBlank(String source, String message) throws BizValidateException {
        if (StringUtils.isBlank(source)) {
            throw new BizValidateException(message);
        }
    }

    public static void isNotNull(Object object, String message) throws BizValidateException {
        if (object==null) {
            throw new BizValidateException(message);
        }
    }

    public static void isNotTrue(boolean value, String message) throws BizValidateException {
        if (!value) {
            throw new BizValidateException(message);
        }
    }
}
