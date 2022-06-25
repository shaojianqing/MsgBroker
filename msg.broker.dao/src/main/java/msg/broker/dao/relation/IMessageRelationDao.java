package msg.broker.dao.relation;

import msg.broker.domain.relation.MessageRelation;
import msg.broker.domain.relation.MsgRelationStatusEnum;

public interface IMessageRelationDao {
	
	public MessageRelation getMessageRelationById(String id) throws Exception;
	
	public MessageRelation getMessageRelationByIdForUpdate(String id) throws Exception;

	public MessageRelation queryMessageRelationOne(String subscribeInfoId, String topic, String event, MsgRelationStatusEnum status) throws Exception;
	
	public int updateMessageRelationStatus(String messageId, MsgRelationStatusEnum oldStatus, MsgRelationStatusEnum newStatus) throws Exception;
	
	public int saveMessageRelation(MessageRelation relation) throws Exception;
}