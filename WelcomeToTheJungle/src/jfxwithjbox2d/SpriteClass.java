package jfxwithjbox2d;

import java.io.FileNotFoundException;
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
//		spriteClass.putImageStrip("horse", "C:/Users/lcbrooks/Dropbox/Hackathon Files/art/Character/runcombined.png", 4, 4, 0, 0, 74, 65, 0.5f);
//		final Sprite sprite = spriteClass.new Sprite(74, 65);
//		Ball b = new Ball(20, 20, 65, BodyType.DYNAMIC,Color.ALICEBLUE);
//		b.setNode(sprite.imageView);
// 		physicsObjects.add(b);
//		root.getChildren().add(b.getNode());

public class SpriteClass {
	private Map<String, ImageStrip> imageStrips;
	
	public SpriteClass() {
		imageStrips = new HashMap<String, ImageStrip>();
	}
	
	public ImageStrip putImageStrip(String label, String path, int columns, int count, int xOffset,
			int yOffset, int width, int height, float duration) {
		try {
			return imageStrips.put(label, new ImageStrip(path, columns, count, xOffset, yOffset, width, height, duration));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public ImageStrip putImageStrip(String label, Image image, int columns, int count, int xOffset,
			int yOffset, int width, int height, float duration) {
		return imageStrips.put(label, new ImageStrip(image, columns, count, xOffset, yOffset, width, height, duration));
	}

	public ImageStrip putImageStrip(String label, String path, int count, float duration) {
		try {
			return imageStrips.put(label, new ImageStrip(path, count, duration));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public ImageStrip removeImageStrip(String label) {
		return imageStrips.remove(label);
	}
	
	public class Sprite implements Updatee {
	    public final ImageView imageView; // important that this is final, it is our Node used for rendering
	    public float progress; // progress through our current strip
	    private boolean running; // are we animating?
	    private boolean looping; // do we loop?
	    private ImageStrip imageStrip;
	    private int previousIndex;
	    private float physicsWidth;
	    private float physicsHeight;
	    
	    public Sprite(float physicsWidth, float physicsHeight) {
	    	imageView = new ImageView();
	    	progress = 0.f; // doesn't matter
	    	running = false; // doesn't matter
	    	looping = false;
	    	imageStrip = null; // don't have anything to render
	    	previousIndex = -1; // no current index in frame
	    	this.physicsWidth = physicsWidth;
	    	this.physicsHeight = physicsHeight;
	    }
	    
	    @Override
	    public void update(float timePassed) {
	    	if(running && imageStrip != null) {
	    		progress += timePassed;
	    		if(progress > imageStrip.duration && !looping) {
	    			progress = imageStrip.duration;
	    			running = false;
	    		}
	    		if(looping) progress %= imageStrip.duration;
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
	    
	    private void setImageStrip(ImageStrip imageStrip, boolean running, boolean looping) {
	    	this.imageStrip = imageStrip;
	    	progress = 0.f;
	    	imageView.setImage(imageStrip.image);
//	    	imageView.setTranslateX(-physicsWidth/2*1.f);
//	    	imageView.setTranslateY(-physicsHeight/2*1.f);
	    	imageView.setScaleX(physicsWidth/imageStrip.width*1.f);
	    	imageView.setScaleY(physicsHeight/imageStrip.height*1.f);
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
	    	imageView.setScaleX(physicsWidth/imageStrip.width*1.f);
	    	imageView.setScaleY(physicsHeight/imageStrip.height*1.f);
	    	imageView.setTranslateX(-physicsWidth/2);
	    	imageView.setTranslateY(-physicsHeight/2);
	    	previousIndex = index;
	    }
	}
}
