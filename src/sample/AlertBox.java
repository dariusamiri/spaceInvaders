package sample;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
    private final int ALERT_WIDTH = 400;
    private final int ALERT_HEIGHT = 125;

    public AlertBox(String stageTitle, String alert) {
        Stage window = new Stage();
        Pane alertRoot = new Pane();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(stageTitle);
        Text message = new Text(alert);
        message.setFont(Font.font(16));
        Button ok = new Button("Ok");
        ok.setFont(Font.font(17));
        ok.setOnAction(e -> window.close());
        setPositionNode(message, alertRoot, 50, 50);
        setPositionNode(ok, alertRoot, ALERT_WIDTH - 50, ALERT_HEIGHT - 40);
        window.setScene(new Scene(alertRoot, ALERT_WIDTH, ALERT_HEIGHT));
        window.show();
    }

    public void setPositionNode(Node node, Pane root, int x, int y) {
        root.getChildren().add(node);
        node.setLayoutX(x);
        node.setLayoutY(y);
    }
}
