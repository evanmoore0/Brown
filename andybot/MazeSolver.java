package andybot;

import cs15.prj.andyBotSupport.AndyBot;
import cs15.prj.andyBotSupport.MazeSolverSupport;

public class MazeSolver extends MazeSolverSupport{

    public MazeSolver(AndyBot andyBot) {
        // This is the constructor.
        // **Please do not alter this part of the code**
        super();
        this.solve(andyBot);
    }


    public void solve(AndyBot andyBot) {
        //Navigate andybot through the maze
        andyBot.moveUp(1);
        andyBot.shuffleLeft(2);
        andyBot.moveUp(2);
        andyBot.shuffleLeft(1);
        andyBot.moveUp(1);
        andyBot.shuffleLeft(1);
        andyBot.moveUp(2);
        andyBot.shuffleRight(3);
        andyBot.moveUp(2);
        andyBot.shuffleRight(2);

        //Pass getHint() as a parameter to solveRoadBlock

        solveRoadBlock(getHint());


        //After solving the password move andybot to the finish line
        andyBot.moveUp(2);
    }


    public void solveRoadBlock(int x) {
        //Call enter password on the final block of the maze
        enterPassword(4*x-6);
    }
}
