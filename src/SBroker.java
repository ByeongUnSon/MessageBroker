import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.util.Scanner;

public class SBroker {

	// Target Server IP/Port
	public static String serverIp = "";
	public static int serverPort = -1;
	/**
	 * 
	 * @param args
	 *            <host name> <port> <target host> <port>
	 */
	public static void main(String[] args) {
		SocketAcceptor sockAthread;
		Socket socket = null;
		try {

			System.out.println("##### Message Broker Program #####");
			if (args.length != 4) {
				System.err
						.println("java Server <host name> <port> <target host> <port>");
				System.exit(1);
			}

			String host = args[0];
			int port = -1;

			try {
				port = Integer.parseInt(args[1]);
			} catch (NumberFormatException e) {
				System.err
						.println("java Server <host name> <port> <target host> <port>");
				System.exit(2);
			}

			serverIp = args[2];

			try {
				serverPort = Integer.parseInt(args[3]);
			} catch (NumberFormatException e) {
				System.err
						.println("java Server <host name> <port> <target host> <port>");
				System.exit(3);
			}

			try {
				Scanner sc = new Scanner(System.in);
				System.out.print("Input workCount Setting: ");
				int workCount = sc.nextInt();
				// 클라이언트의 소켓 객체를 받아오는 쓰레드를 가동한다.
				sockAthread = new SocketAcceptor(host, port, workCount);
				sockAthread.start();

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}  finally {
					socket = null;
				}
			}
		}
	}

}