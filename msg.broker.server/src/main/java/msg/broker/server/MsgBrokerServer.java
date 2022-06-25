package msg.broker.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import msg.broker.processor.IMsgProcessor;

public class MsgBrokerServer {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		String contextFilepath = "classpath:config/applicationContext.xml";
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(contextFilepath);
		IMsgProcessor msgProcessor = (IMsgProcessor)applicationContext.getBean("msgProcessor");
		msgProcessor.startMessageService();
	}
}
