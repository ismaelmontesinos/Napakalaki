package napakalaki;

import java.util.ArrayList;
import java.util.Random;

public class Napakalaki {
    private static final Napakalaki instance = new Napakalaki();
    
    private Monster currentMonster;
    private CardDealer dealer=CardDealer.getInstance();
    private ArrayList<Player> players;
    private Player currentPlayer;
    
    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/
    
    private Napakalaki(){
    }
    
    
    public static Napakalaki getInstance(){
        return instance;
    }
    
    
    private void initPlayers(ArrayList<String> names){
        players=new ArrayList();
        Player p;
        for(String nombre : names){
            p=new Player (nombre);
            players.add(p);
        }
        nextPlayer();
    }
    
    
    private Player nextPlayer(){
        Random r1=new Random();
        int indice;
        if(this.currentPlayer==null){
            currentPlayer=players.get(r1.nextInt(players.size()));
            return currentPlayer;
        }
         else{
            indice = players.indexOf(currentPlayer);
            if(indice==(players.size()-1)){
                currentPlayer=players.get(0);
                return players.get(0);
            }
            else{
                currentPlayer=players.get(indice+1);
                return currentPlayer;
            }
         }
    }
    
    
    public boolean nextTurnAllowed(){
        if (currentPlayer == null)
            return true;
        else
            return currentPlayer.validState();
    }
    

    public void setEnemies(){
        int i;
        Random r1=new Random();
        for(int j=0;j<players.size();j++){
            i=r1.nextInt(players.size());
            if(j!=i){
                players.get(i).setEnemy(players.get(i));
            }
        }
    }
    
  
    public CombatResult developCombat(){
        CombatResult combatResult=currentPlayer.combat(currentMonster);//1.1
        dealer.giveMonsterBack(currentMonster);//1.2
        if(combatResult==combatResult.LOSEANDCONVERT){
            Cultist cultist=dealer.nextCultist();
            CultistPlayer cultistPlayer=new CultistPlayer(this.currentPlayer,cultist);
            int currentPlayerIndex = this.players.indexOf(this.currentPlayer);
            this.players.set(currentPlayerIndex,cultistPlayer);
            for(Player p: players){
                    if(p.getEnemy()==currentPlayer)
                        p.setEnemy(cultistPlayer);
            }
            this.currentPlayer=cultistPlayer;
        }
        return combatResult;
    }
    
    
    public void discardVisibleTreasures(ArrayList<Treasure> treasures){
        for(Treasure treasure : treasures){//1.1
            currentPlayer.discardVisibleTreasure(treasure);//1.2
            dealer.giveTreasureBack(treasure);//1.3
        }
    }
    
    
    public void discardHiddenTreasures (ArrayList<Treasure> treasures){
        for(Treasure treasure : treasures){//1.1
            currentPlayer.discardHiddenTreasure(treasure);//1.2
            dealer.giveTreasureBack(treasure);//1.3
        }
    }
    
    
    public void makeTreasuresVisible (ArrayList<Treasure> treasures){
        for (Treasure t : treasures){//1.1
            if(currentPlayer.canMakeTreasureVisible(t))
           currentPlayer.makeTreasureVisible(t);
        }
    }
    
    
    public void initGame (ArrayList<String> names){
        this.initPlayers(names);//1.1
        this.setEnemies();//1.2
        dealer.initCards();//1.3
        this.nextTurn();//1.4
    }
    
    
    public Player getCurrentPlayer(){
        return currentPlayer;
    }
    
    
    public Monster getCurrentMonster(){
        return currentMonster;
    }
    
    
    public boolean nextTurn(){
        boolean stateOK = this.nextTurnAllowed();//1.1
        if(stateOK){//opt
            currentMonster = dealer.nextMonster();//1.2
            currentPlayer = this.nextPlayer();//1.3
            boolean dead = currentPlayer.isDead();//1.4
            if(dead){
                 currentPlayer.initTreasures();//1.5
            }
        }
       return stateOK; 
    }
    
    
    public boolean endOfGame(CombatResult result){
        return result==CombatResult.WINGAME;
    }
    
    
}
