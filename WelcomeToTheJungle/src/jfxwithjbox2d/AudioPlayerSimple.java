package jfxwithjbox2d;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class AudioPlayerSimple {
	
	public static void main(String[] args) {
		AudioPlayerSimple audioPlayerSimple = new AudioPlayerSimple();
//		audioPlayerSimple.play("C:/Users/lcbrooks/Documents/FFOutput/nationalbroadcastnetwork_4-3-2-1-superrobotgo.ogg");
		audioPlayerSimple.play("C:/Users/lcbrooks/Dropbox/Hackathon Files/Sounds/Music/BGM_0.ogg",-10.f);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		audioPlayerSimple.play("C:/Users/lcbrooks/Dropbox/Hackathon Files/Sounds/Character/death1.wav");
//		try {
//			Thread.sleep(100);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		audioPlayerSimple.play("C:/Users/lcbrooks/Dropbox/Hackathon Files/Sounds/Character/death1.wav");
//		try {
//			Thread.sleep(100);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		audioPlayerSimple.play("C:/Users/lcbrooks/Dropbox/Hackathon Files/Sounds/Character/death1.wav");
//		try {
//			Thread.sleep(100);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		audioPlayerSimple.play("C:/Users/lcbrooks/Dropbox/Hackathon Files/Sounds/Character/death1.wav");
//		try {
//			Thread.sleep(100);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		audioPlayerSimple.play("C:/Users/lcbrooks/Dropbox/Hackathon Files/Sounds/Character/death1.wav");
//		try {
//			Thread.sleep(100);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		audioPlayerSimple.play("C:/Users/lcbrooks/Dropbox/Hackathon Files/Sounds/Character/death1.wav");
//		try {
//			Thread.sleep(100);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		while(true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void play(final String filename) {
		play(filename, 0.f);
	}
	
	public void play(final String filename, final float gain) {
		new Thread() {
			@Override
			public void run() {
				testPlay(filename, gain);
			}
		}.start();
	}
	
	protected void testPlay(String filename, float gain)
	{
		try
		{
			File file = new File(filename);
			// Get AudioInputStream from given file.	
			AudioInputStream in= AudioSystem.getAudioInputStream(file);
			AudioInputStream din = null;
			if (in != null)
			{
				AudioFormat baseFormat = in.getFormat();
				AudioFormat  decodedFormat = new AudioFormat(
						AudioFormat.Encoding.PCM_SIGNED,
						baseFormat.getSampleRate(),
						16,
						baseFormat.getChannels(),
						baseFormat.getChannels() * 2,
						baseFormat.getSampleRate(),
						false);
				// Get AudioInputStream that will be decoded by underlying VorbisSPI
				din = AudioSystem.getAudioInputStream(decodedFormat, in);
				// Play now !
				rawplay(decodedFormat, din, gain);
				in.close();		
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void rawplay(AudioFormat targetFormat, 
			AudioInputStream din, float gain) throws IOException, LineUnavailableException
			{
		byte[] data = new byte[4096];
		SourceDataLine line = getLine(targetFormat);
		// System.out.println(Arrays.toString(line.getControls()));
		// Adjust the volume on the output line.
         if (line.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
             FloatControl volume = (FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(gain);
         }
		if (line != null)
		{
			// Start
			line.start();
			int nBytesRead = 0, nBytesWritten = 0;
			while (nBytesRead != -1)
			{
				nBytesRead = din.read(data, 0, data.length);
				if (nBytesRead != -1) nBytesWritten = line.write(data, 0, nBytesRead);
			}
			// Stop
			line.drain();
			line.stop();
			line.close();
			din.close();
		}		
			}

	private SourceDataLine getLine(AudioFormat audioFormat) throws LineUnavailableException
	{
		SourceDataLine res = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
		res = (SourceDataLine) AudioSystem.getLine(info);
		res.open(audioFormat);
		return res;
	}

}

