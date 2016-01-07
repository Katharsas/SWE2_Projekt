package swe2;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		
		String operator_mask ="/view/operator_mask.fxml";
		String delivery_mask = "/view/delivery_mask.fxml";
		
		String arguments[] = new String[]{"Müllabgeber 2000 Xpress", delivery_mask, "Deliverer"};
		//String arguments[] = new String[]{"Müllverbrenner 2000 Express", operator_mask, "Operator"};
		
		new Thread(
				() -> swe2.server.net.Server.main(new String[]{})
			).start();
		new Thread(
				() -> swe2.client.JavaFxGui.main(arguments)).start();
	}
}
