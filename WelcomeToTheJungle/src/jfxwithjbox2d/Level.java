package jfxwithjbox2d;

import java.util.ArrayList;

import javafx.scene.paint.Color;

import org.jbox2d.dynamics.BodyType;

public class Level {

	
	public ArrayList<PhysicsObject> levelOne(){
		ArrayList<PhysicsObject> list = new ArrayList<PhysicsObject>();
		list.add(new Box(0f,0f,20f,1f,0, BodyType.STATIC, Color.DARKGREEN));
		
		
		
		list.addAll(randomLandscape(10, 20f));
		
		return list;
	}
	public ArrayList<PhysicsObject> randomLandscape(int num, float width){
		ArrayList<PhysicsObject> list = new ArrayList<PhysicsObject>();
		for(int i = 0; i< num; i++){
			float xStart = (float)Math.random()*width;
			float height = (float)Math.random();
			list.add(new Box(xStart,0f, (float)Math.random()*.2f, height,0, BodyType.STATIC, Color.BROWN));
			list.add(new Box(xStart,height, (float)Math.random()*.2f, height*2,0, BodyType.STATIC, Color.BROWN));
			list.add(new Box(xStart,height*2, (float)Math.random()*.2f, height*2+(float)Math.random()*2,0, BodyType.STATIC, Color.BROWN));
			
		}
		return list;
	}
	
}
