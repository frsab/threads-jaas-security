package threads_jaas_security.device;

import java.util.LinkedList;
import java.util.List;

public class MasterDevice extends Device {
	private int nbSlaves;
List<SlaveDevice>  slaves = new LinkedList<SlaveDevice>();


	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();
	    System.out.println(this.getClass().toString() +"run" +threadName);
	    for (int i = 0; i < nbSlaves; i++) {
			SlaveDevice e = new SlaveDevice();
			slaves.add(e );
			e.run();
		}
	}

	public int getNbSlaves() {
		return nbSlaves;
	}

	public void setNbSlaves(int nbSlaves) {
		this.nbSlaves = nbSlaves;
	}

}
