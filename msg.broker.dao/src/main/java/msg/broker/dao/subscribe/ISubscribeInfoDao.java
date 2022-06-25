package msg.broker.dao.subscribe;

import java.util.List;

import msg.broker.domain.subscribe.SubscribeInfo;

public interface ISubscribeInfoDao {
	
	public SubscribeInfo getSubscribeInfoById(String id) throws Exception;
	
	public SubscribeInfo getSubscribeInfoByIdForUpdate(String id) throws Exception;

	public List<SubscribeInfo> querySubscribeInfo(String topic, String event) throws Exception;
	
	public SubscribeInfo querySubscribeInfoOne(String group, String topic, String event) throws Exception;
	
	public int updateSubscribeInfo(SubscribeInfo subscribeInfo) throws Exception;
	
	public int saveSubscribeInfo(SubscribeInfo subscribeInfo) throws Exception;
}
