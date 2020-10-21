package uk.co.kfa.test;

public class ApplicationWorkThread implements Runnable {

	@Override
	public void run() {
		int i = 0;
		
        while(ApplicationControl.running) {
        	i++;
        	System.out.println(Thread.currentThread().getName() + " iterated " + i + " times ");
        	try {
        		Thread.sleep(1000); // Wait a second
        	} catch (InterruptedException e) {
        		System.out.println(Thread.currentThread().getName() + " Interrupted");
        		e.printStackTrace();
        	}
        }
        System.out.println(Thread.currentThread().getName() + " Exiting");
	}

}
