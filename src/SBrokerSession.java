import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SBrokerSession {

	private Socket requestSocket;
	private Socket responseSocket;
	private DataInputStream in;
	private DataOutputStream out;
	private DataInputStream input;
	private DataOutputStream output;
		
	/*
	 * 클라이언트로 온 소켓객체를 받아서 스트림 객체를 생성하고
	 * 서버쪽으로 보낼 소켓을 열어서 스트림 객체를 생성한다.
	 */
	public SBrokerSession(Socket requestSocket) throws IOException {
		this.requestSocket = requestSocket;
		this.in = new DataInputStream(requestSocket.getInputStream());
		this.out = new DataOutputStream(requestSocket.getOutputStream());
		
		// ������ ���� ���� ����
		this.responseSocket = new Socket(SBroker.serverIp, SBroker.serverPort);
		this.output = new DataOutputStream(responseSocket.getOutputStream());
		this.input = new DataInputStream(responseSocket.getInputStream());		
	}

	/*
	 * Message Spec�� �ؼ��Ͽ� �޽����� �ۼ����Ѵ�.
	 * Client <-> Broker <-> Server
	 */
	public void execute() throws IOException {
		
		// Ŭ���̾�Ʈ�� �� �����͸� 10����Ʈ ��ŭ �ڸ���.
		byte[] buf = new byte[10];
		in.readFully(buf);

		// ������ ���� �ʵ�
		String dataStr = new String(buf);
		System.out.println("dataLength : " + dataStr);
		
		int dataSize = 0;
		dataSize = Integer.parseInt(dataStr.trim()) - 10;
		System.out.println("dataSize : " + dataSize);

		byte[] body = new byte[dataSize + buf.length];
		System.arraycopy(buf, 0, body, 0, buf.length);

		in.readFully(body, buf.length, dataSize);
		System.out.println("Data : " + body);

		/*
		 * Send To Server And Receive From Server And Send To Client Again
		 */

		// Send Data in Client To Server
		output.write(body);
		output.flush();

		// Read Receive Data From Server Again
		input.read(body);

		// Send Data To Client Again
		out.write(body);
		out.flush();
		
	}
	
	// ���� ��Ʈ�� ��ü�� ���� ��ü�� �ݾ��ش�.
	public void close() {

		try {
			if (in != null) {
				in.close();
			}
		} catch (IOException ignored) {
		} finally {
			in = null;
		}
		
		try {
			if (out != null) {
				out.close();
			}
		} catch (IOException ignored) {
		} finally {
			out = null;
		}
		
		try {
			if (input != null) {
				input.close();
			}
		} catch (IOException ignored) {			
		} finally {
			input = null;
		}
		
		try {
			if (output != null) {
				output.close();
			}
		} catch (IOException ignored) {
		} finally {
			output = null;
		}
		
		try {
			if (responseSocket != null) {
				responseSocket.close();
			}
		} catch (IOException ignored) {
		} finally {
			responseSocket = null;
		}
		
		try {
			if (requestSocket != null) {
				requestSocket.close();
			}
		} catch (IOException ignored) {
		} finally {
			requestSocket = null;
		}

	
	}

}
