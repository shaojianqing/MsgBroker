package msg.broker.service.impl;

import java.util.List;

import msg.broker.dao.config.IMessageConfigDao;
import msg.broker.domain.config.MessageConfig;
import msg.broker.event.MessageContext;
import msg.broker.event.SubscribeRequest;
import msg.broker.util.AssertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import msg.broker.dao.message.IMessageDao;
import msg.broker.dao.relation.IMessageRelationDao;
import msg.broker.dao.subscribe.ISubscribeInfoDao;
import msg.broker.domain.message.Message;
import msg.broker.domain.relation.MessageRelation;
import msg.broker.domain.relation.MsgRelationStatusEnum;
import msg.broker.domain.subscribe.SubscribeInfo;
import msg.broker.event.MessageData;
import msg.broker.service.IMessageService;

public class MessageServiceImpl implements IMessageService {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);
	
	private TransactionTemplate transactionTemplate;
	
	private IMessageDao messageDao;

	private IMessageConfigDao messageConfigDao;
	
	private IMessageRelationDao messageRelationDao;
	
	private ISubscribeInfoDao subscribeInfoDao;

	@Transactional
	public boolean restoreMessage(final MessageData messageData) throws Exception {

		MessageConfig messageConfig = messageConfigDao.queryMessageConfigByTopicAndEvent(messageData.getTopic(), messageData.getEvent());
		AssertUtil.isNotNull(messageConfig, String.format("发送的消息类型不存在:%s", messageData.toString()));

		Message message = new Message();
		message.setTopicCode(messageData.getTopic());
		message.setEventCode(messageData.getEvent());
		message.setMessageBody(messageData.getData());
		message.setClassName(messageData.getClazz());

		messageDao.saveMessage(message);

		logger.info("Save Message:", message);

		List<SubscribeInfo> subscribeInfoList = subscribeInfoDao.querySubscribeInfo(messageData.getTopic(), messageData.getEvent());
		if (subscribeInfoList!=null) {
			for (SubscribeInfo subscribeInfo:subscribeInfoList) {
				MessageRelation messageRelation = new MessageRelation();
				messageRelation.setGroupId(subscribeInfo.getGroupId());
				messageRelation.setTopicCode(subscribeInfo.getTopicCode());
				messageRelation.setEventCode(subscribeInfo.getEventCode());
				messageRelation.setMessageId(message.getId());
				messageRelation.setSubscribeInfoId(subscribeInfo.getId());
				messageRelation.setStatus(MsgRelationStatusEnum.INIT.getStatus());
				messageRelationDao.saveMessageRelation(messageRelation);

				logger.info("Save MessageRelation:", messageRelation);
			}
		}
		
		return false;
	}

	public MessageData queryMessageDataBySubscribe(SubscribeRequest subscribeRequest) throws Exception {

		AssertUtil.isNotNull(subscribeRequest, "subscribeRequest不能为空!");

		SubscribeInfo subscribeInfo = subscribeInfoDao.querySubscribeInfoOne(subscribeRequest.getGroupId(),
				subscribeRequest.getTopicCode(), subscribeRequest.getEventCode());
		AssertUtil.isNotNull(subscribeInfo, String.format("查无此订阅关系:%s", subscribeRequest));

		MessageRelation messageRelation = messageRelationDao.queryMessageRelationOne(subscribeInfo.getId(), subscribeInfo.getTopicCode(),
				subscribeInfo.getEventCode(), MsgRelationStatusEnum.INIT);
		AssertUtil.isNotNull(messageRelation, String.format("查无此消息关系记录:%s", subscribeInfo));

		Message message = messageDao.getMessageById(messageRelation.getMessageId());
		AssertUtil.isNotNull(message, String.format("查无此消息记录:%s", messageRelation));

		MessageData messageData = new MessageData();
		messageData.setTopic(message.getTopicCode());
		messageData.setEvent(message.getEventCode());
		messageData.setClazz(message.getClassName());
		messageData.setData(message.getMessageBody());
		messageData.setMessageRelationId(messageRelation.getId());

		return messageData;
	}

	@Transactional
	public boolean confirmMessage(MessageContext messageContext) throws Exception {

		AssertUtil.isNotNull(messageContext, "messageContext不能为空!");

		MessageRelation messageRelation = messageRelationDao.getMessageRelationById(messageContext.getMessageRelationId());
		AssertUtil.isNotNull(messageRelation, String.format("查无此消息关系记录:%s", messageContext.getMessageRelationId()));

		if (messageContext.isSuccess()) {
			messageRelationDao.updateMessageRelationStatus(messageRelation.getId(),
					MsgRelationStatusEnum.INIT, MsgRelationStatusEnum.SUCCESS);
		} else if (!messageContext.isRetry()) {
			messageRelationDao.updateMessageRelationStatus(messageRelation.getId(),
					MsgRelationStatusEnum.INIT, MsgRelationStatusEnum.FAILED);
		}
		return true;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public void setMessageDao(IMessageDao messageDao) {
		this.messageDao = messageDao;
	}

	public void setMessageConfigDao(IMessageConfigDao messageConfigDao) {
		this.messageConfigDao = messageConfigDao;
	}

	public void setMessageRelationDao(IMessageRelationDao messageRelationDao) {
		this.messageRelationDao = messageRelationDao;
	}

	public void setSubscribeInfoDao(ISubscribeInfoDao subscribeInfoDao) {
		this.subscribeInfoDao = subscribeInfoDao;
	}
}
