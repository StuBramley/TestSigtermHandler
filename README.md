# TestSigtermHandling
Test Sigterm Handling

Test case to demonstrate an issue with the IBM JDK on IBM i version 1.8.0_261 

The test case is run via the SigtermTestRunner class. 

By default, 10 threads will be set up and run, simply logging out to the console every minute. It will run until ended by a signal. 

In order to test SIGTERM handing, send a kill -15 instruction to the pid of the process. This is logged to the console at startup for convenience. 

When working correctly, after SIGTERM is sent, the following output will be seen 

    Shutdown hook activated
    pool-1-thread-2 Exiting
    pool-1-thread-8 Exiting
    pool-1-thread-9 Exiting
    pool-1-thread-6 Exiting
    pool-1-thread-4 Exiting
    pool-1-thread-3 Exiting
    pool-1-thread-10 Exiting
    pool-1-thread-1 Exiting
    pool-1-thread-5 Exiting
    pool-1-thread-7 Exiting
    ====== Ended KFA Sigterm Handler ======
    Waiting for threads to end...
    All threads ended

The process will terminate. 

The issue we are seeing with IBM JDK on IBM i version 1.8.0_261 is that the above output is logged as expected but the process does not end. The "SIGINT handler" thread of the JVM goes into a suspended state, holding a lock which the "DestroyJavaVM helper thread" is waiting for. The process will only end with a SIGKILL 
