/*
 * Creates a Game Object vector to store all game objects.
 * Also contains all functions callable by Game.java, and
 * implements their functionalities.
 */

package com.mycompany.a2;
import java.util.Iterator;
import java.util.Observable;
import java.util.Random;
import java.util.Vector;

public class GameWorld extends Observable implements IGameWorld{
	private Random random = new Random();
	private Vector<GameObject> store = new Vector<GameObject>();
	//private GameObjectCollection store;
	private int numLives;
	private int elapsedGameTime;
	private int playerScore;
	private int numPSMissiles;
	private double width;
	private double length;
	private boolean sound;
	
	public void init()
	{
		this.width = 1024.0;
		this.length = 768.0;
		this.numLives = 3;
		this.elapsedGameTime = 0;
		this.numPSMissiles = 10;
		this.sound = true;
		//store = new GameObjectCollection();
	}
	
	public void addNewAsteroid() {
		Asteroid roid = new Asteroid(random.nextInt(25) + 6);
		store.add(roid);
		
		setChanged();
		notifyObservers();
		
	}
	
	public void addPlayerShip() throws Exception {
		for(int i=0; i<store.size(); i++) {
			if(store.elementAt(i) instanceof PlayerShip) {
				throw new Exception("Error: There is already a player ship!");
			}
		}
		store.add(PlayerShip.getPlayerShip());
		setChanged();
		notifyObservers();
	}
	
	public void addNonPlayerShip() {
		NonPlayerShip tiFighter = new NonPlayerShip();
		store.add(tiFighter);
		setChanged();
		notifyObservers();
	}
	
	public void addSpaceStation() {
		SpaceStation deathStar = new SpaceStation();
		store.add(deathStar);
		setChanged();
		notifyObservers();
	}
	
	public void firePSMissile() {
		for(int i=0; i<store.size(); i++) {
			if(store.elementAt(i) instanceof PlayerShip) {
				PlayerShip mFalcon = (PlayerShip) store.get(i);
				if(mFalcon.getMC() > 0) {
					Missile pewPew = new Missile(mFalcon);
					store.add(pewPew);
					mFalcon.decrementMC();
					this.numPSMissiles = mFalcon.getMC();
					store.setElementAt(mFalcon, i);
					System.out.println("PS missile fired");
					setChanged();
					notifyObservers();
					return;
				}
				else {
					System.out.println("Error: Out of missiles!");
				}
			}
		}
	}
	
	public void fireNPSMissile() {
		for(int i=0; i<store.size(); i++) {
			if(store.elementAt(i) instanceof NonPlayerShip) {
				NonPlayerShip tiFighter = (NonPlayerShip) store.get(i);
				if(tiFighter.getMC() > 0) {
					Missile pewPew = new Missile(tiFighter);
					store.add(pewPew);
					tiFighter.decrementMC();
					store.setElementAt(tiFighter, i);
					System.out.println("NPS missile fired");
					setChanged();
					notifyObservers();
					return;
				}
				else {
					System.out.println("Error: Out of missiles!");
				}
			}
		}
	}
	
	public void printMap()
	{
		for (int i=0; i<store.size(); i++)
		{
			if (store.elementAt(i) instanceof GameObject)
			{
				GameObject gObj = (GameObject) store.elementAt(i);
				System.out.println(gObj.toString());
			}
		}
	}
	
	public void displayScores()
	{
		System.out.println("playerScore = " + this.playerScore);
		System.out.println("number of missles = " + this.numPSMissiles);
		System.out.println("current elapsed time = " + this.elapsedGameTime);
		System.out.println("number of lives = " + this.numLives);
	}
	
	public void increasePSSpeed()
	{
		for(int i=0; i<store.size(); i++) {
			if(store.elementAt(i) instanceof PlayerShip) {
				PlayerShip mFalcon = (PlayerShip) store.get(i);
				if(mFalcon.getSpeed() < 20) {
					mFalcon.increaseSpeed();
					System.out.println("Player Ship Speed increased to: " + mFalcon.getSpeed());
					setChanged();
					notifyObservers();
					return;
				}
				else {
					System.out.println("Ship alread going ludicrous speed. Cannot jump to plaid.");
					return;
				}
			}
		}
	}
	
	public void decreasePSSpeed()
	{
		for(int i=0; i<store.size(); i++) {
			if(store.elementAt(i) instanceof PlayerShip) {
				PlayerShip mFalcon = (PlayerShip) store.get(i);
				if (mFalcon.getSpeed() >= 0) {
					mFalcon.decreaseSpeed();
					setChanged();
					notifyObservers();
					return;
				}
				else {
					System.out.println("Cannot decrease speed. PS already stopped.");
					return;
				}
			}
		
		}
	}
	
	public void turnPSL()
	{
		for(int i=0; i<store.size(); i++) {
			if(store.elementAt(i) instanceof PlayerShip) {
				PlayerShip mFalcon = (PlayerShip) store.get(i);
				mFalcon.turnLeft();
				System.out.println("Drifting lazily to the left");
				setChanged();
				notifyObservers();
				return;
			}
		}
	}
	
	public void turnPSR()
	{
		for(int i=0; i<store.size(); i++) {
			if(store.elementAt(i) instanceof PlayerShip) {
				PlayerShip mFalcon = (PlayerShip) store.get(i);
				mFalcon.turnRight();
				System.out.println("PS turned right");
				setChanged();
				notifyObservers();
				return;
			}
		}
	}
	
	public void turnPSML() {
		for(int i=0;i<store.size();i++) {
			if(store.elementAt(i) instanceof PlayerShip) {
				PlayerShip mFalcon = (PlayerShip) store.get(i);
				mFalcon.revolveML();
				System.out.println("Rotating Missile Launcher");
				setChanged();
				notifyObservers();
				return;
			}
		}
	}
	
	public void jump()
	{
		for(int i=0; i<store.size(); i++) {
			if(store.elementAt(i) instanceof PlayerShip) {
				PlayerShip mFalcon = (PlayerShip) store.get(i);
				mFalcon.setLocation(512, 384);
				System.out.println("Punch it Chewie!");
				setChanged();
				notifyObservers();
				return;
			}
		}
	}
	
	//TODO Currently reloads all ships. Make it so it only reloads ships "close to" stations
	public void reload()
	{
		for(int i=0; i<store.size(); i++) {
			if(store.elementAt(i) instanceof Ship) {
				for(int j=0; j<store.size(); j++) {
					if(store.elementAt(j) instanceof SpaceStation) {
						SpaceStation deathStar = (SpaceStation) store.get(j);
						Ship someShip = (Ship) store.get(i);
						//if(deathStar.getLocation() == someShip.getLocation())
							someShip.reloadMissiles();
					}
				}
			}
		}
		setChanged();
		notifyObservers();
	}
	
	//TODO Currently destroys a random missile and asteroid. Make it destroy "close" missiles and asteroids
	@SuppressWarnings("deprecation")
	public void missileHitAsteroid()
	{
		for (int i=0; i<store.size(); i++) {
			if(store.elementAt(i) instanceof Missile) {
				Missile pewPew = (Missile) store.get(i);
				for(int j=0; j<store.size(); j++) {
					if(store.elementAt(j) instanceof Asteroid) {
						Asteroid roid = (Asteroid) store.get(j);
						//if(pewPew.getLocation() == roid.getLocation()) {
							System.out.println(pewPew.getShipType().getSimpleName() + "'s Missile has destroyed an Asteroid");
							store.remove(pewPew);
							store.remove(roid);
							if(pewPew.getShipType().getSimpleName() == "PlayerShip") {
								this.playerScore += 10;
								System.out.println("+10 points");
							}
							setChanged();
							notifyObservers();
							return;
						//}
					}
				}
			}
		}
	}
	
	//TODO Currently destroys a random missile and random NPS. Make it destroy "Close" missiles and NPS
	@SuppressWarnings("deprecation")
	public void missileHitNPS()
	{
		for (int i=0; i<store.size(); i++) {
			if(store.elementAt(i) instanceof Missile) {
				Missile pewPew = (Missile) store.get(i);
				for(int j=0; j<store.size(); j++) {
					if(store.elementAt(j) instanceof NonPlayerShip) {
						NonPlayerShip tiFighter = (NonPlayerShip) store.get(j);
						//if(pewPew.getLocation() == tiFighter.getLocation()) {
							System.out.println(pewPew.getShipType().getSimpleName() + "'s Missile has destroyed an NPS");
							store.remove(pewPew);
							store.remove(tiFighter);
							if(pewPew.getShipType().getSimpleName() == "PlayerShip") {
								this.playerScore += 50;
								System.out.println("+50 points");
							}
							setChanged();
							notifyObservers();
							return;
						//}
					}
				}
			}
		}
	}
	
	//TODO Currently destroys a random Missile and the PS. Make it destroy "close" missile and PS
	@SuppressWarnings("deprecation")
	public void explodedPS() {
		for (int i=0; i<store.size(); i++) {
			if(store.elementAt(i) instanceof Missile) {
				Missile pewPew = (Missile) store.get(i);
				for(int j=0; j<store.size(); j++) {
					if(store.elementAt(j) instanceof PlayerShip) {
						PlayerShip mFalcon = (PlayerShip) store.get(j);
						//if(pewPew.getLocation() == mFalcon.getLocation()) {
							System.out.println(pewPew.getShipType().getSimpleName() + "'s Missile has hit the PS");
							store.remove(pewPew);
							this.numLives -= 1;
							if(this.numLives == 0) {
								System.out.println("Player Ship destroyed");
								store.remove(mFalcon);
							}
							setChanged();
							notifyObservers();
							return;
						//}
					}
				}
			}
		}
	}
	
	//TODO Currently destroys a random Asteroid and the PS. Make it destroy "close" Asteroid and PS
	public void asteroidHitPS()
	{
		for (int i=0; i<store.size(); i++) {
			if(store.elementAt(i) instanceof Asteroid) {
				Asteroid roid = (Asteroid) store.get(i);
				for(int j=0; j<store.size(); j++) {
					if(store.elementAt(j) instanceof PlayerShip) {
						PlayerShip mFalcon = (PlayerShip) store.get(j);
						//if(roid.getLocation() == mFalcon.getLocation()) {
							System.out.println("Let me guess, never tell you the odds?");
							store.remove(roid);
							this.playerScore += 10;
							System.out.println("+10 points");
							this.numLives -= 1;
							if(this.numLives == 0) {
								System.out.println("Player Ship destroyed");
								store.remove(mFalcon);
							}
							setChanged();
							notifyObservers();
							return;
						//}
					}
				}
			}
		}
	}
	
	//TODO Currently destroys a random NPS and the PS. Make it destroy "close" NPS and PS
	public void npsHitPS() {
		for (int i=0; i<store.size(); i++) {
			if(store.elementAt(i) instanceof NonPlayerShip) {
				NonPlayerShip tiFighter = (NonPlayerShip) store.get(i);
				for(int j=0; j<store.size(); j++) {
					if(store.elementAt(j) instanceof PlayerShip) {
						PlayerShip mFalcon = (PlayerShip) store.get(j);
						//if(tiFighter.getLocation() == mFalcon.getLocation()) {
							System.out.println("Careful with the kamikazes");
							store.remove(tiFighter);
							this.numLives -= 1;
							this.playerScore += 50;
							System.out.println("+50 points");
							if(this.numLives == 0) {
								System.out.println("Player Ship destroyed");
								store.remove(mFalcon);
							}
							setChanged();
							notifyObservers();
							return;
						//}
					}
				}
			}
		}
	}
	
	//TODO currently destroys two random Asteroids. Make it destroy "close" Asteroids
	public void asteroidHitAsteroid()
	{
		for (int i=0; i<store.size(); i++) {
			if(store.elementAt(i) instanceof Asteroid) {
				Asteroid roid = (Asteroid) store.get(i);
				for(int j=i + 1; j<store.size(); j++) {
					if(store.elementAt(j) instanceof Asteroid) {
						Asteroid roid2 = (Asteroid) store.get(j);
						//if(roid.getLocation() == roid2.getLocation()) {
							System.out.println("Two Asteroids collided");
							store.remove(roid);
							store.remove(roid2);
							setChanged();
							notifyObservers();
							return;
						//}
					}
				}
			}
		}
	}
	
	//TODO currently destroys a random Asteroid and random NPS. Make it destroy "close" Asteroid and NPS
	public void asteroidHitNPS() {
		for (int i=0; i<store.size(); i++) {
			if(store.elementAt(i) instanceof Asteroid) {
				Asteroid roid = (Asteroid) store.get(i);
				for(int j=0; j<store.size(); j++) {
					if(store.elementAt(j) instanceof NonPlayerShip) {
						NonPlayerShip tiFighter = (NonPlayerShip) store.get(j);
						//if(roid.getLocation() == tiFighter.getLocation()) {
							System.out.println("An Asteroid collided with an NPS");
							store.remove(roid);
							store.remove(tiFighter);
							setChanged();
							notifyObservers();
							return;
						//}
					}
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public void tick() {
		this.elapsedGameTime += 1;
		System.out.println("elapsed game time: " + this.elapsedGameTime);
		for(int i=0; i<store.size(); i++){
			if(store.elementAt(i) instanceof MoveableGameObject) {
				MoveableGameObject moveR = (MoveableGameObject) store.get(i);
				moveR.move();
				if(moveR instanceof Missile) {
					Missile pewPew = (Missile) moveR;
					pewPew.decrementFuelLevel();
					if (pewPew.getFuelLevel() == 0) {
						System.out.println(pewPew.getShipType().getSimpleName() + "'s Missile is out of fuel. Cya");
						store.remove(pewPew);
						i--;
					}
				}
			}
			else if(store.elementAt(i) instanceof SpaceStation) {
				SpaceStation deathStar = (SpaceStation) store.get(i);
				if((this.elapsedGameTime % deathStar.getBlinkRate()) == 0)
					deathStar.toggleLight();
			}
		}
		setChanged();
		notifyObservers();
	}
	
	public void toggleSound() {
		this.sound = !sound;
		setChanged();
		notifyObservers();
	}
	
	public int getPlayerScore () { return this.playerScore; }
	public int getNumLives () { return this.numLives; }
	public boolean getSoundState () { return this.sound; }
	public int getMissileCount () { return this.numPSMissiles; }
	public int getElapsedTime () { return this.elapsedGameTime; }
	
	@Override
	public Iterator getIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addGameObject(GameObject o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean removeGameObject(GameObject o) {
		// TODO Auto-generated method stub
		return false;
	}
}
