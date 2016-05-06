
import RoboRace.*;
import javax.swing.*;
import COSC3P40.graphics.*;
import COSC3P40.xml.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import networking.NetworkPort;

public class RoboRace {

  public static void main(String[] args) {
    try {
      JFrame.setDefaultLookAndFeelDecorated(true);
      JDialog.setDefaultLookAndFeelDecorated(true);
      ImageManager.getInstance().setImagePath("./Images/");
      XMLReader.setXMLPath("./");
      XMLReader.setXSDPath("./XSD/");
      int nHuman = 0;
      while (nHuman == 0 || nHuman > 4) {
        try {
          nHuman = Integer.parseInt(GameDialogs.showInputDialog("Number of human players", "Please, input the number of human players (1-4):"));
        } catch (Exception e) {
        };
      };
      String[] names = new String[nHuman];
      Port[] ports = new Port[nHuman];
      ServerSocket ssock = new ServerSocket(33333);
      for (int i = 0; i < nHuman; i++){
        ports[i] = new NetworkPort(ssock.accept());
        names[i] = ports[i].recieve();
        System.out.println("Player " + names[i] + " joined");
      }
      
      
      (new GameMaster(nHuman, names, ports)).run();
    } catch (IOException ex) {
      Logger.getLogger(RoboRace.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
