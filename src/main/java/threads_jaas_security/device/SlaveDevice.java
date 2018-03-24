package threads_jaas_security.device;

import threads_jaas_security.ScenarioMain;

public class SlaveDevice extends Device {

	@Override
	public void run() {
		speakOut(this.getName());
		while (!ScenarioMain.endTimeout) {
			makeAStain();
		}
		System.err.println("Thread " + this.getName() + " exiting.");
	}

	private void makeAStain() {
		speakOut(this.getName());
		
	}

}
