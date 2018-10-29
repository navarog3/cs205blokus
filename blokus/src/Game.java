import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class Game {
    /*
    Old code, given by netbeans, we are just using it for reference
    */
//public class Game extends Application {
//    
//    @Override
//    public void start(Stage primaryStage) {
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        
//        Scene scene = new Scene(root, 300, 250);
//        
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
        public static void main(String[] args) {
//        launch(args);
            //Initialize all 21 pieces
            ArrayList<Block> blocks = new ArrayList<>();
            blocks.add(new Block(0, 0, Color.BLUE));
            blocks.add(new Block(-1, 0, Color.BLUE));
            blocks.add(new Block(-1, -1, Color.BLUE));
            blocks.add(new Block(1, 0, Color.BLUE));
            blocks.add(new Block(1, -1, Color.BLUE));
            Piece uPiece = new Piece(blocks, "R0", Color.BLUE, 1, 1);
            blocks.clear();
            blocks.add(new Block(0, 0, Color.BLUE));
            blocks.add(new Block(-1, 0, Color.BLUE));
            blocks.add(new Block(-1, -1, Color.BLUE));
            blocks.add(new Block(1, 0, Color.BLUE));
            blocks.add(new Block(1, -1, Color.BLUE));
            Piece wPiece = new Piece(blocks, "R0", Color.BLUE, 1, 1);
    }
}
