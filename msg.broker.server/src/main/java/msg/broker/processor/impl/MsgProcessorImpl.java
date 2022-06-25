package msg.broker.processor.impl;

import msg.broker.event.*;
import msg.broker.util.ThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import msg.broker.callback.RequestProcessor;
import msg.broker.processor.IMsgProcessor;
import msg.broker.service.IMessageService;
import msg.broker.util.ProtocolUtil;
import msg.broker.util.ServerUtil;

public class MsgProcessorImpl implements IMsgProcessor {
	
	private static final Logger logger = LoggerFactory.getLogger(MsgProcessorImpl.class);
	
	private IMessageService messageService;
	
	private int serverPort;
	
	private int subscribePort;

	private int confirmPort;
	
	public void startMessageService() {
		
		logger.info("Start the message service!!");

		ThreadUtil.run(new Runnable() {
			public void run() {
				//Start Message Listener Service~
				ServerUtil.accept(serverPort, new RequestProcessor(){

					public byte[] onService(byte[] content) throws Exception {

						MessageResult messageResult;
						try {
							MessageData messageData = (MessageData)ProtocolUtil.transToObject(content);
							boolean result = messageService.restoreMessage(messageData);
							if (result) {
								messageResult = new MessageResult(true, "Success");
							} else {
								messageResult = new MessageResult(false, "Failure");
							}
						} catch (Exception e) {
							messageResult = new MessageResult(false, e.getMessage());
							logger.error("发送消息发生异常!", e);
						}
						return ProtocolUtil.transToByte(messageResult);
					}
				});
			}
		});


		ThreadUtil.run(new Runnable() {
			public void run() {
				//Start Message Subscribe Service~
				ServerUtil.accept(subscribePort, new RequestProcessor(){

					public byte[] onService(byte[] content) throws Exception {

						MessageResult messageResult;
						try {
							SubscribeRequest SubscribeRequest = (SubscribeRequest)ProtocolUtil.transToObject(content);
							MessageData messageData = messageService.queryMessageDataBySubscribe(SubscribeRequest);
							messageResult = new MessageResult(true, messageData, "Success");
						} catch (Exception e) {
							messageResult = new MessageResult(false, e.getMessage());
							logger.error("接受消息发生异常!", e);
						}

						return ProtocolUtil.transToByte(messageResult);
					}
				});
			}
		});

		ThreadUtil.run(new Runnable() {
			public void run() {
				//Start Message Confirm Service~
				ServerUtil.accept(confirmPort, new RequestProcessor() {

					public byte[] onService(byte[] content) throws Exception {

						MessageResult messageResult = null;
						try {

							MessageContext messageContext = (MessageContext) ProtocolUtil.transToObject(content);
							boolean result = messageService.confirmMessage(messageContext);
							if (result) {
								messageResult = new MessageResult(true, "Success");
							} else {
								messageResult = new MessageResult(false, "Failure");
							}
						} catch (Exception e) {
							messageResult = new MessageResult(false, e.getMessage());
							logger.error("确认消息发生异常!", e);
						}

						return ProtocolUtil.transToByte(messageResult);
					}
				});
			}
		});
	}

	public void setMessageService(IMessageService messageService) {
		this.messageService = messageService;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public void setSubscribePort(int subscribePort) {
		this.subscribePort = subscribePort;
	}

	public void setConfirmPort(int confirmPort) {
		this.confirmPort = confirmPort;
	}
}