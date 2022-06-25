package msg.broker.service;

import msg.broker.event.MessageInfo;
import msg.broker.event.MessageResult;

public interface IMessagePublisher {

	public MessageResult publish(MessageInfo messageInfo);
}