package tictactoe;

/**
 * Created by sankalp on 26/3/16.
 */
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;


import java.io.IOException;

public class ConfirmBox {

    static boolean answer;
    ;
    private static Stage window;

    public  boolean display() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/confirmbox.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        window = new Stage();
        window.setTitle("Exit");
        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(false);
        window.setScene(scene);
        window.showAndWait();
        return answer;
    }

    @FXML
    public void YesClicked(){
        answer = true;
        window.close();
    }

    @FXML
    public void NoClicked(){
        answer =false;
        window.close();
    }

}
