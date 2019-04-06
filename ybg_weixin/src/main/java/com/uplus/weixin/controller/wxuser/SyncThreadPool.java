package com.uplus.weixin.controller.wxuser;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SyncThreadPool {
	/**
	 * 核心池的大小，这个参数跟后面讲述的线程池的实现原理有非常大的关系。
	 * 在创建了线程池后，默认情况下，线程池中并没有任何线程，而是等待有任务到来才创建线程去执行任务，
	 * 除非调用了prestartAllCoreThreads()或者prestartCoreThread()方法，从这2个方法的名字就可以看出，
	 * 是预创建线程的意思，即在没有任务到来之前就创建corePoolSize个线程或者一个线程。默认情况下，在创建了线程池后，
	 * 线程池中的线程数为0，当有任务来之后，就会创建一个线程去执行任务，当线程池中的线程数目达到corePoolSize后， <br>
	 * 就会把到达的任务放到缓存队列当中
	 */
	static int corePoolSize = 6;
	/**
	 * 表示线程没有任务执行时最多保持多久时间会终止。<br>
	 * 默认情况下，只有当线程池中的线程数大于corePoolSize时，keepAliveTime才会起作用，<br>
	 * 直到线程池中的线程数不大于corePoolSize，即当线程池中的线程数大于corePoolSize时，<br>
	 * 如果一个线程空闲的时间达到keepAliveTime，则会终止，直到线程池中的线程数不超过corePoolSize。<br>
	 * 但是如果调用了allowCoreThreadTimeOut(boolean)方法，在线程池中的线程数不大于corePoolSize时，<br>
	 * keepAliveTime参数也会起作用，直到线程池中的线程数为0；
	 */
	static long keepAliveTime = 10;
	/**
	 * 线程池最大线程数，这个参数也是一个非常重要的参数，它表示在线程池中最多能创建多少个线程；
	 */
	static int maximumPoolSize = 6;

	/**
	 * 参数keepAliveTime的时间单位，有7种取值
	 */
	static TimeUnit unit = TimeUnit.SECONDS;
	/**
	 *
	 */
	public static BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<Runnable>();
	public static ThreadPoolExecutor syncUser = new ThreadPoolExecutor(
			corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
}
