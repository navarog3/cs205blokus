
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * The blokus Application, which contains the board and a message label.
 *
 */
public class Blokus extends Application {

    public static double MILLISEC = 300;
    private Game game;
    private Board board;
    private Board inventory;
    private Timeline animation;
    private Label statusLabel;

    /**
     * Launches the application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * Sets up the blokus board and game, as well as a status label that can be
     * used to display scores and messages.
     *
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        board = new Board();
        inventory = new Board(0.5);

        statusLabel = new Label("");
        statusLabel.setTextFill(Color.BLACK);

        BorderPane pane = new BorderPane();
        
        pane.setCenter(board);
        pane.setTop(statusLabel);
        pane.setRight(inventory);
        
        Scene scene = new Scene(pane);

        game = new Game(this, board, inventory);

        // Can add events that occur automatically here.
        // setUpAnimation();
        setUpKeyPresses();

        setUpMouseEvents();

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    /**
     * Changes the message in the status label at the top of the screen.
     *
     * @param message
     */
    public void setMessage(String message) {
        statusLabel.setText(message);
    }

    /**
     * Sets up an animation timeline that calls update on the game every
     * MILLISEC milliseconds.
     */
//    private void setUpAnimation() {
//        // Create a handler
//        EventHandler<ActionEvent> eventHandler = (ActionEvent e) -> {
//            this.pause();
//            game.update();
//            this.resume();
//        };
//        // Create an animation for alternating text
//        animation = new Timeline(new KeyFrame(Duration.millis(MILLISEC), eventHandler));
//        animation.setCycleCount(Timeline.INDEFINITE);
//        animation.play();
//    }
    /**
     * Sets up key events for the arrow keys and space bar. All keys send
     * messages to the game, which should react appropriately.
     */
    private void setUpKeyPresses() {
        board.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case LEFT:
                    game.rotateLeft();
                    break;
                case RIGHT:
                    game.rotateRight();
                    break;
                case DOWN:
                    break;
                case UP:
                    game.mirror();
                    break;
                case M:
                    game.mirror();
                    break;
                case SPACE:
                    game.checkForMove();
                    break;

            }
        });
        board.requestFocus(); // board is focused to receive key input

    }

    /**
     * Handles the mouse click.
     */
    private void setUpMouseEvents() {
        board.setOnMousePressed(e -> {
            game.placePiece();
        });
        board.setOnMouseMoved(e -> {
            game.hover(e.getSceneX(), e.getSceneY());
        });
        inventory.setOnMousePressed(e -> {
            game.selectPiece();
        });
        inventory.setOnMouseMoved(e -> {
            game.hover(e.getSceneX(), e.getSceneY());
        });
        
    }

    /**
     * Pauses the animation.
     */
    private void pause() {
        animation.pause();
    }

    /**
     * Resumes the animation.
     */
    private void resume() {
        animation.play();
    }

}
