package napakalaki;


public class Monster {
    private String name;
    private int combatLevel;
    private BadConsequence badConsequence;
    private Prize prize;
    private int levelChangeAgainstCultistPlayer=0;
 
    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/

    public Monster (String n, int l, BadConsequence badConsequence, Prize p, int IC){
        this.name=n;
        this.combatLevel=l;
        this.badConsequence=badConsequence;
        this.prize=p;
        this.levelChangeAgainstCultistPlayer=IC;
    }
   
    public String getName(){
        return name;
    }
    
    public int getCombatLevel(){
        return combatLevel;
    }
    
    public BadConsequence getBadConsequence(){
        return badConsequence;
    }
    public Prize getPrize(){
        return prize;
    }
    
    public int getLevelsGained(){
        return prize.getLevel(); 
    }
    
    public int getTreasuresGained(){
        return prize.getTreasures();
    }
    
    public int getCombatLevelAgainstCultistPlayer(){
        return this.getCombatLevel()+levelChangeAgainstCultistPlayer;
    }
    public String toString(){
        return "\tNombre: " + name + "\n\tNivel: " + Integer.toString(combatLevel)+"\n\tBad Consequence: "+badConsequence+"\n\tPremio: "+prize;
     }
   
}