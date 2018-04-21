package threads_jaas_security;

import java.io.IOException;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import threads_jaas_security.device.MasterDevice;
import threads_jaas_security.jaas_framework_class.MyCallbackHandler;

public class ScenarioMain {
	public static void main(String[] args) throws IOException {
		endTimeout = false;
		/**
		 * add property java.security.auth.login.config
		 */
		System.setProperty("java.security.auth.login.config", "./src/main/resources/jaas.config");
		/**
		 * instanciate LoginContext object
		 */
		LoginContext lc = instanciateLoginContext();
		try {
			System.out.println("begin login Main");
			lc.login();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			System.out.println("End login Main");

		}
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
		try {
			Thread.sleep(10000);
			endTimeout = true;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			endTimeout = true;
		}
	}

	private static LoginContext instanciateLoginContext() {
		LoginContext lc = null;
		try {
			lc = new LoginContext("Sample", new MyCallbackHandler("my_login_string", "my_password_string"));

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
