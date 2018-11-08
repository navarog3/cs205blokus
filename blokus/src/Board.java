
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * A Pane in which blokus squares can be displayed.
 *
 */
public class Board extends Pane {

    // The size of the side of a blokus square
    public static final int SQUARE_SIZE = 20;
    // The number of squares that fit on the screen in the x and y dimensions
    public static final int DIM_SQUARES = 40;
    public int score;

    /**
     * Sizes the board to hold the specified number of squares in the x and y
     * dimensions.
     */
    public Board() {
        this.setPrefHeight(DIM_SQUARES * SQUARE_SIZE);
        this.setPrefWidth(DIM_SQUARES * SQUARE_SIZE);
        BackgroundFill myBF = new BackgroundFill(Color.WHITE, new CornerRadii(1),
                new Insets(0.0, 0.0, 0.0, 0.0));// or null for the padding
        setBackground(new Background(myBF));

    }

    //Creates a 2D array that will track the locations of all the pieces on the board.
    public Block[][] squares = new Block[DIM_SQUARES][DIM_SQUARES];

    /**
     * Adds ablokusSquare to the board array
     *
     * @param square
     */
    public void addToBoard(Block square) {
        squares[square.getX()][square.getY()] = square;
    }

    /**
     * Checks if a location in the board array is occupied. Useful for checking
     * if a piece is allowed to move there.
     *
     * @param y the y location to be checked
     * @param x the x location to be checked
     * @return
     */
    public boolean isOccupied(int x, int y) {
        return (squares[x][y] != null);
    }
}
