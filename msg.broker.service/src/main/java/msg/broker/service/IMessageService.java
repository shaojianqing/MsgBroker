package msg.broker.service;

import msg.broker.event.MessageContext;
import msg.broker.event.MessageData;
import msg.broker.event.SubscribeRequest;

public interface IMessageService {

	public boolean restoreMessage(MessageData messageData) throws Exception;

	public MessageData queryMessageDataBySubscribe(SubscribeRequest SubscribeRequest) throws Exception;

	public boolean confirmMessage(MessageContext messageContext) throws Exception;
}
