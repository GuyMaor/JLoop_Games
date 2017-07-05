

public class JLCamera extends JLRectangle
{
	private double originalWidth;
	private double perspectiveRatio;
	private double originalX;
	private double originalY;
	private double inflationRatio;
	/**
	 * Creates a JLCamera object.
	 * @param the x position of the camera
	 * @param the y position of the camera
	 * @param the width of the camera
	 * @param the height of the camera
	 */
	public JLCamera(double x, double y, double w, double h)
	{
		super(x,y,w,h);
		originalWidth = w;
		perspectiveRatio = h/w;
		originalX = w;
		originalY = y;
		inflationRatio = 1;
	}
	/**
	 * inflates the camera size by a given ratio
	 * @param the ratio that will inflate the camera
	 */
	public void inflateCameraBy(double r)
	{
		inflationRatio*=r;
		double newWidth = originalWidth*inflationRatio;
		double newHeight = newWidth*perspectiveRatio;
		changeWidthTo(newWidth);
		changeHeightTo(newHeight);
		super.moveHorTo(originalX+(originalWidth-newWidth)/2);
		super.moveVertTo(originalY+(originalWidth*perspectiveRatio-newHeight)/2);
	}
	/**
	 * moves the camera horizontally
	 * @param the amount it will move horizontally.
	 */
	public void moveHor(double x)
	{
		super.moveHor(x);
		originalX+=x;
	}
	/**
	 * moves the camera vertically
	 * @param the amount it will move vertically.
	 */
	public void moveVert(double y)
	{
		super.moveVert(y);
		originalY+=y;
	}
	/**
	 * moves the camera to a given x location.
	 * @param the x location the camera will move.
	 */
	public void moveHorTo(double x)
	{
		double xDiff = (originalWidth-getWidth())/2;
		super.moveHorTo(x+xDiff);
		originalX = x;
	}
	/**
	 * moves the camera to a give y location
	 * @param the y location the camera will move.
	 */
	public void moveVertTo(double y)
	{
		double yDiff = (originalWidth*perspectiveRatio-getHeight())/2;
		super.moveVertTo(y+yDiff);
		originalY = y;
	}
	/**
	 * resets the camera to its original size.
	 */
	public void resetCameraSize()
	{
		moveHorTo(originalX);
		moveVertTo(originalY);
		changeWidthTo(originalWidth);
		changeHeightTo(originalWidth*perspectiveRatio);
	}
	/**
	 * gets the current camera size divided
	 * by the original camera size which is
	 * called the inflation ratio.
	 * @return the inflation ratio
	 */
	public double getInflationRatio()
	{
		return inflationRatio;
	}
}
