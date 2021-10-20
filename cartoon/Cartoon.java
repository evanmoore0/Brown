package cartoon;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.util.Duration;

/*
Cartoon class- Contains all of the event handlers/ timelines for my cartoon. Creates a new instance of CartoonHandler
(my composite shape) which adds everything to my rocketPane. Also adds my label (countDown) to the label Pane
 */
public class Cartoon {
    /*
    Declare an instance of my composite shape, label, and counters
     */
    private CartoonHandler _cartoon;
    private Label countDown;
    public int labelCounter;
    private int spacePress;

    /*
    Cartoon constructor, adds my composite shape to rocketPane, my countDown label to my Pane label, creates an
    instance of my counter, and sets up my event handler startLaunch()
     */
    public Cartoon(Pane rocketPane, Pane label) {

        //Padding variable for my label
        Insets padding = new Insets(20);


        //Keeps track of when the label should change
        labelCounter = 10;

        //Sets up label
        countDown = new Label(Constants.STARTING_MESSAGE);
        countDown.setFont(new Font("Verdana", Constants.FONT_SIZE));
        countDown.setPadding(padding);

        //Add countDown label to label Pane
        label.getChildren().add(countDown);

        //Set an event handler to listen for keys pressed
        rocketPane.addEventHandler(KeyEvent.KEY_PRESSED, new StartLaunch());
        rocketPane.setFocusTraversable(true);

        //Create an instance of my composite shape
        _cartoon = new CartoonHandler(rocketPane);

    }

    /*
    Moves my composite shape, and moves Earth after count is = 100
     */
    private class rocketTimeHandler implements EventHandler<ActionEvent> {
        private double _distance;

        public rocketTimeHandler() {
            _distance = Constants.DISTANCE;
        }

        public void handle(ActionEvent event) {

            _cartoon.setEarthCurve(_distance);
            _cartoon.setYLoc(_distance);
            _cartoon.setLoop(_cartoon.getEndRocket());

        }
    }

    //Calls rocketTimeHandler() indefinitely which makes the rocket continue to move every 15 milliseconds
    public void setupTimeline() {
        KeyFrame kf = new KeyFrame(Duration.millis(15), new rocketTimeHandler());
        Timeline launch = new Timeline(kf);
        launch.setCycleCount(Animation.INDEFINITE);
        launch.play();
    }

    /*
    Sets text of the label to the labelCounter variable, and calls setUpTimeline() and setupDistanceTimeline() once the
    countdown to launch is zero
     */
    private class TimeHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            countDown.setText(String.valueOf(labelCounter));
            labelCounter--;

            //Once countDown shows 0 (equals -1) call the timeline that launches the rocket, and changes the label
            if(labelCounter == -1){
                setupTimeline();
                countDown.setText(Constants.LIFT_OFF);
                labelCounter++;
                setupDistanceTimeline();
            }
        }
    }

    //Called in startLaunch because the countdown begins when the user presses the space bar
    public void setupLabelTimeline() {
        KeyFrame kf = new KeyFrame(Duration.seconds(1), new TimeHandler());
        Timeline launchCount = new Timeline(kf);
        launchCount.setCycleCount(11);
        launchCount.play();
    }

    /*
    Displays the distance traveled by the spacecraft seconds * 4.8miles/hour (actual speed of a spacecraft)
     */
    private class DistanceHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            countDown.setText("Distance: " + String.valueOf((int)(labelCounter*Constants.ROCKET_SPEED)) + " Miles");
            labelCounter++;

            //Once the spacecraft has traveled 100 miles a secret message will be displayed for the next 20 miles it
            // travels
            if(labelCounter*Constants.ROCKET_SPEED > Constants.SECRET_MESSAGE_DISTANCE && labelCounter*Constants.ROCKET_SPEED < Constants.SECRET_MESSAGE_EXIT){
                countDown.setText(Constants.SECRET_MESSAGE);
            }
        }
    }

    //Timeline for the DistanceHandler which displays how far the spacecraft has traveled
    public void setupDistanceTimeline() {
        KeyFrame kr = new KeyFrame(Duration.seconds(1), new DistanceHandler());
        Timeline distance = new Timeline(kr);
        distance.setCycleCount(Animation.INDEFINITE);
        distance.play();
    }

    /*
    Starts the whole cartoon when the user presses space and listens for the user to press c.
     */
    private class StartLaunch implements EventHandler<KeyEvent> {
        public void handle(KeyEvent event) {
            KeyCode space = event.getCode();

            //If the user presses space call setupLabelTimeline() which starts the countdown to launch
            //If the user press c call the setColor method which changes the color of the spacecraft.
            //If space is pressed spacePress is incremented preventing people from pressing space multiple times and
            //causing the spacecraft to move very quickly and the countDown to go into the negatives and count down
            // quickly
            if (space == KeyCode.SPACE && spacePress == 0) {
                spacePress++;
                setupLabelTimeline();

            } else if (space == KeyCode.C) {
                _cartoon.setColor();
            }
            event.consume();
        }
    }

}