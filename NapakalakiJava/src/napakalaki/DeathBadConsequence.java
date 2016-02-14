package napakalaki;

public class DeathBadConsequence extends NumericBadConsequence{
    public DeathBadConsequence(String t, boolean d) {
        super(t,Player.MAXLEVEL,MAXTREASURES,MAXTREASURES);
    }
    
    
    @Override
    public String toString(){
        return super.toString() +"\n\tMuerte: " + super.getDeath();
     }
    
}
