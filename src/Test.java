import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;


public class Test {

    public Test() {
    	
    }
    
   
    public static void main(String[] args)throws Exception
	{
		Test a=new Test();
		a.run();
	}


	private void run() {
		// Static Body
        Vec2  gravity = new Vec2(0,-10);
        boolean doSleep = true;
        World world = new World(gravity,doSleep);
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(0, -10);
        Body groundBody = world.createBody(groundBodyDef);
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(50,10);
        groundBody.createFixture(groundBox, 0);

        // Dynamic Body
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.position.set(0, 4);
        Body body = world.createBody(bodyDef);
        PolygonShape dynamicBox = new PolygonShape();
        dynamicBox.setAsBox(1, 1);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = dynamicBox;
        fixtureDef.density=1;
        fixtureDef.friction=0.3f;
        body.createFixture(fixtureDef);

        // Setup world
        float timeStep = 1.0f/60.0f;
        int velocityIterations = 6;
        int positionIterations = 2;

        // Run loop
        for (int i = 0; i < 60; ++i)
        {
            world.step(timeStep, velocityIterations, positionIterations);
            Vec2 position = body.getPosition();
            float angle = body.getAngle();
            System.out.printf("%4.2f %4.2f %4.2f\n", position.x, position.y, angle);
        }
	}
}
