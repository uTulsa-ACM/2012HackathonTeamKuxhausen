package jfxwithjbox2d;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.contacts.Contact;

public class CollisionDetector implements ContactListener {
	long lastGroan = System.nanoTime();
	
	@Override
	public void beginContact(Contact contact) {
//		System.out.println(PhysicsObject.objectMap);
//		System.out.println("Contact detected");
//		System.out.println(PhysicsObject.objectMap.get(contact.m_nodeA.other) + " " + PhysicsObject.objectMap.get(contact.m_nodeA.other));
		PhysicsObject po1 = PhysicsObject.objectMap.get(contact.m_nodeA.other);
		PhysicsObject po2 = PhysicsObject.objectMap.get(contact.m_nodeB.other);
		
		if(po1==null) return;
		if(po2==null) return;
		
		if(po1 instanceof Flame && po2 instanceof TreeSegment ||
		   po2 instanceof Flame && po1 instanceof TreeSegment) {
			Flame flame = (Flame)(po1 instanceof Flame? po1 : po2);
			TreeSegment segment = (TreeSegment)(po1 instanceof TreeSegment? po1 : po2);
//			System.out.println("BURNINATIN'");
			segment.onFire = true;
			segment.health-=50;
		}
		
		if(po1 instanceof Flame && po2 instanceof GorillaEnemy ||
				po2 instanceof Flame && po1 instanceof GorillaEnemy) {
			Flame flame = (Flame)(po1 instanceof Flame? po1 : po2);
			GorillaEnemy gorilla = (GorillaEnemy)(po1 instanceof GorillaEnemy? po1 : po2);
			gorilla.onFire = true;
			gorilla.health-=1;
		}
		
		if(po1 == Utils.player && po2 instanceof GorillaEnemy ||
				po2 == Utils.player && po1 instanceof GorillaEnemy) {
			Player player = Utils.player;
			GorillaEnemy gorilla = (GorillaEnemy)(po1 instanceof GorillaEnemy? po1 : po2);
			if(!player.dead && !gorilla.dead) {
				JFXwithJBox2d.playerSprite.setImageStrip("death", false, false);
				long time = System.nanoTime();
				if((float)(time-lastGroan)/1000000000>0.5f) {
					lastGroan = time;
					new AudioPlayerSimple().play("death1.wav",-10.f);
				}
				player.health-=5;
			}
		}

		if(po1 == Utils.player && po2 instanceof Box || po2 == Utils.player && po1 instanceof Box) {
			Utils.lastLandscapeTime = System.nanoTime();
		}
	}

	@Override
	public void endContact(Contact contact) {
//		System.out.println("Contact ended");
//		System.out.println(PhysicsObject.objectMap.get(contact.m_nodeA.other) + " " + PhysicsObject.objectMap.get(contact.m_nodeA.other));
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
	}
}
