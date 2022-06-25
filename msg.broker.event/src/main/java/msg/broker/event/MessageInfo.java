package msg.broker.event;

import java.io.Serializable;

import msg.broker.value.ToStringObject;

public class MessageInfo extends ToStringObject implements Serializable {
	
	private static final long  serialVersionUID  = Long.MAX_VALUE;

	private String messageRelationId;

	private String topic;
	
	private String event;
	
	private Object data;

	public String getMessageRelationId() {
		return messageRelationId;
	}

	public void setMessageRelationId(String messageRelationId) {
		this.messageRelationId = messageRelationId;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
