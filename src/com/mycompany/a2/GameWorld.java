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
	private GameObjectCollection gwc = new GameObjectCollection();
	//IIterator theElements = gwc.getIterator();
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
	}
	
	public void addNewAsteroid() {
		Asteroid roid = new Asteroid(random.nextInt(25) + 6);
		gwc.add(roid);
		setChanged();
		notifyObservers();
	}
	
	public void addPlayerShip() throws Exception {
		IIterator theElements = gwc.getIterator();
		while(theElements.hasNext())
		{
			GameObject temp = (GameObject) theElements.getNext();
			if (temp instanceof PlayerShip) 
				throw new Exception("Error: There is already a playership!");
		}
		gwc.add(PlayerShip.getPlayerShip());
		setChanged();
		notifyObservers();
	}
	
	
	public void addNonPlayerShip() {
		NonPlayerShip tiFighter = new NonPlayerShip();
		gwc.add(tiFighter);
		setChanged();
		notifyObservers();
	}
	
	public void addSpaceStation() {
		SpaceStation deathStar = new SpaceStation();
		gwc.add(deathStar);
		setChanged();
		notifyObservers();
	}
	
	public void firePSMissile() {
		if(PlayerShip.getPlayerShip().getMC() > 0) {
			Missile pewPew = new Missile(PlayerShip.getPlayerShip());
			gwc.add(pewPew);
			PlayerShip.getPlayerShip().decrementMC();
			this.numPSMissiles = PlayerShip.getPlayerShip().getMC();
			System.out.println("PS missile fired");
			setChanged();
			notifyObservers();
			return;
		}
		else {
			System.out.println("Error: Out of PS missiles!");
		}
	}
	
	public void fireNPSMissile() {
		IIterator theElements = gwc.getIterator();
		while(theElements.hasNext())
		{
			GameObject tempObj = (GameObject) theElements.getNext();
			if(tempObj instanceof NonPlayerShip) {
				NonPlayerShip tiFighter = (NonPlayerShip) tempObj;
				if(tiFighter.getMC() > 0) {
					Missile pewPew = new Missile(tiFighter);
					gwc.add(pewPew);
					tiFighter.decrementMC();
					System.out.println("NPS missile fired");
					setChanged();
					notifyObservers();
					return;
				}
				else {
					System.out.println("Error: Out of NPS missiles!");
				}
			}
		}
	}
	
	public void printMap()
	{
		IIterator theElements = gwc.getIterator();
		System.out.println("\n");
		while(theElements.hasNext())
		{
			GameObject tempObj = (GameObject) theElements.getNext();
			System.out.println(tempObj);
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
		if(PlayerShip.getPlayerShip().getSpeed() < 20) {
			PlayerShip.getPlayerShip().increaseSpeed();
			System.out.println("Player Ship Speed increased to: " + PlayerShip.getPlayerShip().getSpeed());
			setChanged();
			notifyObservers();
			return;
		}
		else {
			System.out.println("Ship is already going ludicrous speed. Cannot jump to plaid.");
			return;
		}
	}
	
	public void decreasePSSpeed()
	{
		if(PlayerShip.getPlayerShip().getSpeed() < 20) {
			PlayerShip.getPlayerShip().decreaseSpeed();
			System.out.println("Player Ship Speed decreased to: " + PlayerShip.getPlayerShip().getSpeed());
			setChanged();
			notifyObservers();
			return;
		}
		else {
			System.out.println("Ship is already going ludicrously slow.");
			return;
		}
	}
	
	public void turnPSL()
	{
		PlayerShip.getPlayerShip().turnLeft();
		System.out.println("Drifting lazily to the left");
		setChanged();
		notifyObservers();
		return;
	}
	
	public void turnPSR()
	{
		PlayerShip.getPlayerShip().turnRight();
		System.out.println("Drifting lazily to the right");
		setChanged();
		notifyObservers();
		return;
	}
	
	public void turnPSMLLeft() {
		PlayerShip.getPlayerShip().revolveMLLeft();
		System.out.println("Rotating Missile Launcher Left");
		setChanged();
		notifyObservers();
	}
	
	public void turnPSMLRight() {
		PlayerShip.getPlayerShip().revolveMLRight();
		System.out.println("Rotating Missile Launcher Right");
		setChanged();
		notifyObservers();
	}
	
	public void jump()
	{
		PlayerShip.getPlayerShip().setLocation(512, 384);
		System.out.println("Punch it Chewie!");
		setChanged();
		notifyObservers();
	}
	
	//TODO Currently reloads all ships. Make it so it only reloads ships "close to" stations
	public void reload()
	{
		IIterator theElements = gwc.getIterator();
		while(theElements.hasNext())
		{
			GameObject tempObj = (GameObject) theElements.getNext();
			if(tempObj instanceof Ship) {
				Ship tempShip = (Ship) tempObj;
				tempShip.reloadMissiles();
			}
		}
		this.numPSMissiles = PlayerShip.getPlayerShip().getMC();
		setChanged();
		notifyObservers();
	}
	
	//TODO Currently destroys a random missile and asteroid. Make it destroy "close" missiles and asteroids
	public void missileHitAsteroid()
	{
		IIterator it1 = gwc.getIterator();
		IIterator it2 = gwc.getIterator();
		while(it1.hasNext()) {
			GameObject tempObj = (GameObject) it1.getNext();
			if(tempObj instanceof Missile) {
				Missile pewPew = (Missile) tempObj;
				while(it2.hasNext()) {
					GameObject tempObj2 = (GameObject) it2.getNext();
					if(tempObj2 instanceof Asteroid) {
						Asteroid roid = (Asteroid) tempObj2;
						//if(pewPew.getLocation() == roid.getLocation()) {
							System.out.println(pewPew.getShipType() + "'s Missile has destroyed an Asteroid");
							//store.remove(pewPew);
							if(pewPew.getShipType() instanceof PlayerShip) {
								this.playerScore += 10;
								System.out.println("+10 points");
							}
							it1.remove(pewPew);
							it1.remove(roid);
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
	public void missileHitNPS()
	{
		for (int i=0; i<store.size(); i++) {
			if(store.elementAt(i) instanceof Missile) {
				Missile pewPew = (Missile) store.get(i);
				for(int j=0; j<store.size(); j++) {
					if(store.elementAt(j) instanceof NonPlayerShip) {
						NonPlayerShip tiFighter = (NonPlayerShip) store.get(j);
						//if(pewPew.getLocation() == tiFighter.getLocation()) {
							System.out.println(pewPew.getShipType() + "'s Missile has destroyed an NPS");
							store.remove(pewPew);
							store.remove(tiFighter);
							if(pewPew.getShipType() instanceof PlayerShip) {
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
	public void explodedPS() {
		for (int i=0; i<store.size(); i++) {
			if(store.elementAt(i) instanceof Missile) {
				Missile pewPew = (Missile) store.get(i);
				for(int j=0; j<store.size(); j++) {
					if(store.elementAt(j) instanceof PlayerShip) {
						PlayerShip mFalcon = (PlayerShip) store.get(j);
						//if(pewPew.getLocation() == mFalcon.getLocation()) {
							System.out.println(pewPew.getShipType() + "'s Missile has hit the PS");
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
						System.out.println(pewPew.getShipType() + "'s Missile is out of fuel. Cya");
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
	
	public int getPlayerScore () 
	{
		return this.playerScore;
	}
	public int getNumLives () 
	{ 
		return this.numLives; 
	}
	public int getMissileCount () 
	{
		return this.numPSMissiles; 
	}
	public int getElapsedTime () 
	{ 
		return this.elapsedGameTime; 
	}
	public String getSoundState ()
	{
		if (this.sound == true)
			return "ON";
		else { return "OFF"; }
	}
	
	
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
