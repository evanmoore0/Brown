package tetris;

public class Constants {
     // TODO: fill this class in with any more constants you might need!

    public static final int BETWEEN_ROWS = 100;

    // width of each square
    public static final int SQUARE_WIDTH = 30;

    //Index of the center of rotation
    public static final int CENTER_OF_ROTATION = 1;

    //Index of the X coordinates
    public static final int PIECE_X = 0;

    //Index of the Y coordinates
    public static final int PIECE_Y = 1;

    //Size of my piece array
    public static final int TETRIS_ARRAY_ROW = 4;

    //Center of the screen
    public static final int GAME_CENTER_X = Constants.GAME_WIDTH/2;

    // coordinates for squares in each tetris piece

    public static final int[][] I_PIECE_COORDS = {
            {GAME_CENTER_X, SQUARE_WIDTH},
            {GAME_CENTER_X, SQUARE_WIDTH*2},
            {GAME_CENTER_X, SQUARE_WIDTH*3},
            {GAME_CENTER_X, SQUARE_WIDTH*4} };
    public static final int[][] SQUARE_PIECE_COORDS = {
            {GAME_CENTER_X - SQUARE_WIDTH, SQUARE_WIDTH},
            {GAME_CENTER_X, SQUARE_WIDTH},
            {GAME_CENTER_X - SQUARE_WIDTH, SQUARE_WIDTH*2},
            {GAME_CENTER_X, SQUARE_WIDTH*2} };
    public static final int[][] T_PIECE_COORDS = {
            {GAME_CENTER_X + -1*SQUARE_WIDTH, SQUARE_WIDTH},
            {GAME_CENTER_X + -1*SQUARE_WIDTH, SQUARE_WIDTH*2},
            {GAME_CENTER_X + -1*SQUARE_WIDTH, SQUARE_WIDTH*3},
            {GAME_CENTER_X, SQUARE_WIDTH*2}};

    public static final int[][] S_PIECE_COORDS = {
            {GAME_CENTER_X - 30, SQUARE_WIDTH},
            {GAME_CENTER_X - 30, SQUARE_WIDTH*2},
            {GAME_CENTER_X, SQUARE_WIDTH*2},
            {GAME_CENTER_X, SQUARE_WIDTH*3}
    };


    public static final int[][] UPPERTRIGHT_PIECE_COORDS = {
            {GAME_CENTER_X, SQUARE_WIDTH},
            {GAME_CENTER_X, SQUARE_WIDTH*2},
            {GAME_CENTER_X, SQUARE_WIDTH*3},
            {GAME_CENTER_X + SQUARE_WIDTH, SQUARE_WIDTH}
    };

    public static final int[][] UPPERTLEFT_PIECE_COORDS = {
            {GAME_CENTER_X, SQUARE_WIDTH},
            {GAME_CENTER_X, SQUARE_WIDTH*2},
            {GAME_CENTER_X, SQUARE_WIDTH*3},
            {GAME_CENTER_X - SQUARE_WIDTH, SQUARE_WIDTH}
    };

    public static final int[][] Z_PIECE_COORDS = {
            {GAME_CENTER_X, SQUARE_WIDTH},
            {GAME_CENTER_X, SQUARE_WIDTH*2},
            {GAME_CENTER_X + SQUARE_WIDTH, SQUARE_WIDTH*2},
            {GAME_CENTER_X + SQUARE_WIDTH, SQUARE_WIDTH*3}
    };

    //Width of button
    public static final int BUTTON_WIDTH = 25;

    //Height of the whole screen
    public static final int SCREEN_HEIGHT = 690;

    //Game width/height
    public static final int GAME_WIDTH = 360;
    public static final int GAME_HEIGHT = 660;

    //Amount of rows/cols in my background array
    public static final int BACKGROUND_Y = GAME_HEIGHT/SQUARE_WIDTH;
    public static final int BACKGROUND_X = GAME_WIDTH/SQUARE_WIDTH;


}
