package swe2;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		new Thread(
				() -> swe2.server.Main.main(new String[]{})
			).start();
		new Thread(
				() -> swe2.control.deliverer.Main.main(new String[]{})
			).start();
	}
}
