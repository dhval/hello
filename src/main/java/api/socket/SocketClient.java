package api.socket;


import java.net.*;
/* The java.io package contains the basics needed for IO operations. */
import java.io.*;
/** The SocketClient class is a simple example of a TCP/IP Socket Client.
 *
 */

public class SocketClient {
	private final static String USER_NAME = System.getProperty("user.name");
	private final static String DEFAULT_HOST = "localhost";
	private final static String DEFAULT_PORT = "6008";
	private final static char EOF_CHAR = (char) 13;

	public static void sendMessage(Socket connection, String message) {
		BufferedOutputStream bos;
		try {
			bos = new BufferedOutputStream(connection.getOutputStream());
			OutputStreamWriter osw = new OutputStreamWriter(bos, "US-ASCII");
			osw.write(message + EOF_CHAR);
			System.out.println("Sending Message:- " + message);
			osw.flush();
		} catch (IOException e) {
			System.out.println("IOException: " + e);
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static void displayServerResponce(Socket connection) {
		BufferedInputStream bis;
		try {
			System.out.println("Waiting for Server Response.");
			bis = new BufferedInputStream(connection.getInputStream());
			InputStreamReader isr = new InputStreamReader(bis, "US-ASCII");
			BufferedReader br = new BufferedReader(isr);
			StringBuffer response = new StringBuffer();
			int ch, cnt = 0, max = 100;
			while (((ch = br.read()) != EOF_CHAR) && (++cnt <= max)) {
				response.append((char) ch);
			}
			System.out.println("Server Response: " + response);
		} catch (IOException e) {
			System.out.println("IOException: " + e);
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static void main(String[] args) {
		/** Define a host server */
		String host = DEFAULT_HOST;
		/** Define a port */
		int port = 0;

		if (args.length == 2) {
			host = args[0];
			try {
				port = Integer.parseInt(args[1]);
			}
			catch (NumberFormatException ne) {
				System.out.println("Invalid Non Numeric Port Using default" + ne);
				port = Integer.parseInt(DEFAULT_PORT);
			}

		}
		else {
			System.out.println("Using default server and port");
			port = Integer.parseInt(DEFAULT_PORT);
		}


		String TimeStamp;
		System.out.println("SocketClient initialized");
		Socket connection = null;
		try {
			InetAddress address = InetAddress.getByName(host);
			TimeStamp = new java.util.Date().toString();
			System.out.println(TimeStamp);
			System.out.println("Welcome:- " + USER_NAME + " you are connected.");
			boolean cont = true;
			do {
				System.out.println("Enter your command, alternatively enter <bye> to exit.");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String command = br.readLine();
				if (!command.equalsIgnoreCase("bye"))
				{
					connection = new Socket(address, port);
					sendMessage(connection, command);
					displayServerResponce(connection);
					connection.close();
				}
				else
					cont = false;
			}
			while(cont);
			System.out.println("Thanks for using " + USER_NAME + " bbye ...");
		}
		catch (MalformedURLException e) {
			System.out.println("MalformedURLException: "+ e);
		}
		catch (IOException f) {
			System.out.println("IOException: " + f);
		}
		catch (Exception g) {
			System.out.println("Exception: " + g);
		}
	}
}
