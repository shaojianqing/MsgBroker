package msg.broker.domain.relation;

public enum MsgRelationStatusEnum {
	
	INIT("init", "初始"), 
	FAILED("fail", "失败"),
	SUCCESS("success", "成功");

	private String status;
	
	private String desc;

	private MsgRelationStatusEnum(String status, String desc) {
		this.status = status;
		this.desc = desc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
