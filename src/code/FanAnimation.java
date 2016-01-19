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
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FanAnimation extends Application {
	/** create layouts */
	// Panes

	Pane author_pane = new Pane();
	Pane problem_pane = new Pane();
	Pane reference_pane = new Pane();
	BorderPane solution_pane = new BorderPane();
	Pane drawpane = new Pane();
	VBox root = new VBox();
	Scene scene;
	Stage stage;

	public static final double SCENE_WIDTH = 550;
	public static final double SCENE_HEIGHT = 500;

    private Timeline animation ;
    private FanPane fan;

	private Text reference_url,identity, declaim, problem;
	private Button author_button, problem_button, solution_button,reference_button;
	
	public void start(Stage stage) {
		

		// root.getChildren().addAll(pane1,button1,button2,button3);//first
		// panne the controls
		root = createFrontPage();
		scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT, Color.WHITE);
        scene.widthProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number old_value,
					Number new_value) {
				BigDecimal bg = new BigDecimal(new_value.doubleValue());  
				fan.setW(bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				
				
			}
		});
       // scene.heightProperty().addListener(e -> fan.setH(fan.getHeight()));
        scene.heightProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number old_value,
					Number new_value) {
				BigDecimal bg = new BigDecimal(new_value.doubleValue());  
				fan.setH(bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()/2);
				//fan.setH(fan.getHeight());
				
			}
		});
		stage.setTitle("Homework8");
		stage.setScene(scene);
		stage.show();
	}



	public VBox createFrontPage() {



		createAuthorPane();
		createProblemPane();
		createReferencePane();
		createSolutionPane();

		HBox buttonBox= new HBox(30);
		author_button = new Button("Author");
		problem_button = new Button("Problem");
		solution_button = new Button("Solution");
		reference_button = new Button("Reference");
		author_button.setLayoutX(100);
		author_button.setLayoutY(20);
		problem_button.setLayoutX(250);
		problem_button.setLayoutY(20);
		reference_button.setLayoutX(400);
		reference_button.setLayoutY(20);
		solution_button.setLayoutX(550);
		solution_button.setLayoutY(20);
		buttonBox.setAlignment(Pos.CENTER);
		VBox.setMargin(buttonBox, new Insets(12,12,12,12));
		buttonBox.getChildren().addAll(author_button, problem_button,reference_button,
				solution_button);

		root.getChildren().addAll(buttonBox,author_pane);

/*		root.getChildren().addAll(author_button, problem_button,reference_button,
				solution_button);*/

		author_button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				root.getChildren().removeAll(author_pane, problem_pane,reference_pane,
						solution_pane);
				root.getChildren().add(author_pane);
			}
		});

		problem_button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent actionEvent) {
				root.getChildren().removeAll(author_pane, problem_pane,reference_pane,
						solution_pane);
				root.getChildren().add(problem_pane);
			}
		});
		
		reference_button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent actionEvent) {
				root.getChildren().removeAll(author_pane, problem_pane,reference_pane,
						solution_pane);
				root.getChildren().add(reference_pane);
			}
		});

		solution_button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent actionEvent) {
				root.getChildren().removeAll(author_pane, problem_pane,reference_pane,
						solution_pane);
				root.getChildren().add(solution_pane);
				animation.play(); // Start animation
			}
		});

		return root;
	}





	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	public void createProblemPane() {
		
		
		problem = new Text(
				10,
				40,
				 "Draw fan and animate it, control animation with buttons to pause,reverse, and resume. In the following, Border pane is used, start and reverse buttons are in North, fan in center, and slider is used to control speed in bottom section of border pane."
				+"Use javadoc style comments. No UML diagram required. Use java conventions for naming classes,"
				+"methods and fields/variables. Name your program and files names accurately. You may use any java code"
				+"from the examples in the book or on my handouts/BB. Print your identification before the output is printed.");
		problem.setFont(new Font(20));
		problem.setFill(Color.BLUE);
		problem.setWrappingWidth(600);
		problem.setTextAlignment(TextAlignment.JUSTIFY);
		
		problem_pane.getChildren().addAll(problem);
		problem_pane.setLayoutX(10);
		problem_pane.setLayoutY(50);
		
	}
	
	public void createAuthorPane() {
		identity = new Text(10, 120,
				"\nPresented by Xudong Jin \n\nEmail address xjgw7@mst.edu\n\n");
		identity.setFont(new Font(20));
		identity.setFill(Color.GREEN);
		identity.setWrappingWidth(500);
		identity.setTextAlignment(TextAlignment.CENTER);

		author_pane.getChildren().addAll(identity);
		author_pane.setLayoutX(10);
		author_pane.setLayoutY(50);
		
	}
	
	private void createReferencePane() {		

		
		declaim = new Text(
				10,
				140,
				"This is my orignal code, No IDE used in the submission. \nI did not give my code to anyone or I did not use anyone's code in this work. \n" +
				"");
		declaim.setFont(new Font(20));
		declaim.setFill(Color.BLUE);
		declaim.setWrappingWidth(600);
		declaim.setTextAlignment(TextAlignment.CENTER);
		
		reference_url = new Text(10, 180,
				"\nLink: url address for reference to specific page and quotation");
		reference_url.setFont(new Font(20));
		reference_url.setFill(Color.GREEN);
		reference_url.setWrappingWidth(500);
		reference_url.setTextAlignment(TextAlignment.CENTER);
		
		reference_pane.getChildren().addAll(declaim, reference_url);
		reference_pane.setLayoutX(10);
		reference_pane.setLayoutY(50);
		
	}

	public void createSolutionPane() {
        animation = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
  			public void handle(ActionEvent actionEvent) {
  				fan.move();
			}
		}));

        animation.setCycleCount(Timeline.INDEFINITE);
        
        
        fan = new FanPane();
        HBox topPane = createTopPane();
        HBox bottomPane = createBottomPane();
		
        BorderPane.setMargin(topPane, new Insets(12,50,12,50));
        BorderPane.setMargin(bottomPane, new Insets(12,50,12,50));
        BorderPane.setMargin(fan, new Insets(12,12,12,12));
        BorderPane.setAlignment(fan, Pos.CENTER);
        solution_pane.setCenter(fan);
        solution_pane.setTop(topPane);
        solution_pane.setBottom(bottomPane);
		
		//solution_pane.setLayoutX(10);
		//solution_pane.setLayoutY(50);

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
	        
	        HBox.setMargin(btStart, new Insets(5,5,5,5));
	        HBox.setMargin(btReverse, new Insets(5,5,5,5));
	        HBox.setHgrow(btStart, Priority.ALWAYS);
	        HBox.setHgrow(btReverse, Priority.ALWAYS);
	        btStart.setMaxWidth(Double.MAX_VALUE);
	        btReverse.setMaxWidth(Double.MAX_VALUE);
	        
	        hBox.getChildren().addAll(btStart,btReverse);      
	        return hBox;
	    	
	    }
	    
	    private HBox createBottomPane(){
	    	
	    	
	    	HBox hBox = new HBox(15);
			Slider speedSlider = new Slider();
			speedSlider.setOrientation(Orientation.HORIZONTAL);
			speedSlider.setShowTickLabels(false);
			speedSlider.setShowTickMarks(false);
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
			
			HBox.setMargin(speedSlider, new Insets(5,5,5,5));
	        HBox.setHgrow(speedSlider, Priority.ALWAYS);
	        speedSlider.setMaxWidth(Double.MAX_VALUE);

	        
			hBox.getChildren().addAll(speedSlider);
	          
	        return hBox;
	    	
	    }
	    
	    
	    
	    class FanPane extends Pane {
	        private double w = SCENE_WIDTH;
	        private double h = SCENE_WIDTH/2;
	        private double radius = Math.min(w, h) * 0.2;
	        private int numberOfBlades =6;
	        private Arc arc[] = new Arc[numberOfBlades];
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
	            
            for (int i = 0; i < numberOfBlades; i++) {
	                arc[i] = new Arc(w / 2, h / 2, radius * 0.9, radius * 0.9, startAngle + i * 360/numberOfBlades, 35);
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
	            
	            for (int i = 0; i < numberOfBlades; i++) {
	                arc[i].setRadiusX(radius * 0.9);
	                arc[i].setRadiusY(radius * 0.9);
	                arc[i].setCenterX(w / 2);
	                arc[i].setCenterY(h / 2);
	                arc[i].setStartAngle(startAngle + i * 360/numberOfBlades);
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



}