package threads_jaas_security.device;

public abstract class Device extends Thread {

	protected void speakOutRandom(String name) {
		System.out.println(name);
		for (int i = 0; i < 5; i++) {
			System.out.println(name + "(speakOutRandom)");

		}
	}

	protected void speakOut(String name) {
		System.out.println(name);

	}

	protected void speakOut(int priority) {
		System.out.println("priority"+priority);

	}

}
