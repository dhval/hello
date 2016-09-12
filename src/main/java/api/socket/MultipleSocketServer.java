package api.socket;


import java.io.IOException;
import java.net.*;

public class MultipleSocketServer {
	private final static int DEFAULT_PORT = 6008;
	static ServerSocket socket1 = null;

	public static void main(String[] args) {
		try {
			ServerSocket socket1 = new ServerSocket(DEFAULT_PORT);
			System.out.println("MultipleSocketServer Initialized");
			while (true) {
				Socket connection = socket1.accept();
				System.out.println(" Client SocketAddress:- " + connection.getRemoteSocketAddress());
				Runnable runnable = new MultipleSocketServerThread(connection);
				Thread thread = new Thread(runnable);
				thread.start();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (socket1 != null)
				try {
					socket1.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}

