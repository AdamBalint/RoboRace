import RoboRace.*;
import javax.swing.*;
import COSC3P40.graphics.*;
import COSC3P40.xml.*;

public class RoboRace {
    
    public static void main(String[] args) {
    	JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
	ImageManager.getInstance().setImagePath("./Images/");
	XMLReader.setXMLPath("./");
	XMLReader.setXSDPath("./XSD/");	
    	int nHuman = 0;
    	while (nHuman==0 || nHuman>4) {
	    	try {
	    		nHuman = Integer.parseInt(GameDialogs.showInputDialog("Number of human players","Please, input the number of human players (1-4):"));
	    	} catch(Exception e) {};
	};
	String[] names = new String[nHuman];
	Port[] ports = new Port[nHuman];
	for (int i=0; i<nHuman; i++) {
            names[i] = GameDialogs.showInputDialog("Player #" + (i+1),"Name of Player #" + (i+1) + ":");
            Channel c = new Channel();
            ports[i] = c.asPort1();
            new Player(names[i],c.asPort2());
        };
    	(new GameMaster(nHuman,names,ports)).run();
    }	   
}
