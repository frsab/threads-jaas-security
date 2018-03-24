package threads_jaas_security;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import threads_jaas_security.device.MasterDevice;
import threads_jaas_security.jaas_framework_class.MyCallbackHandler;

public class ScenarioMain {

	public static boolean endTimeout;

	public static void main(String[] args) {
		endTimeout = false;
		LoginContext lc = null;
		 try {
		 lc = new LoginContext("Sample", new MyCallbackHandler());
		
		 } catch (LoginException le) {
		 System.err.println("Cannot create LoginContext. " + le.getMessage());
		 System.exit(-1);
		 } catch (SecurityException se) {
		 System.err.println("Cannot create LoginContext. " + se.getMessage());
		 System.exit(-1);
		 }
		System.out.println("hello ");
		MasterDevice masterDevice = new MasterDevice();
		/**
		 * enter the number of slave threads.
		 * 
		 * run the master thread to initialize it.
		 * 
		 * add for each a login and password.
		 * 
		 * lancher the slave threads
		 */
		masterDevice.setNbSlaves(10);
		masterDevice.start();
		try {
			Thread.sleep(10000);
			endTimeout = true;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			endTimeout = true;
		}
	}

}
