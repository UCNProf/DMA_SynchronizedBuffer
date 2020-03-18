
public class Producer extends Thread {
	
	private SynchronizedBuffer buffer; // reference to shared object

	// Constructor
	public Producer(SynchronizedBuffer buffer) {
		
		super("Producer"); // create thread named "Producer"

		this.buffer = buffer; // initialize shared location
	}

	@Override
	public void run() {

		for(int v = 1; v <= 4; v++) {
			try {
				Thread.sleep((int) (Math.random() * 3001));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				this.buffer.set(v);

		}

		System.out.println(getName() + " done producing");
	}	
}
