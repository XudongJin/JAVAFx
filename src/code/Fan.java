package code;

import java.math.BigDecimal;
import java.rmi.activation.ActivationGroupDesc.CommandEnvironment;
import java.util.ArrayList;

import org.omg.CORBA.PUBLIC_MEMBER;

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
import javafx.scene.control.Label;
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
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.geometry.Rectangle2D;

public class Fan extends Application {
	/** create layouts */
	// Panes

	BorderPane author_pane = new BorderPane();
	BorderPane problem_pane = new BorderPane();
	BorderPane reference_pane = new BorderPane();
	BorderPane solution_pane = new BorderPane();
	Pane drawpane = new Pane();
	VBox root = new VBox();
	HBox centerpan = new HBox(5);
	ArrayList<FanAnimationPane> fanListPane = new ArrayList<FanAnimationPane>();
	Scene scene;
	Stage stage;
	
    public Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

	public final double SCENE_WIDTH = screenBounds.getWidth();
	public final double SCENE_HEIGHT = screenBounds.getHeight();
	public static final int DEFAULT_BLADE_NUM = 6;
	public static final int DEFAULT_PANE_NUM = 5;
	
	public static final int MAX_BLADE_NUM = 10;
	public static final int MAX_PANE_NUM = 5;

	// private Timeline animation;
	// private FanPane fan;

	private Text reference_url, identity, declaim, problem;
	private Button author_button, problem_button, solution_button,
			reference_button;

	public void start(Stage stage) {

		// root.getChildren().addAll(pane1,button1,button2,button3);//first
		// panne the controls
		root = createFrontPage();

		//scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT, Color.WHITE);
		scene = new Scene(root, SCENE_WIDTH,SCENE_HEIGHT, Color.WHITE);

		stage.setTitle("Homework9");
		stage.setScene(scene);
		stage.show();
	}

	public VBox createFrontPage() {

		createAuthorPane();
		createProblemPane();
		createReferencePane();
		createSolutionPane();

		HBox buttonBox = new HBox(30);
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
		VBox.setMargin(buttonBox, new Insets(12, 12, 12, 12));
		buttonBox.getChildren().addAll(author_button, problem_button,
				reference_button, solution_button);

		root.getChildren().addAll(buttonBox, author_pane);

		/*
		 * root.getChildren().addAll(author_button,
		 * problem_button,reference_button, solution_button);
		 */

		author_button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				root.getChildren().removeAll(author_pane, problem_pane,
						reference_pane, solution_pane);
				root.getChildren().add(author_pane);
			}
		});

		problem_button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent actionEvent) {
				root.getChildren().removeAll(author_pane, problem_pane,
						reference_pane, solution_pane);
				root.getChildren().add(problem_pane);
			}
		});

		reference_button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent actionEvent) {
				root.getChildren().removeAll(author_pane, problem_pane,
						reference_pane, solution_pane);
				root.getChildren().add(reference_pane);
			}
		});

		solution_button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent actionEvent) {
				root.getChildren().removeAll(author_pane, problem_pane,
						reference_pane, solution_pane);
				root.getChildren().add(solution_pane);

				/*
				 * for(int i=0;i<fanListPane.size();++i){
				 * fanListPane.get(i).getAnimation().play(); // Start animation
				 * }
				 */

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
				"Draw fan and animate it, control animation with buttons to pause,reverse, and resume. In the following, Border pane is used, start and reverse buttons are in North, fan in center, and slider is used to control speed in bottom section of border pane.");
		problem.setFont(new Font(20));
		problem.setFill(Color.BLUE);
		problem.setWrappingWidth(600);
		problem.setTextAlignment(TextAlignment.JUSTIFY);

	//	problem_pane.getChildren().addAll(problem);
		BorderPane.setAlignment(problem, Pos.CENTER);
		problem_pane.setCenter(problem);
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

		BorderPane.setAlignment(identity, Pos.CENTER);
		author_pane.setCenter(identity);
		author_pane.setLayoutX(10);
		author_pane.setLayoutY(50);

	}

	private void createReferencePane() {

		declaim = new Text(
				10,
				140,
				"This is my orignal code, No IDE used in the submission. \nI did not give my code to anyone or I did not use anyone's code in this work. \n");
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
		BorderPane.setAlignment(declaim, Pos.CENTER);
		BorderPane.setAlignment(reference_url, Pos.CENTER);
		reference_pane.setCenter(declaim);
		reference_pane.setBottom(reference_url);
		reference_pane.setLayoutX(10);
		reference_pane.setLayoutY(50);

	}

	public void createSolutionPane() {

		createFanPane(DEFAULT_PANE_NUM);
		HBox topPane = createSolutionTop();
		VBox bottomPane = createSolutionBottom();

		BorderPane.setMargin(topPane, new Insets(12, 50, 12, 50));
		BorderPane.setMargin(bottomPane, new Insets(12, 50, 12, 50));
		BorderPane.setMargin(centerpan, new Insets(12, 12, 12, 12));

		solution_pane.setCenter(centerpan);
		solution_pane.setTop(topPane);
		solution_pane.setBottom(bottomPane);
		BorderPane.setAlignment(centerpan, Pos.CENTER);
		// solution_pane.setLayoutX(10);
		// solution_pane.setLayoutY(50);

	}

	private HBox createSolutionTop() {

		HBox hBox = new HBox(15);

		// gPane.setPadding(new Insets(12, 11, 11, 11));

		final Button btStart = new Button("PauseAll");
		final Button btReverse = new Button("ReverseAll");

		// btPause.setOnAction(e -> animation.pause());

		btStart.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				if (btStart.getText().equals("StartAll")) {
					btStart.setText("PauseAll");
					for (int i = 0; i < fanListPane.size(); ++i) {
						fanListPane.get(i).globalStartControl("Start");
					}
					return;
				}

				if (btStart.getText().equals("PauseAll")) {
					btStart.setText("StartAll");
					for (int i = 0; i < fanListPane.size(); ++i) {
						fanListPane.get(i).globalStartControl("Pause");
					}
					return;
				}

			}
		});

		btReverse.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {

				for (int i = 0; i < fanListPane.size(); ++i) {
					fanListPane.get(i).globalReverseControl(); // Start
																// animation
				}
			}
		});

		hBox.setAlignment(Pos.CENTER);
		hBox.setStyle("-fx-border-color:blue;-fx-border-width:5");
		// hBox.setStyle("-fx-border-width: 5");
		// rectangleview.setStrokeWidth(2);
		// hBox.setPadding(new Insets(12, 11, 11, 11));

		HBox.setMargin(btStart, new Insets(5, 5, 5, 5));
		HBox.setMargin(btReverse, new Insets(5, 5, 5, 5));
		HBox.setHgrow(btStart, Priority.ALWAYS);
		HBox.setHgrow(btReverse, Priority.ALWAYS);
		btStart.setMaxWidth(Double.MAX_VALUE);
		btReverse.setMaxWidth(Double.MAX_VALUE);

		hBox.getChildren().addAll(btStart, btReverse);
		return hBox;

	}

	private VBox createSolutionBottom() {
        
		VBox bottom = new VBox(5);
		HBox hBox = new HBox(15);
		
		Slider speedSlider = new Slider();
		speedSlider.setOrientation(Orientation.HORIZONTAL);
		speedSlider.setShowTickLabels(false);
		speedSlider.setShowTickMarks(false);
		speedSlider.setValue(fanListPane.get(0).getAnimation().getRate());
		speedSlider.setMax(fanListPane.get(0).getAnimation().getRate() * 2);
		speedSlider.setMin(0);

		// speedSlider.setValue(rectangle1.getWidth());
		speedSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> ov,
					Number old_value, Number new_value) {
				BigDecimal bg = new BigDecimal(new_value.doubleValue());
				for (int i = 0; i < fanListPane.size(); ++i) {
					fanListPane.get(i).globalSpeedControl(
							bg.setScale(2, BigDecimal.ROUND_HALF_UP)
									.doubleValue());
				}
			}
		});

		Slider bladeSlider = new Slider();
		bladeSlider.setOrientation(Orientation.HORIZONTAL);
		bladeSlider.setShowTickLabels(false);
		bladeSlider.setShowTickMarks(false);
		bladeSlider.setValue(DEFAULT_BLADE_NUM);
		bladeSlider.setMax(MAX_BLADE_NUM);
		bladeSlider.setMin(1);

		// speedSlider.setValue(rectangle1.getWidth());
		bladeSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> ov,
					Number old_value, Number new_value) {
				for (int i = 0; i < fanListPane.size(); ++i) {
					fanListPane.get(i).globalBladeControl(new_value.intValue());
				}

				/*
				 * getChildren().remove(fan); fan = new
				 * FanPane(new_value.intValue()); BorderPane.setMargin(fan, new
				 * Insets(12, 12, 12, 12)); BorderPane.setAlignment(fan,
				 * Pos.CENTER); setCenter(fan);
				 */

			}
		});
		
		Slider paneSlider = new Slider();
		paneSlider.setOrientation(Orientation.HORIZONTAL);
		paneSlider.setShowTickLabels(false);
		paneSlider.setShowTickMarks(false);
		paneSlider.setValue(DEFAULT_PANE_NUM);
		paneSlider.setMax(MAX_PANE_NUM);
		paneSlider.setMin(1);
		paneSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> ov,
					Number old_value, Number new_value) {
				
				if(new_value.intValue()>fanListPane.size())
					addFanePane(new_value.intValue()-fanListPane.size());
				if(new_value.intValue()<fanListPane.size())
					deleteFanePane(fanListPane.size()-new_value.intValue());

				/*
				 * getChildren().remove(fan); fan = new
				 * FanPane(new_value.intValue()); BorderPane.setMargin(fan, new
				 * Insets(12, 12, 12, 12)); BorderPane.setAlignment(fan,
				 * Pos.CENTER); setCenter(fan);
				 */

			}
		});
		
		hBox.setAlignment(Pos.CENTER);
		

		HBox.setMargin(speedSlider, new Insets(5, 5, 0, 5));
		HBox.setHgrow(speedSlider, Priority.ALWAYS);
		speedSlider.setMaxWidth(Double.MAX_VALUE);

		HBox.setMargin(bladeSlider, new Insets(5, 5, 0, 5));
		HBox.setHgrow(bladeSlider, Priority.ALWAYS);
		bladeSlider.setMaxWidth(Double.MAX_VALUE);
		
		
		HBox.setMargin(paneSlider, new Insets(5, 5, 0, 5));
		HBox.setHgrow(paneSlider, Priority.ALWAYS);
		paneSlider.setMaxWidth(Double.MAX_VALUE);

		hBox.getChildren().addAll(speedSlider,bladeSlider,paneSlider);
		
		HBox hBox2 = new HBox(15);
		
	    final Label speedlabel = new Label("UpdateSpeed");
	    final Label bladelabel = new Label("UpdateBlades");
	    final Label fanlabel = new Label("UpdateFans");
	    
		
		HBox.setMargin(speedlabel, new Insets(0, 5, 5, 5));
		HBox.setHgrow(speedlabel, Priority.ALWAYS);
		speedlabel.setMaxWidth(Double.MAX_VALUE);
		speedlabel.setAlignment(Pos.CENTER);
	    
		HBox.setMargin(bladelabel, new Insets(0, 5, 5, 5));
		HBox.setHgrow(bladelabel, Priority.ALWAYS);
		bladelabel.setMaxWidth(Double.MAX_VALUE);
		bladelabel.setAlignment(Pos.CENTER);
		
		HBox.setMargin(fanlabel, new Insets(0, 5, 5, 5));
		HBox.setHgrow(fanlabel, Priority.ALWAYS);
		fanlabel.setMaxWidth(Double.MAX_VALUE);
		fanlabel.setAlignment(Pos.CENTER);
		
	    hBox2.setAlignment(Pos.CENTER);
	    hBox2.getChildren().addAll(speedlabel,bladelabel,fanlabel);
	    
	    bottom.getChildren().addAll(hBox,hBox2);
	    bottom.setStyle("-fx-border-color:deeppink;-fx-border-width:5");

		return bottom;

	}

	private void createFanPane(int numberOfFan) {

		for (int index = 0; index < numberOfFan; ++index) {
			FanAnimationPane oneFanPan = new FanAnimationPane(DEFAULT_BLADE_NUM);
/*			HBox.setHgrow(oneFanPan, Priority.ALWAYS);
			oneFanPan.setMaxWidth(Double.MAX_VALUE);
			centerpan.getChildren().add(oneFanPan);*/
			fanListPane.add(oneFanPan);
		}
	     
		centerpan.getChildren().addAll(fanListPane);		
	}
	
	private void addFanePane(int add_number) {

		for (int index = 0; index < add_number; ++index) {
			FanAnimationPane oneFanPan = new FanAnimationPane(DEFAULT_BLADE_NUM);
			centerpan.getChildren().add(oneFanPan);
			fanListPane.add(oneFanPan);
			
		}
		
		BorderPane.setMargin(centerpan, new Insets(12, 12, 12, 12));
		BorderPane.setAlignment(centerpan, Pos.CENTER);
		solution_pane.setCenter(centerpan);

	}
	
	
	private void deleteFanePane(int delete_number) {

		for (int index = 0; index < delete_number; ++index) {
			fanListPane.remove(fanListPane.size()-1);
			centerpan.getChildren().remove(centerpan.getChildren().size()-1);
		}
		
		BorderPane.setMargin(centerpan, new Insets(12, 12, 12, 12));
		BorderPane.setAlignment(centerpan, Pos.CENTER);
		solution_pane.setCenter(centerpan);
	}

	class FanAnimationPane extends BorderPane {

		private Timeline animation;
		private FanPane fan;
		private HBox topPane;
		private VBox bottomPane;

		public FanAnimationPane(int Blades) {

			animation = new Timeline(new KeyFrame(Duration.millis(10),
					new EventHandler<ActionEvent>() {
						public void handle(ActionEvent actionEvent) {
							fan.move();
						}
					}));

			animation.setCycleCount(Timeline.INDEFINITE);

			fan = new FanPane(Blades);
			topPane = createTopPane();
			bottomPane = createBottomPane();

			BorderPane.setMargin(topPane, new Insets(12, 50, 12, 50));
			BorderPane.setMargin(bottomPane, new Insets(12, 50, 12, 50));
			BorderPane.setMargin(fan, new Insets(12, 12, 12, 12));
			BorderPane.setAlignment(fan, Pos.CENTER);
			setCenter(fan);
			setTop(topPane);
			setBottom(bottomPane);
			setStyle("-fx-border-color:black;-fx-border-width:2");
			animation.play();
		}

		public void globalStartControl(String commandString) {

			if (commandString.equals("Pause")) {
				if (animation.getStatus() == Animation.Status.RUNNING) {
					animation.pause(); // Start animation
					((Button) topPane.getChildren().get(0)).setText("Start");
				}

				return;
			}

			if (commandString.equals("Start")) {
				if (animation.getStatus() == Animation.Status.PAUSED) {
					animation.play(); // Start animation
					((Button) topPane.getChildren().get(0)).setText("Pause");
				}
				return;
			}

		}

		public void globalReverseControl() {
			fan.reverse();
		}

		public void globalSpeedControl(double speed) {
			((Slider)((HBox) bottomPane.getChildren().get(0)).getChildren().get(0)).setValue(speed);
			/*
			 * fanListPane.get(i).getAnimation().setRate(bg.setScale(2,
			 * BigDecimal.ROUND_HALF_UP) .doubleValue());
			 */
		}

		public void globalBladeControl(int blades) {
			((Slider)((HBox) bottomPane.getChildren().get(0)).getChildren().get(1)).setValue(blades);
		}

		public Timeline getAnimation() {
			return animation;
		}

		public void setAnimation(Timeline animation) {
			this.animation = animation;
		}

		public FanPane getFan() {
			return fan;
		}

		public void setFan(FanPane fan) {
			this.fan = fan;
		}

		private HBox createTopPane() {

			HBox hBox = new HBox(15);

			// gPane.setPadding(new Insets(12, 11, 11, 11));

			final Button btStart = new Button("Pause");
			final Button btReverse = new Button("Reverse");

			// btPause.setOnAction(e -> animation.pause());

			btStart.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent actionEvent) {
					if (animation.getStatus() == Animation.Status.PAUSED) {
						animation.play();
						btStart.setText("Pause");
					} else {
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
			// rectangleview.setStrokeWidth(2);
			// hBox.setPadding(new Insets(12, 11, 11, 11));

			HBox.setMargin(btStart, new Insets(5, 5, 5, 5));
			HBox.setMargin(btReverse, new Insets(5, 5, 5, 5));
			HBox.setHgrow(btStart, Priority.ALWAYS);
			HBox.setHgrow(btReverse, Priority.ALWAYS);
			btStart.setMaxWidth(Double.MAX_VALUE);
			btReverse.setMaxWidth(Double.MAX_VALUE);

			hBox.getChildren().addAll(btStart, btReverse);
			return hBox;

		}

		private VBox createBottomPane() {

			VBox bottom= new VBox(5);
			HBox hBox = new HBox(15);
			Slider speedSlider = new Slider();
			speedSlider.setOrientation(Orientation.HORIZONTAL);
			speedSlider.setShowTickLabels(false);
			speedSlider.setShowTickMarks(false);
			speedSlider.setValue(animation.getRate());
			speedSlider.setMax(animation.getRate() * 2);
			speedSlider.setMin(0);

			// speedSlider.setValue(rectangle1.getWidth());
			speedSlider.valueProperty().addListener(
					new ChangeListener<Number>() {
						@Override
						public void changed(
								ObservableValue<? extends Number> ov,
								Number old_value, Number new_value) {
							BigDecimal bg = new BigDecimal(new_value
									.doubleValue());
							animation.setRate(bg.setScale(2,
									BigDecimal.ROUND_HALF_UP).doubleValue());
						}
					});

			Slider bladeSlider = new Slider();
			bladeSlider.setOrientation(Orientation.HORIZONTAL);
			bladeSlider.setShowTickLabels(false);
			bladeSlider.setShowTickMarks(false);
			bladeSlider.setValue(fan.getBlades());
			bladeSlider.setMax(MAX_BLADE_NUM);
			bladeSlider.setMin(1);

			// speedSlider.setValue(rectangle1.getWidth());
			bladeSlider.valueProperty().addListener(
					new ChangeListener<Number>() {
						@Override
						public void changed(
								ObservableValue<? extends Number> ov,
								Number old_value, Number new_value) {

							getChildren().remove(fan);
							fan = new FanPane(new_value.intValue());
							// BorderPane.setMargin(topPane, new Insets(12, 50,
							// 12, 50));
							// BorderPane.setMargin(bottomPane, new Insets(12,
							// 50, 12, 50));
							BorderPane.setMargin(fan,
									new Insets(12, 12, 12, 12));
							BorderPane.setAlignment(fan, Pos.CENTER);
							setCenter(fan);

							// fan.setNumberOfBlade(new_value.intValue());

						}
					});
			hBox.setAlignment(Pos.CENTER);
			

			HBox.setMargin(speedSlider, new Insets(5, 5, 0, 5));
			HBox.setHgrow(speedSlider, Priority.ALWAYS);
			speedSlider.setMaxWidth(Double.MAX_VALUE);

			HBox.setMargin(bladeSlider, new Insets(5, 5, 0, 5));
			HBox.setHgrow(bladeSlider, Priority.ALWAYS);
			bladeSlider.setMaxWidth(Double.MAX_VALUE);

			hBox.getChildren().addAll(speedSlider, bladeSlider);
			
			
			
			HBox hBox2 = new HBox(15);
			
		    final Label speedlabel = new Label("UpdateSpeed");
		    final Label bladelabel = new Label("UpdateBlades");
		    			
			HBox.setMargin(speedlabel, new Insets(0, 5, 5, 5));
			HBox.setHgrow(speedlabel, Priority.ALWAYS);
			speedlabel.setMaxWidth(Double.MAX_VALUE);
			speedlabel.setAlignment(Pos.CENTER);
		    
			HBox.setMargin(bladelabel, new Insets(0, 5, 5, 5));
			HBox.setHgrow(bladelabel, Priority.ALWAYS);
			bladelabel.setMaxWidth(Double.MAX_VALUE);
			bladelabel.setAlignment(Pos.CENTER);

			
		    hBox2.setAlignment(Pos.CENTER);
		    hBox2.getChildren().addAll(speedlabel,bladelabel);
		    
		    bottom.getChildren().addAll(hBox,hBox2);
		    bottom.setStyle("-fx-border-color:deeppink;-fx-border-width:5");

			return bottom;

		}

	}

	class FanPane extends Pane {
		private double w = SCENE_WIDTH/5;
		private double h = w/2;
		private double radius = Math.min(w, h) * 0.2;
		private int numberOfBlades = 6;
		// private Arc arc[] = new Arc[numberOfBlades*12];
		private ArrayList<Arc> arc = new ArrayList<Arc>();
		private double startAngle = 30;
		private Circle bigCircle = new Circle(w / 2, h / 2, radius);
		private Circle middleCircle = new Circle(w / 2, h / 2, radius * 2 / 3);
		private Circle smallCircle = new Circle(w / 2, h / 2, radius / 3);

		public int getBlades() {

			return numberOfBlades;
		}

		public FanPane(int Blades) {
			this.numberOfBlades = Blades;
			bigCircle.setStroke(Color.BLUE);
			bigCircle.setStrokeWidth(5);
			bigCircle.setFill(Color.WHITE);
			middleCircle.setStroke(Color.BLUE);
			middleCircle.setFill(Color.WHITE);
			smallCircle.setStroke(Color.BLUE);
			smallCircle.setFill(Color.WHITE);
			getChildren().addAll(bigCircle, middleCircle, smallCircle);

			/*
			 * for (int i = 0; i < numberOfBlades; i++) { arc[i] = new Arc(w /
			 * 2, h / 2, radius * 0.9, radius * 0.9, startAngle + i *
			 * 360/numberOfBlades, 35); arc[i].setFill(Color.RED); // Set fill
			 * color arc[i].setType(ArcType.ROUND);
			 * getChildren().addAll(arc[i]); }
			 */
			for (int i = 0; i < numberOfBlades; i++) {
				Arc newArc = new Arc(w / 2, h / 2, radius * 0.9, radius * 0.9,
						startAngle + i * 360 / numberOfBlades, 35);
				newArc.setFill(Color.RED); // Set fill color
				newArc.setType(ArcType.ROUND);
				getChildren().addAll(newArc);
				arc.add(newArc);
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

		public void setNumberOfBlade(int number) {
			System.out.println(number);
			if (number > numberOfBlades) {
				for (int i = 0; i < number - numberOfBlades; ++i) {
					Arc newArc = new Arc();
					arc.add(newArc);
				}

			}

			if (number < numberOfBlades) {
				for (int i = numberOfBlades - 1; i > number; --i) {

					arc.remove(i);
				}

			}
			numberOfBlades = number;
			setValues();
		}

		public void setValues() {
			radius = Math.min(w, h) * 0.45;
			bigCircle.setRadius(radius);
			bigCircle.setCenterX(w / 2);
			bigCircle.setCenterY(h / 2);
			middleCircle.setRadius(radius * 2 / 3);
			middleCircle.setCenterX(w / 2);
			middleCircle.setCenterY(h / 2);
			smallCircle.setRadius(radius / 3);
			smallCircle.setCenterX(w / 2);
			smallCircle.setCenterY(h / 2);

			/*
			 * for (int i = 0; i < numberOfBlades; i++) {
			 * arc[i].setRadiusX(radius * 0.9); arc[i].setRadiusY(radius * 0.9);
			 * arc[i].setCenterX(w / 2); arc[i].setCenterY(h / 2);
			 * arc[i].setStartAngle(startAngle + i * 360/numberOfBlades); }
			 */

			for (int i = 0; i < numberOfBlades; i++) {
				arc.get(i).setRadiusX(radius * 0.9);
				arc.get(i).setRadiusY(radius * 0.9);
				arc.get(i).setCenterX(w / 2);
				arc.get(i).setCenterY(h / 2);
				arc.get(i).setStartAngle(startAngle + i * 360 / numberOfBlades);
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