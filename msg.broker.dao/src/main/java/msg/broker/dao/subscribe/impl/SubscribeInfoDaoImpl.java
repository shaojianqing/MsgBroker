package msg.broker.dao.subscribe.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import msg.broker.domain.base.Base;
import msg.broker.util.AssertUtil;
import msg.broker.util.GUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;

import msg.broker.dao.subscribe.ISubscribeInfoDao;
import msg.broker.domain.subscribe.SubscribeInfo;

public class SubscribeInfoDaoImpl implements ISubscribeInfoDao {
	
	private SqlSessionTemplate sqlSessionTemplate;
	
	public SubscribeInfo getSubscribeInfoById(String id) throws Exception {

		AssertUtil.isNotBlank(id, "subscribeInfoId不能为空!");

		return sqlSessionTemplate.selectOne("msg.broker.domain.subscribe.SubscribeInfo.getSubscribeInfoById", id);
	}

	public SubscribeInfo getSubscribeInfoByIdForUpdate(String id) throws Exception {

		AssertUtil.isNotBlank(id, "subscribeInfoId不能为空!");

		return sqlSessionTemplate.selectOne("msg.broker.domain.subscribe.SubscribeInfo.getSubscribeInfoByIdForUpdate", id);
	}

	public List<SubscribeInfo> querySubscribeInfo(String topic, String event) throws Exception {

		AssertUtil.isNotBlank(topic, "订阅关系topicCode不能为空!");
		AssertUtil.isNotBlank(event, "订阅关系eventCode不能为空!");

		Map<String, String> param = new HashMap<String, String>();
		param.put("topic", topic);
		param.put("event", event);
		return sqlSessionTemplate.selectList("msg.broker.domain.subscribe.SubscribeInfo.querySubscribeInfo", param);
	}

	public SubscribeInfo querySubscribeInfoOne(String groupId, String topic, String event) throws Exception {

		AssertUtil.isNotBlank(groupId, "订阅关系groupId不能为空!");
		AssertUtil.isNotBlank(topic, "订阅关系topicCode不能为空!");
		AssertUtil.isNotBlank(event, "订阅关系eventCode不能为空!");

		Map<String, String> param = new HashMap<String, String>();
		param.put("groupId", groupId);
		param.put("topic", topic);
		param.put("event", event);

		return sqlSessionTemplate.selectOne("msg.broker.domain.subscribe.SubscribeInfo.querySubscribeInfoOne", param);
	}

	public int updateSubscribeInfo(SubscribeInfo subscribeInfo) throws Exception{

		AssertUtil.isNotNull(subscribeInfo, "订阅关系subscribeInfo不能为空!");

		return sqlSessionTemplate.update("msg.broker.domain.subscribe.SubscribeInfo.updateMessageRelationStatus", subscribeInfo);
	}

	public int saveSubscribeInfo(SubscribeInfo subscribeInfo) throws Exception {

		AssertUtil.isNotNull(subscribeInfo, "订阅关系subscribeInfo不能为空!");

		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		subscribeInfo.setId(GUIDUtil.generateUUID());
		subscribeInfo.setIsValid(Base.TRUE);
		subscribeInfo.setCreateTime(currentTime);
		subscribeInfo.setOperateTime(currentTime);

		return sqlSessionTemplate.update("msg.broker.domain.subscribe.SubscribeInfo.saveSubscribeInfo", subscribeInfo);
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
}
