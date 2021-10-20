package fruitninja;

import cs15.prj.fruitNinjaSupport.CS15Fruit;

import static cs15.prj.fruitNinjaSupport.Constants.PEACH_PATH;

//(Lemons, Pears, and Apples classes are all the same)
//Each fruit class has to inherits getImage from the CS15Fruit god class, and implements the Fruit interface
//Call super() in the constructor so whenever a peach is created it is instantly washed and ripened.
public class Peaches extends CS15Fruit implements Choppable {
    public Peaches(){
        super();
        ripen();
        wash();

    }

    //Return the path to the desired fruit
    @Override
    public java.lang.String getImage(){
        return PEACH_PATH;
    }



    //Chops the fruit, adds a splash, and returns the score of the fruit.
    @Override
    public int chop(){
        this.chopGraphically();
        this.splash();
        return 10;
    }

}
