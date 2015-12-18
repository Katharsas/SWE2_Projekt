package swe2;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		
		String operator_mask ="/view/operator_mask.fxml";
		String delivery_mask = "/view/delivery_mask.fxml";
		
		new Thread(
				() -> swe2.server.net.Server.main(new String[]{})
			).start();
		new Thread(														//Name, Pfad zur GUI, BenutzerTyp
				() -> swe2.client.deliverer.JavaFxGui.main(new String[]{"Müllverbrenner 2000 Xpress", delivery_mask, "Deliverer"})
			).start();
		/*new Thread(
				() -> swe2.client.operator.Main.main(new String[]{})
			).start();*/
	}
}
