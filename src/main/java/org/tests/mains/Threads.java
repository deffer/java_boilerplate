package org.tests;

/**
 * author: Irina Benediktovich - http://plus.google.com/+IrinaBenediktovich
 */
public class Threads {
	public static void main(String[] args) {
		Runnable run = new Runnable() {
			public void run() {
				try {
					for (int i = 0; i < 20; i++) {
						Thread.sleep(1000);
						System.out.print(i + "\n" + "..");
					}

				} catch (InterruptedException e) {
					System.out.println(" interrupted");
				}
			}
		};
		new Thread(run).start();
		new Thread(run).start();
	}
}
