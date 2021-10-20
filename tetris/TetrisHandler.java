package tetris;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;


/*
Handles all of the events that occur during the game
 */
public class TetrisHandler {

    //Declare an instance of board and piece
    Board _board;
    Piece _piece;

    //Declare an instance of the piece pane
    Pane _piecePane;

    //Check to see if the game is paused
    boolean _pause;

    //Label that is displayed when the game is paused
    Label _pauseLabel;

    //Instance of the timeline so I can pause it in my pause method
    Timeline pieceLocation;

    //Instance of Key event handler so I can remove it when game is paused
    MoveTetris _moveTetris;


    //TetrisHandler Constructor
    public TetrisHandler(Pane board) {

        //_piecePane = piece;

        //Create an instance of Key event handler so I can remove it when the game is paused
        _moveTetris = new MoveTetris();

        //Label that is displayed when the game is paused
        _pauseLabel = new Label();

        //Create an instance of the piece and board class
        _board = new Board(board);
        _piece = new Piece(board);

        board.addEventHandler(KeyEvent.KEY_PRESSED, _moveTetris);

        this.setUpTimeline();
        board.setFocusTraversable(true);

    }

    //Setup the timeline
    public void setUpTimeline() {
        KeyFrame kf = new KeyFrame(Duration.seconds(1), new PieceHandler());
        pieceLocation = new Timeline(kf);
        pieceLocation.setCycleCount(Animation.INDEFINITE);
        pieceLocation.play();
    }

    //Shift the piece down, check to see if the game is over, and generate a new piece
    private class PieceHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            _board.checkIntersectX(_piece);


            //If the piece doesn't intersect with the board and the game isn't over
            if(_board.checkIntersect(_piece) && _board._endGame == 0) {
                _piece.shiftDown();

                //If the game isn't over
            } else if (_board._endGame == 0) {
                _piece.generatePiece();

                //Otherwise end the game
            } else if(_board._endGame == 1) {

                endGame();

            }

        }

    }

    //Event handler for keys pressed
    private class MoveTetris implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent event) {
            KeyCode input = event.getCode();

            if (!_pause) {
                //If the piece doesn't intersect with the board to the left of it, if it isn't between two pieces,
                //and it doesn't intersect with the board
                if (input == KeyCode.LEFT && _board.checkIntersectX(_piece) >= 0 && _board.checkIntersectX(_piece)
                        != 100 && _board.checkIntersect(_piece)) {
                    _piece.MovePiece(false);

                    //If the piece doesn't intersect with the board to the right of it, if it isn't between two pieces,
                    //and it doesn't intersect with the board
                } else if (input == KeyCode.RIGHT && _board.checkIntersectX(_piece) <= 0 &&
                        _board.checkIntersectX(_piece) != 100 && _board.checkIntersect(_piece)) {
                    _piece.MovePiece(true);

                    //If the piece isn't a rectangle
                } else if (input == KeyCode.UP && _piece._Piece[0].getFill() != Color.PINK) {
                    _piece.rotatePiece(_board);
                    _piece._checkRotation = 0;

                    //Drop the piece
                } else if (input == KeyCode.SPACE) {
                    _board.dropPiece(_piece);

                    //Shift the piece down if it hasn't intersected with the board
                } else if (input == KeyCode.DOWN && _board.checkIntersect(_piece)) {
                    _piece.shiftDown();

                    //pause the game
                } else if (input == KeyCode.P) {

                    pauseGame();

                }
                event.consume();

                //If the game is paused and the user presses P
            } else if(input == KeyCode.P) {
                resumeGame();
            }
        }
    }

    //Pause the game
    public void pauseGame() {

        pieceLocation.pause();
        _pauseLabel.setText("You have paused the game press p to continue");
        _piecePane.getChildren().add(_pauseLabel);

        _pause = true;

    }

    //Resume the game
    public void resumeGame() {

        //Remove the pause Label
        _piecePane.getChildren().remove(_pauseLabel);

        _pause = false;
        pieceLocation.play();
    }

    //Display the endgame label
    public void endGame() {

        //So the endGame method is only called once
        _board._endGame++;

        _pauseLabel.setText("End Game");
        _piecePane.getChildren().add(_pauseLabel);

        //Remove the eventHandler
        _piecePane.removeEventHandler(KeyEvent.KEY_PRESSED, _moveTetris);

        //Stop the timeline
        pieceLocation.stop();

    }

}
