package msg.broker.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NetworkUtil {

	private static final Logger logger = LoggerFactory.getLogger(ServerUtil.class);

	private static Map<String, Socket> connectionPool = new ConcurrentHashMap<String, Socket>();

	private static byte[] buffer = new byte[4096];

	public static byte[] sendRequest(String serverIp, int port, byte[] data) throws Exception {

		Socket socket = null;
		String address = String.format("%s:%d", serverIp, port);
		try {
			if (!connectionPool.containsKey(address)) {
				Socket socketInstance = new Socket(serverIp, port);
				socketInstance.setSoTimeout(0);
				socketInstance.setKeepAlive(false);
				connectionPool.put(address, socketInstance);
			}

			socket = connectionPool.get(address);
			OutputStream os = socket.getOutputStream();
			os.write(data);
			os.flush();

			InputStream is = socket.getInputStream();

			synchronized (buffer) {
				int n = is.read(buffer);
				return copyByte(buffer, n);
			}
		} catch (Exception e) {
			logger.error("Connect Server Raise Exception!", e);
			try {
				if (socket!=null) {
					OutputStream os = socket.getOutputStream();
					InputStream is = socket.getInputStream();

					os.close();
					is.close();
					socket.close();
					connectionPool.remove(address);
				}
			} catch (Exception ex) {
				logger.error("Close Server Connection Raise Exception!", e);
			}

			throw e;
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
