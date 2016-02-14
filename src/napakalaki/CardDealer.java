package napakalaki;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CardDealer {
    
    private static final CardDealer instance = new CardDealer();
    private ArrayList<Treasure> unusedTreasures=new ArrayList();
    private ArrayList<Treasure> usedTreasures = new ArrayList();
    private ArrayList<Monster> unusedMonsters;
    private ArrayList<Monster> usedMonsters = new ArrayList();
    private ArrayList<Cultist> unusedCultists;

    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/
    
    CardDealer(){
        
    }
    public static CardDealer cardDealer(){
        return instance;
    }
    private void initMonsterCardDeck(){
        
        //Inicializamos los monstruos con en Array que había en Napakalaki.java
        unusedMonsters=new ArrayList();
        
        SpecificBadConsequence specificBadConsequence = new SpecificBadConsequence ("Pierdes tu armadura visible y otra oculta",0,new ArrayList(Arrays.asList(TreasureKind.ARMOR)),new ArrayList(Arrays.asList(TreasureKind.ARMOR)));
        Prize prize = new Prize (2,1);
        unusedMonsters.add(new Monster("3 Byckees de bonanza",8, specificBadConsequence, prize,0));
        
        specificBadConsequence = new SpecificBadConsequence ("Embobados con el lindo primigenio te descartas de tu casco visible",0,new ArrayList(Arrays.asList(TreasureKind.ARMOR)),new ArrayList());
        prize = new Prize (1,1);
        unusedMonsters.add(new Monster("Chibithulu",2, specificBadConsequence, prize,0));
  
        specificBadConsequence = new SpecificBadConsequence ("El primordial bostezo contagioso. Pierdes el calzado visible",0, new ArrayList(Arrays.asList(TreasureKind.SHOES)),new ArrayList());
        prize = new Prize (1,1);
        unusedMonsters.add(new Monster("El sopor de Dunwich",2, specificBadConsequence, prize,0));

        specificBadConsequence = new SpecificBadConsequence ("Te atrapan para llevarte de fiesta y te dejan caer en mitad del vuelo. Descarta 1 mano visible y 1 mano oculta",0,new ArrayList(Arrays.asList(TreasureKind.ONEHAND)),new ArrayList(Arrays.asList(TreasureKind.ONEHAND)));
        prize = new Prize (4,1);
        unusedMonsters.add(new Monster("Ángeles de al noche ibicenca",14, specificBadConsequence, prize,0));

        NumericBadConsequence numericBadConsequence = new NumericBadConsequence ("Pierdes todos tus tesoros visibles",0,5,0);
        prize = new Prize (3,1);
        unusedMonsters.add(new Monster("El gorrón en el umbral",10, numericBadConsequence, prize,0));
    
   
        specificBadConsequence = new SpecificBadConsequence ("Pierdes la armadura visible",0,new ArrayList(Arrays.asList(TreasureKind.ARMOR)),new ArrayList());
        prize = new Prize (2,1);
        unusedMonsters.add(new Monster("H.P.Munchcraft",6, specificBadConsequence, prize,0));
    
   
        specificBadConsequence = new SpecificBadConsequence ("Sientes bichos bajo la ropa. Descarta la aramdura visible",0,new ArrayList(Arrays.asList(TreasureKind.ARMOR)),new ArrayList());
        prize = new Prize (1,1);
        unusedMonsters.add(new Monster("Bichgooth",2, specificBadConsequence, prize,0));
    
    
        numericBadConsequence = new NumericBadConsequence ("Pierdes 5 niveles y 3 tesoros visibles",5,3,0);
        prize = new Prize (4,2);
        unusedMonsters.add(new Monster("El rey de rosa",13, numericBadConsequence, prize,0));
    
    
        numericBadConsequence = new NumericBadConsequence ("Toses los pulmones y pierdes 2 niveles",2,0,0);
        prize = new Prize (1,1);
        unusedMonsters.add(new Monster("La que redacta en las tinieblas",2, numericBadConsequence, prize,0));
    

        DeathBadConsequence deathBadConsequence = new DeathBadConsequence ("Estos monstruos reusltan bastante superficiales y te aburren mortalmente, estas muerto",true);
        prize = new Prize (2,1);
        unusedMonsters.add(new Monster("Los Hondos",8, deathBadConsequence, prize,0));
    
  
        numericBadConsequence = new NumericBadConsequence ("Pierdes 2 niveles y 2 tesoros ocultos",2,0,2);
        prize = new Prize (2,1);
        unusedMonsters.add(new Monster("Semillas Cthulhu",4, numericBadConsequence, prize,0));
    
   
        specificBadConsequence = new SpecificBadConsequence ("Te intentas escaquear. Pierdes una mano visible",0,new ArrayList(Arrays.asList(TreasureKind.ONEHAND)),new ArrayList());
        prize = new Prize (2,1);
        unusedMonsters.add(new Monster("Dameargo",1, specificBadConsequence, prize,0));
    
   
        numericBadConsequence = new NumericBadConsequence ("Da mucho asquito. Pierdes 3 niveles",3,0,0);
        prize = new Prize (1,1);
        unusedMonsters.add(new Monster("Pollipólipo volante",3, numericBadConsequence, prize,0));
    
  
        deathBadConsequence = new DeathBadConsequence ("No le hace gracia que pronuncien mal su nombre. Estas muerto",true);
        prize = new Prize (3,1);
        unusedMonsters.add(new Monster("Yskhtihyssg-Goth",12, deathBadConsequence, prize,0));
    
    
        deathBadConsequence = new DeathBadConsequence ("La familia te atrapa. Estas muerto",true);
        prize = new Prize (4,1);
        unusedMonsters.add(new Monster("Famila feliz",1, deathBadConsequence, prize,0));
    
   
        specificBadConsequence = new SpecificBadConsequence ("La quinta directiva primaria te obliga a perder 2 niveles y un tesoro 2 manos visible",2,new ArrayList(Arrays.asList(TreasureKind.BOTHHANDS)),new ArrayList());
        prize = new Prize (2,1);
        unusedMonsters.add(new Monster("Roboggoth",8, specificBadConsequence, prize,0));
    
   
        specificBadConsequence = new SpecificBadConsequence ("Te asusta en la noche. Pierdes un casco visible",0,new ArrayList(Arrays.asList(TreasureKind.HELMET)),new ArrayList());
        prize = new Prize (1,1);
        unusedMonsters.add(new Monster("El espia",5, specificBadConsequence, prize,0));
  
        numericBadConsequence = new NumericBadConsequence ("Menudo susto te llevas. Pierdes 2 niveles y 5 tesoros visibles",2,5,0);
        prize = new Prize (1,1);
        unusedMonsters.add(new Monster("El lenguas",20, numericBadConsequence, prize,0));
    
   
        numericBadConsequence = new NumericBadConsequence ("Te faltan manos para tanta cabeza. Pierdes 3 niveles y tus tesoros visibles de las manos",3,5,0);
        prize = new Prize (1,1);
        unusedMonsters.add(new Monster("Bicéfalo",20, numericBadConsequence, prize,0));
        
        specificBadConsequence = new SpecificBadConsequence ("Pierdes una mano visible",0,new ArrayList(Arrays.asList(TreasureKind.ONEHAND)), new ArrayList());
        prize = new Prize (3,1);
        unusedMonsters.add(new Monster("El mal indecible impronunciable",10, specificBadConsequence, prize, -2));
        
        numericBadConsequence = new NumericBadConsequence ("Pierdes tus tesoros visibles. Jajaja",0,5,0);
        prize = new Prize (2,1);
        unusedMonsters.add(new Monster("Testigos Oculares",6, numericBadConsequence, prize,2));
        
        deathBadConsequence = new DeathBadConsequence ("Hoy no es tu día de suerte. Mueres.",true);
        prize = new Prize (2,5);
        unusedMonsters.add(new Monster("El Gran Chtulhu",20, deathBadConsequence, prize,4));
        
        numericBadConsequence = new NumericBadConsequence ("Tu gobierno te recorta dos niveles",2,0,0);
        prize = new Prize (2,1);
        unusedMonsters.add(new Monster("Serpiente Político",8, numericBadConsequence, prize,-2));
        
        ArrayList<TreasureKind> v;
        v=new ArrayList();
        v.add(TreasureKind.ARMOR);
        v.add(TreasureKind.HELMET);
        ArrayList<TreasureKind> h;
        h = new ArrayList();
        h.add(TreasureKind.BOTHHANDS);
        h.add(TreasureKind.ONEHAND);
        specificBadConsequence = new SpecificBadConsequence ("Pierdes tu casco y tu armadura visible. Pierdes tus manos ocultas.",0,v,h);
        prize = new Prize (1,1);
        unusedMonsters.add(new Monster("Felpuggoth",2, specificBadConsequence, prize,5));
        
        numericBadConsequence = new NumericBadConsequence ("Pierdes 2 niveles",2,0,0);
        prize = new Prize (4,2);
        unusedMonsters.add(new Monster("Shoggoth",16, numericBadConsequence, prize,-4));
        
        numericBadConsequence = new NumericBadConsequence ("Pintalabios negro. Pierdes dos niveles",2,0,0);
        prize = new Prize (1,1);
        unusedMonsters.add(new Monster("Lilitagooth",2, numericBadConsequence, prize,3));
    }
    
    private void initTreasureCardDeck(){
        
        //Inicializamos los tesoros con en Array que había en Napakalaki.java
        
        
        unusedTreasures.add(new Treasure("¡Si mi amo!",4,TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Botas de investigacion",3,TreasureKind.SHOES));
        unusedTreasures.add(new Treasure("Capucha de Cthulhu",3,TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("A prueba de babas",2,TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("Botas de lluvia acida",1,TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Casco minero",2,TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Ametralladora Thompson",4,TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Camiseta de la UGR",1,TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("Clavo de rail ferroviario",3,TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Cuchillo de sushi arcano",2,TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Fez alópodo",3,TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Hacha prehistórica",2,TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("El aparato del Pr. Tesla",4,TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("Gaita",4,TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Insecticida",2,TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Escopeta de 3 cañones",4,TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Garabato místico",2,TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("La rebeca metálica",2,TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("Lanzallamas",4,TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Necro-comicón",1,TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Necronomicón",5,TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Linterna a dos manos",4,TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Necro-gnonicón",2,TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Necrotelecom",2,TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Mazo d los antiguos",3,TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Necroplayboychón",3,TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("porra preternatural",2,TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Shogulador",1,TreasureKind.BOTHHANDS));
        unusedTreasures.add(new Treasure("Varita de atizamiento",3,TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Tentáculo de pega",2,TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Zapato deja-amigos",1,TreasureKind.SHOES));
    }
    
    private void initCultistCardDeck(){
        
        unusedCultists=new ArrayList();
        
        unusedCultists.add(new Cultist("Sectario",1));
        unusedCultists.add(new Cultist("Sectario",2));
        unusedCultists.add(new Cultist("Sectario",1));
        unusedCultists.add(new Cultist("Sectario",2));
        unusedCultists.add(new Cultist("Sectario",1));
        unusedCultists.add(new Cultist("Sectario",1));
    }
    
    private void shuffleTreasures(){
        Collections.shuffle(unusedTreasures);
    }
    
    private void shuffleMonsters(){
        Collections.shuffle(unusedMonsters);
    }
    
    public static CardDealer getInstance(){
        return instance;
    }
    
    public Treasure nextTreasure(){
        if(!unusedTreasures.isEmpty()){//Si no hay tesoros sin usar
            for(int i=0;i<usedTreasures.size();i++){//recorremos los tesoros usados
                unusedTreasures.add(usedTreasures.get(i));//añade cada tesoro usado a la lista de tesoros sin usar
                usedTreasures.remove(usedTreasures.get(i));//y lo elimina de los usados
            }
            this.shuffleTreasures();
        }
        Treasure t=unusedTreasures.get(0);
        usedTreasures.add(t);
        unusedTreasures.remove(t);
        return t;
    }
        
    public Monster nextMonster(){
        if(!unusedMonsters.isEmpty()){
            for(int i=0;i<usedMonsters.size();i++){
                unusedMonsters.add(usedMonsters.get(i));
                usedMonsters.remove(usedMonsters.get(i));
            }
            this.shuffleMonsters();
        }
        Monster m=unusedMonsters.get(0);
        usedMonsters.add(m);
        unusedMonsters.remove(m);
        return m;
    }
    
    public void giveTreasureBack(Treasure t){
       this.usedTreasures.add(t);
    }
    
    public void giveMonsterBack (Monster m){
        this.usedMonsters.add(m);
    }

    public void initCards(){
        
        this.initTreasureCardDeck();
        this.initMonsterCardDeck();
        this.initCultistCardDeck();
        
    }
   
    private void shuffleCultists(){

        Collections.shuffle(unusedCultists);
        
    }
    
    public Cultist nextCultist(){
        if(unusedCultists.isEmpty()){
            this.initCultistCardDeck();
            this.shuffleCultists();
        }
        Cultist c = this.unusedCultists.get(0);
        this.unusedCultists.remove(c);
        return c;
    }
            
    
}