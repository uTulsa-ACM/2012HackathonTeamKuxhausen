package jfxwithjbox2d;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.Circle;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

public class Player extends PhysicsObject {

	//Ball radius in pixels
    private float radius;

    //Gradient effects for balls
    private LinearGradient gradient;
    
    private BodyType bodyType;
	
	public Player(float posX, float posY) {
		super(null, null);
		this.radius = .2f;
        this.gradient = Utils.getBallGradient(Color.GREEN);
        this.bodyType = BodyType.DYNAMIC;
        create(posX,posY);
    }
    
	/*
     * This method creates a ball by using Circle object from JavaFX and CircleShape from JBox2D
     */
    private void create(float posX, float posY){
        //Create an UI for ball - JavaFX code
        Circle ball = new Circle();
        ball.setRadius(radius*Utils.physicsToGraphicsFactor);
        ball.setFill(gradient); //set look and feel 
        
        /*
         * Set ball position on JavaFX scene. We need to convert JBox2D coordinates 
         * to JavaFX coordinates which are in pixels.
         */
        ball.setLayoutX(Utils.toPixelPosX(posX)); 
        ball.setLayoutY(Utils.toPixelPosY(posY));
        
       
        ball.setCache(true); //Cache this object for better performance
        
        //Create an JBox2D body definition for ball.
        BodyDef bd = new BodyDef();
        bd.type = bodyType;
        bd.position.set(posX, posY);
        
        CircleShape cs = new CircleShape();
        cs.m_radius = radius;
        
        // Create a fixture for ball
        FixtureDef fd = new FixtureDef();
        fd.shape = cs;
        fd.density = 0.9f;
        fd.friction = 0.3f;        
        fd.restitution = 0.6f;

        /*
        * Virtual invisible JBox2D body of ball. Bodies have velocity and position. 
        * Forces, torques, and impulses can be applied to these bodies.
        */
        Body body = Utils.world.createBody(bd);
        body.createFixture(fd);
        ball.setUserData(body);
        
        setBody(body);
        setNode(ball);
    }

	@Override
	public void act() {
		// TODO Auto-generated method stub
		
	}
}