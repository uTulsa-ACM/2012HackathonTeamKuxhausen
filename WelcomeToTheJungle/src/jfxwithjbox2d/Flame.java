package jfxwithjbox2d;

import javafx.scene.paint.Color;
import jfxwithjbox2d.SpriteClass.Sprite;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

import com.sun.javafx.geom.Vec2f;

public class Flame extends Ball {

	boolean onFire=true;
	int health=200;
	
	public Flame(float posX, float posY, float radius) {
		
		super( posX, posY, radius, BodyType.DYNAMIC, Color.RED);
		
		JFXwithJBox2d.physicsObjects.add(this);
		SpriteClass spriteClass = new SpriteClass();
		spriteClass.putImageStrip("flame", "./flamestrip.png", 6, 4f);
		final Sprite sprite = spriteClass.new Sprite(40, 40);
		JFXwithJBox2d.updatees.add(sprite);
		this.setNode(sprite.imageView);
		JFXwithJBox2d.root.getChildren().add(this.getNode());
		sprite.setImageStrip("flame", true, true);
		
	}

	
	@Override
	public boolean act() {
		// TODO Auto-generated method stub

		this.getBody().applyForce( new Vec2 (0, (-this.getBody().getMass()*Utils.world.getGravity().y)),this.getBody().getWorldCenter() );
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
