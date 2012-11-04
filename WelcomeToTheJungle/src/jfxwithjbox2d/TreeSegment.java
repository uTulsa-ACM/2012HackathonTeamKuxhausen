package jfxwithjbox2d;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import jfxwithjbox2d.SpriteClass.Sprite;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;

public class TreeSegment extends Box {

	boolean onFire=false;
	int health=1000;
	static SpriteClass spriteClass = new SpriteClass();
	static {
		spriteClass.putImageStrip("tree", "./blocka.png", 3, 100f);
	}
	final Sprite sprite = spriteClass.new Sprite(20, 80);
	
	public TreeSegment(float posX, float posY, float width, float height,
			float angle){
		super( posX, posY, width, height,
			 angle, BodyType.DYNAMIC, Color.BROWN);
		
		JFXwithJBox2d.physicsObjects.add(this);
		JFXwithJBox2d.updatees.add(sprite);
		this.setNode(sprite.imageView);
		JFXwithJBox2d.root.getChildren().add(this.getNode());
		sprite.setImageStrip("tree", true, true);
	}

	@Override
	public boolean act() {
		// TODO Auto-generated method stub
		
		sprite.progress = 100f*(1000-health)/1000;
		
		if(onFire){
			//burn
			health--;
		}
		if(health<=0){
			return true;
		}
		return false;
	}

}
