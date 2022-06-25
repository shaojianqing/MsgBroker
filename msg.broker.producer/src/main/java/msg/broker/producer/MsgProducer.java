package msg.broker.producer;

import msg.broker.event.MessageInfo;
import msg.broker.producer.domain.UserLoginStatus;
import msg.broker.service.impl.MessagePublisherImpl;

import java.util.UUID;

public class MsgProducer {

    private static final String MSG_TOPIC = "TOPIC_USER_LOGIN";

    private static final String MSG_EVENT = "EVENT_USER_LOGIN";

    private static MessagePublisherImpl messagePublisher;

    public static void main(String[] args) {

        messagePublisher = new MessagePublisherImpl();
        messagePublisher.setMessageServer("msg.broker.server");

        UserLoginStatus userLoginStatus = new UserLoginStatus();
        userLoginStatus.setUserId(UUID.randomUUID().toString());
        userLoginStatus.setUserName("yangshiyin");
        userLoginStatus.setLoginStatus("Success");

        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setTopic(MSG_TOPIC);
        messageInfo.setEvent(MSG_EVENT);
        messageInfo.setData(userLoginStatus);

        messagePublisher.publish(messageInfo);

        System.out.println("Publish Message Successfully^+^");

        while(true) {
            try{
                Thread.sleep(500l);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
