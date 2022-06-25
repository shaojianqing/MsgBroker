package msg.broker.domain.config.base;

import msg.broker.domain.base.Base;

public class BaseMessageConfig extends Base {

    private String topicCode;

    private String eventCode;

    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
