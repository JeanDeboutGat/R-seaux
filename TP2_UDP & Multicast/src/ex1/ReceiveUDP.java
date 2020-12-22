package ex1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
/**
 * A UDP UDP receiving message
 * @author Thomas Liard
 *
 */

public class ReceiveUDP {
	
	public static void main(String[] args) throws SocketException, IOException{
		DatagramSocket s;
		DatagramPacket p = null;
		String msg;
		
		s = new DatagramSocket(Integer.parseInt(args[0]));
		
		try {
			p = new DatagramPacket(new byte[512], 512);
		} catch (Exception e) {
			e.printStackTrace();
		}
		s.receive(p);
		
		msg = new String(p.getData());
		System.out.println(msg);
		s.close();
	}

}
