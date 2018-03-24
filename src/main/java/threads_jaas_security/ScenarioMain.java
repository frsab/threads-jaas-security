package threads_jaas_security;

import javax.security.auth.login.LoginContext;

import threads_jaas_security.device.MasterDevice;

public class ScenarioMain {

	public static void main(String[] args) {
		LoginContext lc = null;
		// try {
		// lc = new LoginContext("Sample", new MyCallbackHandler());
		//
		// } catch (LoginException le) {
		// System.err.println("Cannot create LoginContext. " + le.getMessage());
		// System.exit(-1);
		// } catch (SecurityException se) {
		// System.err.println("Cannot create LoginContext. " + se.getMessage());
		// System.exit(-1);
		// }
		System.out.println("hello ");
		MasterDevice masterDevice = new MasterDevice();
		/**
		 * enter the number of slave threads run the master thread to initialize it add
		 * for each a login and password lancher the slave threads
		 */
		masterDevice.setNbSlaves(10);
		masterDevice.run();

	}

}
