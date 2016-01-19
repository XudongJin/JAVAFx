package code;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class MouseDemo2 extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
    Label label = new Label("Mouse events");
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        
       label.setLayoutX(100);
       label.setLayoutY(100);
        
        //   root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 300, 250);
        
        scene.setOnMousePressed(mouseHandler);
        scene.setOnMouseReleased(mouseHandler);
        scene.setOnMouseClicked(mouseHandler);
        scene.setOnMouseEntered(mouseHandler);
        scene.setOnMouseExited(mouseHandler);
        scene.setOnMouseMoved(mouseHandler);
        scene.setOnMouseDragged(mouseHandler);
        
        root.getChildren().add(label);
        primaryStage.setScene(scene);
        primaryStage.setTitle("All events of Mouse");
        primaryStage.show();
    }
    
    EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
    
    @Override
    public void handle(MouseEvent mouseEvent) {
    
    double x =mouseEvent.getX();
    double y =mouseEvent.getY();

    String str = mouseEvent.getEventType() + "\n"
                       + "(X , Y) = (" + x + " : " + mouseEvent.getY() + ")\n"
                      // + "(SceneX , SceneY) = (" + mouseEvent.getSceneX() + " : " + mouseEvent.getSceneY() + ")\n"
                       + "(ScreenX , ScreenY) = (" + mouseEvent.getScreenX() + " : " + mouseEvent.getScreenY()+")";
   // screen output shows all the movements more clearly.
    System.out.println(str);
    label.setText(str);
    label.setLayoutX(x);
    label.setLayoutY(y);
                }
        };
}

