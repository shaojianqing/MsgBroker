package msg.broker.domain.relation.base;

import msg.broker.domain.base.Base;

public class BaseMessageRelation extends Base {

	private String messageId;
	
	private String subscribeInfoId;
	
	private String groupId;
	
	private String topicCode;
	
	private String eventCode;
	
	private String status;

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getSubscribeInfoId() {
		return subscribeInfoId;
	}

	public void setSubscribeInfoId(String subscribeInfoId) {
		this.subscribeInfoId = subscribeInfoId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "MessageRelation{" +
				"messageId='" + messageId + '\'' +
				", subscribeInfoId='" + subscribeInfoId + '\'' +
				", groupId='" + groupId + '\'' +
				", topicCode='" + topicCode + '\'' +
				", eventCode='" + eventCode + '\'' +
				", status='" + status + '\'' +
				'}';
	}
}
