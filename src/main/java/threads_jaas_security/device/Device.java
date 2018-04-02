package threads_jaas_security.device;

import java.security.Principal;

public abstract class Device extends Thread implements Principal {

	protected void speakOutRandom(String name) {
		System.out.println(name);
		System.out.println("______"+name + "(speakOutRandom)");
		for (int i = 0; i < 50; i++) {
			System.out.println(name + "(speakOutRandom)");

		}
	}

	protected void speakOut(String name) {
		System.out.println(name + " Priority : "+this.getPriority());

	}

	protected void speakOut(int priority) {
		System.out.println("priority"+priority);

	}

}
