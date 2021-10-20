package fruitninja;

import cs15.prj.fruitNinjaSupport.CS15Fruit;

import static cs15.prj.fruitNinjaSupport.Constants.PEAR_PATH;

public class Pears extends CS15Fruit implements Choppable {
    public Pears(){
        super();
        ripen();
        wash();

    }

    @Override
    public java.lang.String getImage(){

        return PEAR_PATH;

    }



    @Override
    public int chop(){
        this.chopGraphically();
        this.splash();
        return 20;
    }
}
