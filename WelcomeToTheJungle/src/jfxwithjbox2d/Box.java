/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package jfxwithjbox2d;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import org.box2d.proto.Box2D;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

/**
 *
 * @author dilip
 */
public class Box extends PhysicsObject{
    
    private float width;
    private float height;
    private float angle;

    //Gradient effects for balls
    private LinearGradient gradient;
    
    private BodyType bodyType;
    
    public Box(float posX, float posY){
        this(posX, posY, 50, 200, (float)Math.PI/4, BodyType.DYNAMIC,Color.RED);
    }

    public Box(float posX, float posY, float width, float height, float angle, BodyType bodyType, Color color){
        super(null, null);
        this.width = width;
        this.height = height;
        this.angle = angle;
        this.gradient = Utils.getBallGradient(color);
        this.bodyType = bodyType;
        create(posX, posY);
    }
    
    /*
     * This method creates a ball by using Circle object from JavaFX and CircleShape from JBox2D
     */
    private void create(float posX, float posY){
        //Create an UI for ball - JavaFX code
    	Rectangle box = new Rectangle();
    	box.setWidth(width);
    	box.setHeight(height);
    	box.setRotate(Math.PI/180*angle);
//    	XXX XXX XXX
//    	box.setArcWidth(width/10);
//    	box.setArcHeight(height/10);
        box.setFill(gradient); //set look and feel 
        
        /*
         * Set ball position on JavaFX scene. We need to convert JBox2D coordinates 
         * to JavaFX coordinates which are in pixels.
         */
        box.setLayoutX(Utils.toPixelPosX(posX)); 
        box.setLayoutY(Utils.toPixelPosY(posY));
        
       
        box.setCache(true); //Cache this object for better performance
        
        //Create an JBox2D body defination for ball.
        BodyDef bd = new BodyDef();
        bd.type = bodyType;
        bd.position.set(posX, posY);
        Vec2[] vertices = 
        	{
//        		new Vec2(posX-0.5f*width,posY-0.5f*height),
//        		new Vec2(posX-0.5f*width,posY+0.5f*height),
//        		new Vec2(posX+0.5f*width,posY+0.5f*height),
//        		new Vec2(posX+0.5f*width,posY-0.5f*height),
//        		new Vec2(-0.5f*width,-0.5f*height),
//        		new Vec2(-0.5f*width,0.5f*height),
//        		new Vec2(0.5f*width,0.5f*height),
//        		new Vec2(0.5f*width,-0.5f*height),
        	};
        PolygonShape ps = new PolygonShape();
//        ps.set(vertices, 4);
//        ps.m_radius = width+height;
        ps.setAsBox(width*0.1f,height*0.1f,new Vec2(width*0.1f*0.5f,height*0.1f*0.5f),angle);
//        ps.setAsBox(width, height);
        ps.m_radius = Math.min(width/10, height/10);
        
        // Create a fixture for ball
        FixtureDef fd = new FixtureDef();
        fd.shape = ps;
        fd.density = 0.9f;
        fd.friction = 0.3f;        
        fd.restitution = 0.6f;

        /*
        * Virtual invisible JBox2D body of ball. Bodies have velocity and position. 
        * Forces, torques, and impulses can be applied to these bodies.
        */
        Body body = Utils.world.createBody(bd);
        body.createFixture(fd);
        box.setUserData(body);
        
        setBody(body);
        setNode(box);
    }

	@Override
	public void act() {
		// TODO Auto-generated method stub
		
	}
}
