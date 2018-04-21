package threads_jaas_security.device;

import threads_jaas_security.data_athent.DataAuthent;
import threads_jaas_security.data_athent.DataAuthentBuilder;
import threads_jaas_security.data_athent.DataAuthentTypes;
import threads_jaas_security.data_athent.exception.NotImplementedBehavior;

public class SlaveBuilder {
	private static DataAuthentBuilder dataAuthentBuilder = new DataAuthentBuilder();
	public Slave getSlave(DataAuthentTypes dataAuthentTypes, String nom) throws NotImplementedBehavior {
		Slave slave = new Slave();
		DataAuthent dataAuthent= dataAuthentBuilder.buildDataAuthent(dataAuthentTypes);
		
		slave.setDataAuthent(dataAuthent);
		
		SlaveDevice slaveDevice=new  SlaveDevice();
		slaveDevice.setName(nom);
		slave.setSlaveDevice(slaveDevice);
		System.out.println("init slaveThread with name [" + nom + "]");

		return slave ;
		
	}

}
