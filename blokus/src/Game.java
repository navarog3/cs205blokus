
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
        
        
        
       activePiece = new Piece(board, Colors[turn], Board.DIM_SQUARES / 2, 2, piece);
       players[0].inventory[piece] = false;
       
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
        
        //TODO: hide if cursor is off the board?
    }

    /**
     * Place the piece onto the board.
     */
    void placePiece() {
        if (turn < 4 && this.activePiece.firstAddPieceToBoard()) {
            turn++;
            piece = (int) (Math.random() * 21);
            activePiece = new Piece(board, Colors[turn % 4], Board.DIM_SQUARES / 2, 2, piece);

        } else if (this.activePiece.addPieceToBoard()) {
            turn++;
            piece = (int) (Math.random() * 21);
            this.activePiece = new Piece(board, Colors[turn % 4], Board.DIM_SQUARES / 2, 2, piece);
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
    	// TODO: update turn indicator
    	
    	// TODO: update player name and score
    	
    	// Add remaining pieces
    	inventory.pieceStack = 0;
    	inventory.inventoryCols = 1;
    	int x = 0;		// x location for origin block
    	int y = 0;		// y location for origin block
    	
    	int i;
    	for (i = 0; i < player.inventory.length; i++) {
    		if (player.inventory[i] == true) {
    			
    			Piece piece;
    			
    			if ((inventory.pieceStack) >= 5) {
    				inventory.inventoryCols++;
    				inventory.pieceStack = 0;
    			}
    			
    			// set estimated x and y locations for origin
    			x = -3 + (inventory.inventoryCols * 5);
    			y = 1 + (inventory.pieceStack * 4);
    			
    			//x = 2;
    		    //y = 0;
    			
    			piece = new Piece(inventory, Colors[turn % 4], x, y, i);
    			
    			if (piece.addPieceToBoard() == true) {

    			} else {
    			// move the piece to a better spot   				
    				
    				if (y >= 19) {
					y = 0;
					x = x + 5;
					inventory.inventoryCols++;
    				inventory.pieceStack = 0;
					}
				
					y = y + 1;
					piece.move(x, y);
				
    			}
    			
    			inventory.pieceStack++;    			
    		}
    	}
    	
    }
    
    // switches out active piece for piece in inventory
    void selectPiece() {
    	//get piece from mouse location on inventory
    	
    	//swap active piece with inventory piece (remember to update square locations and player inventory)
    	
    	
    }
}
