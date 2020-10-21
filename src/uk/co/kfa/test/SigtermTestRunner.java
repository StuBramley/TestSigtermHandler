package uk.co.kfa.test;

import java.lang.management.ManagementFactory;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SigtermTestRunner {

	public static void main(String[] args) {
		
		ApplicationControl.running = true;
		ApplicationControl.stopped = false;
		System.out.println("====== Started KFA Sigterm Handler ======");
		Runtime.getRuntime().addShutdownHook(new Thread(new ShutdownHookThread(),"Shutdown Hook Thread"));
		
		String processName = ManagementFactory.getRuntimeMXBean().getName();
		String pid = processName.split("@")[0];
		
		System.out.println("pid = " + pid);
		System.out.println("HUP: kill -1 " + pid);
		System.out.println("SIGINT: kill -2 " + pid);
		System.out.println("SIGTERM: kill -15 " + pid);
		System.out.println("SIGKILL: kill -9 " + pid);
		
		int threads = 10;
		
		if(args.length > 0)
    		threads = Integer.parseInt(args[0]);
		
		ExecutorService es = Executors.newCachedThreadPool();
		
		for (int i=0; i<threads; i++) {
			es.execute(new ApplicationWorkThread());
		}
		
		try {
			es.shutdown();
			es.awaitTermination(5, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ApplicationControl.stopped = true;
        System.out.println("====== Ended KFA Sigterm Handler ======");
	}	
}
