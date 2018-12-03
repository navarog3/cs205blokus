
import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 * BlokusGame class
 *
 */
public class Game {

    private final Blokus blokusApp;
    private Piece activePiece;
    private final Board board;
    private final Board inventory;
    private final Player[] players;
    private int piece = (int) (Math.random() * 21);
    private final Color[] Colors = {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN};
    private int turn = 0;

    /**
     * Initialize the game. Selects a random shape to act as the current piece.
     *
     * @param blokusApp A reference to the application (use to set messages).
     * @param board A reference to the board on which blocks are drawn
     */
    public Game(Blokus blokusApp, Board board, Board inventory) {

        //Initializes the game with the piece.
        activePiece = new Piece(board, inventory, Colors[turn], Board.DIM_SQUARES / 2, 2, piece);

        this.board = board;
        
        this.inventory = inventory;
        
        this.blokusApp = blokusApp;
        
        this.players = new Player[4];
        
        blokusApp.setMessage("      BLOKUS     |  P1       0  |  P2       0  |  P3       0  |  P4       0  |  ");
        
        int i;
        for (i = 0; i < players.length; i++) {
        	Player player;
        	player = new Player();
        	players[i] = player;
        }
        
        this.populateInventory(players[0]);
    }

    /**
     * Anything that happens automatically can go here.
     */
    void update() {
    }

    /**
     * Rotate the current piece counter-clockwise.
     */
    void rotateLeft() {
        this.activePiece.rotate(0);
        System.out.println("rotate left key was pressed!");
    }

    /**
     * Rotate the current piece clockwise.
     */
    void rotateRight() {
        this.activePiece.rotate(1);
        System.out.println("rotate right key was pressed!");
    }

    /**
     * Mirror the current piece.
     */
    void mirror() {
        this.activePiece.rotate(2);
        System.out.println("mirror key was pressed!");
    }

    /**
     * Handle the mouse clicked event.
     */
    void click(double x, double y) {
    }

    void hover(double x, double y) {
        this.activePiece.move((int) x / Board.BLOCK_SIZE, (int) (y - 20) / Board.BLOCK_SIZE);
    }

    /**
     * Place the piece onto the board.
     */
    void placePiece() {
        if (turn < 4 && this.activePiece.firstAddPieceToBoard()) {
            turn++;
            piece = (int) (Math.random() * 21);
            activePiece = new Piece(board, inventory, Colors[turn % 4], Board.DIM_SQUARES / 2, 2, piece);

        } else if (this.activePiece.addPieceToBoard()) {
            turn++;
            piece = (int) (Math.random() * 21);
            this.activePiece = new Piece(board, inventory, Colors[turn % 4], Board.DIM_SQUARES / 2, 2, piece);
        }
    }

    void checkForMove() {
        if (this.activePiece.availableMove()) {
            System.out.println("Move is available");
        } else {
            System.out.println("No move available");
        }
    }
    
    // fills out inventory pane for player
    void populateInventory(Player player){
    	// update turn indicator
    	
    	// update player name and score
    	
    	// Add remaining pieces
    	int i;
    	for (i = 0; i < player.inventory.length; i++) {
    		if (player.inventory[i] == true) {
    			
    			Piece piece;
    			piece = new Piece(board, inventory, Colors[turn % 4], Board.DIM_SQUARES / 2, 2, i);
    			piece.addPieceToInventory();
    		}
    	}
    	
    }
}
