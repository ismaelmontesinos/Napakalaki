package napakalaki;

import GUI.Dice;
import GUI.NapakalakiView;
import GUI.PlayerNamesCapture;
import java.util.ArrayList;

public class Principal {
    public static void main (String[] args){
        Napakalaki game=Napakalaki.getInstance();
        NapakalakiView napakalakiView=new NapakalakiView();
        Dice.createInstance(napakalakiView);
        ArrayList<String> names;
        PlayerNamesCapture namesCapture=new PlayerNamesCapture (napakalakiView,true);
        
        names = namesCapture.getNames();
        
        game.initGame(names);
        
        napakalakiView.setNapakalaki(game);
        napakalakiView.setVisible(true);
    }
}
