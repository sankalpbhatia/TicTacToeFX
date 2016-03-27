package tictactoe;

import com.sun.deploy.xml.XMLable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by sankalp on 26/3/16.
 */
public class ResultBox {

    private static Stage window;

    public static void display(char winner, boolean tie) throws IOException {

        FXMLLoader loader = new FXMLLoader(ResultBox.class.getResource("/resources/resultbox.fxml"));
        Parent root = loader.load();
        GridPane gp = (GridPane) root;

        HBox hbox= (HBox)gp.getChildren().get(1);

        ImageView iw = (ImageView)hbox.getChildren().get(0);
        Label label = (Label)hbox.getChildren().get(1);

        if(tie==false){
            if(winner=='X'){
                iw.setImage(new Image("/resources/red-cross-icon.png"));
            }
            else
                iw.setImage(new Image("/resources/green-cd-icon.png"));
            label.setText("Wins!!!!");
        }
        else
        {
            label.setText("It's a tie!!");
        }

        Scene scene = new Scene(root);
        window = new Stage();
        window.setTitle("Result");
        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(false);
        window.setScene(scene);
        window.showAndWait();


    }
}
