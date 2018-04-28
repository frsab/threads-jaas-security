package threads_jaas_security;

import java.io.IOException;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import threads_jaas_security.device.MasterDevice;
import threads_jaas_security.jaas_framework_class.MyCallbackHandler;

public class ScenarioMain {
	public static boolean endTimeout;

	public static void main(String[] args) throws IOException {

		/**
		 * add property java.security.auth.login.config
		 */
		System.setProperty("java.security.auth.login.config", "./src/main/resources/jaas.config");
		/**
		 * instanciate LoginContext object
		 */
		MyCallbackHandler myCallbackHandler= new MyCallbackHandler();
		LoginContext lc = instanciateLoginContext(myCallbackHandler);
		invokeLoginMethpdOfLoginContext(lc);
		
		MasterDevice masterDevice = new MasterDevice("MyUserName");
		/**
		 * enter the number of slave threads.
		 * 
		 * run the master thread to initialize it.
		 * 
		 * add for each a login and password.
		 * 
		 * lancher the slave threads
		 */
		masterDevice.setNbSlaves(0);
		masterDevice.start();
		timeout(100);

	}

	private static void invokeLoginMethpdOfLoginContext(LoginContext lc) {
		try {
			System.out.println("begin login LoginContext");
			lc.login();
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			System.out.println("End login LoginContext");

		}
	}

	private static void timeout(int time ) {
		try {
			Thread.sleep(time);
			endTimeout = true;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			endTimeout = true;
		}
	}

	private static LoginContext instanciateLoginContext(MyCallbackHandler myCallbackHandler) {
		LoginContext lc = null;
		
		try {
			lc = new LoginContext("Sample",myCallbackHandler );

		} catch (LoginException le) {
			System.err.println("Cannot create LoginContext. LoginException :" + le.getMessage());
			System.exit(-1);
		} catch (SecurityException se) {
			System.err.println("Cannot create LoginContext. SecurityException" + se.getMessage());
			System.exit(0);
		}
		return lc;
	}
}
