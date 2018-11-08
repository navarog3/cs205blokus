import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * A single Square on the blokus board. Squares have fixed height and width and
 * are positioned at grid locations on the board. A square at board location 3,
 * 4 for example, would be drawn at 3*TetrisBoard.SQUARE_SIZE,
 * 4*TetrisBoard.SQUARE_SIZE.
 *
 * The blokus board x and y locations of the Square are IntegerProperties.
 */
public class Block {

    // The shape for the square
    private Rectangle shape = new Rectangle(0, 0, 0, 0);
    // The x location in board coordinates
    private int xPos;
    // The y location in board coordinates
    private int yPos;
    public final Board board;

    /**
     * Creates a block and draws it in the board. The shape for a block is a
     * rectangle with height and width equal to Board.SQUARE_SIZE. The location
     * of the shape is set to blokus_x*TetrisBoard.SQUARE_SIZE,
     * blokus_y*TetrisBoard.SQUARE_SIZE via bindings, so that whenever blokus_x
     * and blokus_y are updated, the block's location will update.
     *
     * @param board
     */
    public Block(Board board) {
        this.board = board;
        this.board.getChildren().add(shape);
        this.shape.setX(xPos);
        this.shape.setY(yPos);
        this.shape.setWidth(Board.SQUARE_SIZE);
        this.shape.setHeight(Board.SQUARE_SIZE);

        // set the x and y locations so that they are always a multiple
        // of the size of a grid square
        // shape.xProperty().bind(blokus_x.multiply(board.SQUARE_SIZE));
        // shape.yProperty().bind(blokus_y.multiply(board.SQUARE_SIZE));
    }

    /**
     * Move the square to the specified x and y board coordinates. Undoes any
     * binding currently in effect on the coordinates, so that the square
     * remains fixed at the specified location.
     *
     * @param x x-coordinate on the blokus board
     * @param y y-coordinate on the blokus board
     */
    public void moveToBlokusLocation(int x, int y) {
        this.shape.setX(x*Board.SQUARE_SIZE);
        this.shape.setY(y*Board.SQUARE_SIZE);
        this.xPos = x;
        this.yPos = y;
    }

    /**
     * Get the x location of the square in board coordinates.
     *
     * @return current x location on the board
     */
    public int getX() {
        return this.xPos;
    }

    /**
     * Get the y location of the square in board coordinates.
     *
     * @return current y location on the board
     */
    public int getY() {
        return this.yPos;
    }

    /**
     * Sets the color of the square.
     *
     * @param color
     */
    public void setColor(Color color) {
        shape.setFill(color);
    }

    /**
     * Removes the block from theblokusBoard's Pane.
     */
    void removeFromDrawing() {
        board.getChildren().remove(shape);
    }

}
