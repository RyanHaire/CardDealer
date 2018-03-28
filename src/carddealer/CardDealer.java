/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carddealer;

import java.util.ArrayList;
import java.util.Collections;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Ryan
 */
public class CardDealer extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    
    private final ArrayList<Image> cards = new ArrayList<Image>();
    private final HBox hBox = new HBox(10);
    private final Image fd = new Image("card/b1fv.png");
    private final BorderPane bPane = new BorderPane();
    private int btnCount = 0;
 
    @Override
    public void start(Stage s) throws Exception {
        
        bPane.setCenter(hBox);
        
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setStyle("-fx-background-color: grey;");

        for(int i = 1; i < 53; i++){
            cards.add(new Image("card/"+i+".png"));
        }
            
        Collections.shuffle(cards);
        setUpCards();
        
        
        Button shuffleBtn = new Button("Shuffle");
        shuffleBtn.setAlignment(Pos.CENTER);
        bPane.setBottom(shuffleBtn);
        BorderPane.setAlignment(shuffleBtn, Pos.CENTER);
        
        
        shuffleBtn.setOnAction((ActionEvent event) -> {
            increaseButtonClickCount();
            if(btnCount == 1) {
                for(int i = 0; i < 5; i++){
                    StackPane span = (StackPane) hBox.getChildren().get(i);
                    span.getChildren().get(1).setVisible(false);
                }
            } else {
                hBox.getChildren().clear();
                Collections.shuffle(cards);
                setUpCards(); 
                btnCount = 0;
            }
            
        });
        
        Scene scene = new Scene(bPane, 420, 150);
        
        s.setScene(scene);
        s.setTitle("Card Dealer");
        s.setResizable(false);
        s.show();
        
    }
    
    private void setUpCards(){
        for(int i = 0; i < 5; i++){
                StackPane sPane = new StackPane(new ImageView(cards.get(i)),new ImageView(fd));
                sPane.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                    sPane.getChildren().get(1).setVisible(false);
                });
                hBox.getChildren().add(sPane);
        }    
    }
    
    private void increaseButtonClickCount(){
        btnCount++;
    }
}
