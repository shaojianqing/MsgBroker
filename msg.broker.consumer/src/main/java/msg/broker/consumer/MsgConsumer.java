package msg.broker.consumer;

import msg.broker.event.MessageContext;
import msg.broker.event.MessageInfo;
import msg.broker.listener.MessageListener;
import msg.broker.producer.domain.UserLoginStatus;
import msg.broker.subscriber.MessageSubscriber;

public class MsgConsumer implements MessageListener {

    private static final String MSG_TOPIC = "TOPIC_USER_LOGIN";

    private static final String MSG_EVENT = "EVENT_USER_LOGIN";

    private static final String GROUP_MSG_CONSUMER = "GROUP_USER_INFO";

    public static void main(String[] args) {

        MessageSubscriber messageSubscriber = new MessageSubscriber();
        messageSubscriber.setTopicCode(MSG_TOPIC);
        messageSubscriber.setEventCode(MSG_EVENT);
        messageSubscriber.setGroupId(GROUP_MSG_CONSUMER);
        messageSubscriber.setMessageBrokerUrl("msg.broker.server");
        messageSubscriber.setMessageListener(new MsgConsumer());

        messageSubscriber.init();

        System.out.println("Message Consumer Init Successfully^+^");
    }

    public void onMessage(MessageInfo messageInfo, MessageContext messageContext) {

        UserLoginStatus userLoginStatus = (UserLoginStatus)messageInfo.getData();

        System.out.println("Receive Message Successfully^+^");
        System.out.println(userLoginStatus);
        System.out.println("Process Message Successfully^+^");

        messageContext.setSuccess(true);
        messageContext.setRetry(false);
    }
}
