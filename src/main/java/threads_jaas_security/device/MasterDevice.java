package threads_jaas_security.device;

import java.util.LinkedList;
import java.util.List;

import threads_jaas_security.data_athent.DataAuthentTypes;
import threads_jaas_security.data_athent.exception.NotImplementedBehavior;

public class MasterDevice extends Device {
	private int nbSlaves;
	List<Slave> slaves = new LinkedList<Slave>();
	private String username;

	public MasterDevice(String username) {
		this.username = username;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public void run() {
		this.setName("[master " + this.getNbSlaves() + "]");
		System.out.println("master " + this.getNbSlaves() + " static");
		System.out.println(this.getClass().toString() + " run " + this.getName());
		SlaveBuilder slaveBuilder = new SlaveBuilder();
		for (int i = 0; i < nbSlaves; i++) {
			String nom = i + " slave";
			Slave slave;
			try {
				slave = slaveBuilder.getSlave(DataAuthentTypes.BASIC, nom);
				slaves.add(slave);
			} catch (NotImplementedBehavior e) {
				e.printStackTrace();
			}
		}
		System.out.println("slaves size to run " + slaves.size());

		for (Slave slave : slaves) {
			System.out.println(" run slave  " + slave.getSlaveDevice().getName());
			slave.getSlaveDevice().start();
		}
	}

	public int getNbSlaves() {
		return nbSlaves;
	}

	public void setNbSlaves(int nbSlaves) {
		this.nbSlaves = nbSlaves;
	}

}
