
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import lempel.blueprint.base.concurrent.WorkerGroup;

public class SocketAcceptor extends Thread {

	private final ServerSocket serverSocket;
	private final WorkerGroup workerGrp;

	/**
	 * @param addr
	 * @param port
	 * @param workCount
	 * @throws IOException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * 
	 */
	public SocketAcceptor(final String addr, final int port, final int workCount) throws IOException, 
			IllegalArgumentException, SecurityException, InstantiationException, 
			IllegalAccessException, InvocationTargetException, NoSuchMethodException { 
		serverSocket = new ServerSocket();
		serverSocket.setReuseAddress(true);
		serverSocket.bind(new InetSocketAddress(InetAddress.getByName(addr),
				port), 20);
		workerGrp = new WorkerGroup(SBrokerWorker.class, workCount);
	}

	/*
	 * ���������� ��ٸ��ٰ� Ŭ���̾�Ʈ�� ���� ��ü�� ������
	 * SBrokerSession Ŭ������ ��ü client�� ������ ���� ��
	 * WorkerGroup�� �߰��Ѵ�.
	 */
	@Override
	public void run() {
		while (true) {
			try {
				Socket sock = serverSocket.accept();
				SBrokerSession client = new SBrokerSession(sock);
				workerGrp.addJob(client);

			} catch (IOException ignored) {
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}

}