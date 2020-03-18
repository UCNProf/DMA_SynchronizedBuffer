
public class Program {

	public static void main(String[] args) {

		SynchronizedBuffer sharedBuffer = new SynchronizedBuffer();
		
		StringBuffer columnHeads = new StringBuffer("Operation");
		columnHeads.setLength(40);
		columnHeads.append("Buffer\t\tOccupied Count");
		System.out.println(columnHeads);
		System.out.println();
		
		sharedBuffer.displayState("Initial State");
		
		Producer producer = new Producer(sharedBuffer);
		Consumer consumer = new Consumer(sharedBuffer);
		
		producer.start();
		consumer.start();
	}

}
