import java.util.*;
public class Satellite
{
	private double mass,xPos,yPos,xVel,yVel,xAcc,yAcc;

	public Satellite(double xP,double yP,double xV,double yV,double m)
	{
		mass=Math.abs(m);
		xPos=xP;
		yPos=yP;
		xVel=xV;
		yVel=yV;
		xAcc=0;
		yAcc=0;
	}
	
	private double getXForce(Satellite s)
	{
		double x = s.getXPos()-xPos;
		double y = s.getYPos()-yPos;
		double angle = Math.atan(y/x);
		double r =  Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
		double force = mass*s.getMass()/r;
		double xForce = Math.cos(angle)*force;
		if(x>0)
			return Math.abs(xForce);
		return -1*Math.abs(xForce);		
	}
	
	private double getYForce(Satellite s)
	{
		
		double x = s.getXPos()-xPos;
		double y = s.getYPos()-yPos;
		double angle = Math.atan(y/x);
		double r =  Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
		double force = mass*s.getMass()/r;
		double yForce = Math.sin(angle)*force;
		if(y>0)
			return Math.abs(yForce);
		return -1*Math.abs(yForce);			
	}
	
	public double getXPos()
	{				
		return xPos;
	}
	
	public double getYPos()
	{
		return yPos;
	}
	
	public double getMass()
	{
		return mass;
	}
	
	public void moveSatellite()
	{	
		xVel+=xAcc;
		yVel+=yAcc;
		xPos+=xVel-xAcc/2;
		yPos+=yVel-yAcc/2;
	}

	public void setAcc(ArrayList<Satellite> satellites)
	{
		int sumForces = 0;
		for(Satellite satellite : satellites)
		{
			sumForces+= this.getXForce(satellite);
		}
		xAcc = sumForces/mass;
		
		sumForces = 0;
		for(Satellite satellite : satellites)
		{
			sumForces+= this.getYForce(satellite);
		}
		yAcc = sumForces/mass;		
	}
}