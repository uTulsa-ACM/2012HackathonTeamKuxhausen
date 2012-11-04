package jfxwithjbox2d;

import javafx.scene.Node;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;

public class PhysicsObject {

	//JavaFX UI for ball
    private Node node;
	
	//X and Y position of the object in JBox2D world
    protected float posX;
    protected float posY;
    
    /*
     * There are three types bodies in JBox2D Static, Kinematic and dynamic 
     * In this application static bodies (BodyType.STATIC – non movable bodies) 
     * are used for drawing hurdles and dynamic bodies (BodyType.DYNAMIC–movable bodies) 
     * are used for falling balls
     */
    private Body body;

	public PhysicsObject(float posX, float posY, Body body, Node node) {
		super();
		this.posX = posX;
		this.posY = posY;
		setNode(node);
		setBody(body);
	}
    
    public void setBody(Body body) {
    	this.body = body;
    	if(this.node!=null) this.node.setUserData(body);
    }
    
    public void setNode(Node node) {
    	if(this.node!=null) this.node.setUserData(null);
    	this.node = node;
    	if(this.node!=null) this.node.setUserData(this.body);
    }

	public Node getNode() {
		return node;
	}

	public Body getBody() {
		return body;
	}
}
