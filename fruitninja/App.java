package fruitninja;

import javafx.application.Application;
import javafx.stage.Stage;
import cs15.prj.fruitNinjaSupport.FruitNinjaFrame;

/**
 * Create an instance of game and add the game to the FruitNinjaFrame frame
 */
public class App extends Application {

	game _game;

	@Override
	public void start(Stage stage)  {
		FruitNinjaFrame frame = new FruitNinjaFrame(stage);
		// Your code goes below!!!
		_game = new game();
		frame.addGame(_game);

	}

	public static void main(String[] argv) {
		launch(argv);
	}

}
