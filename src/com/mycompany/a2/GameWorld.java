/*
 * Creates a Game Object vector to store all game objects.
 * Also contains all functions callable by Game.java, and
 * implements their functionalities.
 */

package com.mycompany.a2;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Vector;
import com.codename1.ui.Container;
import Interfaces.IGameWorld;
import Interfaces.IIterator;
import Objects.Asteroid;
import Objects.Missile;
import Objects.NonPlayerShip;
import Objects.PlayerShip;
import Objects.SpaceStation;
import Objects.SteerableMissileLauncher;
import Sounds.BGSound;
import Sounds.SoundEffects;

public class GameWorld extends Observable implements IGameWorld{
	private Random random = new Random(); 
	private GameObjectCollection gwc;
	private SoundEffects sounds;
	private int numLives;
	private int elapsedGameTime;
	private int playerScore;
	private int numPSMissiles;
	
	private double width;
	private double height;
	
	private boolean sound;
	
	
	public GameWorld()
	{
		gwc = new GameObjectCollection();
		sounds = new SoundEffects();
		sounds.bgMusicSound();
	}
	public void init()
	{
		numLives = 3;
		elapsedGameTime = 0;
		numPSMissiles = 10;
		sound = true;
		setChanged();
		notifyObservers(new GameWorldProxy(this));
	}
	
	public void addNewAsteroid() {
		Asteroid roid = new Asteroid(random.nextInt(25) + 6);
		addGameObject(roid);
		setChanged();
		notifyObservers(new GameWorldProxy(this));
	}
	
	public void addPlayerShip() throws Exception {
		IIterator theElements = getIterator();
		while(theElements.hasNext())
		{
			GameObject temp = (GameObject) theElements.getNext();
			if (temp instanceof PlayerShip) 
				throw new Exception("Error: There is already a playership!");
		}
		addGameObject(PlayerShip.getPlayerShip());
		setChanged();
		notifyObservers(new GameWorldProxy(this));
	}
	
	
	public void addNonPlayerShip() {
		NonPlayerShip tiFighter = new NonPlayerShip();
		addGameObject(tiFighter);
		setChanged();
		notifyObservers(new GameWorldProxy(this));
	}
	
	public void addSpaceStation() {
		SpaceStation deathStar = new SpaceStation();
		addGameObject(deathStar);
		setChanged();
		notifyObservers(new GameWorldProxy(this));
	}
	
	public void firePSMissile() {
		IIterator theElements = getIterator();
		while(theElements.hasNext()) {
			GameObject tempObj = (GameObject) theElements.getNext();
			if(tempObj instanceof PlayerShip) {
				PlayerShip mFalcon = (PlayerShip) tempObj;
				if(mFalcon.getMC() > 0) {
					Missile pewPew = new Missile(mFalcon);
					addGameObject(pewPew);
					mFalcon.decrementMC();
					this.numPSMissiles = mFalcon.getMC();
					System.out.println("PS missile fired");
					sounds.missileSound();
					setChanged();
					notifyObservers(new GameWorldProxy(this));
					return;
				}
				else {
					System.out.println("Error: Out of PS missiles!");
					return;
				}
			}
		}
		System.out.println("Error: No Player Ship to fire missiles from!");
	}
	
	public void fireNPSMissile() {
		IIterator theElements = getIterator();
		while(theElements.hasNext())
		{
			GameObject tempObj = (GameObject) theElements.getNext();
			if(tempObj instanceof NonPlayerShip) {
				NonPlayerShip tiFighter = (NonPlayerShip) tempObj;
				if(tiFighter.getMC() > 0) {
					Missile pewPew = new Missile(tiFighter);
					addGameObject(pewPew);
					tiFighter.decrementMC();
					System.out.println("NPS missile fired");
					setChanged();
					notifyObservers(new GameWorldProxy(this));
					return;
				}
				else {
					System.out.println("Error: Out of NPS missiles!");
					return;
				}
			}
		}
		System.out.println("Error: There are no NPS!");
	}
	
	public void printMap()
	{
		IIterator theElements = getIterator();
		System.out.println("\n");
		while(theElements.hasNext())
		{
			GameObject tempObj = (GameObject) theElements.getNext();
			System.out.println(tempObj);
		}
	}
	
	public void increasePSSpeed()
	{
		IIterator theElements = getIterator();
		while(theElements.hasNext()) {
			GameObject tempObj = (GameObject) theElements.getNext();
			if(tempObj instanceof PlayerShip) {
				PlayerShip mFalcon = (PlayerShip) tempObj;
				if(mFalcon.getSpeed() < 20) {
					mFalcon.increaseSpeed();
					System.out.println("Player Ship Speed increased to: " + mFalcon.getSpeed());
					setChanged();
					notifyObservers(new GameWorldProxy(this));
					return;
				}
				else {
					System.out.println("Ship is already going ludicrous speed. Cannot jump to plaid.");
					return;
				}
			}
		}
		System.out.println("Error: There is no Player Ship!");
	}
	
	public void decreasePSSpeed()
	{
		IIterator theElements = getIterator();
		while(theElements.hasNext()) {
			GameObject tempObj = (GameObject) theElements.getNext();
			if(tempObj instanceof PlayerShip) {
				PlayerShip mFalcon = (PlayerShip) tempObj;
				if(mFalcon.getSpeed() > 0) {
					mFalcon.decreaseSpeed();
					System.out.println("Player Ship Speed decreased to: " + mFalcon.getSpeed());
					setChanged();
					notifyObservers(new GameWorldProxy(this));
					return;
				}
				else {
					System.out.println("Ship is already going ludicrously slow.");
					return;
				}
			}
		}
		System.out.println("Error: There is no Player Ship!");
	}
	
	public void turnPSL()
	{
		IIterator theElements = getIterator();
		while(theElements.hasNext()) {
			GameObject tempObj = (GameObject) theElements.getNext();
			if(tempObj instanceof PlayerShip) {
				PlayerShip mFalcon = (PlayerShip) tempObj;
				mFalcon.turnLeft();
				System.out.println("Drifting lazily to the left");
				setChanged();
				notifyObservers(new GameWorldProxy(this));
				return;
			}
		}
		System.out.println("Error: There is no Player Ship!");
	}
	
	public void turnPSR()
	{
		IIterator theElements = getIterator();
		while(theElements.hasNext()) {
			GameObject tempObj = (GameObject) theElements.getNext();
			if(tempObj instanceof PlayerShip) {
				PlayerShip mFalcon = (PlayerShip) tempObj;
				mFalcon.turnRight();
				System.out.println("Drifting lazily to the right");
				setChanged();
				notifyObservers(new GameWorldProxy(this));
				return;
			}
		}
		System.out.println("Error: There is no Player Ship!");
	}
	
	public void turnPSMLLeft() {
		IIterator theElements = getIterator();
		while(theElements.hasNext()) {
			GameObject tempObj = (GameObject) theElements.getNext();
			if(tempObj instanceof PlayerShip) {
				PlayerShip mFalcon = (PlayerShip) tempObj;
				mFalcon.revolveMLLeft();
				System.out.println("Rotating Missile Launcher Left");
				sounds.launcherRotateSound();
				setChanged(); 
				notifyObservers(new GameWorldProxy(this));
				return;
			}
		}
		System.out.println("Error: There is no Player Ship!");
	}
	
	public void turnPSMLRight() {
		IIterator theElements = getIterator();
		while(theElements.hasNext()) {
			GameObject tempObj = (GameObject) theElements.getNext();
			if(tempObj instanceof PlayerShip) {
				PlayerShip mFalcon = (PlayerShip) tempObj;
				mFalcon.revolveMLRight();
				System.out.println("Rotating Missile Launcher Right");
				sounds.launcherRotateSound();
				setChanged();
				notifyObservers(new GameWorldProxy(this));
				return;
			}
		}
		System.out.println("Error: There is no Player Ship!");
	}
	
	public void jump()
	{
		IIterator theElements = getIterator();
		while(theElements.hasNext()) {
			GameObject tempObj = (GameObject) theElements.getNext();
			if(tempObj instanceof PlayerShip) {
				PlayerShip mFalcon = (PlayerShip) tempObj;
				mFalcon.setLocation(MapView.getMVWidth()/2, MapView.getMVHeight()/2);
				System.out.println("Punch it Chewie!");
				setChanged();
				notifyObservers(new GameWorldProxy(this));
				return;
			}
		}
		System.out.println("Error: There is no Player Ship!");
	}
	
	//TODO Currently reloads all ships. Make it so it only reloads ships "close to" stations
	public void reload()
	{
		IIterator theElements = getIterator();
		while(theElements.hasNext())
		{
			GameObject tempObj = (GameObject) theElements.getNext();
			if(tempObj instanceof Ship) {
				Ship tempShip = (Ship) tempObj;
				tempShip.reloadMissiles();
				if(tempShip instanceof PlayerShip) {
					PlayerShip mFalcon = (PlayerShip) tempShip;
					this.numPSMissiles = mFalcon.getMC();
				}
			}
		}
		setChanged();
		notifyObservers(new GameWorldProxy(this));
	}
	
	//TODO Currently destroys a random missile and asteroid. Make it destroy "close" missiles and asteroids
	public void missileHitAsteroid()
	{
		IIterator it1 = getIterator();
		IIterator it2 = getIterator();
		while(it1.hasNext()) {
			GameObject tempObj = (GameObject) it1.getNext();
			if(tempObj instanceof Missile) {
				Missile pewPew = (Missile) tempObj;
				while(it2.hasNext()) {
					GameObject tempObj2 = (GameObject) it2.getNext();
					if(tempObj2 instanceof Asteroid) {
						Asteroid roid = (Asteroid) tempObj2;
						//if(pewPew.getLocation() == roid.getLocation()) {
							System.out.println(pewPew.getShipType().getClass().getSimpleName() + 
									"'s Missile has destroyed an Asteroid");
							if(pewPew.getShipType() instanceof PlayerShip) {
								this.playerScore += 10;
								System.out.println("+10 points");
							}
							it1.remove(pewPew);
							it1.remove(roid);
							setChanged();
							notifyObservers(new GameWorldProxy(this));
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
		IIterator it1 = getIterator();
		IIterator it2 = getIterator();
		while(it1.hasNext()) {
			GameObject tempObj = (GameObject) it1.getNext();
			if(tempObj instanceof Missile) {
				Missile pewPew = (Missile) tempObj;
				while(it2.hasNext()) {
					GameObject tempObj2 = (GameObject) it2.getNext();
					if(tempObj2 instanceof NonPlayerShip) {
						NonPlayerShip tiFighter = (NonPlayerShip) tempObj2;
						//if(pewPew.getLocation() == tiFighter.getLocation()) {
							System.out.println(pewPew.getShipType().getClass().getSimpleName() + 
									"'s Missile has destroyed an NPS");
							it1.remove(pewPew);
							it1.remove(tiFighter);
							if(pewPew.getShipType() instanceof PlayerShip) {
								this.playerScore += 50;
								System.out.println("+50 points");
							}
							setChanged();
							notifyObservers(new GameWorldProxy(this));
							return;
						//}
					}
				}
			}
		}
	}
	
	//TODO Currently destroys a random Missile and the PS. Make it destroy "close" missile and PS
	public void explodedPS() {
		IIterator it1 = getIterator();
		IIterator it2 = getIterator();
		while(it1.hasNext()) {
			GameObject tempObj = (GameObject) it1.getNext();
			if(tempObj instanceof Missile) {
				Missile pewPew = (Missile) tempObj;
				while(it2.hasNext()) {
					//if(pewPew.getLocation() == mFalcon.getLocation()) {
					System.out.println(pewPew.getShipType().getClass().getSimpleName() + 
							"'s Missile has hit the PS");
					it1.remove(pewPew);
					this.numLives -= 1;
					if(this.numLives == 0) {
						System.out.println("Player Ship destroyed");
						sounds.gameOverSound();
						it1.remove(PlayerShip.getPlayerShip());
					}
					setChanged();
					notifyObservers(new GameWorldProxy(this));
					return;
					//}
				}
			}
		}
	}
	
	//TODO Currently destroys a random Asteroid and the PS. Make it destroy "close" Asteroid and PS
	public void asteroidHitPS()
	{
		IIterator it1 = getIterator();
		IIterator it2 = getIterator();
		while(it1.hasNext()) {
			GameObject tempObj = (GameObject) it1.getNext();
			if(tempObj instanceof Asteroid) {
				Asteroid roid = (Asteroid) tempObj;
				while(it2.hasNext()) {
				    //if(roid.getLocation() == mFalcon.getLocation()) {
						System.out.println("Let me guess, never tell you the odds?");
						it1.remove(roid);
						this.playerScore += 10;
						System.out.println("+10 points");
						this.numLives -= 1;
						if(this.numLives == 0) {
							System.out.println("Player Ship destroyed");
							sounds.gameOverSound();
							it1.remove(PlayerShip.getPlayerShip());
						}
						setChanged();
						notifyObservers(new GameWorldProxy(this));
						return;
					//}
					
				}
			}
		}
	}
	
	//TODO Currently destroys a random NPS and the PS. Make it destroy "close" NPS and PS
	public void npsHitPS() 
	{
		IIterator it1 = getIterator();
		IIterator it2 =getIterator();
		while(it1.hasNext()) {
			GameObject tempObj = (GameObject) it1.getNext();
			if(tempObj instanceof NonPlayerShip) {
				NonPlayerShip tiFighter = (NonPlayerShip) tempObj;
				while(it2.hasNext()) {
					//if(tiFighter.getLocation() == mFalcon.getLocation()) {
						System.out.println("Careful with the kamikazes");
						it1.remove(tiFighter);
						this.numLives -= 1;
						this.playerScore += 50;
						System.out.println("+50 points");
						if(this.numLives == 0) {
							System.out.println("Player Ship destroyed");
							sounds.gameOverSound();
							it1.remove(PlayerShip.getPlayerShip());
						}
						setChanged();
						notifyObservers(new GameWorldProxy(this));
						return;
					//}
				}
			}
		}
	}
	
	//TODO currently destroys two random Asteroids. Make it destroy "close" Asteroids
	public void asteroidHitAsteroid()
	{
		IIterator it1 = getIterator();
		IIterator it2 = getIterator();
		while(it2.hasNext()) {
			GameObject tempObj = (GameObject) it2.getNext();
			if(tempObj instanceof Asteroid) {
				Asteroid roid = (Asteroid) tempObj;
				while(it2.hasNext()) {
					GameObject tempObj2 = (GameObject) it2.getNext();
					if(tempObj2 instanceof Asteroid) {
						Asteroid roid2 = (Asteroid) tempObj2;
						//if(roid.getLocation() == roid2.getLocation()) {
							System.out.println("Two Asteroids collided");
							it1.remove(roid);
							it1.remove(roid2);
							setChanged();
							notifyObservers(new GameWorldProxy(this));
							return;
						//}
					}
				}
			}
		}
	}
	
	//TODO currently destroys a random Asteroid and random NPS. Make it destroy "close" Asteroid and NPS
	public void asteroidHitNPS() {
		IIterator it1 = getIterator();
		IIterator it2 = getIterator();
		while(it1.hasNext()) {
			GameObject tempObj = (GameObject) it1.getNext();
			if(tempObj instanceof Asteroid) {
				Asteroid roid = (Asteroid) tempObj;
				while(it2.hasNext()) {
					GameObject tempObj2 = (GameObject) it2.getNext();
					if(tempObj2 instanceof NonPlayerShip) {
						NonPlayerShip tiFighter = (NonPlayerShip) tempObj2;
						//if(roid.getLocation() == tiFighter.getLocation()) {
							System.out.println("An Asteroid collided with an NPS");
							it1.remove(roid);
							it1.remove(tiFighter);
							setChanged();
							notifyObservers(new GameWorldProxy(this));
							return;
						//}
					}
				}
			}
		}
	}
	
	public void tick() {
		IIterator it1 = getIterator();
		IIterator it2 = getIterator();
		elapsedGameTime += 1; 
		while(it1.hasNext()) {
			GameObject tempObj = (GameObject) it1.getNext();
			if(tempObj instanceof MoveableGameObject) {
				MoveableGameObject moveR = (MoveableGameObject) tempObj;
				moveR.move(elapsedGameTime);
				if(moveR instanceof Missile) {
					Missile pewPew = (Missile) moveR;
					pewPew.decrementFuelLevel();
					if (pewPew.getFuelLevel() == 0) {
						System.out.println(pewPew.getShipType() + "'s Missile is out of fuel. Cya");
						it1.remove(pewPew);
						return;
					}
				}
			}
			else if(tempObj instanceof SpaceStation) {
				SpaceStation deathStar = (SpaceStation) tempObj;
				if((this.elapsedGameTime % deathStar.getBlinkRate()) == 0)
					deathStar.toggleLight();
			}
		}
		//if()
		setChanged();
		notifyObservers(new GameWorldProxy(this));
	}
	
	public void toggleSound() {
		this.sound = !sound;
		sounds.toggleEnable();
		setChanged();
		notifyObservers(new GameWorldProxy(this));
	}
	

	public int getPlayerScore () { return this.playerScore; }
	public int getNumLives () { return this.numLives; }
	public int getMissileCount () { return this.numPSMissiles; }
	public int getElapsedTime () { return this.elapsedGameTime; }
	public boolean getSound() { return this.sound; }
	public String getSoundState ()
	{
		if (this.sound == true)
			return "ON";
		else { return "OFF"; }
	}
	public GameObjectCollection getGameCollection() { return gwc;}
	
	public void setPlayerScore (int x) {this.playerScore = x;}
	public void setNumLives (int x) { this.numLives = x; }
	public void setMissileCount (int x) { this.numPSMissiles = x; }
	public void setElapsedTime (int x) { this.elapsedGameTime = x; }
	public void setSound (boolean x) { this.sound = x;}
	
	
	@Override
	public IIterator getIterator() {
		return gwc.getIterator();
	}

	
	public void addGameObject(GameObject o) {
		gwc.add(o);
	}
 
	@Override
	public boolean removeGameObject(GameObject o) {
		gwc.getIterator().remove(o);
		return false;
	}
	
	public void setDimensions(Container mv)
	{
		width = mv.getWidth();
		height = mv.getHeight();
	}
	
	public double getMVWidth()
	{
		return width;
	}
	
	public double getMVHeight()
	{
		return height;
	}
}
