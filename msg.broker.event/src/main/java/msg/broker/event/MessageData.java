package msg.broker.event;

import msg.broker.value.ToStringObject;

import java.io.Serializable;

public class MessageData extends ToStringObject implements Serializable {

    private String messageRelationId;
	
	private String topic;
	
	private String event;
	
	private String data;
	
	private String clazz;

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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	@Override
	public String toString() {
		return "MessageData{" +
				"topic='" + topic + '\'' +
				", event='" + event + '\'' +
				", data='" + data + '\'' +
				", clazz='" + clazz + '\'' +
				'}';
	}
}
