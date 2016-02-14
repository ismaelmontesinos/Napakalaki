package napakalaki;

import java.util.ArrayList;


public class NumericBadConsequence extends BadConsequence{
    int nVisibleTreasures;
    int nHiddenTreasures;
    
    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/
    
    public NumericBadConsequence(String t, int l, int tesoroVisible, int tesoroOculto) {
        super(t,l);
        this.nVisibleTreasures=tesoroVisible;
        this.nHiddenTreasures=tesoroOculto;
    }
    

    public int getnVisibleTreasures() {
        return nVisibleTreasures;
    }
    
    
    public int getnHiddenTreasures(){
        return nHiddenTreasures;
    }
    

    @Override
    public boolean isEmpty() {
        return this.nVisibleTreasures == 0 && this.nHiddenTreasures == 0;
    }
    

    @Override
    public void substractVisibleTreasure(Treasure t) {
        if(this.nVisibleTreasures >0)
            this.nVisibleTreasures -= 1;
    }

    
    @Override
    public void substractHiddenTreasure(Treasure t) {
        if(this.nHiddenTreasures > 0)
            this.nHiddenTreasures -= 1;    
    }
    

    @Override
    public BadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h) {
        BadConsequence bc = new NumericBadConsequence("",0,0,0);
        ArrayList<TreasureKind> tkvisible = new ArrayList();
        ArrayList<TreasureKind> tkhidden = new ArrayList();
        
        for (Treasure t : v)
            tkvisible.add(t.getType());
        for (Treasure t : h)
            tkhidden.add(t.getType());
        
        if(this.nHiddenTreasures != 0 || this.nVisibleTreasures != 0){
            int nvisible = 0;
            int nhidden = 0;
            if(this.nVisibleTreasures > 0){
                if(this.nVisibleTreasures > tkvisible.size())
                    nvisible = tkvisible.size();
                else
                    nvisible = this.nVisibleTreasures;
            }
            if(this.nHiddenTreasures > 0){
                if(this.nHiddenTreasures > tkhidden.size())
                    nhidden = tkhidden.size();
                else
                     nhidden = this.nHiddenTreasures;
            }
            bc = new NumericBadConsequence(super.getText(),0,nvisible,nhidden);
        }
        return bc;
    }
    
    
    @Override
    public String toString(){
        return super.toString()+ "\n\tTesoros Visibles: "+this.nVisibleTreasures+"\n\tTesoros Ocultos: "+this.nHiddenTreasures;
     }
}
