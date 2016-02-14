package napakalaki;

import java.util.ArrayList;


public class SpecificBadConsequence extends BadConsequence{
    ArrayList<TreasureKind> specificHiddenTreasures = new ArrayList();
    ArrayList<TreasureKind> specificVisibleTreasures = new ArrayList();
    
    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/
    
    
    public SpecificBadConsequence(String t, int l, ArrayList<TreasureKind> tVisible, ArrayList<TreasureKind> tHidden){
        super(t,l);
        this.specificHiddenTreasures=tHidden;
        this.specificVisibleTreasures=tVisible;
    }

    
    @Override
    public boolean isEmpty() {
        return this.specificHiddenTreasures.isEmpty() && this.specificVisibleTreasures.isEmpty();
    }

    
    @Override
    public void substractVisibleTreasure(Treasure t) {
        if(specificVisibleTreasures.contains(t.getType()))
            specificVisibleTreasures.remove(t.getType());
    }

    @Override
    public void substractHiddenTreasure(Treasure t) {
        if(specificHiddenTreasures.contains(t.getType()))
            specificHiddenTreasures.remove(t.getType());
    }
    
    @Override
    public BadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h) {
        BadConsequence spebc = new SpecificBadConsequence(null,0, new ArrayList(), new ArrayList());
        ArrayList<TreasureKind> tkvisible = new ArrayList();
        ArrayList<TreasureKind> tkhidden = new ArrayList();
        
        for (Treasure t:v)
            tkvisible.add(t.getType());
        for (Treasure t : h)
            tkhidden.add(t.getType());
        
        ArrayList<TreasureKind> visibleTreasures = new ArrayList();
        ArrayList<TreasureKind> hiddenTreasures = new ArrayList();
            
            if(!this.specificVisibleTreasures.isEmpty()){
                for (TreasureKind specificVisibleTreasure : specificVisibleTreasures) {
                    if (tkvisible.contains(specificVisibleTreasure)) {
                        visibleTreasures.add(specificVisibleTreasure);
                    }
                }
            }
            if(!this.specificHiddenTreasures.isEmpty()){
                for (TreasureKind specificHiddenTreasure : specificHiddenTreasures) {
                    if (tkhidden.contains(specificHiddenTreasure)) {
                        hiddenTreasures.add(specificHiddenTreasure);
                    }
                }
            }
            
            spebc = new SpecificBadConsequence(super.getText(),0,visibleTreasures,hiddenTreasures);
        
        return spebc;
    }
    
    @Override
    public String toString(){
        return super.toString()+ "\n\tTesoros Visibles Específicos: "+this.specificVisibleTreasures+"\n\tTesoros Ocultos Específicos: "+this.specificHiddenTreasures;
     }
}
