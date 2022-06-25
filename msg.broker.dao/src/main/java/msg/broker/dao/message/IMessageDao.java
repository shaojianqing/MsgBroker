package msg.broker.dao.message;

import msg.broker.domain.message.Message;

public interface IMessageDao {
	
	public Message getMessageById(String id) throws Exception;
	
	public Message getMessageByIdForUpdate(String id) throws Exception;
	
	public int saveMessage(Message message) throws Exception;
}