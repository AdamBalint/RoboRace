
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
      sock = new Socket("localhost"/*"139.57.242.36"*/, 33333);
      Port port = new NetworkPort(sock);
      names = GameDialogs.showInputDialog("Enter Player Name", "Name of Player: ");
      port.send(names);
      new Player(names, port);
    } catch (IOException ex) {
      Logger.getLogger(RoboRace.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

}
