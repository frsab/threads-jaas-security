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
		System.out
				.println("CallbackHandler-constructor called with name : '" + name + "' and pswd: '" + password + "'");
		this.name = name;
		this.password = password;
	}

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		System.out.println("callbacks.length : " + callbacks.length);
		for (int i = 0; i < callbacks.length; i++) {
			System.out.println("callbacks[" + i + "]: " + callbacks[i]);
			System.out.println("instance of " + callbacks[i].getClass().getName());
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
				System.out.println("instanceof NameCallback");

				// prompt the user for a username
				NameCallback nc = (NameCallback) callbacks[i];

				System.err.print(nc.getPrompt());
				System.err.flush();
				nc.setName((new BufferedReader(new InputStreamReader(System.in))).readLine());

			} else if (callbacks[i] instanceof PasswordCallback) {
				//TODO
				System.out.println("Vous devez saisir votre mot de passe, on fait de notre mieux pour que ça soit le plus securisé possible");
				System.out.println("Vous devez saisir votre mot de passe, on fait de notre mieux pour que ça soit le plus securisé possible");

				// prompt the user for sensitive information
				PasswordCallback pc = (PasswordCallback) callbacks[i];
				System.err.print(pc.getPrompt());
				System.err.flush();
				InputStream systemIn = System.in;
				System.out.println("instanceof PasswordCallback endsystemIn" + systemIn);

				 //char[] readPassword=readPassword();
				char[] readPassword = { 'a', 'z', 'e', 'r', 't', 'y' };
				pc.setPassword(readPassword);

			} else {
				throw new UnsupportedCallbackException(callbacks[i], "Unrecognized Callback");
			}
		}
	}

	private char[] readPassword() {
		char[] r = null;
		char[] s = { 'a', 'z', 'e', 'r', 't', 'y' };
		for (int i = 0; i < 6; i++) {
			r[i] = (char) i;
		}
		System.out.println(s+ "   est le mot de passe genere pour vous");
		return s;
	}

}
