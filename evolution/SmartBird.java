package evolution;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

/*
Contains the neural network for the birds - gets the inputs, initializes the weights for every population, calculates
the activated layer,  output, determines whether the bird should jump or not, and passes the weights of the best two
birds onto the next generation. Also sets the birds velocity and removes birds if they intersect with a pipe/ the bottom
of the screen.
 */
public class SmartBird{

    //Declare instance of game pane
    Pane _game;

    //ArrayList of smartBirds
    ArrayList<Bird> _smartBirds;

    //Array of inputs
    double[] _inputs;

    //Weights of the two fittest birds
    ArrayList<double[][]> _syn0SmartBird;
    ArrayList<double[][]> _syn1SmartBird;

    //Initial weight/ weight passed on
    ArrayList<double[][]> _syn0BirdWeight;
    ArrayList<double[][]> _syn1BirdWeight;

    //Smart bird constructor
    public SmartBird(Pane game) {
        //Instantiate game pane
        _game = game;

        //Instantiate smartBird ArrayList
        _smartBirds = new ArrayList<>();

        //Instantiate inputs array
        _inputs = new double[Constants.NUM_INPUTS];

        //Instantiate bird weights
        _syn0BirdWeight = new ArrayList<>();
        _syn1BirdWeight = new ArrayList<>();

        //Generate birds/set the weights of the new population of birds
        this.generateSmartBirds();
        this.setWeight();

    }

    //Generate birds/set initial position and opacity
    public void generateSmartBirds() {

        for(int i = 0; i < Constants.NUM_SMART_BIRDS; i++) {
            _smartBirds.add(new Bird(_game));

            for(int j = 0; j < Constants.NUM_SHAPES; j++) {

                _smartBirds.get(i).getBird()[j].setLayoutY(Constants.SMART_BIRD_START);
                _smartBirds.get(i).getBird()[j].setOpacity(Constants.SMART_BIRD_OPACITY);

            }

        }
    }

    //Set the velocity for each smartBird
    public void setVelocity() {

        for(int i = 0; i < _smartBirds.size(); i++) {

            _smartBirds.get(i).velocity();

        }
    }

    //Set the inputs for each bird
    public void setInputs(Rectangle[] closestPipe) {

        for(int i = 0; i < _smartBirds.size(); i++) {

            _inputs[0] = closestPipe[0].getX();
            _inputs[1] = closestPipe[0].getHeight();
            _inputs[2] = closestPipe[1].getHeight();
            _inputs[3] = _smartBirds.get(i).getBird()[0].getLayoutY();

            //Call forwardPropagation foreach bird
            forwardPropagation(_inputs, i);
        }
    }

    //Set the weight for each bird once a new population is created, if first population randomly generate an array of
    //random values between 1 and 0, if not set the weight for each bird of the population to be the two last surviving
    //birds
    public void setWeight() {

        //If it is the first generation of birds
        if(_syn0SmartBird == null) {

            //Loop through each bird
            for (int m = 0; m < _smartBirds.size(); m++) {

                double[][] syn0 = new double[Constants.HIDDEN_LAYER][Constants.NUM_INPUTS];
                double[][] syn1 = new double[1][Constants.HIDDEN_LAYER];

                //Loop through the weight array and assign values
                for (int row = 0; row < Constants.HIDDEN_LAYER; row++) {
                    for (int col = 0; col < Constants.NUM_INPUTS; col++) {
                        syn0[row][col] = (Math.random() * 2 - 1.0);
                    }
                }

                //Loop through the second weight array and assign values
                for (int i = 0; i < 1; i++) {
                    for (int j = 0; j < Constants.HIDDEN_LAYER; j++) {
                        syn1[i][j] = (Math.random() * 2 - 1.0);
                    }
                }

                //Store the weights of the bird
                _syn0BirdWeight.add(syn0);
                _syn1BirdWeight.add(syn1);

            }
        } else {

            //If it is not the first generation of birds
            //Loop through each smartBird
            for(int i = 0; i < _smartBirds.size(); i++) {

                double[][] syn0 = new double[Constants.HIDDEN_LAYER][Constants.NUM_INPUTS];
                double[][] syn1 = new double[1][Constants.HIDDEN_LAYER];


                //If it is the first 25 birds
                if(i < _smartBirds.size()/2) {

                    //Loop through the first weight array
                    for (int row = 0; row < Constants.HIDDEN_LAYER; row++) {
                        for (int col = 0; col < Constants.NUM_INPUTS; col++) {
                            //Assign the weight to be the weight of the last bird alive + mutated value
                            syn0[row][col] = _syn0SmartBird.get(0)[row][col] + Math.random() * Constants.MUTATE_CONSTANT - 1.0;
                        }
                    }

                    //Loop through the second weight array
                    for (int l = 0; l < 1; l++) {
                        for (int j = 0; j < Constants.HIDDEN_LAYER; j++) {
                            //Assign the weight to be the second weight + mutated value
                            syn1[l][j] = _syn1SmartBird.get(0)[l][j] + Math.random() * Constants.MUTATE_CONSTANT - 1.0;
                        }
                    }
                    //For the second half of the birds
                } else {

                    //Loop through the first weight array
                    for (int row = 0; row < Constants.HIDDEN_LAYER; row++) {
                        for (int col = 0; col < Constants.NUM_INPUTS; col++) {
                            //Assign the weight of the bird to be the weight of second to last bird alive + mutated value
                            syn0[row][col] = _syn0SmartBird.get(1)[row][col] + Math.random() * Constants.MUTATE_CONSTANT - 1.0;
                        }
                    }

                    //Loop through the second weight array
                    for (int l = 0; l < 1; l++) {
                        for (int j = 0; j < Constants.HIDDEN_LAYER; j++) {
                            //Assign the weight of the bird to be the weight of second to last bird alive + mutated value
                            syn1[l][j] = _syn1SmartBird.get(1)[l][j] + Math.random() * Constants.MUTATE_CONSTANT - 1.0;
                        }
                    }
                } //end of if/else

                //Add the new weights to the ArrayList of weights
                _syn0BirdWeight.add(syn0);
                _syn1BirdWeight.add(syn1);

            } // end of smartBird for loop

            //Remove the last weights passed on
            _syn0BirdWeight.remove(0);
            _syn0BirdWeight.remove(0);
            _syn1BirdWeight.remove(0);
            _syn1BirdWeight.remove(0);

        }
    }

    //Calculates the hiddenLayer, output, and calls the jump method
    public void forwardPropagation(double[] inputs, int i) {

        //Declare output array and activated hiddenLayer array
        double[] output;
        double[] activatedHiddenLayer;

        //Take the dotProduct of the given bird weight and the input of the bird and activate it
        activatedHiddenLayer = activate(dotProduct(_syn0BirdWeight.get(i), inputs));

        //Take the dotProduct of the second array of weights and the activated hiddenLayer
        output = activate(dotProduct(_syn1BirdWeight.get(i), activatedHiddenLayer));

        //Determines whether a bird should jump or not
        this.jump(output, i);
    }

    //Sets the birds velocity based on the output value
    public void jump(double[] output, int i) {

        if(output[0] > 0.5) {

            _smartBirds.get(i)._velocity = Constants.REBOUND_VELOCITY;
        }
    }

    //Gets the weights of the last two birds on the screen
    public void getFittest() {

        if(_syn0BirdWeight.size() == 2) {

            _syn0SmartBird = _syn0BirdWeight;
            _syn1SmartBird = _syn1BirdWeight;

        }

    }

    //Checks to see if a bird intersected with a pipe/ the top of the screen/ bottom of the screen
    public void checkIntersect(Rectangle[] closestPipe) {

        //Loop through the smartBird ArrayList
        for(int i = 0; i < _smartBirds.size(); i++) {

            //Loop through the pipe array
            for(int j = 0; j < Constants.SIZE_PIPE_ARRAY; j++) {

                //If the bird is above the top of the screen set its position to be the top of the screen
                if (_smartBirds.get(i).getBird()[0].getLayoutY() < 0) {
                    _smartBirds.get(i).getBird()[0].setLayoutY(0);
                }

                //If a pipe intersects with a bird
                if (closestPipe[j].intersects(_smartBirds.get(i).getBird()[0].getLayoutX(),
                        _smartBirds.get(i).getBird()[0].getLayoutY() - Constants.BIRD_RADIUS,
                        _smartBirds.get(i).getBird()[0].getRadius(),
                        _smartBirds.get(i).getBird()[0].getRadius()*2) ||
                        _smartBirds.get(i).getBird()[0].getLayoutY() > Constants.SCREEN_WIDTH) {

                    //Get the two fittest birds if only two remain
                    this.getFittest();

                    //Remove the bird graphically
                    _game.getChildren().removeAll(_smartBirds.get(i).getBird()[0], _smartBirds.get(i).getBird()[1],
                            _smartBirds.get(i).getBird()[2]);

                    //If there are only two birds left remove logically, but leave their weights in the array
                    if(_syn0BirdWeight.size() == 2) {
                        _smartBirds.remove(i);
                        break;

                    }

                    //Remove the bird and its weights logically
                    _smartBirds.remove(i);
                    _syn0BirdWeight.remove(i);
                    _syn1BirdWeight.remove(i);

                    break;

                }
            }
        }
    }

    //Converts passed in array values into a number between 0 and 1
    public double[] activate(double[] hiddenLayer) {

        double[] activatedLayer = new double[Constants.HIDDEN_LAYER];

        for(int i = 0; i < hiddenLayer.length; i++) {

            activatedLayer[i] = Math.exp(hiddenLayer[i])/(Math.exp(hiddenLayer[i]) + 1.0);
        }

        return activatedLayer;

    }

    //Takes the dot product of the two given arrays
    public double[] dotProduct(double[][] syn, double[] inputs) {

        int expectedRow = 0;

        double[] hiddenLayer = new double[Constants.HIDDEN_LAYER];

        //Loop through the weight array
        for(int row = 0; row < syn.length; row++) {
            for(int col = 0; col < syn[0].length; col++) {

                //Loop through the input array
                for(int z = 0; z < inputs.length; z++) {

                    //If it is the desired column and row
                    if(col == z && row == expectedRow) {

                        //Loop through input array
                        for(int j = 0; j < inputs.length; j++) {

                            //Calculate the value of the output/ hiddenLayer
                            hiddenLayer[row] += syn[row][j] * inputs[j];
                        }
                        expectedRow++;
                    }
                }
            }
        }
        return hiddenLayer;
    }

}
