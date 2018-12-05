
import java.awt.Point;
import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 * A contains the info and methods for the blokus pieces
 */
public class Piece {

    public ArrayList<Block> squares = new ArrayList<>();

    private Board board;

    private Point[] squareLocations;

    private Point[] diagonals = {new Point(-1, -1), new Point(1, -1), new Point(-1, 1), new Point(1, 1)};

    private Point[] adjacents = {new Point(-1, 0), new Point(0, -1), new Point(1, 0), new Point(0, 1)};

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
     * @param color color of the piece
     * @param x the original x location of the piece
     * @param y the original y location of the piece
     * @param z Chooses which blokus piece to create
     */
    public Piece(Board board, Color color, int x, int y, int z) {
        squareLocations = pieceConfigs[z];
        this.color = color;
        this.board = board;
        for (int i = 0; i < squareLocations.length; i++) {
            Block block = new Block(board, color);
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
        //Updates the rotational configuration of the piece.
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
        for (int i = 0; i < this.squareLocations.length; i++) {
            this.squares.get(i).moveToBlokusLocation(this.squares.get(0).getX() + (int) squareLocations[i].getX(),
                    this.squares.get(0).getY() + (int) squareLocations[i].getY());

        }
    }

    /**
     * Moves the piece so that its origin is at the given location.
     *
     * @param xPos
     * @param yPos
     */
    public void move(int xPos, int yPos) {
        for (int i = 0; i < this.squareLocations.length; i++) {
            this.squares.get(i).moveToBlokusLocation(xPos + (int) this.squareLocations[i].getX(),
                    yPos + (int) this.squareLocations[i].getY());
        }
    }

    /**
     * Adds the piece to the board at its current location.
     *
     * @return Whether or not the piece was added to the board.
     */
    public boolean addPieceToBoard() {
        ArrayList<Point> newLocation = new ArrayList<>();
        for (int i = 0; i < this.squareLocations.length; i++) {
            Point point = new Point(this.squares.get(0).getX() + (int) squareLocations[i].getX(),
                    this.squares.get(0).getY() + (int) squareLocations[i].getY());
            newLocation.add(point);
        }
        boolean place = false;
        //Checks if the piece fits on the board at the current location
        //If so, adds the piece's blocks to the board.
        if (this.fitsOnBoard(newLocation) && this.legalMove(newLocation)) {
            for (int i = 0; i < squareLocations.length; i++) {
                this.board.addToBoard(this.squares.get(i));
            }
            place = true;
        }
        return place;
    }

    /**
     * Adds the piece to the board at its current location. Does not check if
     * the move is legal
     *
     * @return Whether or not the piece was added to the board.
     */
    public boolean firstAddPieceToBoard() {
        ArrayList<Point> newLocation = new ArrayList<>();
        for (int i = 0; i < this.squareLocations.length; i++) {
            Point point = new Point(this.squares.get(0).getX() + (int) squareLocations[i].getX(),
                    this.squares.get(0).getY() + (int) squareLocations[i].getY());
            newLocation.add(point);
        }

        //Forces the first piece of each color to be places in the corresponding corner
        boolean correctCorner = false;
        if (this.color == Color.RED) {
            for (int i = 0; i < squareLocations.length; i++) {
                if (this.squares.get(i).getX() == 0 && this.squares.get(i).getY() == Board.DIM_SQUARES - 1) {
                    correctCorner = true;
                }
            }
        }
        if (this.color == Color.BLUE) {
            for (int i = 0; i < squareLocations.length; i++) {
                if (this.squares.get(i).getX() == 0 && this.squares.get(i).getY() == 0) {
                    correctCorner = true;
                }
            }
        }
        if (this.color == Color.YELLOW) {
            for (int i = 0; i < squareLocations.length; i++) {
                if (this.squares.get(i).getX() == Board.DIM_SQUARES - 1 && this.squares.get(i).getY() == 0) {
                    correctCorner = true;
                }
            }
        }
        if (this.color == Color.GREEN) {
            for (int i = 0; i < squareLocations.length; i++) {
                if (this.squares.get(i).getX() == Board.DIM_SQUARES - 1 && this.squares.get(i).getY() == Board.DIM_SQUARES - 1) {
                    correctCorner = true;
                }
            }
        }
        //Checks if the piece also fits on the board at the correct corner.
        //If so, adds the piece's blocks to the board.
        boolean place = false;
        if (this.fitsOnBoard(newLocation) && correctCorner) {
            for (int i = 0; i < squareLocations.length; i++) {
                this.board.addToBoard(this.squares.get(i));
            }
            place = true;
        }
        return place;
    }

    //A method that returns whether or not an arrayList of points is entirely within
    //the boundaries of the board.
    private boolean onBoard(ArrayList<Point> newLocation) {
        boolean on = true;
        for (int i = 0; i < this.squareLocations.length; i++) {
            if (newLocation.get(i).getX() < 0
                    || newLocation.get(i).getY() < 0
                    || newLocation.get(i).getX() >= Board.DIM_SQUARES
                    || newLocation.get(i).getY() >= Board.DIM_SQUARES) {
                on = false;
                break;
            }
        }
        return on;
    }

    //Takes an arrayList of points and returns true if those points are on the
    //board and unoccupied.
    private boolean fitsOnBoard(ArrayList<Point> newLocation) {
        boolean on = true;
        for (int i = 0; i < this.squareLocations.length; i++) {
            if (newLocation.get(i).getX() < 0
                    || newLocation.get(i).getY() < 0
                    || newLocation.get(i).getX() >= Board.DIM_SQUARES
                    || newLocation.get(i).getY() >= Board.DIM_SQUARES
                    || board.isOccupied((int) newLocation.get(i).getX(), (int) newLocation.get(i).getY())) {
                on = false;
                break;
            }
        }
        return on;
    }

    //Takes an arrayList of points and returns true if the current piece
    //can legally be placed at that location. Checks adjacent and diagonal squares 
    //for blocks of the same color.
    private boolean legalMove(ArrayList<Point> newLocation) {
        boolean adjacentSameColor = false;
        boolean diagonalSameColor = false;

        //Loops through the block locations of the piece being checked
        for (int i = 0; i < this.squareLocations.length; i++) {

            Point location = newLocation.get(i);

            //Breaks the loop if there is an adjacent square of the same color
            //A piece will never be able to be placed here.
            if (adjacentSameColor == true) {
                break;
            }

            //Loops through the squares diagonal to the piece being checked
            for (int j = 0; j < this.diagonals.length; j++) {
                //Creates a new square to be checked
                Point checkSquare = new Point((int) location.getX() + (int) diagonals[j].getX(),
                        (int) location.getY() + (int) diagonals[j].getY());
                //Checks that it is on the board and not empty
                if (checkSquare.getX() >= 0 && checkSquare.getY() >= 0
                        && checkSquare.getX() < Board.DIM_SQUARES
                        && checkSquare.getY() < Board.DIM_SQUARES) {
                    if (board.squares[(int) checkSquare.getX()][(int) checkSquare.getY()] != null) {
                        //Returns true if this square being checked is the same color as the piece
                        if (board.squares[(int) checkSquare.getX()][(int) checkSquare.getY()].getColor() == this.color) {
                            diagonalSameColor = true;
                        }
                    }
                }
            }

            //Loops through the squares adjacent to the piece being checked
            for (int j = 0; j < this.adjacents.length; j++) {
                //Creates a new square to be checked
                Point checkSquare = new Point((int) location.getX() + (int) adjacents[j].getX(),
                        (int) location.getY() + (int) adjacents[j].getY());
                //Checks that it is on the board and not empty
                if (checkSquare.getX() >= 0 && checkSquare.getY() >= 0
                        && checkSquare.getX() < Board.DIM_SQUARES
                        && checkSquare.getY() < Board.DIM_SQUARES) {
                    if (board.squares[(int) checkSquare.getX()][(int) checkSquare.getY()] != null) {
                        //Returns true if this square being checked is the same color as the piece
                        if (board.squares[(int) checkSquare.getX()][(int) checkSquare.getY()].getColor() == this.color) {
                            adjacentSameColor = true;
                            break;
                        }
                    }
                }
            }
        }
        //Returns true only if there is a diagnonal block of the same color
        //and no adjacent blocks of the same color
        return (diagonalSameColor && !adjacentSameColor);
    }

    //Checks to see if the piece can be placed at any square on the board in 
    //any orientation
    public boolean availableMove() {

        boolean move = false;

        //Create new "dummy" piece that goes around the board
        ArrayList<Point> newLocation = new ArrayList<>();

        //squareLocations for the dummy piece are the same as the current piece.
        Point[] dummyLocations = this.squareLocations;

        rotateLoop:
        for (int h = 1; h < 9; h++) {
            //Loops through all the spaces on the board
            for (int i = 0; i < Board.DIM_SQUARES; i++) {
                for (int j = 0; j < Board.DIM_SQUARES; j++) {
                    //Clears the dummy piece from the last check
                    newLocation.clear();
                    //Creates a new dummy piece using dummyLocations and the current
                    //spot on the board
                    for (int k = 0; k < dummyLocations.length; k++) {
                        Point point = new Point(i + (int) dummyLocations[k].getX(),
                                j + (int) dummyLocations[k].getY());
                        newLocation.add(point);
                    }
                    //Checks if this location is a valid move.
                    if (this.fitsOnBoard(newLocation) && this.legalMove(newLocation)) {
                        move = true;
                        System.out.println("Legal Move at (" + i + ", " + j + ")");
                        //Uncomment to break when 1 legal move is found
                        this.squareLocations = dummyLocations;
                        this.move(i, j);
                        break rotateLoop;
                    }
                }
            }
            //Rotates the block so the checks can be re-done in a new orientation.
            for (int i = 0; i < dummyLocations.length; i++) {
                dummyLocations[i].move(-(int) dummyLocations[i].getY(), (int) dummyLocations[i].getX());
            }
            //Once the block has been rotated 4 times, mirror it.
            if (h % 4 == 0) {
                for (int i = 0; i < dummyLocations.length; i++) {
                    dummyLocations[i].translate(-2 * (int) dummyLocations[i].getX(), 0);
                }
            }
        }
        return move;
    }
    
    public void remove() {
        for (int i = 0; i < this.squares.size(); i++){
            squares.get(i).removeFromDrawing();
        }
    	this.diagonals = null;
    	this.adjacents = null;
    	this.squareLocations = null;
    	this.squares.clear();
    	
    }
}
