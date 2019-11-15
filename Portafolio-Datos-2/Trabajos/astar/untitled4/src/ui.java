
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ui extends Application{

    Image img = new Image("/images/car.png");
    Image img2 = new Image("/images/car2.png");


    @Override
    public void start(Stage stage) {

        Pane canvas = new Pane();
        Scene scene = new Scene(canvas, 1000, 1000, Color.CADETBLUE);


        Matriz M1 = new Matriz(30,30, 1000,1000);
        M1.iniciar();
        M1.printM();


        Rectangle personaje = new Rectangle(M1.ResX/M1.SizeX,  M1.ResY/M1.SizeY, Color.CADETBLUE);
        Rectangle inicio =new Rectangle(M1.ResX/M1.SizeX,  M1.ResY/M1.SizeY, Color.YELLOW);
        inicio.relocate(M1.ball.posx*(M1.ResX/M1.SizeX), M1.ball.posy*(M1.ResY/M1.SizeY));
        Rectangle destino =new Rectangle(M1.ResX/M1.SizeX,  M1.ResY/M1.SizeY, Color.RED);
        personaje.setFill(new ImagePattern(img));
        personaje.relocate(M1.ball.posx*(M1.ResX/M1.SizeX), M1.ball.posy*(M1.ResY/M1.SizeY));

        Group obstaculos= new Group();


        for(int i = 0; i<= M1.SizeX-1; i++){
            for(int j = 0; j<= M1.SizeY-1; j++){
                if(M1.Matrix[i][j] == 2){
                    Rectangle obstaculo=new Rectangle(M1.ResX/M1.SizeX,  M1.ResY/M1.SizeY, Color.BLACK);
                    obstaculo.relocate(i*(M1.ResX/M1.SizeX), j*(M1.ResY/M1.SizeY));

                    obstaculos.getChildren().add(obstaculo);
                }
                if(M1.Matrix[i][j] == 3){
                    destino.relocate(i*(M1.ResX/M1.SizeX), j*(M1.ResY/M1.SizeY));
                }

            }
        }






        canvas.getChildren().add(obstaculos);
        canvas.getChildren().add(destino);
        canvas.getChildren().add(inicio);
        canvas.getChildren().add(personaje);

        stage.setTitle("Ui");
        stage.setScene(scene);
        stage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000),
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent t) {
                        //move the personaje

                        M1.ball.mover(M1.ball.speed, 0);
                        personaje.relocate(M1.ball.posx*(M1.ResX/M1.SizeX), M1.ball.posy*(M1.ResY/M1.SizeY));


                        //If the personaje reaches the left or right border make the step negative

                        //If the personaje reaches the bottom or top border make the step negative

                    }
                }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    public static void main(String[] args) {
        launch();
    }
}

