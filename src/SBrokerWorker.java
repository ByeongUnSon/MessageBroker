
import java.io.IOException;

import lempel.blueprint.base.concurrent.JobQueue;
import lempel.blueprint.base.concurrent.Worker;

public class SBrokerWorker extends Worker<SBrokerSession> {


	public SBrokerWorker(final JobQueue<SBrokerSession> jobQueue) {
		super(jobQueue);
	}

	/*
	 *  Worker<SBrokerSession>�� �߻�Ŭ����
	 *  Process(T clientObject) �޼��带 �������̵� �Ѵ�.
	 *  �� �޼���� WorkerGroup Ŭ��������
	 *  SBrokerSessionŬ������ ��ü client�� �߰��Ǹ� ����Ǵ� �޼����̴�.
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