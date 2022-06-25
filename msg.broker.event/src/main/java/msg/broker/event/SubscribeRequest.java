package msg.broker.event;

import java.io.Serializable;

import msg.broker.value.ToStringObject;

public class SubscribeRequest extends ToStringObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String groupId;
	
	private String topicCode;
	
	private String eventCode;
	
	public static SubscribeRequest buildSubscribeRequest(String groupId, String topicCode, String eventCode) {
		SubscribeRequest subscribeRequest = new SubscribeRequest();
		subscribeRequest.setTopicCode(topicCode);
		subscribeRequest.setEventCode(eventCode);
		subscribeRequest.setGroupId(groupId);
		return subscribeRequest;
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

	@Override
	public String toString() {
		return "SubscribeRequest{" +
				"groupId='" + groupId + '\'' +
				", topicCode='" + topicCode + '\'' +
				", eventCode='" + eventCode + '\'' +
				'}';
	}
}
