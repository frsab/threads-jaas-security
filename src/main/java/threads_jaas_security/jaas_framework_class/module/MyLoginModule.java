package threads_jaas_security.jaas_framework_class.module;

import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import threads_jaas_security.device.Device;
import threads_jaas_security.device.MasterDevice;

public class MyLoginModule implements LoginModule {

	private Subject subject;
	private CallbackHandler callbackHandler;
	private Map<String, ?> sharedState;
	private Map<String, ?> options;

	// username and password
	private String username;
	private char[] password;

	private boolean debug;
	// the authentication status
	private boolean succeeded = false;
	private boolean commitSucceeded = false;

	// testUser's SamplePrincipal
	private Device userDevicePrincipal;

	/**
	 * *
	 * <p>
	 *
	 * @param subject
	 *            the <code>Subject</code> to be authenticated.
	 *            <p>
	 *
	 * @param callbackHandler
	 *            a <code>CallbackHandler</code> for communicating with the end user
	 *            (prompting for user names and passwords, for example).
	 *            <p>
	 *
	 * @param sharedState
	 *            shared <code>LoginModule</code> state.
	 *            <p>
	 *
	 * @param options
	 *            options specified in the login <code>Configuration</code> for this
	 *            particular <code>LoginModule</code>.
	 */
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
			Map<String, ?> options) {
		this.subject = subject;
		this.callbackHandler = callbackHandler;
		this.sharedState = sharedState;
		this.options = options;

		// initialize any configured options
		debug = "true".equalsIgnoreCase((String) options.get("debug"));

	}

	
	public boolean justCall() throws LoginException {
		System.out.println("im just called from loginContext");
		return true;
	}
	/**
	 * Authenticate the user by prompting for a user name and password.
	 *
	 * <p>
	 *
	 * @return true in all cases since this <code>LoginModule</code> should not be
	 *         ignored.
	 *
	 * @exception FailedLoginException
	 *                if the authentication fails.
	 *                <p>
	 *
	 * @exception LoginException
	 *                if this <code>LoginModule</code> is unable to perform the
	 *                authentication.
	 */
	@Override
	public boolean login() throws LoginException {
		// prompt for a user name and password
		if (callbackHandler == null)
			throw new LoginException(
					"Error: no CallbackHandler available " + "to garner authentication information from the user");

		Callback[] callbacks = new Callback[2];
		NameCallback nameRequest = new NameCallback("user name !: ");
		callbacks[0] = nameRequest;
		PasswordCallback passwordRequest = new PasswordCallback("password: ?", false);
		callbacks[1] = passwordRequest;

		try {
			callbackHandler.handle(callbacks);
			username = ((NameCallback) callbacks[0]).getName();
			char[] tmpPassword = ((PasswordCallback) callbacks[1]).getPassword();
			if (tmpPassword == null) {
				// treat a NULL password as an empty password
				tmpPassword = new char[0];
			}
			password = new char[tmpPassword.length];
			System.arraycopy(tmpPassword, 0, password, 0, tmpPassword.length);
			((PasswordCallback) callbacks[1]).clearPassword();

		} catch (java.io.IOException ioe) {
			throw new LoginException(ioe.toString());
		} catch (UnsupportedCallbackException uce) {
			throw new LoginException("Error: " + uce.getCallback().toString()
					+ " not available to garner authentication information " + "from the user");
		}

		// print debugging information
		if (debug) {
			System.out.println("\t\t[SampleLoginModule] " + "user entered user name: " + username);
			System.out.print("\t\t[SampleLoginModule] " + "user entered password: ");
			for (int i = 0; i < password.length; i++)
				System.out.print(password[i]);
			System.out.println();
		}

		// verify the username/password
		boolean usernameCorrect = false;
		usernameCorrect = checkUserName(usernameCorrect);
		return checkPasswordAndUserName(usernameCorrect);
	}

	private boolean checkPasswordAndUserName(boolean usernameCorrect) throws FailedLoginException {
		boolean passwordCorrect;
		if (usernameCorrect && password.length == 6 && password[0] == 'a' && password[1] == 'z' && password[2] == 'e'
				&& password[3] == 'r' && password[4] == 't' && password[5] == 'y') {

			// authentication succeeded!!!
			passwordCorrect = true;
			if (debug)
				System.out.println("\t\t[SampleLoginModule] " + "authentication succeeded");
			succeeded = true;
			return true;
		} else {

			// authentication failed -- clean out state
			if (debug)
				System.out.println("\t\t[SampleLoginModule] " + "authentication failed");
			succeeded = false;
			username = null;
			for (int i = 0; i < password.length; i++)
				password[i] = ' ';
			password = null;
			if (!usernameCorrect) {
				throw new FailedLoginException("User Name Incorrect");
			} else {
				throw new FailedLoginException("Password Incorrect");
			}
		}
	}

	private boolean checkUserName(boolean usernameCorrect) {
		if (username.equals("testUser"))
			usernameCorrect = true;
		return usernameCorrect;
	}

	/**
	 * <p>
	 * This method is called if the LoginContext's overall authentication succeeded
	 * (the relevant REQUIRED, REQUISITE, SUFFICIENT and OPTIONAL LoginModules
	 * succeeded).
	 *
	 * <p>
	 * If this LoginModule's own authentication attempt succeeded (checked by
	 * retrieving the private state saved by the <code>login</code> method), then
	 * this method associates a <code>SamplePrincipal</code> with the
	 * <code>Subject</code> located in the <code>LoginModule</code>. If this
	 * LoginModule's own authentication attempted failed, then this method removes
	 * any state that was originally saved.
	 *
	 * <p>
	 *
	 * @exception LoginException
	 *                if the commit fails.
	 *
	 * @return true if this LoginModule's own login and commit attempts succeeded,
	 *         or false otherwise.
	 */
	@Override
	public boolean commit() throws LoginException {
		if (succeeded == false) {
			return false;
		} else {
			// add a Device (authenticated identity) to the Subject
			// assume the user we authenticated is the Device Principal
			userDevicePrincipal = new MasterDevice(username);
			if (!subject.getPrincipals().contains(userDevicePrincipal))
				subject.getPrincipals().add(userDevicePrincipal);

			if (debug) {
				System.out.println("\t\t[SampleLoginModule] " + "added Device Principal to Subject");
			}

			// in any case, clean out state
			username = null;
			for (int i = 0; i < password.length; i++)
				password[i] = ' ';
			password = null;

			commitSucceeded = true;
			return true;
		}
	}

	/**
	 * <p>
	 * This method is called if the LoginContext's overall authentication failed.
	 * (the relevant REQUIRED, REQUISITE, SUFFICIENT and OPTIONAL LoginModules did
	 * not succeed).
	 *
	 * <p>
	 * If this LoginModule's own authentication attempt succeeded (checked by
	 * retrieving the private state saved by the <code>login</code> and
	 * <code>commit</code> methods), then this method cleans up any state that was
	 * originally saved.
	 *
	 * <p>
	 *
	 * @exception LoginException
	 *                if the abort fails.
	 *
	 * @return false if this LoginModule's own login and/or commit attempts failed,
	 *         and true otherwise.
	 */
	@Override
	public boolean abort() throws LoginException {
		if (succeeded == false) {
			return false;
		} else if (succeeded == true && commitSucceeded == false) {
			// login succeeded but overall authentication failed
			succeeded = false;
			username = null;
			if (password != null) {
				for (int i = 0; i < password.length; i++)
					password[i] = ' ';
				password = null;
			}
			userDevicePrincipal = null;
		} else {
			// overall authentication succeeded and commit succeeded,
			// but someone else's commit failed
			logout();
		}
		return true;
	}

	/**
	 * Logout the user.
	 *
	 * <p>
	 * This method removes the <code>Device</code> that was added by the
	 * <code>commit</code> method.
	 *
	 * <p>
	 *
	 * @exception LoginException
	 *                if the logout fails.
	 *
	 * @return true in all cases since this <code>LoginModule</code> should not be
	 *         ignored.
	 */
	@Override
	public boolean logout() throws LoginException {

		subject.getPrincipals().remove(userDevicePrincipal);
		succeeded = false;
		succeeded = commitSucceeded;
		username = null;
		if (password != null) {
			for (int i = 0; i < password.length; i++)
				password[i] = ' ';
			password = null;
		}
		userDevicePrincipal = null;
		return true;
	}

}
