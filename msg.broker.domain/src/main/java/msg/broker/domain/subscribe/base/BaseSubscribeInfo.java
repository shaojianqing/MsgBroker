package msg.broker.domain.subscribe.base;

import msg.broker.domain.base.Base;

public class BaseSubscribeInfo extends Base {

	private String groupId;
	
	private String topicCode;
	
	private String eventCode;

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
		return "SubscribeInfo{" +
				"groupId='" + groupId + '\'' +
				", topicCode='" + topicCode + '\'' +
				", eventCode='" + eventCode + '\'' +
				'}';
	}
}
