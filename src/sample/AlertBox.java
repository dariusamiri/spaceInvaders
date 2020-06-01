package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

    public static void displayAlert(String stageTitle, String alert) {
        int ALERT_WIDTH = 275;
        int ALERT_HEIGHT = 125;
        Stage window = new Stage();
        GridPane alertRoot = new GridPane();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(stageTitle);
        Text message = new Text(alert);
        Button ok = new Button("Ok");
        ok.setOnAction(e -> window.close());
        alertRoot.setHgap(5);
        alertRoot.setVgap(5);
        alertRoot.add(message, 1, 3);
        alertRoot.add(ok, 2, 11);
        Scene alertScene = new Scene(alertRoot, ALERT_WIDTH, ALERT_HEIGHT);
        window.setScene(alertScene);
        window.showAndWait();
    }


}
