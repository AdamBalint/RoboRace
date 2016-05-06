/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoboRace;

import COSC3P40.sound.SoundManager;
import javax.sound.sampled.AudioFormat;

public class GameSoundManager extends SoundManager{

  private String path = "";
  
  public GameSoundManager() {
    super(new AudioFormat(8000, 8,1,false,false));
  }
  
  public void setSoundPath(String path){
    super.setSoundPath(path);
  }
  
  public void playBumpSound(){
    super.play(super.getSound("/bump.wav"));
  }
  
  public void playHornSound(){
    super.play(super.getSound("/fanfare.wav"));
  }
  
  public void playExplodeSound(){
    super.play(super.getSound("/explosion.wav"));
  }
  
}
