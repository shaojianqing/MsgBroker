package msg.broker.util;

import java.util.UUID;

public class GUIDUtil {

    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-","");
    }
}
