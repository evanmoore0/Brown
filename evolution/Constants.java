package evolution;

public class Constants {

    //Size of Screen
    public static final int SCREEN_WIDTH = 500;
    public static final int SCREEN_HEIGHT = 700;

    //Top and bottom pipe
    public static final int SIZE_PIPE_ARRAY = 2;

    //How much the pipe moves each keyframe
    public static final int MOVE_PIPE = 3;

    //Gets multiplied by random num to mutate passed on weights
    public static final double MUTATE_CONSTANT = 2.0;

    //Height of the game
    public static final int GAME_HEIGHT = 500;

    //Height of the scoreboard
    public static final int SCOREBOARD_HEIGHT = 192;

    //X Location of buttons at the start of the game
    public static final int MANUEL_FLAPPY_BUTTON_X = 100;
    public static final int SMART_FLAPPY_BUTTON_X = 265;

    //Duration between keyframes
    public static final double DURATION = 0.03;

    //Radius of the birds
    public static final int BIRD_RADIUS = 12;

    //Y-location the smartBirds start the game at
    public static final int SMART_BIRD_START = 50;

    //Smart birds opacity
    public static final double SMART_BIRD_OPACITY = 0.4;

    //Acceleration constant (UNITS: pixels/s^2)
    public static final int GRAVITY = 600;

    //Initial jump velocity (UNITS: pixels/s)
    public static final int REBOUND_VELOCITY = -200;

    //Size of smartBird population
    public static final double NUM_SMART_BIRDS = 50;

    //Number of inputs each smart bird takes in
    public static final int NUM_INPUTS = 4;

    //Size of the hiddenLayer
    public static final int HIDDEN_LAYER = 3;

    //Width/Height of the speed up buttons
    public static final int BUTTON_WIDTH_HEIGHT = 40;

    //Height of the bottom of the game
    public static final int BOTTOM_HEIGHT = 4;

    //Width of the pipes
    public static final int PIPE_WIDTH = 50;

    //Gap between the pipes
    public static final int PIPE_GAPE = 100;

    //Bottom of the game y value
    public static final int BOTTOM_GAME = 500;

    //Number of shapes in the composite shape
    public static final int NUM_SHAPES = 3;

    //Body of the bird starting x location
    public static final int BIRD_START_X = 75;

    //Eye of the bird starting x location
    public static final int EYE_START_X = 80;

    //Radius of the eye
    public static final int EYE_RADIUS = 6;

    //Radius of center of the eye
    public static final int MIDDLE_EYE_RADIUS = 2;

    //Width of the label container
    public static final int LABEL_CONTAINER_WIDTH = 320;



}
