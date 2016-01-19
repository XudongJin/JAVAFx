package code;

import static java.lang.Math.PI;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Circle2D1 {


	private double x;
	private double y;
	
	private double radius;
	
	private Circle circleview=new Circle();
	


	public double getRadius() {
		return radius;
	}
	
	public double getX() {
		return x;
	}



	public double getY() {
		return y;
	}

	
	public Circle2D1() {
		super();
		this.x = 0;
		this.y = 0;
		this.radius = 0;
		drawCircle(x,y,radius,Color.BLACK);
	}
	
	public Circle2D1(double x, double y, double radius,Paint color) {
		super();
		this.x = x;
		this.y = y;
		this.radius = radius;
		circleview=new Circle();
		drawCircle(x,y,radius,color);
	}
	
	
	public double getArea(){
		return radius*radius*PI;
	}
	
	
	public double getPerimeter(){
		return 2*radius;
	}
	
	public Circle getCircleview(){
		return circleview;
	}
	
	
	public boolean contains(double x, double y){
		if(distance2centre(x,y)<radius)
			return true;
		else 
			return false;
	}
	
	
	public boolean contains(Circle2D circle){
		double distance = distance2centre(circle.getX(),circle.getY());
		if(distance<=Math.abs((circle.getRadius()-radius)))
			return true;
		else 
			return false;

	}
	
	public boolean overlaps(Circle2D circle){
		double distance = distance2centre(circle.getX(),circle.getY());
		if(distance<(circle.getRadius()+radius)&&
				distance>Math.abs((circle.getRadius()-radius)))
			return true;
		else 
			return false;
		
	}
	
	private void drawCircle(double x,double y,double r,Paint color){
        // Create a circle and set its properties
        //circleview = new Circle(x,y,r);
        circleview.setCenterX(x);
        circleview.setCenterY(y);
        circleview.setRadius(r);
        circleview.setStroke(color);
        circleview.setStrokeWidth(2);
        circleview.setFill(null);
        
        
       
	}

	
	private double distance2centre(double x,double y){
		return Math.sqrt((x-this.x)*(x-this.x)+(y-this.y)*(y-this.y));
	}

	@Override
	public boolean equals(Object obj) {
	
		if(this == obj)
		{
			return true;
		}
		
		if (obj != null && obj.getClass() == Circle2D.class)
		{
			Circle2D cirelceObj = (Circle2D)obj;
			if (this.x==cirelceObj.getX()
					&& this.y==cirelceObj.getY() 
					&& this.radius==cirelceObj.getRadius())
			{
				return true;
			}
		}
		return false;
	
	}
	
	

}
