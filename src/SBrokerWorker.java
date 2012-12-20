
import java.io.IOException;

import lempel.blueprint.base.concurrent.JobQueue;
import lempel.blueprint.base.concurrent.Worker;

public class SBrokerWorker extends Worker<SBrokerSession> {


	public SBrokerWorker(final JobQueue<SBrokerSession> jobQueue) {
		super(jobQueue);
	}

	/*
	 *  Worker<SBrokerSession>의 추상클래스
	 *  Process(T clientObject) 메서드를 오버라이딩 한다.
	 *  이 메서드는 WorkerGroup 클래스에서
	 *  SBrokerSession클래스의 객체 client가 추가되면 실행되는 메서드이다.
	 */
	@Override
	protected void process (final SBrokerSession client)   {
		try {
			client.execute();
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}


}