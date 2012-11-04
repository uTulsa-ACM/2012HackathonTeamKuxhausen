/*
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

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

/**
 *
 */
public class Box extends PhysicsObject{
    
    //Ball radius in physics units
    private float width;
    private float height;
    private float angle;

    //Gradient effects for balls
    private LinearGradient gradient;
    
    private BodyType bodyType;

    /** takes in distance values in physics units **/
    public Box(float posX, float posY, float width, float height, float angle, BodyType bodyType, Color color){
        super(null, null);
        this.width = width;
        this.height = height;
        this.angle = angle;
        this.gradient = Utils.getBallGradient(color);
        this.bodyType = bodyType;
        create(posX, posY, angle);
    }
    
    /*
     * This method creates a ball by using Circle object from JavaFX and CircleShape from JBox2D
     */
    private void create(float posX, float posY, float angle){
        //Create an UI for ball - JavaFX code
        Rectangle box = new Rectangle();
        box.setWidth(width*Utils.physicsToGraphicsFactor);
        box.setHeight(height*Utils.physicsToGraphicsFactor);
        box.setX(-(width*Utils.physicsToGraphicsFactor)/2);
        box.setY(-(height*Utils.physicsToGraphicsFactor)/2);
        box.setRotate(180/Math.PI*angle);
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
        
        PolygonShape ps = new PolygonShape();
        
        ps.setAsBox(width/2, height/2, new Vec2(0.f,0.f), angle);
        
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
