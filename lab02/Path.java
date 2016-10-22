/** A class that represents a path via pursuit curves.
 *  @author You!
 */
//import Point.java;
public class Path {
	private Point currPoint;
	private Point nextPoint;
    /** What to do, what to do... */
	public Path(double x, double y){
		this.nextPoint = new Point(x,y);
		this.currPoint = new Point(0,0);
	}
	public double getCurrX(){
		return this.currPoint.getX();
	}
	public double getCurrY(){
		return this.currPoint.getY();
	}
	public double getNextX(){
		return this.nextPoint.getX();
	}
	public double getNextY(){
		return this.nextPoint.getY();
	}
	public void iterate(double dx, double dy){
		this.currPoint=this.nextPoint;
		double temp_x= this.getCurrX();
		double temp_y= this.getCurrY();
		this.nextPoint = new Point(temp_x+dx,temp_y+dy);
	}

}
