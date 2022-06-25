package msg.broker.dao.config;

import msg.broker.domain.config.MessageConfig;

public interface IMessageConfigDao {

    public MessageConfig getMessageConfigById(String id) throws Exception;

    public MessageConfig queryMessageConfigByTopicAndEvent(String topicCode, String eventCode) throws Exception;

    public int updateMessageConfig(MessageConfig messageConfig) throws Exception;

    public int saveMessageConfig(MessageConfig messageConfig) throws Exception;
}
