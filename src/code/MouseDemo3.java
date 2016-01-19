package code;
//basic libraries
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

public class MouseDemo3 extends Application {
   
    public static void main(String[] args) {
        launch(args);
    }
    
    Label label = new Label("Mouse events");
    Path path = new Path();
    LineTo lineTo = new LineTo();
    MoveTo moveTo = new MoveTo();
    boolean draw= true,notDone=true;
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        
       label.setLayoutX(100);
       label.setLayoutY(100);
        
        //   root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 300, 250);
        
        // path initialization
        path.setStrokeWidth(1);
        path.setStroke(Color.BLUE);
        //add the start point of the path
        path.getElements().add(new MoveTo(100,100));
        
        scene.setOnMousePressed(mouseHandler);
        scene.setOnMouseReleased(mouseHandler);
       // scene.setOnMouseClicked(mouseHandler);
        //mouse clicked
        scene.setOnMouseClicked(mouseClickHandler);
        scene.setOnMouseEntered(mouseHandler);
        scene.setOnMouseExited(mouseHandler);
        scene.setOnMouseMoved(mouseHandler);
        scene.setOnMouseDragged(mouseHandler);
        
        root.getChildren().add(label);
        root.getChildren().add(path);
        primaryStage.setScene(scene);
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
    label.setLayoutX(x);label.setLayoutY(y);

   //lineTo=new LineTo();// keep on adding points to path otherwise  keeps updating the line point
    if (draw)
        {
        lineTo.setX(x);
        lineTo.setY(y);
         //one one click, if not done add new segmentwith second point floating
        path.getElements().add(lineTo);
        }
    }

    };
    
    EventHandler<MouseEvent> mouseClickHandler = new EventHandler<MouseEvent>() {
        
    @Override
    public void handle(MouseEvent mouseEvent) {
        //freeze the point and get to a new point
        lineTo=new LineTo();
        lineTo.setX(mouseEvent.getX());
        lineTo.setY(mouseEvent.getY());
        //one one click, if not done add new segmentwith second point floating
        if (notDone)
            path.getElements().add(lineTo);
        //on two clicks terminate drawing
        if (mouseEvent.getClickCount()>1)
            draw=false;
            notDone=false;
    }

    };

}

