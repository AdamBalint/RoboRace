
import RoboRace.*;
import javax.swing.*;
import COSC3P40.graphics.*;
import COSC3P40.midi.MidiManager;
import COSC3P40.xml.*;
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.*;
import networking.NetworkPort;

public class RoboRace {

  public static void main(String[] args) {
    JFrame.setDefaultLookAndFeelDecorated(true);
    JDialog.setDefaultLookAndFeelDecorated(true);
    ImageManager.getInstance().setImagePath("./Images/");
    XMLReader.setXMLPath("./");
    XMLReader.setXSDPath("./XSD/");

    MidiManager mm = MidiManager.getInstance();
    mm.setMidiPath("./Sounds&Midi/");

    try {
      Sequence s = MidiSystem.getSequence(new File("./Sounds&Midi/animaniacs.mid"));
      mm.play(s, true);
    } catch (InvalidMidiDataException ex) {
      Logger.getLogger(RoboRace.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(RoboRace.class.getName()).log(Level.SEVERE, null, ex);
    }

    String names;
    Socket sock;
    try {
      System.out.println("Client: Cnnecting...");
      sock = new Socket(GameDialogs.showInputDialog("Connect to server", "IP Address:"), Integer.parseInt(GameDialogs.showInputDialog("Enter Port Number", "Port Number")));
      Port port = new NetworkPort(sock);
      names = GameDialogs.showInputDialog("Enter Player Name", "Name of Player: ");
      System.out.println("Client: Sending name");
      port.send(names);
      new Player(names, port);
    } catch (IOException ex) {
      Logger.getLogger(RoboRace.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

}
