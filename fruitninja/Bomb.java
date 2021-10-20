package fruitninja;

import cs15.prj.fruitNinjaSupport.CS15Bomb;

//Implements the
public class Bomb extends CS15Bomb implements Choppable {

    public Bomb(){
    }

    //Explodes the bomb and returns a score of 0
    @Override
    public int chop(){
        this.explode();
        return 0;
    }

}
