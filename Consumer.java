
public class Consumer extends Thread {

	private SynchronizedBuffer buffer; // reference to shared object

	
	public Consumer(SynchronizedBuffer buffer) {
		
		super("Consumer");
		
		this.buffer = buffer;
	}

	@Override
	public void run() {

		int sum = 0;
		
		for(int v = 1; v <= 4; v++) {
			
			try {
				Thread.sleep((int) (Math.random() * 3001));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			sum += buffer.get();
		}
		
		System.out.println(getName() + " read values totaling: "+ sum);
	}	
}