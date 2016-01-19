package code;

import java.util.Scanner;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
 
public class Shape extends Application{
	
	 public static final Color SHAPE1_COLOR  = Color.RED;	 
	 public static final Color SHAPE2_COLOR  = Color.BLUE;
	
	private Label     label0,label1,label2,label3,space,colorlabel1,colorlabel2;
	private TextField tField;
    Pane drawpane = new Pane();
    VBox solution_pane = new VBox();


	
	
   public static void main(String[] args) {
	   Application.launch(args);
    }
   
// Create a pane and set its properties
/*   public void start(Stage primaryStage) {
	// Create a pane and set its properties
	Pane pane = new Pane();
	Text text = new Text(20, 20, "Programming is
	fun");
	pane.getChildren().addAll(text);
	text.setOnMouseDragged(e -> {
	text.setX(e.getX());
	text.setY(e.getY());
	text.setText("Programming is Fun,
	mouse is at ("+e.getX()+","+e.getY()+")");
	
	setOnAction(new EventHandler<ActionEvent>
{ public void handle(ActionEvent e) {
System.out.println("Push button clicked");}
});
	});*/
 
   
   /** Create label */
   public void createPane (){
	   
	   Font font1 = Font.font("Courier New", FontWeight.BOLD, FontPosture.REGULAR, 18);
	   Font font2 = Font.font("SanSerif", FontWeight.BOLD, FontPosture.REGULAR, 20);
	   label0 = new Label();
	   label0.setText("Spatial Relations by Xudong Jin");
	   label0.setTextAlignment(TextAlignment.CENTER);
	   label0.setFont(font1);
	   label0.setTextFill(Color.BLUE);
	   space = new Label();
	   space.setText(" ");
	   space.setTextAlignment(TextAlignment.CENTER);
	   label1 = new Label();
	   label1.setText("Input Circles:x1 y1 r1 x2 y2 r2");
	   label1.setTextAlignment(TextAlignment.CENTER);
	   label1.setFont(font1);
	   label1.setTextFill(Color.BLUE);
	   label2 = new Label();
	   label2.setText(" ");
	   label2.setTextAlignment(TextAlignment.CENTER);
	   colorlabel1=new Label();
	   colorlabel1.setText(" ");
	   colorlabel1.setTextAlignment(TextAlignment.CENTER);
	   colorlabel1.setTextFill(SHAPE1_COLOR);
	   colorlabel2=new Label();
	   colorlabel2.setText(" ");
	   colorlabel2.setTextAlignment(TextAlignment.CENTER);
	   colorlabel2.setTextFill(SHAPE2_COLOR);
	   label3 = new Label();
	   label3.setText(" ");
	   label3.setTextAlignment(TextAlignment.CENTER);
	   label3.setFont(font2);
	   label3.setTextFill(Color.GREEN);
	   tField = new TextField();
	   tField.setOnAction(new TextFieldHandler());
	   tField.setStyle("-fx-text-inner-color: green");  
	   
	   Slider widthSlider = new Slider();
	   widthSlider.setOrientation(Orientation.VERTICAL);
	   widthSlider.setShowTickLabels(true);
	   widthSlider.setShowTickMarks(true);
	   widthSlider.setValue(100);
	   widthSlider.valueProperty().addListener(new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> ov, Number old_value, Number new_value) {
			// TODO Auto-generated method stub
			
           //  sepiaValue.setText(String.format("%.2f", new_value));
		}
	});
	   
	   Slider heightSlider = new Slider();
	   
	   heightSlider.setOrientation(Orientation.VERTICAL);
	   heightSlider.setShowTickLabels(true);
	   heightSlider.setShowTickMarks(true);
	   heightSlider.setValue(100);
  
       solution_pane.getChildren().addAll(label0,space,label1, tField,
       label2,colorlabel1,colorlabel2,label3,drawpane);


   }
   
	class TextFieldHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			String str = tField.getText();
			String input,result;
			double x1,y1,r1,x2,y2,r2;
			Scanner myscanner = new Scanner(str);
			x1 = myscanner.nextDouble();
			y1 = myscanner.nextDouble();
			r1 = myscanner.nextDouble();
			x2 = myscanner.nextDouble();
			y2 = myscanner.nextDouble();
			r2 = myscanner.nextDouble();
			myscanner.close();
			tField.setText(""); // clear data entry field
			Circle2D circle1= new Circle2D(x1,y1,r1,SHAPE1_COLOR);
			Circle2D circle2= new Circle2D(x2,y2,r2,SHAPE2_COLOR);		
			input = String.format("Input is:"+x1+" "+y1+" "+r1+" "+x2+" "+y2+" "+r2);
			label2.setText(input);
			colorlabel1.setText("Circle1 is RED");
			colorlabel2.setText("Circle2 is BLUE");
			  if(circle1.equals(circle2))
				  result = String.format("EQ:Two circles are equal");
			  else if (circle1.ntppc(circle2))
				  result = String.format("NTPPC:circle1 is inside the circle2\nwithout touching the boundaries");	
			  else if (circle1.tppc(circle2))
				  result = String.format("TPPC:circle1 is inside the circle2\nwith touching the boundaries");	
			  else if (circle1.ntpp(circle2))
				  result = String.format("NTPP:circle2 is inside the circle1\nwithout touching the boundaries");	
			  else if (circle1.tpp(circle2))
				  result = String.format("TPP:circle2 is inside the circle1\nwith touching the boundaries");							
			  else if (circle1.po(circle2))
				  result = String.format("PO:Two circles are proper overlap");
			  else if (circle1.ec(circle2))
				  result = String.format("EC:Two circles are disjoint\nwith touching the boundaries ");
			  else
				  result = String.format("DC:Two circles are disjoint\nwithout touching the boundaries");
			label3.setText(result);	
			drawpane.getChildren().clear();
			drawpane.getChildren().addAll(circle1.getCircleview(),circle2.getCircleview());
			//root.getChildren().add(pane);
		}
	}
	
	
	

   
  
   @Override
   public void start(Stage stage) {
              
       createPane();
       Scene scene = new Scene(solution_pane, 450, 600);
       stage.setTitle("Homework3 by Xudong Jin");
       stage.setScene(scene);
       stage.show();

       
    }
}
