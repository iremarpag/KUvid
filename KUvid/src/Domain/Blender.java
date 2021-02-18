package Domain;

import java.io.Serializable;

public class Blender implements Serializable {
    private boolean isOpen;


    public void openBlender(){
        isOpen = true;
    }

    public void closeBlender(){
        isOpen = false;
    }

    public Blender(){

    }

    public int blend(int first, int sec){
        //returns the change in the small number

        return Math.abs(sec - first) + 1;
    }

    public boolean open(){
        return isOpen;
    }

}
