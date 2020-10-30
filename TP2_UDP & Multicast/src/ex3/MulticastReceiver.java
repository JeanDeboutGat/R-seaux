package ex3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.HashMap;



/**
 * A receiver of multicast transmissions using UDP
 * protocol and threads
 * @author Thomas Liard
 *
 */
public class MulticastReceiver extends Thread {
	protected MulticastSocket socket = null;
	protected InetAddress group = null;
	protected HashMap<String, String> names;

	

	public MulticastReceiver(String address, int port) throws IOException {
		super();
		this.socket = new MulticastSocket(port);
		this.group = InetAddress.getByName(address);
		this.names = new HashMap<String, String>();
		this.names.put(InetAddress.getLocalHost().getHostAddress().toString(), "Me");
		
	}



	public void run(){
		byte[] msg;
		DatagramPacket pack;
		
		
		try {
			socket.joinGroup(this.group);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(true){
			msg = new byte[1024];
			pack = new DatagramPacket(msg, msg.length);
			try {
				socket.receive(pack);
				if(!this.names.containsKey(pack.getAddress().toString())) {
					this.names.put(pack.getAddress().toString(), NameGenerator.generateName());
				}
				System.out.print(this.names.get(pack.getAddress().toString()) + " says : ");
				System.out.println(new String(pack.getData()));
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
		
	}
}
