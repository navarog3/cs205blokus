
import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 * BlokusGame class
 *
 */
public class Game {

    public double mouseX;
    public double mouseY;
    private final Blokus blokusApp;
    private Piece activePiece;
    private final Board board;
    private Board inventory;
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
        
        String message = "																Scores:    ";
        int i;
        for (i = 0; i < players.length; i++) {
            Player player;
            player = new Player("Player " + (i + 1));
            players[i] = player;
        	message = message + players[i].getName() + "    ";
        	message = message + players[i].getScore() + ",    ";
        }
        blokusApp.setMessage(message);

        activePiece = new Piece(board, Colors[turn % 4], Board.DIM_SQUARES / 2, 2, piece);
        players[0].inventory[piece] = false;

        this.populateInventory();
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
        this.mouseX = x;
        this.mouseY = y;
        this.activePiece.move((int) x / Board.BLOCK_SIZE, (int) (y - 20) / Board.BLOCK_SIZE);

        //TODO: hide if cursor is off the board?
    }

    /**
     * Place the piece onto the board.
     */
    void placePiece() {
        if (players[turn % 4].active = true) {
            if (turn < 4 && this.activePiece.firstAddPieceToBoard()) {
                // add # of blocks placed to score
            	players[turn % 4].setScore(players[turn % 4].getScore() + this.activePiece.squares.size());
            	
            	turn++;

                //pick up random piece for first turn
                piece = (int) (Math.random() * 21);
                players[turn % 4].inventory[piece] = false;
                activePiece = new Piece(board, Colors[turn % 4], (int) mouseX / Board.BLOCK_SIZE, (int) (mouseY - 20) / Board.BLOCK_SIZE, piece);

            } else if (this.activePiece.addPieceToBoard()) {
            	//if last placed, and not monomino, +15. Last placed is monomino, +20.
            	
            	// add # of blocks placed to score
            	players[turn % 4].setScore(players[turn % 4].getScore() + this.activePiece.squares.size());
            	
            	
            	turn++;
                piece = -1;

                // find a piece not yet played
                int i = 0;
                while (piece == -1) {
                    if (players[turn % 4].inventory[i] == true) {
                        piece = i;
                        players[turn % 4].inventory[i] = false;
                    }
                    i++;

                    if (i > 21) {
                        piece = -2;
                    }
                }

                if (piece >= 0) {
                    this.activePiece = new Piece(board, Colors[turn % 4], (int) mouseX / Board.BLOCK_SIZE, (int) (mouseY - 20) / Board.BLOCK_SIZE, piece);
                } else {
                    //player has no more pieces. set inactive
                    players[turn % 4].active = false;
                }
            }
        } else {
            turn++;
        }
        //update scoreboard
        String message = "																Scores:    ";
        int i;
        for (i = 0; i < 4; i++) {
        	message = message + players[i].getName() + "    ";
        	message = message + players[i].getScore() + ",    ";
        }
        blokusApp.setMessage(message);
        
        //redraw inventory for next player
        inventory.clearBoard();
        populateInventory();
    }

    void checkForMove() {
        if (this.activePiece.availableMove()) {
            System.out.println("Move is available");
        } else {
            players[turn % 4].active = false;
            System.out.println("No move available");
        }
    }

    // fills out inventory pane for player
    void populateInventory() {
        // clear pane
        inventory.getChildren().clear();

        // TODO: update turn indicator
        // TODO: update player name and score
        // Add remaining pieces
        inventory.pieceStack = 0;
        inventory.inventoryCols = 1;
        int x = 0;		// x location for origin block
        int y = 0;		// y location for origin block

        int i;
        for (i = 0; i < players[turn % 4].inventory.length; i++) {
            if (players[turn % 4].inventory[i] == true) {

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

    public void nextPiece() {
        // swaps active piece with inventory piece of higher index

        //find next piece
        int i = 0;
        for (i = 1; i <= 21; i++) {
            if (players[turn % 4].inventory[(piece + i) % 21] == true) {
                //swap
                players[turn % 4].inventory[piece] = true;
                piece = (piece + i) % 21;
                players[turn % 4].inventory[piece] = false;

                //redraw inventory and active piece
                activePiece.remove();
                activePiece = new Piece(board, Colors[turn % 4], (int) mouseX / Board.BLOCK_SIZE, (int) (mouseY - 20) / Board.BLOCK_SIZE, piece);

                populateInventory();

                return;
            }
        }
    }

    public void previousPiece() {
        // swaps active piece with inventory piece of lower index

        //find previous piece
        int i = 0;
        for (i = piece + 20; i > piece; i--) {
            if (players[turn % 4].inventory[i % 21] == true) {
                //swap
                players[turn % 4].inventory[piece] = true;
                piece = i % 21;
                players[turn % 4].inventory[piece] = false;

                //redraw inventory and active piece
                activePiece.remove();
                activePiece = new Piece(board, Colors[turn % 4], (int) mouseX / Board.BLOCK_SIZE, (int) (mouseY - 20) / Board.BLOCK_SIZE, piece);

                populateInventory();

                return;
            }
        }
    }
}
