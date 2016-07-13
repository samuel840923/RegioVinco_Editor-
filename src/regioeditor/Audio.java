/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regioeditor;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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

/**
 *
 * @author samuelchen
 */
public class Audio {
     private HashMap<String, Sequencer> midiAudio;
   
    public Audio() 
    {
        midiAudio = new HashMap();
       
    }
    
    public void loadAudio(String audioName, String audioFileName) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InvalidMidiDataException, MidiUnavailableException
    {
      
            Sequence sequence = MidiSystem.getSequence(new File(audioFileName));
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.setSequence(sequence);
            midiAudio.put(audioName, sequencer);
        
    }
    
    public void play(String audioName, boolean loop)
    {
        Sequencer sequencer = midiAudio.get(audioName);
        if (sequencer != null)
        {
            sequencer.setTickPosition(0);
            sequencer.start();
        }
      
    }
    
    public boolean isPlaying(String audioName)
    {
        Sequencer sequencer = midiAudio.get(audioName);
        if (sequencer != null)
        {
            return sequencer.isRunning();
        }
       return false;
    }
    
    public void stop(String audioName)
    {
        Sequencer sequencer = midiAudio.get(audioName);
        if (sequencer != null)
        {
            sequencer.stop();
        }
       
    }
}
