package jfxwithjbox2d;

import java.util.ArrayList;

import javafx.scene.paint.Color;

import org.jbox2d.dynamics.BodyType;

public class Level {

	
	public ArrayList<PhysicsObject> levelOne(){
		ArrayList<PhysicsObject> list = new ArrayList<PhysicsObject>();
		
		
		//list.add(new Box(-50f,0f,200f,3f,0, BodyType.STATIC, Color.DARKGREEN));
		list.add(new Box(-5f,0f,50f,3f,0, BodyType.STATIC, Color.DARKGREEN));
		//list.add(new Box(35f,-5f,15f,1f,.785398163f, BodyType.STATIC, Color.DARKGREEN));
		//list.add(new Box(40f,-5f,15f,1f,-.785398163f, BodyType.STATIC, Color.DARKGREEN));
		list.add(new Box(70f,0f,40f,1f,0, BodyType.STATIC, Color.DARKGREEN));
		
 		

		list.add(new Box(-50f,0f,1f,200f,0, BodyType.STATIC, Color.DARKGRAY));
		list.add(new Box(200f,0f,1f,200f,0, BodyType.STATIC, Color.DARKGRAY));
 		
		list.add(new Box(-50f,-5f,200f,5f,0, BodyType.STATIC, Color.DARKGREEN));
		
		new TreeSegment(20f,1f, .8f,.2f, 0f);
		
		
		generateTree(-3f);
		generateTree(-5f);
		generateTree(-7f);
		generateTree(-8f);
		new GorillaEnemy(-10f, 3f);
		
		generateTree(10f);
		
		generateTree(21f);
		new GorillaEnemy(25f, 3f);
		generateTree(26f);
		
		generateTree(34);
		generateTree(35f);
		generateTree(36f);
		generateTree(36f);
		
		generateTree(39f);
		generateTree(40f);
		generateTree(41f);
		generateTree(42f);
		generateTree(43f);
		
		randomLandscape(10, 50f);
		
		return list;
	}
	public void generateTree(float x){
		for(int i = 0; i < 5; i++){
			new TreeSegment(x, i*.8f, .2f, .8f, 0f);
		}
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
//package jfxwithjbox2d;
//
//import java.util.ArrayList;
//
//import javafx.scene.paint.Color;
//
//import org.jbox2d.dynamics.BodyType;
//
//public class Level {
//
//	
//	public ArrayList<PhysicsObject> levelOne(){
//		ArrayList<PhysicsObject> list = new ArrayList<PhysicsObject>();
//		
//		
//		list.add(new Box(-50f,0f,200f,3f,0, BodyType.STATIC, Color.DARKGREEN));
//		list.add(new Box(150f,0f,30f,1f,.85f, BodyType.STATIC, Color.DARKGREEN));
//		list.add(new Box(175f,-20f,30f,1f,.85f, BodyType.STATIC, Color.DARKGREEN));
//		list.add(new Box(200f,0f,10f,1f,0, BodyType.STATIC, Color.DARKGREEN));
//		
// 		
//
//		list.add(new Box(-50f,0f,1f,200f,0, BodyType.STATIC, Color.DARKGRAY));
//		list.add(new Box(200f,0f,1f,200f,0, BodyType.STATIC, Color.DARKGRAY));
// 		
//		//list.add(new TreeSegment())
//		//new TreeSegment(40f, (float)Math.random()*.2f, height,0);
//		randomLandscape(10, 20f);
//		
//		return list;
//	}
//	public void randomLandscape(int num, float width){
//		for(int i = 0; i< num; i++){
//			float xStart = (float)Math.random()*width;
//			float height = (float)Math.random();
//			
//			new TreeSegment(xStart,0f, (float)Math.random()*.2f, height,0);
//			new TreeSegment(xStart,height, (float)Math.random()*.2f, height*2,0);
//			new TreeSegment(xStart,height*2, (float)Math.random()*.2f, height*2+(float)Math.random()*2,0);
//			
//			//new GorillaEnemy(20f, 1f);
//			//new GorillaEnemy(30f, 1f);
//			
//			
//		}
//		new GorillaEnemy(3f, 3f);
//		return;
//	}
//	
//}
