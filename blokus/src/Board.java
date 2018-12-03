
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 * A Pane in which blokus squares can be displayed.
 *
 */
public class Board extends Pane {

    // The size of the side of a blokus square
    public static final int BLOCK_SIZE = 20;
    // The number of squares that fit on the screen in the x and y dimensions
    public static final int DIM_SQUARES = 20;
    private final ArrayList<Line> horizontalLines = new ArrayList<>();
    private final ArrayList<Line> verticalLines = new ArrayList<>();
    private final ArrayList<Circle> startingCircles = new ArrayList<>();
    public int score;
    public int pieceStack;

    //Creates a 2D array that will track the locations of all the pieces on the boards.
    public Block[][] squares = new Block[DIM_SQUARES][DIM_SQUARES];

    /**
     * Sizes the board to hold the specified number of squares in the x and y
     * dimensions.
     */
    public Board() {
        this.setPrefHeight(DIM_SQUARES * BLOCK_SIZE);
        this.setPrefWidth(DIM_SQUARES * BLOCK_SIZE);

        //Draw the grid on the board
        for (int i = 0; i < DIM_SQUARES - 1; i++) {
            horizontalLines.add(new Line(0, (i + 1) * BLOCK_SIZE, DIM_SQUARES * BLOCK_SIZE, (i + 1) * BLOCK_SIZE));
            verticalLines.add(new Line((i + 1) * BLOCK_SIZE, 0, (i + 1) * BLOCK_SIZE, DIM_SQUARES * BLOCK_SIZE));
        }
        //Create the starting circles
        startingCircles.add(new Circle(BLOCK_SIZE / 2, BLOCK_SIZE / 2, BLOCK_SIZE / 2, Color.BLUE));
        startingCircles.add(new Circle(BLOCK_SIZE / 2, DIM_SQUARES * (BLOCK_SIZE - .5), BLOCK_SIZE / 2, Color.RED));
        startingCircles.add(new Circle(DIM_SQUARES * (BLOCK_SIZE - .5), BLOCK_SIZE / 2, BLOCK_SIZE / 2, Color.YELLOW));
        startingCircles.add(new Circle(DIM_SQUARES * (BLOCK_SIZE - .5), DIM_SQUARES * (BLOCK_SIZE - .5), BLOCK_SIZE / 2, Color.GREEN));

        for (int i = 0; i < 4; i++) {
            this.getChildren().add(startingCircles.get(i));
        }

        for (int i = 0; i < horizontalLines.size(); i++) {
            this.getChildren().add(horizontalLines.get(i));
            this.getChildren().add(verticalLines.get(i));
        }

        BackgroundFill myBF = new BackgroundFill(Color.WHITE, new CornerRadii(1),
                new Insets(0.0, 0.0, 0.0, 0.0));// or null for the padding
        setBackground(new Background(myBF));

    }
    
    // Initialize inventory board.
    // 	boardScalar multiplier for the width of the board (0.5).
    public Board(double boardScalar) {
        this.setPrefHeight(DIM_SQUARES * BLOCK_SIZE);
        this.setPrefWidth(DIM_SQUARES * BLOCK_SIZE);
        this.pieceStack = 1;

        //Create turn indicators
        //startingCircles.add(new Circle(BLOCK_SIZE / 2, BLOCK_SIZE / 2, BLOCK_SIZE / 2, Color.BLUE));
        //startingCircles.add(new Circle(BLOCK_SIZE / 2, DIM_SQUARES * (BLOCK_SIZE - .5), BLOCK_SIZE / 2, Color.RED));
        //startingCircles.add(new Circle(DIM_SQUARES * (BLOCK_SIZE - .5), BLOCK_SIZE / 2, BLOCK_SIZE / 2, Color.YELLOW));
        //startingCircles.add(new Circle(DIM_SQUARES * (BLOCK_SIZE - .5), DIM_SQUARES * (BLOCK_SIZE - .5), BLOCK_SIZE / 2, Color.GREEN));

        //for (int i = 0; i < 4; i++) {
        //    this.getChildren().add(startingCircles.get(i));
        //}

        BackgroundFill myBF = new BackgroundFill(Color.GREY, new CornerRadii(1),
                new Insets(0.0, 0.0, 0.0, 0.0));// or null for the padding
        setBackground(new Background(myBF));

    }

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
    
    public void addToInventory(Block square) {
        squares[square.getX()][square.getY()] = square;
    }
    
    // Empties out the inventory pane
    public void clearInventory() {
    	
    }
}
