import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Inventory extends Pane {
	// The size of the side of a blokus square
    public static final int BLOCK_SIZE = 20;
    // The number of squares that fit on the screen in the x and y dimensions
    public static final int DIM_SQUARES = 20;
    //private final ArrayList<Line> horizontalLines = new ArrayList<>();
    //private final ArrayList<Line> verticalLines = new ArrayList<>();
    //private final ArrayList<Circle> startingCircles = new ArrayList<>();
    public int score;

    //Creates a 2D array that will track the locations of all the pieces on the board.
    public Block[][] squares = new Block[DIM_SQUARES][DIM_SQUARES / 2];

    /**
     * Sizes the board to hold the specified number of squares in the x and y
     * dimensions.
     */
    public Inventory() {
        this.setPrefHeight(DIM_SQUARES * BLOCK_SIZE);
        this.setPrefWidth(DIM_SQUARES * BLOCK_SIZE / 2);

        //Create the starting circles
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
    public void addToInventory(Block square) {
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