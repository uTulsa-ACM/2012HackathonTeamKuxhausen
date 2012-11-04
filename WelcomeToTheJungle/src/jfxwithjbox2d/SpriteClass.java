package jfxwithjbox2d;

import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//		final SpriteClass spriteClass = new SpriteClass();
//		spriteClass.putImageStrip("horse", "http://upload.wikimedia.org/wikipedia/commons/7/73/The_Horse_in_Motion.jpg", 4, 10, 18, 25, 374, 243, 5.f);
//		final Sprite sprite = spriteClass.new Sprite();
//		sprite.setImageStrip("horse", true, true);
//		root.getChildren().add(sprite.imageView);
// UPDATE SPRITE IN TIMESTEP METHOD.

public class SpriteClass {
	private Map<String, ImageStrip> imageStrips;
	
	public SpriteClass() {
		imageStrips = new HashMap<String, ImageStrip>();
	}
	
	public ImageStrip putImageStrip(String label, String path, int columns, int count, int xOffset,
			int yOffset, int width, int height, float duration) {
		return imageStrips.put(label, new ImageStrip(path, columns, count, xOffset, yOffset, width, height, duration));
	}
	
	public ImageStrip putImageStrip(String label, Image image, int columns, int count, int xOffset,
			int yOffset, int width, int height, float duration) {
		return imageStrips.put(label, new ImageStrip(image, columns, count, xOffset, yOffset, width, height, duration));
	}
	
	public ImageStrip removeImageStrip(String label) {
		return imageStrips.remove(label);
	}
	
	public class Sprite implements Updatee {
	    public final ImageView imageView; // important that this is final, it is our Node used for rendering
	    private float progress; // progress through our current strip
	    private boolean running; // are we animating?
	    private boolean looping; // do we loop?
	    private ImageStrip imageStrip;
	    private int previousIndex;
	    
	    public Sprite() {
	    	imageView = new ImageView();
	    	progress = 0.f; // doesn't matter
	    	running = false; // doesn't matter
	    	looping = false;
	    	imageStrip = null; // don't have anything to render
	    	previousIndex = -1; // no current index in frame
	    }
	    
	    @Override
	    public void update(float timePassed) {
	    	if(running && imageStrip != null) {
	    		progress += timePassed;
	    		if(progress > imageStrip.duration && !looping) {
	    			progress = 0.f;
	    			running = false;
	    		}
	    		progress %= imageStrip.duration;
	    		double normalizedProgress = progress / imageStrip.duration;
	    		final int index = Math.min((int) Math.floor(normalizedProgress * imageStrip.count), imageStrip.count - 1);
	    		if (index != previousIndex) {
	    			setIndex(index);
	    		}
	    	}
	    }
	    
	    public void setImageStrip(String label, boolean running, boolean looping) {
	    	setImageStrip(imageStrips.get(label), running, looping);
	    }
	    
	    public void setImageStrip(ImageStrip imageStrip, boolean running, boolean looping) {
	    	this.imageStrip = imageStrip;
	    	progress = 2.f;
	    	imageView.setImage(imageStrip.image);
	    	setIndex(0);
	    	this.running = running;
	    	this.looping = looping;
	    }
	    
	    public void start() {
	    	this.running = true;
	    }
	    
	    public void pause() {
	    	this.running = false;
	    }
	    
	    private void setIndex(int index) {
	    	final int x = (index % imageStrip.columns) * imageStrip.width  + imageStrip.xOffset;
	    	final int y = (index / imageStrip.columns) * imageStrip.height + imageStrip.yOffset;
	    	imageView.setViewport(new Rectangle2D(x, y, imageStrip.width, imageStrip.height));
	    	previousIndex = index;
	    }
	}
}
