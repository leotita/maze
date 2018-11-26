package maze;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class windowControl {
	
	
	
	
	@FXML
	private Button close;
	public void close()
	{
		close.setOnMouseClicked(e->
		{
			FxmlEventHander.window.close();
		});
	}

}
