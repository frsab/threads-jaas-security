package threads_jaas_security.device;

public abstract class Device implements Runnable {
	protected void speakOut() {
		System.out.println(this.getClass().toString());
		
	}

}
