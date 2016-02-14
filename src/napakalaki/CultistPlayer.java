package napakalaki;

import java.util.ArrayList;
import java.util.Random;

public class CultistPlayer extends Player{
    private static int totalCultistPlayers=0;
    private Cultist myCultistCard;
    
    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/
    
    public CultistPlayer(Player p, Cultist c) {
        super (p);
        myCultistCard = c; 
        this.totalCultistPlayers++;
    }
    
    @Override
    public int getCombatLevel() {
        int i=(int)(super.getCombatLevel()*1.2)+(myCultistCard.getGainedLevels()*totalCultistPlayers);
        return i;
    }
    
    
    protected int getOponentLevel(Monster m){
        return m.getCombatLevelAgainstCultistPlayer();
    }
    
    
    @Override
    public boolean shouldConvert(){
        return false;
    }
    
    
    @Override 
    protected Treasure giveMeATreasure(){
        Random r1=new Random();
        ArrayList<Treasure> gvt = super.getVisibleTreasures();
        int i=r1.nextInt(gvt.size());
        return gvt.get(i);
    }
    
    
    @Override 
    protected boolean canYouGiveMeATreasure(){
        return !super.enemy.getVisibleTreasures().isEmpty();
    }
    
    
    public int getTotalCultistPlayers(){
        return totalCultistPlayers;
    }
    public Cultist getMyCultistCard(){
        return myCultistCard;
    }
    
}
