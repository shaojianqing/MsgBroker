package msg.broker.listener;

import msg.broker.event.MessageContext;
import msg.broker.event.MessageInfo;

public interface MessageListener {
	
	public void onMessage(MessageInfo messageInfo, MessageContext messageContext);
}