package jfxwithjbox2d;

import javafx.scene.Node;

import org.jbox2d.dynamics.BodyType;

public class PhysicsObject {

	//JavaFX UI for ball
    public Node node;
	
	//X and Y position of the object in JBox2D world
    protected float posX;
    protected float posY;
    
    /*
     * There are three types bodies in JBox2D Static, Kinematic and dynamic 
     * In this application static bodies (BodyType.STATIC – non movable bodies) 
     * are used for drawing hurdles and dynamic bodies (BodyType.DYNAMIC–movable bodies) 
     * are used for falling balls
     */
    protected BodyType bodyType;

	public PhysicsObject(float posX, float posY, BodyType bodyType, Node node) {
		super();
		this.node = node;
		this.posX = posX;
		this.posY = posY;
		this.bodyType = bodyType;
	}
    
    
}
