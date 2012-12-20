
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
		
		// 서버로 보낼 소켓 열기
		this.responseSocket = new Socket(SBroker.serverIp, SBroker.serverPort);
		this.output = new DataOutputStream(responseSocket.getOutputStream());
		this.input = new DataInputStream(responseSocket.getInputStream());		
	}

	/*
	 * Message Spec을 준수하여 메시지를 송수신한다.
	 * Client <-> Broker <-> Server
	 */
	public void execute() throws IOException {
		
		// 클라이언트로 온 데이터를 10바이트 만큼 자른다.
		byte[] buf = new byte[10];
		in.readFully(buf);

		// 데이터 길이 필드
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
	
	// 모든 스트림 객체와 소켓 객체를 닫아준다.
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
