
import RoboRace.*;
import javax.swing.*;
import COSC3P40.graphics.*;
import COSC3P40.midi.MidiManager;
import COSC3P40.xml.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.*;

public class RoboRace {
  
  
  
  public static void main(String[] args) {    
    JFrame.setDefaultLookAndFeelDecorated(true);
    JDialog.setDefaultLookAndFeelDecorated(true);
    ImageManager.getInstance().setImagePath("./Images/");
    XMLReader.setXMLPath("./");
    XMLReader.setXSDPath("./XSD/");
    
    MidiManager mm = MidiManager.getInstance();
    mm.setMidiPath("./Sounds&Midi/");
    Sequencer seq = null; 
    try {
      Sequence s = MidiSystem.getSequence(new File("./Sounds&Midi/animaniacs.mid"));
      mm.play(s, true);
    } catch (InvalidMidiDataException ex) {
      Logger.getLogger(RoboRace.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(RoboRace.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    String names;
    Port port;
    //for (int i = 0; i < nHuman; i++) {
      names = GameDialogs.showInputDialog("Enter Player Name", "Name of Player: ");
      Channel c = new Channel();
      port = c.asPort1();
      new Player(names, port);
      
      
    //};
    //(new GameMaster(nHuman, names, ports)).run();

    if (seq != null) {
      seq.close();
    }
  }

}
