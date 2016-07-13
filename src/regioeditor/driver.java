package regioeditor;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import saf.AppTemplate;
import saf.components.AppWorkspaceComponent;
import saf.ui.AppGUI;
import javafx.application.Application;
import javafx.stage.Stage;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import properties_manager.PropertiesManager;
import static saf.settings.AppStartupConstants.PATH_DATA;
import static saf.settings.AppStartupConstants.PROPERTIES_SCHEMA_FILE_NAME;
import static saf.settings.AppStartupConstants.WORKSPACE_PROPERTIES_FILE_NAME;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author samuelchen
 */
public class driver{

   
    private HashMap<String, Sequencer> midiAudio;
    private HashMap<String, Clip> sampledAudio;

    public driver() 
    {
        midiAudio = new HashMap();
        sampledAudio = new HashMap();
    }
    
    public void loadAudio(String audioName, String audioFileName) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InvalidMidiDataException, MidiUnavailableException
    {
        if (audioFileName.endsWith(".wav"))
        {
            File soundFile = new File(audioFileName);
            AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
            
            // load the sound into memory (a Clip)
            DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(sound);
            sampledAudio.put(audioName, clip);
        }
        else if (audioFileName.endsWith(".mid"))
        {
            Sequence sequence = MidiSystem.getSequence(new File(audioFileName));
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.setSequence(sequence);
            midiAudio.put(audioName, sequencer);
        }
    }
    
    public void play(String audioName, boolean loop)
    {
        Sequencer sequencer = midiAudio.get(audioName);
        if (sequencer != null)
        {
            sequencer.setTickPosition(0);
            sequencer.start();
        }
        else
        {
            Clip clip = sampledAudio.get(audioName);
            clip.setFramePosition(0);
            clip.start();
        }   
    }
    
    public boolean isPlaying(String audioName)
    {
        Sequencer sequencer = midiAudio.get(audioName);
        if (sequencer != null)
        {
            return sequencer.isRunning();
        }
        else
        {
            Clip clip = sampledAudio.get(audioName);  
            return clip.isRunning();
        }
    }
    
    public void stop(String audioName)
    {
        Sequencer sequencer = midiAudio.get(audioName);
        if (sequencer != null)
        {
            sequencer.stop();
        }
        else
        {
            Clip clip = sampledAudio.get(audioName);
            clip.stop();
        }
    }
    public static void main(String[] args){
      String parent = "anc/abc/";
       
        String newfile = parent.replace("abc","hi");
        System.out.println(newfile);
    }
}
