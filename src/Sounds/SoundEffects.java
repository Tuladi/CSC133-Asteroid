package Sounds;

public class SoundEffects {
	private Sound fireMissile, launcherRotates, gameOver;
	private BGSound spaceMusic;
	private boolean enable;
	private int volume, bgVolume;
	public SoundEffects()
	{
		volume = 50;
		bgVolume = 50;
		enable = true;
		fireMissile = new Sound("Missile.wav");
		launcherRotates = new Sound("LauncherTurn.wav");
		gameOver = new Sound("GameOver.wav");
		spaceMusic = new BGSound("Space.wav");
	}
	
	public void missileSound(){
		if (enable){
			fireMissile.play(getVolume());
		}
	}
	
	public void launcherRotateSound(){
		if (enable){
			launcherRotates.play(getVolume());
		}
	}
	
	public void gameOverSound(){
		if (enable){
			gameOver.play(getVolume());
		}
	}
	
	public void bgMusicSound()
	{
		if (enable){
			spaceMusic.play(getBGVolume());
		}
	}
	public int getVolume()
	{
		return volume;
	}
	
	public int getBGVolume()
	{
		return bgVolume;
	}
	
	public void toggleEnable()
	{
		this.enable = !this.enable;
	}
}
