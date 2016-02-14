/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki;

/**
 *
 * @author Admin1
 */

//INICIO EXAMEN
public class PlayerMiope extends Player{
    int nivel;
    protected String name;
    private boolean dead;
    private boolean canISteal;
    
    public PlayerMiope(Player p){//SI TIENE LOS DOS ARMOR EQUIPADOS, EL NIVEL ES EL DE PLAYER
                                //EN CASO DE QUE NO, CUANDO LLEUGE A 7 EL NIVEL VUELVE A UNO
        super(p);
        nivel=p.getLevels();
        name=p.getName();
        dead=p.isDead();
        canISteal=p.canISteal();
        
    }
    
    public boolean canMakeArmorVisible(Treasure t){
    TreasureKind treasureType = t.getType();
        boolean allowed = true;
        if (treasureType == TreasureKind.ONEHAND){
                if ( super.howManyVisibleTreasures(TreasureKind.ONEHAND) == 2)
                    allowed = false;
                if ( super.howManyVisibleTreasures(TreasureKind.BOTHHANDS) == 1)
                    allowed = false;
        } 
        else
        {
            if (treasureType == TreasureKind.BOTHHANDS){
                if ( super.howManyVisibleTreasures(TreasureKind.ONEHAND) != 0 )
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
    
    @Override
    protected int howManyVisibleTreasures(TreasureKind tk){
        int contador=super.howManyVisibleTreasures(tk);
        if(tk==TreasureKind.ARMOR)
            contador--;
        return contador;
    }
    @Override
    public boolean shouldConvert(){
        return false;
    }
    
    public int getMiopeLevel(){
        if(nivel==7){
            nivel=1;
        }
        return nivel;
    }
    
//FINAL EXAMEN
}
