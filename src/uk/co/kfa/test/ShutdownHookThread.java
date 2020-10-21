package uk.co.kfa.test;

public class ShutdownHookThread implements Runnable {

	@Override
	public void run() {
		System.out.println("Shutdown hook activated");
        ApplicationControl.running = false;
        while (!ApplicationControl.stopped){
        	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	System.out.println("Waiting for threads to end...");
        }
        System.out.println("All threads ended");
	}
}
