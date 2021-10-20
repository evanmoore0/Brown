package cartoon;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Rectangle;

/*
Creates the shapes that make up my composite shape and the methods that change my composite shape
 */
public class CartoonHandler {

    //Declare the shapes that makeup my composite
    private Rectangle rocketBody;
    private Polygon rocketTop;
    private Polygon rocketBottom;
    private Circle window;
    private Circle windowBackground;
    private Circle star;
    private Circle personHead;
    private Rectangle personBody;
    private Polygon flame;
    private Polygon flame2;
    private Circle eyeR;
    private Circle eyeL;
    private int color;
    private QuadCurve earthCurve;
    private Button end;

    //Constructor that accepts rocketPane as parameter
    //Creates instances of all of the shapes that make up my composite shape
    //Add these instances to the rocketPane
    public CartoonHandler(Pane rocketPane) {
        window = new Circle();
        rocketBody = new Rectangle();
        rocketTop = new Polygon();
        rocketBottom = new Polygon();
        windowBackground = new Circle();
        personHead = new Circle();
        personBody = new Rectangle();
        flame = new Polygon();
        flame2 = new Polygon();
        eyeR = new Circle();
        eyeL = new Circle();
        end =  new Button();

        //Earth
        earthCurve = new QuadCurve(Constants.EARTH_STARTX, Constants.EARTH_STARTY, Constants.EARTH_CONTROLX,
                Constants.EARTH_CONTROLY, Constants.EARTH_ENDX, Constants.EARTH_ENDY);
        earthCurve.setFill(Color.GREEN);

        //Right Eye
        eyeR.setFill(Color.BLACK);
        eyeR.setRadius(Constants.EYE_RADIUS);
        eyeR.setCenterX(Constants.EYER_X);
        eyeR.setCenterY(Constants.START_Y);

        //Left Eye
        eyeL.setFill(Color.BLACK);
        eyeL.setRadius(Constants.EYE_RADIUS);
        eyeL.setCenterX(Constants.EYEL_X);
        eyeL.setCenterY(Constants.START_Y);


        //Person Head
        personHead.setFill(Color.SANDYBROWN);
        personHead.setRadius(Constants.PERSONHEAD_RADIUS);
        personHead.setCenterX(Constants.PERSONHEAD_X);
        personHead.setCenterY(Constants.START_Y);

        //Person Body
        personBody.setFill(Color.SANDYBROWN);
        personBody.setY(Constants.START_Y);
        personBody.setX(Constants.PERSONBODY_X);
        personBody.setWidth(Constants.PERSONBODY_WIDTH);
        personBody.setHeight(Constants.PERSONBODY_HEIGHT);

        //Window
        window.setFill(Color.WHITE);
        window.setStroke(Color.WHITE);
        window.setRadius(Constants.WINDOW_RADIUS);
        window.setCenterX(Constants.WINDOW_X);
        window.setCenterY(Constants.START_Y);

        //Window Background
        windowBackground.setFill(Color.BLACK);
        windowBackground.setStroke(Color.BLACK);
        windowBackground.setRadius(Constants.WINDOWBACKGROUND_RADIUS);
        windowBackground.setCenterX(Constants.WINDOW_X);
        windowBackground.setCenterY(Constants.START_Y);

        //RocketTop
        //I CHOSE to not make these constants because it is significantly harder to read as constants (trust me I
        //tried) because the polygons use vertices to create the shape
        rocketTop.getPoints().addAll(
                500.0, 500.0,
                515.0, 470.0,
                530.0, 500.0);

        rocketTop.setFill(Color.DARKGRAY);

        //Rocket Bottom
        //I CHOSE to not make these constants because it is significantly harder to read as constants (trust me I
        //tried) because the polygons use vertices to create the shape
        rocketBottom.getPoints().addAll(
                500.0, 550.0,
                485.0, 570.0,
                500.0, 560.0,
                530.0, 560.0,
                545.0, 570.0,
                530.0, 550.0);

        rocketBottom.setFill(Color.DARKGRAY);


        //Rocket Body
        rocketBody.setWidth(30);
        rocketBody.setHeight(50);
        rocketBody.setFill(Color.DARKGRAY);
        rocketBody.setX(Constants.START_X);
        rocketBody.setY(Constants.START_Y);

        //Setup button
        end.setLayoutX(Constants.BUTTON_POS_X);
        end.setLayoutY(Constants.BUTTON_POS_Y);
        end.setText("EXIT");
        end.setTextFill(Color.RED);
        end.setMinSize(Constants.BUTTON_WIDTH,Constants.BUTTON_HEIGHT);
        end.setOnAction(new ButtonHandler());

        //Creates 400 starts and adds them to rocketPane
        this.createStar(rocketPane);
        rocketPane.getChildren().addAll( earthCurve, flame, flame2, rocketBody, rocketTop, rocketBottom, windowBackground, window, personHead, personBody, eyeL, eyeR, end);
    }

    //Rocket animation logic
    public void setYLoc(double x){
        rocketBody.setY(rocketBody.getY()-x);
        rocketTop.setLayoutY(rocketTop.getLayoutY() - x);
        rocketBottom.setLayoutY(rocketBottom.getLayoutY()-x);
        window.setCenterY(window.getCenterY() - x);
        windowBackground.setCenterY(windowBackground.getCenterY()-x);
        personHead.setCenterY(personHead.getCenterY()-x);
        personBody.setY(personBody.getY() - x);
        eyeR.setCenterY(eyeR.getCenterY()-x);
        eyeL.setCenterY(eyeL.getCenterY()-x);

        rocketBlast();
        flame.setLayoutY((flame.getLayoutY()-x));
        flame2.setLayoutY(flame.getLayoutY()-x);

    }

    //Created this method so the flames from the rocket only appear when the rocket has launched
    public void rocketBlast(){

            //I CHOSE to not make these constants because it is significantly harder to read as constants (trust me I
            //tried) because the polygons use vertices to create the shape
            flame.getPoints().addAll(
                    530.0, 560.0,
                    530.0, 600.0,
                    522.5, 575.0,
                    518.25, 585.0,
                    515.0, 575.0,
                    511.25, 585.0,
                    507.5, 575.0,
                    500.0, 600.0,
                    500.0, 560.0

            );
            //I CHOSE to not make these constants because it is significantly harder to read as constants (trust me I
            //tried) because the polygons use vertices to create the shape
            flame2.getPoints().addAll(
                    530.0, 560.0,
                    528.0, 590.0,
                    522.5, 565.0,
                    518.25, 575.0,
                    515.0, 565.0,
                    511.25, 575.0,
                    507.5, 565.0,
                    500.0, 590.0,
                    502.0, 560.0

            );

            flame.setFill(Color.RED);
            flame2.setFill(Color.ORANGE);


    }

    //Creates starts and adds them to the rocketPane
    public void createStar(Pane rocketPane) {
        for(int i = 1; i <= Constants.NUM_STARS; i++){
            star = new Circle();
            star.setCenterX(Math.random()*1001);
            star.setCenterY(Math.random()*1001);
            star.setFill(Color.WHITE);
            star.setStroke(Color.WHITE);
            rocketPane.getChildren().add(star);
        }
    }

    //Allows the user to toggle between three colors
    public void setColor() {
        if(color % 3 == 1){
            rocketBody.setFill(Color.PURPLE);
            rocketTop.setFill(Color.PURPLE);
            rocketBottom.setFill(Color.PURPLE);
            personHead.setFill(Color.PURPLE);
            personBody.setFill(Color.PURPLE);
            color++;
        } else if(color % 3 == 0) {
            rocketBody.setFill(Color.RED);
            rocketTop.setFill(Color.RED);
            rocketBottom.setFill(Color.RED);
            personHead.setFill(Color.RED);
            personBody.setFill(Color.RED);
            color++;


        } else {
            rocketBody.setFill(Color.GRAY);
            rocketTop.setFill(Color.GRAY);
            rocketBottom.setFill(Color.GRAY);
            personHead.setFill(Color.SANDYBROWN);
            personBody.setFill(Color.SANDYBROWN);
            color++;

        }
    }

    //Returns true if the rocket has exited the screen
    public boolean getEndRocket(){
        Boolean loop = false;
        if(flame.getLayoutY()==Constants.TOP_OF_SCREEN){
            loop = true;
            return loop;
        }

        return loop;
    }

    //Resets the location of the spacecraft to the bottom of the plane
    public void setLoop(boolean endOfScreen){
        if(endOfScreen){
            rocketBody.setY(Constants.BOTTOM_OF_SCREEN);
            rocketTop.setLayoutY(Constants.BOTTOM_OF_SCREEN_POLYGONS);
            rocketBottom.setLayoutY(Constants.BOTTOM_OF_SCREEN_POLYGONS);
            window.setCenterY(Constants.BOTTOM_OF_SCREEN);
            windowBackground.setCenterY(Constants.BOTTOM_OF_SCREEN);
            personHead.setCenterY(Constants.BOTTOM_OF_SCREEN);
            personBody.setY(Constants.BOTTOM_OF_SCREEN);
            eyeR.setCenterY(Constants.BOTTOM_OF_SCREEN);
            eyeL.setCenterY(Constants.BOTTOM_OF_SCREEN);
            flame.setLayoutY(Constants.BOTTOM_OF_SCREEN_POLYGONS);
            flame2.setLayoutY(Constants.BOTTOM_OF_SCREEN_POLYGONS);
        }
    }

    //Moves the earth
    public void setEarthCurve(double distance){
        earthCurve.setStartY(earthCurve.getStartY() + distance);
        earthCurve.setEndY(earthCurve.getEndY() + distance);
        earthCurve.setControlY(earthCurve.getControlY() + distance);
    }

    //Exits the app when the button is clicked
    private class ButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            Platform.exit();
        }
    }
}
