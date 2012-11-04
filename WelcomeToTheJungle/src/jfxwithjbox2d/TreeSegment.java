package jfxwithjbox2d;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import jfxwithjbox2d.SpriteClass.Sprite;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;

public class TreeSegment extends Box {

	boolean onFire=true;
	int health=1000;
	
	public TreeSegment(float posX, float posY, float width, float height,
			float angle){
		super( posX, posY, width, height,
			 angle, BodyType.DYNAMIC, Color.BROWN);
		
		
		JFXwithJBox2d.physicsObjects.add(this);
		SpriteClass spriteClass = new SpriteClass();
		spriteClass.putImageStrip("tree", "./blocka.png", 3, 16f);
		final Sprite sprite = spriteClass.new Sprite(20, 80);
		JFXwithJBox2d.updatees.add(sprite);
		this.setNode(sprite.imageView);
		JFXwithJBox2d.root.getChildren().add(this.getNode());
		sprite.setImageStrip("tree", true, true);
	}

	@Override
	public boolean act() {
		// TODO Auto-generated method stub

		
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
