package com.apple2.ali;
/*这是一个经典的生产者与消费者问题，具体实现请参见附件。
自己把Info类修改为Apple，InfoPool改为ApplePool，Producer改为AppleSetter，Consumer改为AppleGetter，这样比较贴近你的要求。
注意例子中实现了单一生产者与单一消费者、单一生产者与多个消费者、单多个生产者与多个消费者三种代码，如果不需要其他的，请自行删除。*/
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Product2Consumer2 {
	private static class Info {
		private String name = "name";

		public Info(String id) {
			this.name = "[" + id + "]";
		}

		@Override
		public String toString() {
			return this.name;
		}
	}

	private static class InfoPool {
		private List<Info> infos = new ArrayList<Info>();
		// private Object putLock = new Object(), getLock = new Object();// 这样可以
		private Object putLock = new Object(), getLock = infos;// 这样也可以，
		// private Object putLock = infos, getLock = infos;// 这样也行，但是因为共用一把锁，所以出错次数多了
		private int maxSize = 3;

		public int size() {
			return infos.size();
		}

		public void push(Info info) {
			synchronized (putLock) {
				if (maxSize <= infos.size()) {
					synchronized (getLock) {
						getLock.notify();
					}
					try {
						putLock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			synchronized (getLock) {
				this.infos.add(info);
				getLock.notify();
			}
		}

		public Info get() {
			Info info = null;
			synchronized (getLock) {
				if (infos.size() == 0) {
					synchronized (putLock) {
						putLock.notify();
					}
					try {
						getLock.wait();
					} catch (InterruptedException e) {
						System.out.println(Thread.currentThread() + "在等待“get”时被中断了");
						return null;
					}
				}
				try {
					info = this.infos.remove(0);// 当多个线程被同时唤醒，这里仍然有出错的可能
				} catch (IndexOutOfBoundsException e) {
					System.out.println("出错了:多个线程被同时唤醒");
				}
			}
			synchronized (putLock) {
				putLock.notify();
			}
			return info;
		}
	}

	private static class Producer extends Thread {
		private InfoPool infos;
		private int count;

		public Producer(String name, InfoPool infos, int count) {
			super(name);
			this.infos = infos;
			this.count = count;
		}

		@Override
		public void run() {
			for (int i = 1; i <= count; i++) {
				Info info = new Info(getName() + "_" + i + "_" + infos.size());
				System.out.println(this.getName() + " 生产了：" + info);
				infos.push(info);
				wait_a_moment();
			}
		}
	}

	private static class Consumer extends Thread {
		private InfoPool infos;
		private int count = 0;
		private Context context;
		private List<Info> consumed = new ArrayList<Info>();

		public Consumer(String name, InfoPool infos, Context context) {
			super(name);
			this.infos = infos;
			this.context = context;
			this.context.addConsumer(this);
		}

		@Override
		public void run() {
			try {
				while (context.isRunable() && wait_a_moment()) {
					Info info = this.infos.get();
					if (info == null) continue;
					count++;
					consumed.add(info);
					System.out.println("\t" + this.getName() + " 消费了[" + count + "]次，此次=" + info + "\t" + this.infos.size());
				}
				context.temminate();
			} catch (Throwable e) {
				System.out.println("出现异常：" + this.getName());
				e.printStackTrace(System.out);
			}
		}
	}

	private static class Context {
		public int count;
		private List<Consumer> consumers = new ArrayList<Consumer>();

		public void addConsumer(Consumer con) {
			this.consumers.add(con);
		}

		public void start() {
			for (Consumer con : consumers) {
				con.start();
			}
		}

		public void temminate() {
			for (Consumer con : consumers) {
				if (con.isAlive()) con.interrupt();
			}
		}

		public boolean isRunable() {
			int tmp = 0;
			for (Consumer con : consumers) {
				tmp += con.count;
			}
			if (tmp >= count) {
				for (Consumer con : consumers) {
					if (con.isAlive()) con.interrupt();
				}
				return false;
			}
			return true;
		}

		void printResult() {
			for (Consumer con : consumers) {
				try {
					con.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// try {
			System.out.println();
			System.out.println("------打印执行结果------------------------------------------");
			int tmp = 0;
			for (Consumer con : consumers) {
				tmp += con.count;
				System.out.println("\t" + con.getName() + " 消费了[" + con.count + "]" + con.consumed);
			}
			System.out.println("总计执行次数 = " + tmp);
		}
	}

	private final static Random random = new Random();

	private static boolean wait_a_moment() {
		try {
			Thread.sleep(random.nextInt(WAITTIME));
			return true;
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread() + "在“wait_a_moment”时被中断了");
			return false;
		}
	}

	static void test单一生产者与单一消费者() throws InterruptedException {
		InfoPool pool = new InfoPool(); // 建立对象池
		Context context = new Context();
		context.count = PRODUCTCOUNT;
		Producer pro = new Producer("P1", pool, context.count); // 生产者
		new Consumer("C1", pool, context); // 消费者
		pro.start(); // 启动了生产者线程后，再启动消费者线程
		context.start();
		context.printResult();
	}

	static void test单一生产者与多个消费者() throws InterruptedException {
		InfoPool pool = new InfoPool(); // 建立对象池
		Context context = new Context();
		context.count = PRODUCTCOUNT;
		Producer pro = new Producer("P1", pool, context.count); // 生产者
		pro.start(); // 启动了生产者线程后，再启动消费者线程
		for (int i = 1; i <= 5; i++) {
			new Consumer("C" + i, pool, context); // 消费者
		}
		context.start();// 把线程的启动放在外面可以避免context.isRunable中因为对象动态添加导致的集合操作错误。如果一定要动态添加，那么在context.isRunable中需要新建一个临时列表对象来操作
		context.printResult();
	}

	static void test多个生产者与多个消费者() {
		InfoPool pool = new InfoPool(); // 建立对象池
		Context context = new Context();
		context.count = PRODUCTCOUNT;
		int pcount = 3, every = PRODUCTCOUNT / pcount;
		Producer p1 = new Producer("P1", pool, every + context.count % pcount); // 生产者
		Producer p2 = new Producer("P2", pool, every); // 生产者
		Producer p3 = new Producer("P3", pool, every); // 生产者
		// 启动了生产者线程后，再启动消费者线程
		p1.start();
		p2.start();
		p3.start();
		for (int i = 1; i <= 5; i++) {
			new Consumer("C" + i, pool, context); // 消费者
		}
		context.start();// 把线程的启动放在外面可以避免context.isRunable中因为对象动态添加导致的集合操作错误。如果一定要动态添加，那么在context.isRunable中需要新建一个临时列表对象来操作
		context.printResult();
	}

	private static int WAITTIME = 100;// 用于wait_a_moment类中生成一个随机等待时间
	private static int PRODUCTCOUNT = 100;// 设置总共生产多少次

	public static void main(String args[]) {
		try {
			// 注：以下时间不太精确，因为wait_a_moment时的等待时间是不固定的
			// 以下是生产时没有加wait_a_moment，消费时加了wait_a_moment的测试结果
			// 如果加了的话，肯定是线程越多的速度越快
			test单一生产者与单一消费者(); // 生产M次并消费完的时间为N毫秒:[M=300,N=4400]
			// test单一生产者与多个消费者();// 生产M次并消费完的时间为N毫秒:[M=300,N=930]
			// test多个生产者与多个消费者();// 生产M次并消费完的时间为N毫秒:[M=300,N=910]
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
