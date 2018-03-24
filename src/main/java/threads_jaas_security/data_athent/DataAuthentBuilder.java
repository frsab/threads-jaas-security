package threads_jaas_security.data_athent;

import threads_jaas_security.data_athent.exception.NotImplementedBehavior;

public class DataAuthentBuilder {

	public DataAuthent buildDataAuthent(DataAuthentTypes dataAuthentTypes) throws NotImplementedBehavior {
		if (dataAuthentTypes != null && dataAuthentTypes.equals(DataAuthentTypes.BASIC)) {

			BasicDataAuthent basicDataAuthent = new BasicDataAuthent();
			return basicDataAuthent;
		} else {
			if (!dataAuthentTypes.equals(DataAuthentTypes.BASIC)) {
				throw new NotImplementedBehavior();
			} else {
				throw new NullPointerException("the authentication data type object must not be null");
			}
		}
	}
}
