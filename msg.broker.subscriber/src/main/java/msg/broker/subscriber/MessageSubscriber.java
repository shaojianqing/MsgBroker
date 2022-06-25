package msg.broker.subscriber;

import java.util.Timer;
import java.util.TimerTask;

import javax.naming.ConfigurationException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import msg.broker.event.MessageContext;
import msg.broker.event.MessageInfo;
import msg.broker.event.SubscribeRequest;
import msg.broker.listener.MessageListener;
import msg.broker.proxy.MessageProxy;

public class MessageSubscriber {

	private static final Logger logger = LoggerFactory.getLogger(MessageSubscriber.class);

	private MessageListener messageListener;
	
	private MessageProxy messageProxy;

	private String messageBrokerUrl;

	private String topicCode;

	private String eventCode;

	private String groupId;
	
	private Timer messageTimer;
	
	private TimerTask messageTask;

	public void init() {

		try {
			if (StringUtils.isBlank(messageBrokerUrl)) {
				throw new ConfigurationException("message.broker.url configuration does not exist!");
			}
			
			messageProxy = new MessageProxy();
			messageTimer = new Timer();
			messageTask = new TimerTask(){
				@Override
				public void run() {
					try {
						SubscribeRequest request = SubscribeRequest.buildSubscribeRequest(groupId, topicCode, eventCode);
						MessageInfo messageInfo = messageProxy.pullMessage(messageBrokerUrl, request);
						MessageContext messageContext = new MessageContext();
						messageContext.setMessageRelationId(messageInfo.getMessageRelationId());
						messageListener.onMessage(messageInfo, messageContext);
						messageProxy.confirmMessage(messageBrokerUrl, messageContext);
					} catch(Exception e) {
						logger.error("MessageSubscriber pull raise message !", e);
					}
				}
			};
			
			messageTimer.schedule(messageTask, 500, 500);
		} catch (Exception e) {
			logger.error("MessageSubscriber init raise exception!", e);
		}
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getTopicCode() {
		return topicCode;
	}

	public void setTopicCode(String topicCode) {
		this.topicCode = topicCode;
	}

	public String getEventCode() {
		return eventCode;
	}

	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}

	public MessageListener getMessageListener() {
		return messageListener;
	}

	public void setMessageBrokerUrl(String messageBrokerUrl) {
		this.messageBrokerUrl = messageBrokerUrl;
	}

	public void setMessageListener(MessageListener messageListener) {
		this.messageListener = messageListener;
	}
}
