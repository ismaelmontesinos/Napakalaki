package napakalaki;



public class Treasure {
    private String name;
    private int bonus;
    private TreasureKind type;
    
    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/
    
    public Treasure(String n, int b, TreasureKind t){
        name= n;
        bonus= b;
        type= t;
    }
    
    
    public String getName(){
        return name;
    }
    
    
    public int getBonus(){
        return bonus;
    }
    
    
    public TreasureKind getType(){
        return type;
    }
    
    
    public String toString(){
        return name +"\n\tBonus:  "+bonus+"\n\tTipo de Tesoro: "+type;
    }
}
