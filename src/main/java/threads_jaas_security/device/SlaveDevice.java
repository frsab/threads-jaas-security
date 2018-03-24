package threads_jaas_security.device;

public class SlaveDevice extends Device {

	@Override
	public void run() {
		speakOut(this.getName());
		speakOut(this.getPriority());
		
			
//			for (; true; ) {
//				speakOutRandom(this.getName());
//				
//			}
	}



}
