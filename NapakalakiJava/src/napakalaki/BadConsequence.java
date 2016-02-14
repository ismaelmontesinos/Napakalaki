package napakalaki;

import java.util.ArrayList;


public abstract class BadConsequence {
    public static final int MAXTREASURES=10;
    protected String text;
    private int levels;
    private boolean death;

    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/
    
    
    public BadConsequence (String t,int levels){
        this.text=t;
        this.levels=levels;
        death=false;
    }
    
    public int getNVisibleTreasures(){
        return 0;
    }
    
    public int getNHiddenTreasures(){
        return 0;
    }
    
    public ArrayList<TreasureKind> getSpecificVisibleTreasures(){
        return null;
    }

    public ArrayList<TreasureKind> getSpecificHiddenTreasures(){
        return null;
    }
    
    public boolean isEmpty(){
        return levels==0 &&death==false;
    }
   
    
    public String getText(){
        return text;
    }
    
    
    public int getLevels(){
        return levels;
    }
     
    
    public boolean getDeath(){
        return death;
    }
    
    
    public abstract void substractVisibleTreasure(Treasure t);

    
    public abstract void substractHiddenTreasure(Treasure t);
    
    public abstract BadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h);


    public String toString(){
        return "\n\tTexto: "+text+"/tNiveles Perdidos: "+levels ;
     }
}
