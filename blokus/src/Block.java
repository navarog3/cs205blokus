import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Block {
    
    private Rectangle shape = new Rectangle(0, 0, 25, 25);
    
    private int xPos, yPos;
    private Color color = Color.BLUE;
    private Board board;
    
    public Block(Color color, Board board) {
        this.color = color;
        this.board = board;
    }
    public Block(Board board) {
        //default block, color = blue
        this.board = board;
    }
    public void moveBlock(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }
}