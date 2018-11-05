import java.awt.Point;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Evan
 */
public class Piece {

    public Point[] squareLocations;
    public ArrayList<Block> blocks = new ArrayList<>();
    private Board board;

    public Point[][] pieceConfigs = {
        {new Point(0, 0)}, //Single                                                                          0
        {new Point(0, 0), new Point(1, 0)}, //Double                                                         1
        {new Point(0, 0), new Point(1, 0), new Point(-1, 0)}, //Tiny I                                       2
        {new Point(0, 0), new Point(1, 0), new Point(0, -1)}, //Tiny L                                       3
        {new Point(0, 0), new Point(1, 0), new Point(0, -1), new Point(-1, 0)}, //Small T                    4
        {new Point(0, 0), new Point(0, -1), new Point(0, 1), new Point(1, 1)}, //Small L                     5
        {new Point(0, 0), new Point(0, -1), new Point(0, 1), new Point(0, 2)}, //Small I                     6
        {new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1)}, //O Piece                      7
        {new Point(0, 0), new Point(1, -1), new Point(-1, 0), new Point(0, -1)}, //Small S                   8
        {new Point(0, 0), new Point(-1, -1), new Point(-1, 0), new Point(1, 0), new Point(2, 0)}, //Big L    9
        {new Point(0, 0), new Point(-1, -1), new Point(0, -1), new Point(1, -1), new Point(0, 1)}, //Big T   10
        {new Point(0, 0), new Point(0, -1), new Point(0, -2), new Point(1, 0), new Point(2, 0)}, //Big V     11
        {new Point(0, 0), new Point(-1, -1), new Point(0, -1), new Point(1, 0), new Point(2, 0)}, // Big N   12
        {new Point(0, 0), new Point(0, -1), new Point(1, -1), new Point(0, 1), new Point(-1, 1)}, // Big S   13
        {new Point(0, 0), new Point(0, -1), new Point(0, -2), new Point(0, 1), new Point(0, 2)}, // Big I    14
        {new Point(0, 0), new Point(0, -1), new Point(0, 1), new Point(1, 0), new Point(1, 1)}, // Big B     15
        {new Point(0, 0), new Point(-1, 0), new Point(-1, -1), new Point(0, 1), new Point(1, 1)}, // Big W   16
        {new Point(0, 0), new Point(-1, 0), new Point(-1, -1), new Point(1, 0), new Point(1, -1)}, // Big U  17
        {new Point(0, 0), new Point(0, -1), new Point(1, -1), new Point(-1, 0), new Point(0, 1)}, // Big F   18
        {new Point(0, 0), new Point(-1, 0), new Point(1, 0), new Point(0, -1), new Point(0, 1)}, // Big X    19
        {new Point(0, 0), new Point(0, -1), new Point(-1, 0), new Point(0, 1), new Point(0, 2)} // Big Y     20
    };

    public Piece(int z, int xPos, int yPos, Board board) { //xPos and yPos are in blocks
        squareLocations = pieceConfigs[z];
        this.board = board;
        for (int i = 0; i < squareLocations.length; i++) {
            Block block = new Block(board);
            this.blocks.add(block);
            block.moveBlock(xPos + (int)squareLocations[i].getX(), yPos + (int)squareLocations[i].getY());
        }
    }

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
}
