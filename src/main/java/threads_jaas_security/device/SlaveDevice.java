package threads_jaas_security.device;

public class SlaveDevice extends Device {

	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();
		    System.out.println("Hello  SlaveDevice " + threadName);
			speakOut();

	}





}
