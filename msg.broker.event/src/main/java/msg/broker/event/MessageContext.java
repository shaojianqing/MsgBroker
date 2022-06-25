package msg.broker.event;

import msg.broker.value.ToStringObject;

import java.io.Serializable;

public class MessageContext extends ToStringObject implements Serializable {

	private String messageRelationId;

	private boolean success = false;
	
	private boolean retry;

	public String getMessageRelationId() {
		return messageRelationId;
	}

	public void setMessageRelationId(String messageRelationId) {
		this.messageRelationId = messageRelationId;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isRetry() {
		return retry;
	}

	public void setRetry(boolean retry) {
		this.retry = retry;
	}

	@Override
	public String toString() {
		return "MessageContext{" +
				"messageRelationId='" + messageRelationId + '\'' +
				", success=" + success +
				", retry=" + retry +
				'}';
	}
}
