
import java.awt.Point;
import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 * A contains the info and methods for the blokus pieces
 *
 * @author Evan Hollar
 */
public class Piece {

    public ArrayList<Block> squares = new ArrayList<>();

    private Board board;

    private Point[] squareLocations;

    private Color color;

    /**
     * Provides the relative square locations for each of the 21 blokus pieces.
     */
    public Point[][] pieceConfigs = {
        {new Point(0, 0)}, //Single                                                                             0
        {new Point(0, 0), new Point(1, 0)}, //Double                                                            1
        {new Point(0, 0), new Point(1, 0), new Point(-1, 0)}, //Tiny I                                          2
        {new Point(0, 0), new Point(1, 0), new Point(0, -1)}, //Tiny L                                          3        
        {new Point(0, 0), new Point(1, 0), new Point(0, -1), new Point(-1, 0)}, //Small T                       4
        {new Point(0, 0), new Point(0, -1), new Point(0, 1), new Point(1, 1)}, //Small L                        5
        {new Point(0, 0), new Point(0, -1), new Point(0, 1), new Point(0, 2)}, //Small I                        6
        {new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1)}, //O Piece                         7
        {new Point(0, 0), new Point(1, -1), new Point(-1, 0), new Point(0, -1)}, //Small S                      8
        {new Point(0, 0), new Point(-1, -1), new Point(-1, 0), new Point(1, 0), new Point(2, 0)}, //Big L       9
        {new Point(0, 0), new Point(-1, -1), new Point(0, -1), new Point(1, -1), new Point(0, 1)}, //Big T      10
        {new Point(0, 0), new Point(0, -1), new Point(0, -2), new Point(1, 0), new Point(2, 0)}, //Big V        11
        {new Point(0, 0), new Point(-1, -1), new Point(0, -1), new Point(1, 0), new Point(2, 0)}, // Big N      12
        {new Point(0, 0), new Point(0, -1), new Point(1, -1), new Point(0, 1), new Point(-1, 1)}, // Big S      13
        {new Point(0, 0), new Point(0, -1), new Point(0, -2), new Point(0, 1), new Point(0, 2)}, // Big I       14
        {new Point(0, 0), new Point(0, -1), new Point(0, 1), new Point(1, 0), new Point(1, 1)}, // Big B        15
        {new Point(0, 0), new Point(-1, 0), new Point(-1, -1), new Point(0, 1), new Point(1, 1)}, // Big W      16
        {new Point(0, 0), new Point(-1, 0), new Point(-1, -1), new Point(1, 0), new Point(1, -1)}, // Big U     17
        {new Point(0, 0), new Point(0, -1), new Point(1, -1), new Point(-1, 0), new Point(0, 1)}, // Big F      18
        {new Point(0, 0), new Point(-1, 0), new Point(1, 0), new Point(0, -1), new Point(0, 1)}, // Big X       19
        {new Point(0, 0), new Point(0, -1), new Point(-1, 0), new Point(0, 1), new Point(0, 2)} // Big Y        20
    };

    public void mirror() {
        for (int i = 0; i < this.squareLocations.length; i++) {
            this.squareLocations[i].translate(-2 * (int) this.squareLocations[i].getX(), 0);
        }
    }

    public void rotateCW() {
        for (int i = 0; i < this.squareLocations.length; i++) {
            this.squareLocations[i].move(-(int) this.squareLocations[i].getY(), (int) this.squareLocations[i].getX());
        }
    }

    public void rotateCCW() {
        for (int i = 0; i < this.squareLocations.length; i++) {
            this.squareLocations[i].move((int) this.squareLocations[i].getY(), -(int) this.squareLocations[i].getX());
        }
    }

    /**
     * The constructor for Piece
     *
     * @param board
     * @param x the original x location of the piece
     * @param y the original y location of the piece
     * @param z Chooses which blokus piece to create
     */
    public Piece(Board board, Color color, int x, int y, int z) {
        squareLocations = pieceConfigs[z];
        this.color = color;
        this.board = board;
        for (int i = 0; i < squareLocations.length; i++) {
            Block block = new Block(board);
            block.setColor(color);
            this.squares.add(block);
            block.moveToBlokusLocation((x + (int) squareLocations[i].getX()),
                    (y + (int) squareLocations[i].getY()));
        }
    }

    /**
     * Re-orients the piece
     *
     * @param dir The direction to rotate the piece. 0=CCW, 1=CW, and 2=mirror.
     */
    public void rotate(int dir) {
        //Updates the rotational configuratioan of the piece.
        //Creates a new arraylist of the pieces' new square locations if it were to move.
        switch (dir) {
            case 0:
                this.rotateCCW();
                break;
            case 1:
                this.rotateCW();
                break;
            case 2:
                this.mirror();
                break;
        }
        ArrayList<Point> newLocation = new ArrayList<>();
        for (int i = 0; i < this.squareLocations.length; i++) {
            Point point = new Point(this.squares.get(0).getX() + (int) squareLocations[i].getX(),
                    this.squares.get(0).getY() + (int) squareLocations[i].getY());
            newLocation.add(point);
        }
        boolean move = true;
        //Checks the new square locations to see if they are occupied or off the board.
        //If either are true, the peice does not move.
        for (int i = 0; i < this.squareLocations.length; i++) {
            if (newLocation.get(i).getX() < 0
                    || newLocation.get(i).getY() < 0
                    || newLocation.get(i).getX() >= Board.DIM_SQUARES
                    || newLocation.get(i).getY() >= Board.DIM_SQUARES // || board.isOccupied((int) newLocation.get(i).getY(), (int) newLocation.get(i).getX())
                    ) {
                move = false;
                break;
            }
        }
        //Rotates the piece if it is allowed to rotate.
        if (move) {
            for (int i = 0; i < this.squareLocations.length; i++) {
                this.squares.get(i).moveToBlokusLocation((int) newLocation.get(i).getX(),
                        (int) newLocation.get(i).getY());
            }
            // If can't move, rotates the piece back to original orientation
        } else {
            switch (dir) {
                case 0:
                    this.rotateCW();
                    break;
                case 1:
                    this.rotateCCW();
                    break;
                case 2:
                    this.mirror();
                    break;
            }
        }
    }

    /**
     * Moves the piece so that its origin is at the given location.
     *
     * @param xPos
     * @param yPos
     */
    public void move(int xPos, int yPos) {
        ArrayList<Point> newLocation = new ArrayList<>();
        for (int i = 0; i < this.squareLocations.length; i++) {
            Point point = new Point(xPos + (int) squareLocations[i].getX(),
                    yPos + (int) squareLocations[i].getY());
            newLocation.add(point);
        }
        boolean move = true;
        //Checks the new square locations to see if they are occupied or off the board.
        //If either are true, the peice does not move.
        for (int i = 0; i < this.squareLocations.length; i++) {
            if (newLocation.get(i).getX() < 0
                    || newLocation.get(i).getY() < 0
                    || newLocation.get(i).getX() >= Board.DIM_SQUARES
                    || newLocation.get(i).getY() >= Board.DIM_SQUARES 
                    //|| board.isOccupied((int) newLocation.get(i).getY(), (int) newLocation.get(i).getX())
                    ) {
                move = false;
                System.out.println(newLocation.get(i));
                break;
            }
        }
        System.out.println(move);
        //If the desired spot is open, then move the piece
        if (move) {
            for (int i = 0; i < this.squareLocations.length; i++) {
                this.squares.get(i).moveToBlokusLocation(xPos + (int) this.squareLocations[i].getX(),
                        yPos + (int) this.squareLocations[i].getY());
            }
        }
    }

    /**
     * Adds the piece to the board at its current location and creates a new
     * active piece.
     */
    public boolean addPieceToBoard() {
        ArrayList<Point> newLocation = new ArrayList<>();
        for (int i = 0; i < this.squareLocations.length; i++) {
            Point point = new Point(this.squares.get(0).getX() + (int) squareLocations[i].getX(),
                    this.squares.get(0).getY() + (int) squareLocations[i].getY());
            newLocation.add(point);
        }
        boolean place = true;
        for (int i = 0; i < this.squareLocations.length; i++) {
            if (newLocation.get(i).getX() < 0
                    || newLocation.get(i).getY() < 0
                    || newLocation.get(i).getX() >= Board.DIM_SQUARES
                    || newLocation.get(i).getY() >= Board.DIM_SQUARES
                    || board.isOccupied((int) newLocation.get(i).getX(), (int) newLocation.get(i).getY())
                    ) {
                place = false;
                System.out.println(newLocation.get(i));
            }
        }
        System.out.println(place);
        if (place) {
            for (int i = 0; i < squareLocations.length; i++) {
                this.board.addToBoard(this.squares.get(i));
            }
        }
        return place;
    }
}
