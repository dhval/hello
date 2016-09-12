package api.socket;



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MultipleSocketServerThread implements Runnable {
	private final static char EOF_CHAR = (char) 13;
	public MultipleSocketServerThread(Socket connection) {
		super();
		this.connection = connection;
	}

	private Socket connection;

	public void run() {
		try {
			String TimeStamp = new java.util.Date().toString();
			String returnCode = "MultipleSocketServer repsonded at " + TimeStamp + EOF_CHAR;
			BufferedInputStream is = new BufferedInputStream(connection.getInputStream());
			InputStreamReader isr = new InputStreamReader(is);
			StringBuffer process = new StringBuffer();
			BufferedReader br = new BufferedReader(isr);
			int ch, cnt = 0, max = 100;
			while (((ch = br.read()) != EOF_CHAR) && (++cnt <= max)) {
				process.append((char) ch);
			}
			if (!process.toString().isEmpty()) {
				// execute command 
				System.out.println( "Executing command:- " + process);
				try {
					new CmdExec().execCmd(process.toString());
					returnCode = "Success: " + returnCode;
				}
				catch (Exception ie) {
					returnCode = "Failed: " + returnCode;
					ie.printStackTrace();
				}
			}
			else {
				System.out.println( "Greetings: " + " no command provided ?");
			}
			try {
				Thread.sleep(10000);
			}
			catch (Exception e) {
				System.out.println("Exception: " + e);
				e.printStackTrace();
			}
			System.out.println("Sending Response " + returnCode);
			BufferedOutputStream os = new BufferedOutputStream(connection.getOutputStream());
			OutputStreamWriter osw = new OutputStreamWriter(os, "US-ASCII");
			osw.write(returnCode);
			osw.flush();
		}
		catch (Exception e) {
			System.out.println("Exception: " + e);
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
