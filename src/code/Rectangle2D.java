package code;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Rectangle2D {
	
	private double x;
	private double y;
	

	private double centerX;
	private double centerY;
	

	
	private double width;
	private double height;
	
	private Rectangle rectangleview;

	public Rectangle getRectangleview() {
		return rectangleview;
	}

	public void setRectangleview(Rectangle rectangleview) {
		this.rectangleview = rectangleview;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
		this.centerX = this.x+this.width/2;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
		this.centerY = this.y+this.height/2;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
		this.centerX = this.x+this.width/2;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
		this.centerY = this.y+this.height/2;
	}
	
	public double getCenterX() {
		return centerX;
	}

	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}

	public double getCenterY() {
		return centerY;
	}

	public void setCenterY(double centerY) {
		this.centerY = centerY;
	}
	
	public Rectangle2D() {
		super();
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
		rectangleview=new Rectangle();
		this.centerX = x+width/2;
		this.centerY = y+height/2;
		drawRectangle(x,y,width,height,Color.BLACK);
	}
	


	public Rectangle2D(double x, double y, double w,double h, Color color) {
		super();
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		rectangleview=new Rectangle();		
		this.centerX = x+width/2;
		this.centerY = y+height/2;		
		drawRectangle(x,y,w,h,color);
	}
	
	public void drawRectangle(Color color) {
		// TODO Auto-generated method stub
		rectangleview.setX(x);
		rectangleview.setY(y);
		rectangleview.setWidth(width);
		rectangleview.setHeight(height);
		rectangleview.setStroke(color);
		rectangleview.setStrokeWidth(2);
		rectangleview.setFill(null);
		
	}
	
	public void drawRectangle(double x, double y, double width,
			double height, Color color) {
		// TODO Auto-generated method stub
		rectangleview.setX(x);
		rectangleview.setY(y);
		rectangleview.setWidth(width);
		rectangleview.setHeight(height);
		rectangleview.setStroke(color);
		rectangleview.setStrokeWidth(2);
		rectangleview.setFill(null);
		
	}
	
	
	public boolean ec(Rectangle2D rectangle){
		double distanceX = Math.abs(rectangle.getCenterX()-this.centerX);
		double distanceY = Math.abs(rectangle.getCenterY()-this.centerY);
		double total_width = width/2+rectangle.getWidth()/2;
		double total_height = height/2+rectangle.getHeight()/2;
		
		if ((distanceX == total_width && total_height >=distanceY)
				|| (distanceY == total_height && total_width >=distanceX))
		  return true;
		else 
			return false;
		
	}
	
	public boolean po(Rectangle2D rectangle){
		double distanceX = Math.abs(rectangle.getCenterX()-this.centerX);
		double distanceY = Math.abs(rectangle.getCenterY()-this.centerY);
		double total_width = width/2+rectangle.getWidth()/2;
		double total_height = height/2+rectangle.getHeight()/2;
		
		if (distanceX < total_width && distanceY<total_height )
		  return true;
		else 
			return false;
		
	}
	
	
	public boolean tppc(Rectangle2D rectangle){
		double distanceX = Math.abs(rectangle.getCenterX()-this.centerX);
		double distanceY = Math.abs(rectangle.getCenterY()-this.centerY);
		double diff_width = width/2-rectangle.getWidth()/2;
		double diff_height = height/2-rectangle.getHeight()/2;
		if((distanceX==diff_width && distanceY<=diff_height)||(distanceY==diff_height && distanceX<=diff_width))
			return true;
		else 
			return false;

	}
	
	
	public boolean ntppc(Rectangle2D rectangle){
		double distanceX = Math.abs(rectangle.getCenterX()-this.centerX);
		double distanceY = Math.abs(rectangle.getCenterY()-this.centerY);
		double diff_width = width/2-rectangle.getWidth()/2;
		double diff_height = height/2-rectangle.getHeight()/2;
		if(distanceX<diff_width && distanceY<diff_height)
			return true;
		else 
			return false;

	}
	
	public boolean tpp(Rectangle2D rectangle){
		double distanceX = Math.abs(rectangle.getCenterX()-this.centerX);
		double distanceY = Math.abs(rectangle.getCenterY()-this.centerY);
		double diff_width = rectangle.getWidth()/2-width/2;
		double diff_height = rectangle.getHeight()/2-height/2;
		if((distanceX==diff_width && distanceY<=diff_height)||(distanceY==diff_height && distanceX<=diff_width))
			return true;
		else 
			return false;

	}
	
	
	public boolean ntpp(Rectangle2D rectangle){
		double distanceX = Math.abs(rectangle.getCenterX()-this.centerX);
		double distanceY = Math.abs(rectangle.getCenterY()-this.centerY);
		double diff_width = rectangle.getWidth()/2-width/2;
		double diff_height = rectangle.getHeight()/2-height/2;
		if(distanceX<diff_width && distanceY<diff_height)
			return true;
		else 
			return false;

	}
	
	
	

	
	@Override
	public boolean equals(Object obj) {
	
		if(this == obj)
		{
			return true;
		}
		
		if (obj != null && obj.getClass() == Rectangle2D.class)
		{
			Rectangle2D rectangleobj = (Rectangle2D)obj;
			if (this.x==rectangleobj.getX()
					&& this.y==rectangleobj.getY() 
					&& this.width==rectangleobj.getWidth()
					&& this.height==rectangleobj.getHeight()
					)
			{
				return true;
			}
		}
		return false;
	
	}

}
