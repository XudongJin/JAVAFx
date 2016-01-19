package code;

import java.math.BigDecimal;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FanWithControls extends Application {
	
	public static final double MAXSPEED = 400;
	
    private Timeline animation ;
    private FanPane fan;
    
    public void start(Stage primaryStage) {
      
        
        animation = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
  			public void handle(ActionEvent actionEvent) {
  				fan.move();
			}
		}));

        animation.setCycleCount(Timeline.INDEFINITE);
        
        
        fan = new FanPane();
        HBox topPane = createTopPane();
        HBox bottomPane = createBottomPane();
        
        
        BorderPane pane = new BorderPane();
        pane.setCenter(fan);
        pane.setTop(topPane);
        pane.setBottom(bottomPane);
      
        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 200, 200);
        primaryStage.setTitle("FanWithControls"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
        

        animation.play(); // Start animation
        
      //  scene.widthProperty().addListener(e -> fan.setW(fan.getWidth()));
        scene.widthProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number old_value,
					Number new_value) {
				fan.setW(fan.getWidth());
				
			}
		});
       // scene.heightProperty().addListener(e -> fan.setH(fan.getHeight()));
        scene.heightProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number old_value,
					Number new_value) {
				fan.setH(fan.getHeight());
				
			}
		});

    }
    
    private HBox createTopPane(){
    	
        HBox hBox = new HBox(15);

       // gPane.setPadding(new Insets(12, 11, 11, 11));

        final Button btStart = new Button("Pause");
        final Button btReverse = new Button("Reverse");
        
        
      //  btPause.setOnAction(e -> animation.pause());
        
        btStart.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				if(animation.getStatus()== Animation.Status.PAUSED){
				animation.play();
				btStart.setText("Pause");
				}
				else {
				animation.pause();
				btStart.setText("Start");
				}
			}
		});
        

          btReverse.setOnAction(new EventHandler<ActionEvent>() {
    			@Override
      			public void handle(ActionEvent actionEvent) {
    				fan.reverse();
      			}
      		});
        
       hBox.setAlignment(Pos.CENTER);
        hBox.setStyle("-fx-border-color:blue;-fx-border-width:5");
       // hBox.setStyle("-fx-border-width: 5");
		//rectangleview.setStrokeWidth(2);
       // hBox.setPadding(new Insets(12, 11, 11, 11));
        hBox.getChildren().addAll(btStart,btReverse);      
        return hBox;
    	
    }
    
    private HBox createBottomPane(){
    	
    	
    	HBox hBox = new HBox(15);
		Slider speedSlider = new Slider();
		speedSlider.setOrientation(Orientation.HORIZONTAL);
		speedSlider.setShowTickLabels(true);
		speedSlider.setShowTickMarks(true);
	//	speedSlider.setValue(animation.getCurrentTime().toMillis());
		speedSlider.setValue(animation.getRate());
		speedSlider.setMax(animation.getRate()*2);
		speedSlider.setMin(0);
		
		//speedSlider.setValue(rectangle1.getWidth());
		speedSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> ov,
					Number old_value, Number new_value) {	
				BigDecimal bg = new BigDecimal(new_value.doubleValue());  
				animation.setRate(bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}
		});
		
		
		hBox.setAlignment(Pos.CENTER);
		hBox.setStyle("-fx-border-color:deeppink;-fx-border-width:5");

		hBox.getChildren().addAll(speedSlider);
          
        return hBox;
    	
    }
    
    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
class FanPane extends Pane {
    private double w = 200;
    private double h = 200;
    private double radius = Math.min(w, h) * 0.45;
    private Arc arc[] = new Arc[4];
    private double startAngle = 30;
    private Circle bigCircle = new Circle(w / 2, h / 2, radius);
    private Circle middleCircle = new Circle(w / 2, h / 2, radius*2/3);
    private Circle smallCircle = new Circle(w / 2, h / 2, radius/3);
    public FanPane() {
        bigCircle.setStroke(Color.BLUE);
        bigCircle.setStrokeWidth(5);
        bigCircle.setFill(Color.WHITE);
        middleCircle.setStroke(Color.BLUE);
        middleCircle.setFill(Color.WHITE);
        smallCircle.setStroke(Color.BLUE);
        smallCircle.setFill(Color.WHITE);
        getChildren().addAll(bigCircle,middleCircle,smallCircle);
        
        for (int i = 0; i < 4; i++) {
            arc[i] = new Arc(w / 2, h / 2, radius * 0.9, radius * 0.9, startAngle + i * 90, 35);
            arc[i].setFill(Color.RED); // Set fill color
            arc[i].setType(ArcType.ROUND);
            getChildren().addAll(arc[i]);
        }
    }
    
    private double increment = 5;
    
    public void reverse() {
        increment = -increment;
    }
    
    public void move() {
        setStartAngle(startAngle + increment);
    }
    
    public void setStartAngle(double angle) {
        startAngle = angle;
        setValues();
    }
    
    public void setValues() {
        radius = Math.min(w, h) * 0.45;
        bigCircle.setRadius(radius);
        bigCircle.setCenterX(w / 2);
        bigCircle.setCenterY(h / 2);
        middleCircle.setRadius(radius*2/3);
        middleCircle.setCenterX(w / 2);
        middleCircle.setCenterY(h / 2);
        smallCircle.setRadius(radius/3);
        smallCircle.setCenterX(w / 2);
        smallCircle.setCenterY(h / 2);
        
        for (int i = 0; i < 4; i++) {
            arc[i].setRadiusX(radius * 0.9);
            arc[i].setRadiusY(radius * 0.9);
            arc[i].setCenterX(w / 2);
            arc[i].setCenterY(h / 2);
            arc[i].setStartAngle(startAngle + i * 90);
        }
    }
    
    public void setW(double w) {
        this.w = w;
        setValues();
    }
    
    public void setH(double h) {
        this.h = h;
        setValues();
    }
}