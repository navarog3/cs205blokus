import java.awt.Point;

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

    public Point[][] pieceConfigs = {
        {new Point(0, 0)}, //Single
        {new Point(0, 0), new Point(1, 0)}, //Double
        {new Point(0, 0), new Point(1, 0), new Point(-1, 0)}, //Tiny I
        {new Point(0, 0), new Point(1, 0), new Point(0, -1)}, //Tiny L
        {new Point(0, 0), new Point(1, 0), new Point(0, -1), new Point(-1, 0)}, //Small T
        {new Point(0, 0), new Point(0, -1), new Point(0, 1), new Point(1, 1)}, //Small L
        {new Point(0, 0), new Point(0, -1), new Point(0, 1), new Point(0, 2)}, //Small I
        {new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1)}, //O Piece
        {new Point(0, 0), new Point(1, -1), new Point(-1, 0), new Point(0, -1)}, //Small S
        {new Point(0, 0), new Point(-1, -1), new Point(-1, 0), new Point(1, 0), new Point(2, 0)}, //Big L
        {new Point(0, 0), new Point(-1, -1), new Point(0, -1), new Point(1, -1), new Point(0, 1)}, //Big T
        {new Point(0, 0), new Point(0, -1), new Point(0, -2), new Point(1, 0), new Point(2, 0)}, //Big V
        {new Point(0, 0), new Point(-1, -1), new Point(0, -1), new Point(1, 0), new Point(2, 0)}, // Big N
        {new Point(0, 0), new Point(0, -1), new Point(1, -1), new Point(0, 1), new Point(-1, 1)}, // Big S
        {new Point(0, 0), new Point(0, -1), new Point(0, -2), new Point(0, 1), new Point(0, 2)}, // Big I
        {new Point(0, 0), new Point(0, -1), new Point(0, 1), new Point(1, 0), new Point(1, 1)}, // Big B
        {new Point(0, 0), new Point(-1, 0), new Point(-1, -1), new Point(0, 1), new Point(1, 1)}, // Big W
        {new Point(0, 0), new Point(-1, 0), new Point(-1, -1), new Point(1, 0), new Point(1, -1)}, // Big U
        {new Point(0, 0), new Point(0, -1), new Point(1, -1), new Point(-1, 0), new Point(0, 1)}, // Big F
        {new Point(0, 0), new Point(-1, 0), new Point(1, 0), new Point(0, -1), new Point(0, 1)}, // Big X
        {new Point(0, 0), new Point(0, -1), new Point(-1, 0), new Point(0, 1), new Point(0, 2)} // Big Y
    };

    public Piece(int z) {
        squareLocations = pieceConfigs[z];
    }

    public void HoInvert() {
        for (int i = 0; i < this.squareLocations.length; i++) {
            this.squareLocations[i].translate(-2 * (int) this.squareLocations[i].getX(), 0);
        }
    }

    public void rotateClock() {
        for (int i = 0; i < this.squareLocations.length; i++) {
            this.squareLocations[i].move(-(int) this.squareLocations[i].getY(), (int) this.squareLocations[i].getX());
        }
    }

    public void rotateCounter() {
        for (int i = 0; i < this.squareLocations.length; i++) {
            this.squareLocations[i].move((int) this.squareLocations[i].getY(), -(int) this.squareLocations[i].getX());
        }
    }
}
