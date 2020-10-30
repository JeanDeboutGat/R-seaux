package ex3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * A UDP Multicaster using threads
 * @author , Jean Debout Gatari
 *
 */
public class MulticastSender extends Thread {
	protected MulticastSocket socket = null;
	protected InetAddress group = null;
	protected BufferedReader buffer;

	

	public MulticastSender(String address, int port) throws IOException {
		super();
		this.socket = new MulticastSocket(port);
		this.group = InetAddress.getByName(address);
		this.buffer = new BufferedReader(new InputStreamReader(System.in));
	}
	
	
	public void run(){
		String msg;
		DatagramPacket pack;
		while(true){
			try {
				msg = buffer.readLine();
				pack = new DatagramPacket(msg.getBytes(), msg.getBytes().length, this.group, 7654);
				socket.send(pack);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
