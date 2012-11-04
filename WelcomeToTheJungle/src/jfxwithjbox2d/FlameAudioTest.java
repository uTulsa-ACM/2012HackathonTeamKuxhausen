package jfxwithjbox2d;

public class FlameAudioTest {
	public static void main(String[] args) {
		FlameAudioTest test = new FlameAudioTest();
		test.activate();
		long time = System.nanoTime();
		while(true) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long newtime = System.nanoTime();
			float dt = (float)(newtime-time)/1000000000;
			time = newtime;
			test.update(dt);
		}
	}
	
	private boolean active = false;
	private float progress = 0.f;
	private float length = 0.1f;
	AudioPlayerSimple audioPlayerSimple = new AudioPlayerSimple();
	
	public void activate() {
		if(active) return;
//		audioPlayerSimple.play("death1.wav",-10.f);
		audioPlayerSimple.play("flame2.wav",0.f);
		active = true;
	}
	
	public void deactivate() {
		active = false;
	}
	
	public void update(float timePassed) {
		if(!active) return;
		progress += timePassed;
		if(progress > length) {
			progress %= length;
			audioPlayerSimple.play("burn1.wav",0.f);
		}
	}
}
