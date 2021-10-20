package fruitninja;

import cs15.prj.fruitNinjaSupport.CS15Fruit;

import static cs15.prj.fruitNinjaSupport.Constants.LEMON_PATH;

public class Lemons extends CS15Fruit implements Choppable {
    public Lemons(){
        super();
        ripen();
        wash();
    }

    @Override
    public java.lang.String getImage(){
        return LEMON_PATH;
    }


    @Override
    public int chop(){
        this.chopGraphically();
        this.splash();
        return 50;
    }
}
