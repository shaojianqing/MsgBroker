package msg.broker.dao.message.impl;

import msg.broker.domain.base.Base;
import msg.broker.util.AssertUtil;
import msg.broker.util.GUIDUtil;
import org.mybatis.spring.SqlSessionTemplate;

import msg.broker.dao.message.IMessageDao;
import msg.broker.domain.message.Message;

import java.sql.Timestamp;

public class MessageDaoImpl implements IMessageDao {
	
	private SqlSessionTemplate sqlSessionTemplate;
	
	public Message getMessageById(String id) throws Exception {

		AssertUtil.isNotBlank(id, "messageId不能为空!");

		return sqlSessionTemplate.selectOne("msg.broker.domain.message.Message.getMessageById", id);
	}

	public Message getMessageByIdForUpdate(String id) throws Exception {

		AssertUtil.isNotBlank(id, "messageId不能为空!");

		return sqlSessionTemplate.selectOne("msg.broker.domain.message.Message.getMessageByIdForUpdate", id);
	}

	public int saveMessage(Message message) throws Exception {

		AssertUtil.isNotNull(message, "消息message不能为空!");

		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		message.setId(GUIDUtil.generateUUID());
		message.setIsValid(Base.TRUE);
		message.setCreateTime(currentTime);
		message.setOperateTime(currentTime);

		return sqlSessionTemplate.update("msg.broker.domain.message.Message.saveMessage", message);
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
}