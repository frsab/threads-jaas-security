package threads_jaas_security.device;

import java.util.LinkedList;
import java.util.List;

public class MasterDevice extends Device {
	private int nbSlaves;
	List<SlaveDevice> slaves = new LinkedList<SlaveDevice>();

	@Override
	public void run() {
		this.setName("[master " + this.getNbSlaves() + "]");
		System.out.println("master " + this.getNbSlaves() + " static");
		System.out.println(this.getClass().toString() + " run " + this.getName());
		for (int i = 0; i < nbSlaves; i++) {
			SlaveDevice slaveThread = new SlaveDevice();
			slaves.add(slaveThread);
			String nom=i + " slave";
			System.out.println("init slaveThread with name ["+nom+"]");
			slaveThread.setName(nom);

		}
		System.out.println("slaves size to run " + slaves.size());
		
		for (SlaveDevice slaveDevice : slaves) {
			System.out.println(" run slave  " + slaveDevice.getName());
			slaveDevice.start();
		}
	}

	public int getNbSlaves() {
		return nbSlaves;
	}

	public void setNbSlaves(int nbSlaves) {
		this.nbSlaves = nbSlaves;
	}

}
