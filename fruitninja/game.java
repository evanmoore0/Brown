package fruitninja;

import cs15.prj.fruitNinjaSupport.Blade;
import cs15.prj.fruitNinjaSupport.CS15Game;

public class game extends CS15Game {

    //Declare instances of the fruits, bomb, and blade
    Apples apple;
    Pears pear;
    Lemons lemon;
    Peaches peach;
    Bomb bomb;
    Blade blade;

    //Game constructor, creates an instances of blade, sets its name, starts the game, launches items, and adds a blade
    public game() {
        blade = new Blade();
        blade.setName("fruitnaenae");
        this.startGame();
        this.launchItem();
        this.addBlade(blade);
    }


    /**
     * Create an instance of the Fruit interface and call the chop method on what is returned from getChoppable(). This
     * is where I used polymorphism so I only have to call the chop method once.
     */

    @Override
    public int onBladeContact() {
        Choppable choppable = this.getChoppable();
        return choppable.chop();

    }


    /**
     * Creates an instance of the fruits and the bomb, and returns one based on a random integer so each time an item is
     * launched the user doesn't know what it will be.
     */
    @Override
    public java.lang.Object launchItem() {
        apple = new Apples();
        pear = new Pears();
        lemon = new Lemons();
        peach = new Peaches();
        bomb = new Bomb();

        int i = (int) (Math.random()*5);

        switch(i){
            case 0:
                return bomb;
            case 1:
                return apple;
            case 2:
                return pear;
            case 3:
                return lemon;
            case 4:
                return peach;
        }
        return apple;
    }
}




