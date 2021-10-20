package tetris;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

/*
My board class contains a 2-D array of rectangles that sets up the background, adds the pieces to the background array,
checks to see if the piece intersects with the background on the x and y axis, clears the line of pieces if full, shifts
the pieces down if a line was cleared, and drops the piece when the user presses the spacebar.
 */
public class Board {

    //Background 2-D array
    public Rectangle[][] _background;

    //Pane that the background goes onto
    public Pane _board;

    //Boolean that checks to see if the game should be ended
    public int _endGame;

    //Increments based on if the tetris piece is going to intersect something on the x axis
    int _countX;

    //Board constructor
    public Board(Pane board) {

        //Sets up the 2-D background array
        _background = new Rectangle[Constants.BACKGROUND_Y][Constants.BACKGROUND_X];

        //Create an instance of the board pane
        _board = board;

        //Initialize _countX
        _countX = 0;

        //Initialize _endGame
        _endGame = 0;

        //Setup the 2-D background array
        this.setUpBackground();

    }

    //Adds the piece to the 2_D background array
    public void addTetris(Piece piece) {

        //Loop through the piece array
        for(int i = 0; i < Constants.TETRIS_ARRAY_ROW; i++) {

                //Set the background at the pieces location to be equal to the piece
                _background[(int)(piece._Piece[i].getY())/Constants.SQUARE_WIDTH]
                        [(int)(piece._Piece[i].getX())/ Constants.SQUARE_WIDTH] = piece._Piece[i];
        }

    }


    //Checks to see if the piece intersects with the boarder/ any pieces added to the boarder
    public int checkIntersectX(Piece piece) {
        _countX =0;
                //Loop through the piece array
                for(int a = 0; a < Constants.TETRIS_ARRAY_ROW; a++) {

                        //If the lowest rectangle in the piece will intersect with the piece on the board or the boarder
                        //to the right increment _countX by two
                        if(_background[piece.getLowestY()/Constants.SQUARE_WIDTH][(int)(piece._Piece[a].getX() +
                                Constants.SQUARE_WIDTH)/ Constants.SQUARE_WIDTH] != null) {
                            _countX += 2;
                        }

                        //If the lowest rectangle in the in the piece will intersect with the piece on the board or the
                        //boarder to the left decrement _countX by one
                        if(_background[piece.getLowestY()/Constants.SQUARE_WIDTH][(int)(piece._Piece[a].getX() -
                                Constants.SQUARE_WIDTH)/ Constants.SQUARE_WIDTH] != null) {
                            _countX -=1;
                        }

                        //If the lowest rectangle in the piece is between two pieces set _countX equal to 100
                        if(_background[piece.getLowestY()/Constants.SQUARE_WIDTH][(int)(piece._Piece[a].getX() +
                                Constants.SQUARE_WIDTH)/ Constants.SQUARE_WIDTH] != null &&
                                _background[piece.getLowestY()/Constants.SQUARE_WIDTH][(int)
                                        (piece._Piece[a].getX() - 30)/Constants.SQUARE_WIDTH] != null) {
                            _countX = Constants.BETWEEN_ROWS;
                        }
        }

        return _countX;

    }

    //Drop the piece to the lowest possible position in the background array
    public void dropPiece(Piece piece) {

        //Loop through the background array, then the piece array
        for(int i = 0; i < Constants.BACKGROUND_Y; i++) {
            for(int j = 0; j < Constants.BACKGROUND_X; j++) {
                for(int a = 0; a < Constants.TETRIS_ARRAY_ROW; a++) {

                        //If the given background position is not null and equals the x position of the piece
                        if(_background[i][j] != null && _background[i][j].getX() == piece._Piece[a].getX()) {

                            //While the piece doesn't intersect with the background array
                            while(this.checkIntersect(piece)) {

                                //Shift the piece down by one rectangle
                                piece.shiftDown();

                            }

                        }
                }
            }
        }

        //Add the piece to the array
        this.addTetris(piece);
    }


    //Shift the rectangles above the row that was cleared down
    public void shiftDown(int row) {

        //Go through the rows from bottom to top
        for(int i = row; i > 1; i--) {
            for(int j = 1; j < Constants.BACKGROUND_X -1; j++) {

                //Check to see if the row above is null
                if(_background[i-1][j] != null) {

                    //Set the row above to be one rectangle below its current position
                    _background[i-1][j].setY(_background[i-1][j].getY() + 30);

                }

                //Set the new rectangle to be the old rectangle
                _background[i][j] = _background[i-1][j];

            }


        }

    }

    //Clear the line if it is full
    public void clearLine() {

        //Store the row value that should be cleared
        ArrayList<Integer> rows = this.checkLine();

        //If there is a row to clear
        if(rows.size() !=0) {

            //Loop through the rows that should be cleared
            for(int row = 0; row < rows.size(); row++) {

                //Loop through the columns of the background
                for(int j = 1; j < Constants.BACKGROUND_X - 1 ; j++) {

                    //Make the row disappear
                    _board.getChildren().remove(_background[rows.get(row)][j]);

                    //Set the row to be null
                    _background[rows.get(row)][j] = null;


                }

                this.shiftDown(rows.get(row));
                System.out.println(rows.get(row));

            }

            //Shift the rows down

        }
    }

    //Return a row if it is full
    public ArrayList<Integer> checkLine() {

        //Keep count of how many rectangle there are in a row
        int countPiecesRow = 0;

        //Store the index of the full row in the array list
        ArrayList<Integer> clearRows = new ArrayList<>();

        for(int i = 1; i < Constants.BACKGROUND_Y -1 ; i++) {
            for(int j = 1; j < Constants.BACKGROUND_X - 1; j++) {

                if(_background[i][j] != null) {
                    countPiecesRow++;
                }

                if(countPiecesRow == 10) {
                    clearRows.add(i);
                }

            }
            countPiecesRow = 0;
        }

        return clearRows;


    }


    //Check to see if the piece intersects with the background array, if it does add it to the background array
    public boolean checkIntersect(Piece piece) {

        //Loop through the background and the piece
        for(int i = 0; i < Constants.BACKGROUND_Y; i++) {


            for(int j = 0; j < Constants.BACKGROUND_X; j++) {


                for(int a = 0; a < Constants.TETRIS_ARRAY_ROW; a++) {

                        //If the background is not null and the x and y position of the piece equal the x and y position
                        //of the background add the tetris piece, clear the line if needed, and check to see if the game
                        //should end

                        if(_background[i][j] != null && _background[i][j].getX() == piece._Piece[a].getX() &&
                                _background[i][j].getY() == piece._Piece[a].getY() + Constants.SQUARE_WIDTH) {


                            //If the highest rectangle's y position in the piece array is equal to the top of the border
                            if(piece._Piece[0].getY() == Constants.SQUARE_WIDTH) {

                                _endGame++;

                            }

                            //Add piece to the background array
                            this.addTetris(piece);

                            //Check to see if the line should be cleared, and clear it if you need to

                            return false;
                        }

                }
            }

        }
        //Clear the line and shift the rows above down by one
        this.clearLine();
        return true;
    }

    //Setup the background array
    public void setUpBackground() {

        //Loop through the background array and add a rectangle to the array if it is on the border
        for(int i = 0; i < Constants.BACKGROUND_Y; i++) {
            for(int j = 0; j < Constants.BACKGROUND_X; j++) {
                int x = j * Constants.SQUARE_WIDTH;
                int y = i * Constants.SQUARE_WIDTH;
                Rectangle _rect = new Rectangle();

                //Check to see if the rectangle should be added to the array
                if(i == 0 || i == (Constants.BACKGROUND_Y - 1) || j == 0 || j == Constants.BACKGROUND_X-1) {
                    _rect.setStyle("-fx-fill: gray; -fx-stroke: black; -fx-stroke-width: 2;");
                    _background[i][j] = _rect;
                    _board.getChildren().add(_background[i][j]);
                } else {
                    _background[i][j] = null;

                }

                //Set the position/size of the rectangle
                _rect.setX(x);
                _rect.setY(y);
                _rect.setHeight(Constants.SQUARE_WIDTH);
                _rect.setWidth(Constants.SQUARE_WIDTH);

            }
        }
    }


}
