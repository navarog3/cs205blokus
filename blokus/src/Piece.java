import java.awt.Point;
import java.util.Arrays;
import java.util.ArrayList;
import javafx.scene.paint.Color;

public class Piece {
    /*
    Fields
    */
    private ArrayList<Block> shape;
    private Point[][] pieceConfigs = {
        //In each piece, the first Point is the origin of that piece
        {new Point(0,0)}, //single
        {new Point(0,0), new Point(1,0)}, //double
        {new Point(0,0), new Point(1,0), new Point(-1,0)}, //triple
        {new Point(0,0), new Point(-1,-1), new Point(-1,0), new Point(1,0), new Point(1,1)} //u piece
    
    
    
    };
    String orient;
    Color color;
    int xPos;
    int yPos;

    
    /*
    Constructor for Piece
    */
    public Piece(ArrayList<Block> shape, String orientation, Color color, int xPos, int yPos) {
        this.shape = shape;
        this.orient = orientation;
        this.color = color;
        this.xPos = xPos;
        this.yPos = yPos;
    }
}