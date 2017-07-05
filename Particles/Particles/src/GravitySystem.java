import java.util.ArrayList;
public class GravitySystem
{
	private ArrayList<Satellite> satellites;
	public GravitySystem()
	{
		satellites = new ArrayList<Satellite>();
	}
	public void addSatellite(Satellite s)
	{
		satellites.add(s);
	}
	public void moveSatellites()
	{		
		for(int x = 0; x<satellites.size(); x++)
		{
			ArrayList<Satellite> sats = new ArrayList<Satellite>(satellites);
			sats.remove(x);
			satellites.get(x).setAcc(sats);
		}	
		
		for(int x = 0; x<satellites.size(); x++)
		{
			Satellite s = satellites.get(x);
			s.moveSatellite();
		}	
	}
	public ArrayList<Satellite> getSatellites()
	{
		return satellites;
	}

}