package game;

import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

	private Clip clip;
	private AudioInputStream ais;
	
	public Sound(String path) {

		try
		{
			clip = AudioSystem.getClip();
			ais = AudioSystem.getAudioInputStream(getClass().getResource(path));
			clip.open(ais);
			clip.start();
		}
		catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public Sound(String path, float gain) {
		
		try
		{
			clip = AudioSystem.getClip();
			ais = AudioSystem.getAudioInputStream(getClass().getResource(path));
			clip.open(ais);
		}
		catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
		{
			e.printStackTrace();
		}

		FloatControl gainControl = 
		    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(gain); // Reduce volume by 10 decibels.
		clip.start();
	}

	public void play()
	{
		clip.start();
	}
	
	public double length()
	{
		AudioFormat format = ais.getFormat();
		long frames = ais.getFrameLength();
		double durationInSeconds = (frames+0.0) / format.getFrameRate();  
		return durationInSeconds;
	}
	
	public void pause() {
		clip.stop();
	}
	
	public boolean playing() {
		return clip.isActive();
	}
}
