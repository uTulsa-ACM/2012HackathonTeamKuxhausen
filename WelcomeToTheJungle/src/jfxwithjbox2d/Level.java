package jfxwithjbox2d;

import java.util.ArrayList;

import javafx.scene.paint.Color;

import org.jbox2d.dynamics.BodyType;

public class Level {

	
	public ArrayList<PhysicsObject> levelOne(){
		ArrayList<PhysicsObject> list = new ArrayList<PhysicsObject>();
		list.add(new Box(-20f,0f,80f,1f,0, BodyType.STATIC, Color.DARKGREEN));
		
		
		
		randomLandscape(10, 20f);
		
		return list;
	}
	public void randomLandscape(int num, float width){
		for(int i = 0; i< num; i++){
			float xStart = (float)Math.random()*width;
			float height = (float)Math.random();
			
			new TreeSegment(xStart,0f, (float)Math.random()*.2f, height,0);
			new TreeSegment(xStart,height, (float)Math.random()*.2f, height*2,0);
			new TreeSegment(xStart,height*2, (float)Math.random()*.2f, height*2+(float)Math.random()*2,0);
			
		}
		return;
	}
	
}
