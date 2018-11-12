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
        //Rotates the piece if it is allowed to rotate.
        if (this.onBoard(newLocation)) {
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
        //If the desired spot is on the board, then move the piece
        if (this.onBoard(newLocation)) {
            for (int i = 0; i < this.squareLocations.length; i++) {
                this.squares.get(i).moveToBlokusLocation(xPos + (int) this.squareLocations[i].getX(),
                        yPos + (int) this.squareLocations[i].getY());
            }
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
    public boolean forceAddPieceToBoard() {
        ArrayList<Point> newLocation = new ArrayList<>();
        for (int i = 0; i < this.squareLocations.length; i++) {
            Point point = new Point(this.squares.get(0).getX() + (int) squareLocations[i].getX(),
                    this.squares.get(0).getY() + (int) squareLocations[i].getY());
            newLocation.add(point);
        }
        boolean place = false;
        //Checks if the piece fits on the board at the current location
        //If so, adds the piece's blocks to the board.
        if (this.fitsOnBoard(newLocation)) {
            for (int i = 0; i < squareLocations.length; i++) {
                this.board.addToBoard(this.squares.get(i));
            }
            place = true;
        }
        return place;
    }

    //A method that returns whether or not an arrayList of points is entirely within
    //the boundaries of the board.
    public boolean onBoard(ArrayList<Point> newLocation) {
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
    public boolean fitsOnBoard(ArrayList<Point> newLocation) {
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

    public boolean legalMove(ArrayList<Point> newLocation) {
        boolean legal = false;
        int xPos;
        int yPos;

        for (int i = 0; i < this.squareLocations.length; i++) {
            xPos = (int) newLocation.get(i).getX();
            yPos = (int) newLocation.get(i).getY();

            //CHECKS THE DIAGONAL SQAURES FOR BLOCKS OF THE SAME COLOR
            //Checks top right diagonal
            if (xPos + 1 < Board.DIM_SQUARES && yPos - 1 >= 0) {
                if (board.squares[xPos + 1][yPos - 1] != null) {
                    if (board.squares[xPos + 1][yPos - 1].getColor() == this.color) {
                        System.out.println("Top Right Same Color");
                        legal = true;
                    }
                }
            }

            //Checks top left diagonal
            if (xPos - 1 >= 0 && yPos - 1 >= 0) {
                if (board.squares[xPos - 1][yPos - 1] != null) {
                    if (board.squares[xPos - 1][yPos - 1].getColor() == this.color) {
                        System.out.println("Top Left Same Color");
                        legal = true;
                    }
                }
            }

            //Checks bottom right diagonal
            if (xPos + 1 < Board.DIM_SQUARES && yPos + 1 < Board.DIM_SQUARES) {
                if (board.squares[xPos + 1][yPos + 1] != null) {
                    if (board.squares[xPos + 1][yPos + 1].getColor() == this.color) {
                        System.out.println("Bottom Right Same Color");
                        legal = true;
                    }
                }
            }

            //Checks bottom left diagonal
            if (xPos - 1 >= 0 && yPos + 1 < Board.DIM_SQUARES) {
                if (board.squares[xPos - 1][yPos + 1] != null) {
                    if (board.squares[xPos - 1][yPos + 1].getColor() == this.color) {
                        System.out.println("Bottom Left Same Color");
                        legal = true;
                    }
                }
            }

            //CHECKS THE ADJACENT SQAURES FOR BLOCKS OF THE SAME COLOR
            //Check the blocks to the right to see if they are the same color.
            if (xPos + 1 < Board.DIM_SQUARES) {
                if (board.squares[xPos + 1][yPos] != null) {
                    if (board.squares[xPos + 1][yPos].getColor() == this.color) {
                        System.out.println("Right same Color");
                        legal = false;
                        break;
                    }
                }
            }
            //Check the blocks to the left to see if they are the same color.
            if (xPos - 1 >= 0) {
                if (board.squares[xPos - 1][yPos] != null) {
                    if (board.squares[xPos - 1][yPos].getColor() == this.color) {
                        System.out.println("Left same Color");
                        legal = false;
                        break;
                    }
                }
            }
            //Check the blocks below to see if they are the same color.
            if (yPos + 1 < Board.DIM_SQUARES) {
                if (board.squares[xPos][yPos + 1] != null) {
                    if (board.squares[xPos][yPos + 1].getColor() == this.color) {
                        System.out.println("Bottom same Color");
                        legal = false;
                        break;
                    }
                }
            }
            //Check the blocks above to see if they are the same color.
            if (yPos - 1 >= 0) {
                if (board.squares[xPos][yPos - 1] != null) {
                    if (board.squares[xPos][yPos - 1].getColor() == this.color) {
                        System.out.println("Top same Color");
                        legal = false;
                        break;
                    }
                }
            }
        }
        return legal;
    }
}
