import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.paint.Color;

/**
 * BlokusGame class
 *
 * @author Evan Hollar
 */
public class Game {

    private final Blokus blokusApp;
    private Piece activePiece;
    private final Board board;
    private int piece = 0;
    private final Color[] Colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
    private int turn = 0;

    /**
     * Initialize the game. Selects a random shape to act as the current piece.
     *
     * @paramblokusApp A reference to the application (use to set messages).
     * @param board A reference to the board on which blocks are drawn
     */
    public Game(Blokus blokusApp, Board board) {

        //Initializes the game with the piece.
        activePiece = new Piece(board, Colors[turn], Board.DIM_SQUARES / 2, 2, piece);

        this.board = board;

        this.blokusApp = blokusApp;
        //blokusApp.setMessage("BLOKUS");
    }

    /**
     * Anything that happens automatically can go here.
     */
    void update() {
    }

    /**
     * Rotate the current piece counter-clockwise.
     */
    void rotateLeft() {
        this.activePiece.rotate(0);
        System.out.println("rotate left key was pressed!");
    }

    /**
     * Rotate the current piece clockwise.
     */
    void rotateRight() {
        this.activePiece.rotate(1);
        System.out.println("rotate right key was pressed!");
    }

    /**
     * Mirror the current piece.
     */
    void mirror() {
        this.activePiece.rotate(2);
        System.out.println("mirror key was pressed!");
    }

    /**
     * Handle the mouse clicked event.
     */
    void click(double x, double y) {
    }

    void hover(double x, double y) {
        this.activePiece.move((int) x / Board.BLOCK_SIZE, (int) (y - 20) / Board.BLOCK_SIZE);
    }

    /**
     * Place the piece onto the board.
     */
    void placePiece() {
        if (turn < 4 && this.activePiece.forceAddPieceToBoard()) {
            turn++;
            piece = (int) (Math.random() * 21);
            activePiece = new Piece(board, Colors[turn % 4], Board.DIM_SQUARES / 2, 2, piece);
        } else if (this.activePiece.addPieceToBoard()) {
            turn++;
            piece = (int) (Math.random() * 21);
            activePiece = new Piece(board, Colors[turn % 4], Board.DIM_SQUARES / 2, 2, piece);
        }
    }
}
