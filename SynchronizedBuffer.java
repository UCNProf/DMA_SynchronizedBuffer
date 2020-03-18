
public class SynchronizedBuffer  {

	private int buffer = -1; // Shared by producer and consumer
	private int occupiedBuffers = 0; // count occupied buffers
	
	// place value into buffer
	public synchronized void set(int value) {
		
		String name = Thread.currentThread().getName();
		
		// place thread in waiting state if no buffers are available
		while(occupiedBuffers == 1) {
			try {
				System.out.println(name + " tries to write.");
				displayState("Buffer full. "+ name +" waits.");
				
				wait(); // wait until buffer is empty
			}
			catch(InterruptedException ex) {
				ex.printStackTrace();
			}	
		} // end while
		
		buffer = value;
		
		++occupiedBuffers;
		
		displayState(name + " writes " + buffer);
		
		notify(); // notify waiting thread to enter ready state
	}
	
	public synchronized int get() {
		
		String name = Thread.currentThread().getName();
		
		while(occupiedBuffers == 0) { // while no data to read, place thread in waiting state
			
			try {
				
				System.out.println(name + " tries to read.");
				displayState("Buffer empty. "+ name + " waits.");
				wait(); // Wait until buffer contains new value.
				
			}
			catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		} // end while
		
		--occupiedBuffers; // indicates that producer can store a new value because consumer just retrieved the buffer 
		
		displayState(name + " reads " + buffer);
		
		notify();
		
		return buffer;
	}
	
	// display current operation and buffer state 
	public void displayState(String operation) {
		StringBuffer outputLine = new StringBuffer(operation);
		outputLine.setLength(40);
		outputLine.append(buffer + "\t\t"+ occupiedBuffers);
		System.out.println(outputLine);
		System.out.println();
	}
}
