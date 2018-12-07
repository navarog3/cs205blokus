
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
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

    private Stage primaryStage;
    private Stage helpStage = null;

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
                case Q:
                    game.rotateLeft();
                    break;
                case E:
                    game.rotateRight();
                    break;
                case S:
                    break;
                case W:
                    game.mirror();
                    break;
                case M:
                    game.mirror();
                    break;
                case SPACE:
                    game.checkForMove();
                    break;
                case A:
                	game.previousPiece();
                	break;
                case D:
                	game.nextPiece();
                	break;
                case F:
                	game.pass();
                	break;
                case H:
                    if(helpStage == null)
                        displayHelp();
            }
        });
        board.requestFocus(); // board is focused to receive key input

    }

    /**
     * Handles the mouse click.
     */
    private void setUpMouseEvents() {
        board.setOnMousePressed(e -> {
            game.takeTurn();
        });
        board.setOnMouseMoved(e -> {
            game.hover(e.getSceneX(), e.getSceneY());
        });
        inventory.setOnMousePressed(e -> {
            game.click(e.getSceneX(), e.getSceneY());
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

    private void displayHelp() {

        Pane pane = new Pane();

        String blokusRules = "\n    Rules of Blokus:\n\n" +
                             "    Blokus is a game played with 2-4 players taking turns placing an assortment\n" +
                             "    of pieces on the board. The first player starts by placing any of their pieces\n" +
                             "    on the board so that one of the blocks is touching the corner corresponding to\n" +
                             "    their color. Players must then place each of their remaining pieces so that pieces\n" +
                             "    of the same color are only touching a diagonal block. Play continues until a\n" +
                             "    player places all of their pieces on the board or until no player can place a piece.\n" +
                             "    Score is determined by who has the most individual blocks places on the board.\n" +
                             "    If a player uses all of their pieces, they get an extra 15 added to their score. If\n" +
                             "    the last piece placed is the monomino (single block), then that player gets an\n" +
                             "    additional 20 instead.\n\n" +
                             "    Controls:\n\n" +
                             "    - A and D cycle through the previous and next pieces, respectively\n" +
                             "    - Q and E rotate the current piece counter clockwise and clockwise, respectively\n" +
                             "    - W mirrors the piece across the Y-axis\n" +
                             "    - F skips the current player's turn";
        Text text = new Text(blokusRules);
        pane.getChildren().add(text);

        pane.setPrefSize(575, 350);
        helpStage = new Stage();
        helpStage.setTitle("Help Screen");
        helpStage.setScene(new Scene(pane));

        helpStage.setOnCloseRequest(e -> {helpStage = null; } );

        helpStage.show();

    }
}
