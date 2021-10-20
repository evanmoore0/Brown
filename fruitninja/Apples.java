package fruitninja;

import cs15.prj.fruitNinjaSupport.CS15Fruit;

import static cs15.prj.fruitNinjaSupport.Constants.*;

public class Apples extends CS15Fruit implements Choppable {

    public Apples(){
        super();
        ripen();
        wash();

    }

    @Override
    public java.lang.String getImage(){

        return APPLE_PATH;

    }



    public int chop(){
        this.chopGraphically();
        this.splash();
        return 1000;
    }
}
