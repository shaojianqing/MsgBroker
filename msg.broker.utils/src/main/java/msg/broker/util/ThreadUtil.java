package msg.broker.util;

public class ThreadUtil {
	
	public static void run(Runnable runnable) {
		Thread thread = new Thread(runnable);
		thread.start();
	}
}
