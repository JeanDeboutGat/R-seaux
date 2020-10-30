package ex2;

import java.net.*;
//import java.io.*;
/**
 * A sender packets to the opening connexion UDP Multicast 
 * @author Jean Debout Gatari
 *
 */

public class MultiSendUDP {
	
	public static void main (String[] args) throws Exception
	{
		DatagramPacket p;
		DatagramSocket s;
		int port = 7654;
		String message = args[0];
		InetAddress dst = InetAddress.getByName("224.0.0.1");
		p = new DatagramPacket (message.getBytes(),message.length() , dst, port);
		s = new DatagramSocket();
		s.send(p);
		s.close();
	}

}
