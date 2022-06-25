package msg.broker.proxy;

import msg.broker.event.*;
import msg.broker.util.AssertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import msg.broker.util.NetworkUtil;
import msg.broker.util.ProtocolUtil;

public class MessageProxy {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageProxy.class);

	public MessageInfo pullMessage(String messsageBrokerUrl, SubscribeRequest request) throws Exception {
		
		byte[] data = ProtocolUtil.transToByte(request);
		byte[] result = NetworkUtil.sendRequest(messsageBrokerUrl, 6666, data);
		MessageResult messageResult = (MessageResult)ProtocolUtil.transToObject(result);

		AssertUtil.isNotNull(messageResult, "[MessageProxy] pull message:messageResult is null");
		logger.info("[MessageProxy] pull messageResult:{}", messageResult);

		MessageData messageData = (MessageData)messageResult.getData();

		Class<?> clazz = Class.forName(messageData.getClazz());
		Object messageBody = ProtocolUtil.transToObject(messageData.getData(), clazz);
		
		MessageInfo messageInfo = new MessageInfo();
		messageInfo.setMessageRelationId(messageData.getMessageRelationId());
		messageInfo.setTopic(messageData.getTopic());
		messageInfo.setEvent(messageData.getEvent());
		messageInfo.setData(messageBody);
		
		return messageInfo;
	}
	
	public MessageContext confirmMessage(String messsageBrokerUrl, MessageContext messageContext) throws Exception {
		byte[] data = ProtocolUtil.transToByte(messageContext);
		byte[] result = NetworkUtil.sendRequest(messsageBrokerUrl, 5555, data);
		MessageResult messageResult = (MessageResult)ProtocolUtil.transToObject(result);

		AssertUtil.isNotNull(messageResult, "[MessageProxy] confirm message:messageResult is null");
		logger.info("[MessageProxy] confirm messageResult:{}", messageResult);

		AssertUtil.isNotTrue(messageResult.isSuccess(), messageResult.getMessage());
		return messageContext;
	}
}
