package ex1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * A UDP send message
 * @author Thomas Liard et Jean Debout Gatari
 *
 */
public class SendUDP {


	public static void main(String[] args) throws UnknownHostException, SocketException, IOException {
		DatagramSocket s;
		DatagramPacket p;
		
		byte[] array;
		String machineName = args[0];
		InetAddress dst  = InetAddress.getByName(machineName);
		
		int port = Integer.parseInt(args[1]);
		String msg = args[2];
		
		p = new DatagramPacket(msg.getBytes(), msg.length(), dst, port);
		s = new DatagramSocket();
		
		s.send(p);
		s.close();
	}

}
