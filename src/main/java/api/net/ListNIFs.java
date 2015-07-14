package api.net;

import java.net.*;
import java.util.*;
import static java.lang.System.out;

public class ListNIFs 
{
    public static void main(String args[]) throws SocketException, UnknownHostException {
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        
        for (NetworkInterface netIf : Collections.list(nets)) {
            out.printf("Display name: %s\n", netIf.getDisplayName());
            out.printf("Name: %s\n", netIf.getName());
            Enumeration<InetAddress> inetAddresses = netIf.getInetAddresses();
            for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                String ipAddress = inetAddress.getHostAddress();
                out.printf("InetAddress: %s\n", ipAddress);
                if (ipAddress.matches("^(192|127).\\d{1,3}.\\d{1,3}.\\d{1,3}$")) {
                    out.printf("Match!");
                }
            }
            displaySubInterfaces(netIf);
            out.printf("\n");
        }
        
        String ipAddress = InetAddress.getLocalHost().getHostAddress();
        out.printf("IP Address %s\n",  ipAddress);
    }

    static void displaySubInterfaces(NetworkInterface netIf) throws SocketException {
        Enumeration<NetworkInterface> subIfs = netIf.getSubInterfaces();
        
        for (NetworkInterface subIf : Collections.list(subIfs)) {
            out.printf("\tSub Interface Display name: %s\n", subIf.getDisplayName());
            out.printf("\tSub Interface Name: %s\n", subIf.getName());
        }
     }
}  