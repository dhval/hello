package api.socket;


import java.io.*;
public class CmdExec {

	public void execWinCmd(String cmd) {
		try {
			String line;
			Process p = Runtime.getRuntime().exec(cmd);
			BufferedReader input = new BufferedReader
				(new InputStreamReader(p.getInputStream()));
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}
			input.close();
		}
		catch (Exception err) {
			err.printStackTrace();
		}
	}

	public void execLinuxCmd(String cmd) {
	File wd = new File("/bin"); 
	System.out.println(wd); 
	Process proc = null; 
	try { 
		// run an instance of the shell and send it commands via the Process's OutputStream 
	   proc = Runtime.getRuntime().exec("/bin/bash", null, wd); 
	} 
	catch (IOException e) { 
	   e.printStackTrace(); 
	} 
	if (proc != null) { 
	   BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream())); 
	   PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(proc.getOutputStream())), true); 
	   out.println(cmd); 
	 //  out.println(cmd2); if we had another command etc. 
	   out.println("exit"); // need this else it would get hanged
	   // the whole process here is like opening shell and executing it.
	   // thus you need to exit it also :).
	   try { 
	      String line;
	      int cnt = 0, max = 100; 
	      System.out.println("Printing Command O/P, very long o/p would be truncated."); 
	      while ((line = in.readLine()) != null && (++cnt <= max)) { 
	         System.out.println(line); 
	      } 
	      proc.waitFor(); 
	      in.close(); 
	      out.close(); 
	      proc.destroy(); 
	      System.out.println("Command executed successfully."); 
	   } 
	   catch (Exception e) { 
	      e.printStackTrace(); 
	   } 
	}}

	public void execCmd(String cmd) {
		System.out.println("our OS is:- " + System.getProperty("os.name"));
		if (System.getProperty("os.name").equals("Windows Vista")) {
			execWinCmd("cmd.exe /c " + cmd);
		}
		else {
			execLinuxCmd(cmd);
		}
	}

}
