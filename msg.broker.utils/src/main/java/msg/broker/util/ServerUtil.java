package msg.broker.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import msg.broker.callback.RequestProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerUtil {

	private static final Logger logger = LoggerFactory.getLogger(ServerUtil.class);

	@SuppressWarnings("resource")
	public static void accept(int port, final RequestProcessor callBack) {
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			while(true) {
				final Socket socket = serverSocket.accept();
				ThreadUtil.run(new Runnable() {

					private byte[] buffer = new byte[4096];
					
					public void run() {
						try {
							while(true) {
								InputStream is = socket.getInputStream();
								int n = is.read(buffer);
								byte[] request = copyByte(buffer, n);

								byte[] response = callBack.onService(request);

								OutputStream os = socket.getOutputStream();
								os.write(response);
								os.flush();
							}
						} catch(Exception e) {
							logger.error("Accept Client Connection Raise Exception!", e);
							try {
								if (socket != null) {
									InputStream is = socket.getInputStream();
									OutputStream os = socket.getOutputStream();
									is.close();
									os.close();
									socket.close();
								}
							} catch (Exception ex) {
								logger.error("Close Client Connection Raise Exception!", ex);
							}
						}
					}
				});
			}
		} catch(Exception e) {
			logger.error("Establish Server Listener Raise Exception!", e);
		}
	}

	private static byte[] copyByte(byte[] buffer, int n) {
		byte[] dest = new byte[n];
		for(int i=0;i<n;++i) {
			dest[i] = buffer[i];
		}
		return dest;
	}
}