package msg.broker.service.impl;

import msg.broker.event.MessageData;
import msg.broker.event.MessageInfo;
import msg.broker.event.MessageResult;
import msg.broker.service.IMessagePublisher;
import msg.broker.util.NetworkUtil;
import msg.broker.util.ProtocolUtil;

public class MessagePublisherImpl implements IMessagePublisher {


	
	private String messageServer;

	public MessageResult publish(MessageInfo messageInfo) {
		try {
		
			MessageData messageData = new MessageData();
			messageData.setTopic(messageInfo.getTopic());
			messageData.setEvent(messageInfo.getEvent());
			messageData.setClazz(messageInfo.getData().getClass().getName());
			String messageBody = ProtocolUtil.transToString(messageInfo.getData());
			messageData.setData(messageBody);
			
			byte[] data = ProtocolUtil.transToByte(messageData);
			byte[] result = NetworkUtil.sendRequest(messageServer, 8888, data);
			return (MessageResult)ProtocolUtil.transToObject(result);

		} catch(Exception e) {
			e.printStackTrace();
			return new MessageResult(false, e.getMessage());
		}
	}
	
	public void setMessageServer(String messageServer) {
		this.messageServer = messageServer;
	}
}