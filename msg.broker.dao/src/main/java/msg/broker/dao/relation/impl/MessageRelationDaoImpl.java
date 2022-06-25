package msg.broker.dao.relation.impl;

import msg.broker.domain.base.Base;
import msg.broker.util.AssertUtil;
import msg.broker.util.GUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;

import msg.broker.dao.relation.IMessageRelationDao;
import msg.broker.domain.relation.MessageRelation;
import msg.broker.domain.relation.MsgRelationStatusEnum;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class MessageRelationDaoImpl implements IMessageRelationDao {
	
	private SqlSessionTemplate sqlSessionTemplate;

	public MessageRelation getMessageRelationById(String id) throws Exception {

		AssertUtil.isNotBlank(id, "messageRelationId不能为空!");

		return sqlSessionTemplate.selectOne("msg.broker.domain.relation.MessageRelation.getMessageRelationById", id);
	}

	public MessageRelation getMessageRelationByIdForUpdate(String id) throws Exception {

		AssertUtil.isNotBlank(id, "messageRelationId不能为空!");

		return sqlSessionTemplate.selectOne("msg.broker.domain.relation.MessageRelation.getMessageRelationByIdForUpdate", id);
	}

	public MessageRelation queryMessageRelationOne(String subscribeInfoId, String topic, String event, MsgRelationStatusEnum status) throws Exception {

		AssertUtil.isNotBlank(subscribeInfoId, "subscribeInfoId不能为空!");
		AssertUtil.isNotBlank(topic, "topic不能为空!");
		AssertUtil.isNotBlank(event, "event不能为空!");
		AssertUtil.isNotNull(status, "status不能为空!");

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("subscribeInfoId", subscribeInfoId);
		param.put("status", status.getStatus());
		param.put("topicCode", topic);
		param.put("eventCode", event);

		return sqlSessionTemplate.selectOne("msg.broker.domain.relation.MessageRelation.queryMessageRelationOne", param);
	}

	public int updateMessageRelationStatus(String messageRelationId, MsgRelationStatusEnum oldStatus, MsgRelationStatusEnum newStatus) throws Exception {

		AssertUtil.isNotBlank(messageRelationId, "messageRelationId不能为空!");
		AssertUtil.isNotNull(oldStatus, "oldStatus不能为空!");
		AssertUtil.isNotNull(oldStatus, "newStatus不能为空!");

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", messageRelationId);
		param.put("oldStatus", oldStatus.getStatus());
		param.put("newStatus", newStatus.getStatus());

		return sqlSessionTemplate.update("msg.broker.domain.relation.MessageRelation.updateMessageRelationStatus", param);
	}
	
	public int saveMessageRelation(MessageRelation relation) throws Exception {

		AssertUtil.isNotNull(relation, "messageRelation不能为空!");

		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		relation.setId(GUIDUtil.generateUUID());
		relation.setIsValid(Base.TRUE);
		relation.setCreateTime(currentTime);
		relation.setOperateTime(currentTime);

		return sqlSessionTemplate.update("msg.broker.domain.relation.MessageRelation.saveMessageRelation", relation);
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
}
