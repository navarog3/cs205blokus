import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class Game extends Application{
    
    @Override
    public void start(Stage primaryStage) {
        Board board = new Board();
        
        Piece uBlock = new Piece(17, 10, 10, board); //in theory, places a u-block at the center of the board
    }
        public static void main(String[] args) {
            launch(args);
    }
}
