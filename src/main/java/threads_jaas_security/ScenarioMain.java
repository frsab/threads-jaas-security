package threads_jaas_security;

import java.io.File;
import java.io.IOException;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import threads_jaas_security.device.MasterDevice;
import threads_jaas_security.jaas_framework_class.MyCallbackHandler;

public class ScenarioMain {

	public static boolean endTimeout;

	public static void main(String[] args) throws IOException {
		endTimeout = false;

		String[] listefichiers;

		int i;
		File repertoire = new File("./src/main/resources/");
		// File repertoire = new File("./src/main/ressources");
		// /threads_jaas_security/jaas.config
		// File repertoire = new File("\\threads_jaas_security");
		listefichiers = repertoire.list();
		for (i = 0; i < listefichiers.length; i++) {
			System.out.println(listefichiers[i].substring(0, listefichiers[i].length()));// on choisit la sous

		}

		System.setProperty("java.security.auth.login.config", "./src/main/resources/jaas.config");

		LoginContext lc = null;
		try {
			lc = new LoginContext("Sample", new MyCallbackHandler());

		} catch (LoginException le) {
			System.err.println("Cannot create LoginContext. LoginException :" + le.getMessage());
			// System.exit(-1);
		} catch (SecurityException se) {
			System.err.println("Cannot create LoginContext. SecurityException" + se.getMessage());
			// System.exit(-1);
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
}
