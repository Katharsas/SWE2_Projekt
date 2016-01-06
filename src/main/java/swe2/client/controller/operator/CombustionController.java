package swe2.client.controller.operator;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import swe2.shared.model.MixedWaste;

public class CombustionController extends TaskController {
	@FXML
	Label lblStatus;

	private static boolean combustionFlag = false;
	private static MixedWaste combusted = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblStatus.setVisible(false);
	}

	public boolean startCombustion(MixedWaste toBeCombusted) {
		if (combustionFlag)
			return false;
		else {
			combustionFlag = true;
			combusted = toBeCombusted;
			return true;
		}
	}

	public MixedWaste endCombustion() {
		if (!combustionFlag)
			return null;
		else {
			combustionFlag = false;
			MixedWaste oldCombusted = combusted;
			combusted = null;
			return oldCombusted;
		}
	}

	public boolean getCombustionState() {
		return combustionFlag;
	}

	public void setLabelText(String text, String hexcolor) {
		lblStatus.setVisible(true);
		String css = "";
		String hex = createHex(hexcolor);
		css = "-fx-text-fill: #" + hex + ";";
		lblStatus.setText(text);
		lblStatus.setStyle(css);
	}

	public void setLabelText(String text, boolean isError) {
		lblStatus.setVisible(true);
		lblStatus.setText(text);
		lblStatus.setStyle(isError ? "-fx-text-fill:red;"
				: "-fx-text-fill:green;");
	}

	private String createHex(String hex) {
		return ((hex.matches("[0-9a-fA-F]{6}")) ? hex : "000000");
	}
}
