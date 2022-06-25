package msg.broker.dao.config.impl;

import msg.broker.dao.config.IMessageConfigDao;
import msg.broker.domain.base.Base;
import msg.broker.domain.config.MessageConfig;
import msg.broker.util.AssertUtil;
import msg.broker.util.GUIDUtil;
import org.mybatis.spring.SqlSessionTemplate;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class MessageConfigDaoImpl implements IMessageConfigDao {

    private SqlSessionTemplate sqlSessionTemplate;

    public MessageConfig getMessageConfigById(String id) throws Exception {

        AssertUtil.isNotBlank(id, "messageConfigId不能为空!");

        return sqlSessionTemplate.selectOne("msg.broker.domain.config.MessageConfig.getMessageConfigById", id);
    }

    public MessageConfig queryMessageConfigByTopicAndEvent(String topicCode, String eventCode) throws Exception {

        AssertUtil.isNotBlank(topicCode, "消息配置TopicCode不能为空!");
        AssertUtil.isNotBlank(eventCode, "消息配置eventCode不能为空!");

        Map<String, String> param = new HashMap<String, String>();
        param.put("topicCode", topicCode);
        param.put("eventCode", eventCode);

        return sqlSessionTemplate.selectOne("msg.broker.domain.config.MessageConfig.queryMessageConfigByTopicAndEvent", param);
    }

    public int updateMessageConfig(MessageConfig messageConfig) throws Exception {

        AssertUtil.isNotNull(messageConfig, "消息配置MessageConfig不能为空!");

        return sqlSessionTemplate.update("msg.broker.domain.config.MessageConfig.updateMessageConfig", messageConfig);
    }

    public int saveMessageConfig(MessageConfig messageConfig) throws Exception {

        AssertUtil.isNotNull(messageConfig, "消息配置MessageConfig不能为空!");

        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        messageConfig.setId(GUIDUtil.generateUUID());
        messageConfig.setIsValid(Base.TRUE);
        messageConfig.setCreateTime(currentTime);
        messageConfig.setOperateTime(currentTime);

        return sqlSessionTemplate.update("msg.broker.domain.config.MessageConfig.saveMessageConfig", messageConfig);
    }

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }
}
