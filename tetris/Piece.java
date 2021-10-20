package tetris;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*
Piece class, generates a random piece, drops piece down by one, rotates piece, moves piece left and right, and returns
lowest rectangle in the piece array
 */

public class Piece {

    //Declare piece array
    public Rectangle[] _Piece;

    //2-D array of piece coords
    private int[][] _coords;

    //Increments based on whether the piece should be able to rotate
    public int _checkRotation;

    //New X and Y location after rotation
    int _newXLoc;
    int _newYLoc;

    //Declare piece pane
    Pane piece;

    //Piece constructor
    public Piece(Pane _piece) {

        //Create an instance of the piece pane
        piece = _piece;

        _checkRotation = 0;

        //Generate a piece when the game starts
        this.generatePiece();

    }

    //Move the piece left and right based on the key input
    public void MovePiece(boolean right) {

        //Loop through the piece array
        for(int i = 0; i < Constants.TETRIS_ARRAY_ROW; i++) {

            //If the right arrow is pressed set the piece location one rectangle to the right
            if(right) {
                _Piece[i].setX(_Piece[i].getX() + Constants.SQUARE_WIDTH);
            }

            //If the left arrow is pressed set the piece location one rectangle to the left
            if(right == false) {

                _Piece[i].setX(_Piece[i].getX() - Constants.SQUARE_WIDTH);


            }
        }
    }

    //Shift the piece down one rectangle
    public void shiftDown() {
        for(int i = 0; i < Constants.TETRIS_ARRAY_ROW; i++) {


                _Piece[i].setY(_Piece[i].getY() + Constants.SQUARE_WIDTH);


        }
    }

    //Generates a random piece
    public void generatePiece() {
        //Sets _coords equal to the 2-D array of x and y position of the piece, and sets tetrisColor to the color of the
        //piece
        Color tetrisColor = this.randomPlatform();

        //Setup piece array
        _Piece = new Rectangle[Constants.TETRIS_ARRAY_ROW];

        //Loop through the piece array
        for(int i = 0; i < Constants.TETRIS_ARRAY_ROW; i++) {

                //Set up the rectangle, add it to the piece array, and add it to the piece pane
                Rectangle tetris = new Rectangle();

                tetris.setWidth(Constants.SQUARE_WIDTH);
                tetris.setHeight(Constants.SQUARE_WIDTH);
                tetris.setFill(tetrisColor);
                tetris.setStyle("-fx-stroke: black; -fx-stroke-width: 2;");

                tetris.setX(_coords[i][Constants.PIECE_X]);
                tetris.setY(_coords[i][Constants.PIECE_Y]);

                _Piece[i] = tetris;
                piece.getChildren().add(tetris);


        }
    }


    //Randomly returns a pieces color, and sets _coords equal to that pieces array of x and y values
    public Color randomPlatform() {
        int random = (int)(Math.random()*7);

        switch (random) {
            case 0:
                _coords = Constants.I_PIECE_COORDS;
                return Color.RED;
            case 1:
                _coords = Constants.T_PIECE_COORDS;
                return Color.ORANGE;
            case 2:
                _coords = Constants.SQUARE_PIECE_COORDS;
                return Color.PINK;
            case 3:
                _coords = Constants.Z_PIECE_COORDS;
                return Color.LIGHTBLUE;
            case 4:
                _coords = Constants.S_PIECE_COORDS;
                return Color.BLUE;
            case 5:
                _coords = Constants.UPPERTLEFT_PIECE_COORDS;
                return Color.GREEN;
            case 6:
                _coords = Constants.UPPERTRIGHT_PIECE_COORDS;
                return Color.YELLOW;
            default:
                return Color.WHITE;

        }
    }

    //Checks to see if the piece can be rotated, and rotates it if it can
    public void rotatePiece(Board board) {

        //Loop through the piece array
        for(int i = 0; i < Constants.TETRIS_ARRAY_ROW; i++) {

                //If it is on the center of rotations row and col
                if(i == Constants.CENTER_OF_ROTATION) {

                    //Set the centerOfRotation of X and Y
                    int centerOfRotationX = (int)_Piece[i].getX();
                    int centerOfRotationY = (int)_Piece[i].getY();

                        //Loop through the piece array again
                        for(int a = 0; a < Constants.TETRIS_ARRAY_ROW; a++) {


                                //Set the new X and Y location of the piece
                                _newXLoc = centerOfRotationX - centerOfRotationY + (int)_Piece[a].getY();
                                _newYLoc = centerOfRotationY + centerOfRotationX - (int)_Piece[a].getX();

                                //Increments _checkRotation if it cannot be rotated
                                if(board._background[_newYLoc/Constants.SQUARE_WIDTH][_newXLoc/Constants.SQUARE_WIDTH]
                                        != null) {

                                    _checkRotation++;
                                }
                    }
                        //If it can be rotated set the new x and y location of the piece
                        if(_checkRotation == 0) {
                            for(int a = 0; a < Constants.TETRIS_ARRAY_ROW; a++) {

                                    _newXLoc = centerOfRotationX - centerOfRotationY + (int)_Piece[a].getY();
                                    _newYLoc = centerOfRotationY + centerOfRotationX - (int)_Piece[a].getX();

                                    _Piece[a].setY(_newYLoc);
                                    _Piece[a].setX(_newXLoc);

                            }

                        }

                }
            }

    }


    //Returns the lowest Y position of the rectangle in the piece array
    public int getLowestY() {

        //Initially set the lowestY position to the first position in the array
        int lowestY = (int)_Piece[0].getY();

        //Loop through the piece array
        for(int i = 0; i < Constants.TETRIS_ARRAY_ROW; i++) {
            for(int j = 0; j < 1; j++){

                //If the current lowestY is less than the current rectangle's y position update lowestY
                if(lowestY < _Piece[i].getY()){

                    lowestY = (int)_Piece[i].getY();

                }
            }
        }
        return lowestY;
    }

}
