
public class Laser
{
	public double b;
	//inverse slope
	public double s;
	public double iX;
	public double iY;
	public double fX;
	public double fY;
	public Laser(double x1,double y1, double x2, double y2)
	{
		iX = x1;
		iY = y1;
		fX = x2;
		fY = y2;
		s = (x1-x2)/(y1-y2);
		b = iX-iY*s;
		shortanLaser();
		
	}
	private void shortanLaser()
	{
		int z1;
		int z2;
		if(iY<110)
			z1 = 1;
		else if(iY<220)
			z1 = 2;
		else if(iY<330)
			z1 = 3;
		else
			z1 = 4;
		if(fY<110)
			z2 = 1;
		else if(fY<220)
			z2 = 2;
		else if(fY<330)
			z2 = 3;
		else
			z2 = 4;
		shortanLaserHelp(z1,z2);
	}
	private void shortanLaserHelp(int z1,int z2)
	{
		if(z1!=z2)
		{
			if(z1==1&&z2==2)
			{
				if(!(passedGap(110,0,188)||passedGap(110,288,562)||passedGap(110,662,850)))
				{
					fY = 110;
					fX = s*110+b;
				}
			}
			if(z1==1&&z2==3)
			{
				if(!(passedGap(110,0,188)||passedGap(110,288,562)||passedGap(110,662,850)))
				{
					fY = 110;
					fX = s*110+b;
				}
				else if(!(passedGap(220,100,375)||passedGap(220,475,750)))
				{
					fY=220;
					fX = s*220+b;
				}
			}
			if(z1==1&&z2==4)
			{
				if(!(passedGap(110,0,188)||passedGap(110,288,562)||passedGap(110,662,850)))
				{
					fY = 110;
					fX = s*110+b;
				}
				else if(!(passedGap(220,100,375)||passedGap(220,475,750)))
				{
					fY=220;
					fX = s*220+b;
				}
				else if(!(passedGap(330,0,188)||passedGap(330,288,562)||passedGap(330,662,850)))
				{
					fY = 330;
					fX = s*330+b;
				}				
			}
			if(z1==2&&z2==1)
			{
				if(!(passedGap(110,0,188)||passedGap(110,288,562)||passedGap(110,662,850)))
				{
					fY = 110;
					fX = s*110+b;
				}			
			}
			if(z1==2&&z2==3)
			{
				if(!(passedGap(220,100,375)||passedGap(220,475,750)))
				{
					fY=220;
					fX = s*220+b;
				}				
			}
			if(z1==2&&z2==4)
			{
				if(!(passedGap(220,100,375)||passedGap(220,475,750)))
				{
					fY=220;
					fX = s*220+b;
				}
				else if(!(passedGap(330,0,188)||passedGap(330,288,562)||passedGap(330,662,850)))
				{
					fY = 330;
					fX = s*330+b;
				}					
			}
			if(z1==3&&z2==1)
			{
				if(!(passedGap(220,100,375)||passedGap(220,475,750)))
				{
					fY=220;
					fX = s*220+b;
				}
				else if(!(passedGap(110,0,188)||passedGap(110,288,562)||passedGap(110,662,850)))
				{
					fY = 110;
					fX = s*110+b;
				}								
			}
			if(z1==3&&z2==2)
			{
				if(!(passedGap(220,100,375)||passedGap(220,475,750)))
				{
					fY=220;
					fX = s*220+b;
				}				
			}
			if(z1==3&&z2==4)
			{
				if(!(passedGap(330,0,188)||passedGap(330,288,562)||passedGap(330,662,850)))
				{
					fY = 330;
					fX = s*330+b;
				}					
			}
			if(z1==4&&z2==1)
			{
				if(!(passedGap(330,0,188)||passedGap(330,288,562)||passedGap(330,662,850)))
				{
					fY = 330;
					fX = s*330+b;
				}
				else if(!(passedGap(220,100,375)||passedGap(220,475,750)))
				{
					fY=220;
					fX = s*220+b;
				}
				else if(!(passedGap(110,0,188)||passedGap(110,288,562)||passedGap(110,662,850)))
				{
					fY = 110;
					fX = s*110+b;
				}					
			}
			if(z1==4&&z2==2)
			{
				if(!(passedGap(330,0,188)||passedGap(330,288,562)||passedGap(330,662,850)))
				{
					fY = 330;
					fX = s*330+b;
				}
				else if(!(passedGap(220,100,375)||passedGap(220,475,750)))
				{
					fY=220;
					fX = s*220+b;
				}				
			}
			if(z1==4&&z2==3)
			{
				if(!(passedGap(330,0,188)||passedGap(330,288,562)||passedGap(330,662,850)))
				{
					fY = 330;
					fX = s*330+b;
				}				
			}
		}
		
	}
	private boolean passedGap(int y,int x1,int x2)
	{
		double point = s*y+b;
		if(point>x1&&point<x2)
			return true;
		return false;
	}
	
}
