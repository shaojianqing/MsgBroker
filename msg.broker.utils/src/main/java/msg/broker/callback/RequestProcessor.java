package msg.broker.callback;

public interface RequestProcessor {
	
	public byte[] onService(byte[] content) throws Exception;
}
