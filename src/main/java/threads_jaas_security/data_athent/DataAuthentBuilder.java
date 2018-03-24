package threads_jaas_security.data_athent;

import org.apache.commons.lang.RandomStringUtils;

import threads_jaas_security.data_athent.exception.NotImplementedBehavior;

public class DataAuthentBuilder {

	public DataAuthent buildDataAuthent(DataAuthentTypes dataAuthentTypes) throws NotImplementedBehavior {
		if (dataAuthentTypes == null) {
			throw new NullPointerException("the authentication data type object must not be null");

		} else {
			switch (dataAuthentTypes) {
			case BASIC:
				BasicDataAuthent basicDataAuthent = new BasicDataAuthent();
				String login = randomString(10);
				basicDataAuthent.setLogin(login);
				String password = randomString(24);
				basicDataAuthent.setPassword(password);
				return basicDataAuthent;

			default:
				throw new NotImplementedBehavior();
			}
		}
	}

	public String randomString(int length) {
		boolean useLetters = true;
		boolean useNumbers = false;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);

		System.out.println(generatedString);
		return generatedString;
	}
}
