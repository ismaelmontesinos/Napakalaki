package napakalaki;

import java.util.ArrayList;
import java.util.Random;
import GUI.Dice;


public class Player {
    public static final int MAXLEVEL=10;
    
    protected String name;
    private int level;
    private boolean dead;
    private boolean canISteal;

    ArrayList<Treasure> hiddenTreasures;
    ArrayList<Treasure> visibleTreasures;
    protected Player enemy;
    private BadConsequence pendingBadConsequence;
    private CardDealer dealer;
    
    //EXAMEN
    private int haSubido;
    //FIN EXAMEN
        
    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/
    
    public Player(String name){
        this.name=name;
        level=1;
        dead=true;
        canISteal= true;
        hiddenTreasures = new ArrayList();
        visibleTreasures = new ArrayList();
        haSubido=0;
    }
    
    
    public Player (Player p){
        this.name = p.name;
        this.level = p.level;
        this.canISteal = p.canISteal;
        this.enemy = p.enemy;
        this.hiddenTreasures.addAll(p.hiddenTreasures);
        this.visibleTreasures.addAll(p.visibleTreasures);
        this.pendingBadConsequence = p.pendingBadConsequence;
        this.dead = p.dead;
        this.haSubido=0;
    }
    
    
    public String getName(){
        return this.name;
    }
    
    
    private void bringTolife(){
        this.dead= false;
    }
    
    
    public int getCombatLevel(){
        int lv=0;
        for(Treasure t : visibleTreasures)
            lv=lv+t.getBonus();
        lv += level;
        if(lv>MAXLEVEL)
            return MAXLEVEL;
        else
            return lv;
    }
    
    
    private void incrementLevels(int l){
        if(level+l < MAXLEVEL)
            level += l;
        else
            level = MAXLEVEL;
    }
    
    
    private void decrementLevels(int l){
        level-=l;
        if(level<1)
            level=1;
    }
    
    
    public BadConsequence getPendingBadConsequence(){
        return this.pendingBadConsequence;
    }
    
    
    private void setPendingBadConsequence(BadConsequence b){
        pendingBadConsequence= b;
    }
    
    
    private void applyPrize(Monster m){
        int nLevels = m.getLevelsGained();//1
        this.incrementLevels(nLevels);//2
        int nTreasures = m.getTreasuresGained();//3
        if(nTreasures>0){
            dealer=CardDealer.getInstance();//4
            for(int i=0;i<nTreasures;i++){
                Treasure treasure=dealer.nextTreasure();//5
                hiddenTreasures.add(treasure);//6
            }
        }
    }
    
    
    private void applyBadConsquence(Monster m){
        BadConsequence badConsequence = m.getBadConsequence();//1
        int nLevels = badConsequence.getLevels();//2
        this.decrementLevels(nLevels);//3
        BadConsequence pendingBad = badConsequence.adjustToFitTreasureLists(visibleTreasures, hiddenTreasures);//4
        this.setPendingBadConsequence(pendingBad);
    }
    
    
    public boolean canMakeTreasureVisible(Treasure t){
       TreasureKind treasureType = t.getType();
        boolean allowed = true;
        if (treasureType == TreasureKind.ONEHAND){
                if ( howManyVisibleTreasures(TreasureKind.ONEHAND) == 2)
                    allowed = false;
                if ( howManyVisibleTreasures(TreasureKind.BOTHHANDS) == 1)
                    allowed = false;
        } 
        else
        {
            if (treasureType == TreasureKind.BOTHHANDS){
                if ( howManyVisibleTreasures(TreasureKind.ONEHAND) != 0 )
                    allowed = false;
                else if ( howManyVisibleTreasures(TreasureKind.BOTHHANDS) == 1)                
                    allowed = false;
            }
            else{
                if( howManyVisibleTreasures(t.getType()) == 1 && t.getType() != TreasureKind.ONEHAND )
                    allowed = false;
            }
        }
        return allowed;
    }
    
    
    protected int howManyVisibleTreasures(TreasureKind tKind){
        int contador= 0;
        for(Treasure treasure : this.visibleTreasures)
            if(treasure.getType()==tKind)
                contador++;
        
        return contador;
    }
    
    
    private void dieIfNoTreasure(){
        if(visibleTreasures.isEmpty() && hiddenTreasures.isEmpty()){
            this.dead=true;
        }
    }
    
    
    public boolean isDead(){
       return this.dead;
    }
    
    
   public ArrayList<Treasure> getHiddenTreasures(){
        return hiddenTreasures;
    }
   
   
    public ArrayList<Treasure> getVisibleTreasures(){
        return visibleTreasures;
    }
    
    
    public CombatResult combat(Monster m){
        int myLevel=this.getCombatLevel();//1.1.1
        int monsterLevel = this.getOponentLevel(m);//1.1.2
        CombatResult combatResult;
        
        if(myLevel>monsterLevel){
            this.applyPrize(m);//1.1.3
            if(level>=MAXLEVEL){
                combatResult=CombatResult.WINGAME;
            }
            else{
                combatResult=CombatResult.WIN;
            }
        }
        else{
            this.applyBadConsquence(m);
            if(this.shouldConvert()==true)
                combatResult=CombatResult.LOSEANDCONVERT;
            else
            combatResult=CombatResult.LOSE;
        }
        return combatResult;
    }
    
    
    public void makeTreasureVisible(Treasure t){
        boolean canI=this.canMakeTreasureVisible(t);
        if(canI){
            visibleTreasures.add(t);
            hiddenTreasures.remove(t);
        }
    }
    
    
    public void discardVisibleTreasure(Treasure t){
        visibleTreasures.remove(t);//1.2.1
        if((pendingBadConsequence!=null)&&(!pendingBadConsequence.isEmpty())){
            pendingBadConsequence.substractVisibleTreasure(t);//1.2.2
        }
        this.dieIfNoTreasure();//1.2.3
    }
    
    
    public void discardHiddenTreasure(Treasure t){
        hiddenTreasures.remove(t);//1.2.1
        if((pendingBadConsequence!=null)&&(!pendingBadConsequence.isEmpty())){
            pendingBadConsequence.substractHiddenTreasure(t);//1.2.2
        }
        this.dieIfNoTreasure();//1.2.3
    }
    
    
    public boolean validState(){
        return pendingBadConsequence == null || pendingBadConsequence.isEmpty() && hiddenTreasures.size() <= 4;
    }
    
    
    public void initTreasures(){
        CardDealer dealer=CardDealer.getInstance();//1
        Dice dice =Dice.getInstance();//2
        this.bringTolife();//3
        Treasure treasure = dealer.nextTreasure();//4
        hiddenTreasures.add (treasure);//5
        int number = dice.nextNumber();//6
        if(number > 1){
            treasure=dealer.nextTreasure();//7
            hiddenTreasures.add(treasure);//8
        }
        if(number==6){
            treasure = dealer.nextTreasure();//9
            hiddenTreasures.add(treasure);//10
        }
    }
    
    
    public int getLevels(){
        return this.level;
    }
    
    
    public Treasure stealTreasure(){
        boolean canI=this.canISteal();//1.1
        boolean canYou;
        Treasure treasure=null;
        if(canI){
            canYou = enemy.canYouGiveMeATreasure();//1.2
            if(canYou){
                treasure = enemy.giveMeATreasure();//1.3
                hiddenTreasures.add(treasure);//1.4
                this.haveStolen();//1.5
            }
        }
        return treasure;
    }
    
    
    public void setEnemy(Player enemy){
        this.enemy=enemy;
    }
    
    
   protected Treasure giveMeATreasure(){
        Random r1=new Random();
        Treasure t = hiddenTreasures.get(r1.nextInt(hiddenTreasures.size()));
        hiddenTreasures.remove(r1.nextInt(hiddenTreasures.size()));
        return t;
    }
   
   
    public boolean canISteal(){
        return canISteal;
    }
    
    
    protected boolean canYouGiveMeATreasure(){
        return !hiddenTreasures.isEmpty();
    }
    
    
    private void haveStolen(){
       canISteal= false;
    }
    
    
    public void discardAllTreasures(){
        for(Treasure treasure : visibleTreasures){//1.1
            this.discardVisibleTreasure(treasure);
        }
        for(Treasure treasure : hiddenTreasures){
            this.discardHiddenTreasure(treasure);
        }
    }
    
    
    protected int getOponentLevel(Monster m){
        return m.getCombatLevel();
    }
    
    
    public boolean shouldConvert(){
        Dice dice = Dice.getInstance();
        return dice.nextNumber()==1;
    }
    
    
    public Player getEnemy(){
        return enemy;
    }
    //EXAMEN
    public boolean subirDeNivel(Treasure t1, Treasure t2,CombatResult cr){
       boolean sube=false;
       int contador=0;
       if(!visibleTreasures.isEmpty()){//comprobamos que tenga tesoros para descartarse          
           contador++;
            visibleTreasures.remove(t1);
            if((pendingBadConsequence!=null)&&(!pendingBadConsequence.isEmpty())){
                pendingBadConsequence.substractVisibleTreasure(t1);
            }
       }
       else if(!hiddenTreasures.isEmpty()){//comprobamos que tiene tesoros para descartarse
           contador++;
            hiddenTreasures.remove(t1);
            if((pendingBadConsequence!=null)&&(!pendingBadConsequence.isEmpty())){
                pendingBadConsequence.substractHiddenTreasure(t1);//1.2.2
            }
            this.dieIfNoTreasure();
        }
       if(t1.getType()!=t2.getType()){
           if(!visibleTreasures.isEmpty()){//comprobamos que tenga tesoros para desargartarse           
                contador++;
                 visibleTreasures.remove(t1);
                 if((pendingBadConsequence!=null)&&(!pendingBadConsequence.isEmpty())){
                     pendingBadConsequence.substractVisibleTreasure(t1);
                 }
            }
                 
            else if(!hiddenTreasures.isEmpty()){//comprobamos que tiene tesoros para descartarse
                 contador++;
                 hiddenTreasures.remove(t1);
                 if((pendingBadConsequence!=null)&&(!pendingBadConsequence.isEmpty())){
                     pendingBadConsequence.substractHiddenTreasure(t1);
                 }
            }
           this.dieIfNoTreasure();
        }
       if(contador==2)
                sube=true;
           
       else
           sube=false;
       
       if(sube){
           if(cr==CombatResult.WIN)
               if(haSubido==0)//Creo una variable que recoja el numero de veces que le ha dado a subir
                   this.level+=2;
        }
       dealer.giveTreasureBack(t1);
       dealer.giveTreasureBack(t2);
       return sube;
    }
    //FIN EXAMEN
    
    @Override
    public String toString(){
        return "\n\tNombre: " + name + "\n\tNivel: " + Integer.toString(level)+"\n\tNivel de combate: "+this.getCombatLevel();
     }
}