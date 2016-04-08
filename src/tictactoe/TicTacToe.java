package tictactoe;

import com.sun.javafx.image.IntPixelGetter;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToe extends Application {

    public static int moves =0;
    private static Scene scene;
    public char[][] board;

    private static Stage stage;
    private boolean gameOn = true;

    public static Stage getStage(){
        return stage;
    }

    public TicTacToe(){

        this.board = new char[3][3];
        for(int i=0;i<3;i++){
            for(int j =0;j<3;j++){
                board[i][j]=' ';
            }
        }
    }

    public void playO(Integer i , Integer j) {

        if(this.board[i][j]==' ') {
            this.board[i][j]='O';
        }


    }
    private void playX() {
        int best=Integer.MIN_VALUE;
        int bestRow=-1;
        int bestColumn=-1;
        int score;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(this.board[i][j]==' ') {
                    this.board[i][j]='X';

                    //if positioning X at this position results in win, we will definitely use this position.
                    if(win('X'))
                        score = Integer.MAX_VALUE;
                    else
                        score = eval(moves+1, 1000000000, 'O');
                    if (score > best) {
                        best = score;
                        bestRow = i;
                        bestColumn = j;
                    }
                    this.board[i][j]=' ';
                }
            }
        }

        if(bestRow==-1 && bestColumn==-1)
            return;
        this.board[bestRow][bestColumn]='X';

        GridPane gp = (GridPane)scene.getRoot();
        GridPane gridpane = (GridPane)gp.getChildren().get(0);
        ObservableList<Node> list = (ObservableList<Node>)gridpane.getChildren();


        for(Node node : list){
            if(node instanceof ImageView) {
                ImageView iw = (ImageView) node;
                Integer r = gridpane.getRowIndex(iw);
                Integer c = gridpane.getColumnIndex(iw);

                if (r == null)
                    r = 0;
                if (c == null)
                    c = 0;
                if (r == bestRow && c == bestColumn) {
                    iw.setImage(new Image("/resources/red-cross-icon.png"));


                }
            }
       }

    }

    private boolean win(char player){

        if(this.board[0][0]==player && this.board[0][1]==player && this.board[0][2]==player)
            return true;
        if(this.board[1][0]==player && this.board[1][1]==player && this.board[1][2]==player)
            return true;
        if(this.board[2][0]==player && this.board[2][1]==player && this.board[2][2]==player)
            return true;
        if(this.board[0][0]==player && this.board[1][0]==player && this.board[2][0]==player)
            return true;
        if(this.board[0][1]==player && this.board[1][1]==player && this.board[2][1]==player)
            return true;
        if(this.board[0][2]==player && this.board[1][2]==player && this.board[2][2]==player)
            return true;
        if(this.board[0][0]==player && this.board[1][1]==player && this.board[2][2]==player)
            return true;
        if(this.board[0][2]==player && this.board[1][1]==player && this.board[2][0]==player)
            return true;

        return false;
    }

    private int eval ( int move, int weight, char player ) {
        int value =0;
        char opponent;
        if(player=='X')
            opponent='O';
        else
            opponent='X';

        if(move>9)
            return 0;


        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(this.board[i][j]==' '){
                    this.board[i][j]=player;
                    if(win(player)){
                        this.board[i][j]=' ';
                        if(player=='X')
                            return weight;
                        else return -weight;
                    }

                    value = value +eval(move+1,weight/10,opponent);
                    this.board[i][j]=' ';
                }
            }
        }
        return value;
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../resources/sample.fxml"));
        stage.setTitle("Tic Tac Toe");
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.setWidth(600);
        stage.setHeight(600);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }


    @FXML
    public void GridClicked(MouseEvent event) throws IOException {

        if (gameOn == true) {

            ImageView iw = (ImageView) event.getSource();
            GridPane gp = (GridPane) scene.getRoot();
            GridPane gridpane = (GridPane) gp.getChildren().get(0);
            ObservableList<ImageView> list = (ObservableList) gridpane.getChildren();
            Integer i = gridpane.getRowIndex(iw);
            if (i == null)
                i = 0;
            Integer j = gridpane.getColumnIndex(iw);
            if (j == null)
                j = 0;
            Image image;

            image = new Image("/resources/green-cd-icon.png");

            if (this.board[i][j] == ' ') {
                iw.setImage(image);
                this.board[i][j] = 'O';
                moves++;
            }

            else if(this.board[i][j]!=' '){
                return;
            }
            if (win('O')) {
                System.out.println("O wins");
                gameOn=false;
                ResultBox.display('0',false);
                return;
            }

            if (moves == 9) {
                System.out.println("Its a tie!!!");
                ResultBox.display(' ',true);
                gameOn=false;
                return;
            } else playX();
            moves++;
            if (win('X')) {
                System.out.println("X wins");
                gameOn=false;
                ResultBox.display('X',false);
                return;
            }

        }
    }

    @FXML
    public void NewGame(){

        for(int i=0;i<3;i++){
            for(int j =0;j<3;j++){
                board[i][j]=' ';
            }
        }

        GridPane gp = (GridPane)scene.getRoot();
        GridPane gridpane = (GridPane)gp.getChildren().get(0);
        ObservableList<Node> list = (ObservableList<Node>)gridpane.getChildren();


        for(Node node : list){
            if(node instanceof ImageView) {
                ImageView iw = (ImageView) node;
                iw.setImage(null);
                }
            }
        moves=0;
        gameOn = true;
    }

    @FXML
    public void ExitGame() throws IOException {
        ConfirmBox cb = new ConfirmBox();
        boolean result=cb.display();
        if(result==true)
            stage.close();
    }

}
