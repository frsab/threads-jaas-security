package threads_jaas_security.jaas_framework_class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.TextOutputCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

public class MyCallbackHandler implements CallbackHandler {
	String name;
	String password;

	public MyCallbackHandler(String name, String password) {
		System.out.println("CallbackHandler-constructor called with name : '" + name + "' and pswd: '" + password + "'");
		this.name = name;
		this.password = password;
	}

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		System.out.println("callbacks.length : "+callbacks.length);
		for (int i = 0; i < callbacks.length; i++) {
			if (callbacks[i] instanceof TextOutputCallback) {

				// display the message according to the specified type
				TextOutputCallback toc = (TextOutputCallback) callbacks[i];
				switch (toc.getMessageType()) {
				case TextOutputCallback.INFORMATION:
					System.out.println(toc.getMessage());
					break;
				case TextOutputCallback.ERROR:
					System.out.println("ERROR: " + toc.getMessage());
					break;
				case TextOutputCallback.WARNING:
					System.out.println("WARNING: " + toc.getMessage());
					break;
				default:
					throw new IOException("Unsupported message type: " + toc.getMessageType());
				}

			} else if (callbacks[i] instanceof NameCallback) {

				// prompt the user for a username
				NameCallback nc = (NameCallback) callbacks[i];

				System.err.print(nc.getPrompt());
				System.err.flush();
				nc.setName((new BufferedReader(new InputStreamReader(System.in))).readLine());

			} else if (callbacks[i] instanceof PasswordCallback) {

				// prompt the user for sensitive information
				PasswordCallback pc = (PasswordCallback) callbacks[i];
				System.err.print(pc.getPrompt());
				System.err.flush();
				pc.setPassword(readPassword(System.in));

			} else {
				throw new UnsupportedCallbackException(callbacks[i], "Unrecognized Callback");
			}
		}
	}

	private char[] readPassword(InputStream in) {
		char[] result = null;
		for (int i = 0; i < 10; i++) {
			result[i] = (char) i;
		}
		return result;
	}

}
