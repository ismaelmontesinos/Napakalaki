package napakalaki;

public class Prize {
    private int treasures;
    private int level;
    
    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/
    
    public Prize(int t, int l){ 
        treasures= t;
        level= l;
    }
    
    public int getTreasures(){
        return treasures;
    }
    
    public int getLevel(){
        return level;
    }
    
    public String toString(){
        return "\n\tTesoros Ganados: " + Integer.toString(treasures) + "\n\tNiveles Ganados: " + Integer.toString(level);
     }

}
