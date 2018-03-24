package threads_jaas_security.device;

import threads_jaas_security.data_athent.DataAuthent;

public class Slave {
	private SlaveDevice slaveDevice ;
	private DataAuthent dataAuthent ;
	/**
	 * @return the slaveDevice
	 */
	public SlaveDevice getSlaveDevice() {
		return slaveDevice;
	}
	/**
	 * @param slaveDevice the slaveDevice to set
	 */
	public void setSlaveDevice(SlaveDevice slaveDevice) {
		this.slaveDevice = slaveDevice;
	}
	/**
	 * @return the dataAuthent
	 */
	public DataAuthent getDataAuthent() {
		return dataAuthent;
	}
	/**
	 * @param dataAuthent the dataAuthent to set
	 */
	public void setDataAuthent(DataAuthent dataAuthent) {
		this.dataAuthent = dataAuthent;
	}
	
}
