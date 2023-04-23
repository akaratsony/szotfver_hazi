package com.sakkjatek.jatek;

import javafx.fxml.FXML;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import static java.lang.Integer.parseInt;

public class HelloController {

    @FXML
    private GridPane grid;
    @FXML
    private Label teszt;
    @FXML
    private ImageView roka;
    @FXML
    private ImageView kutya;
    @FXML
    private ImageView kutya2;
    @FXML
    private ImageView kutya3;
    @FXML
    private ImageView kutya4;
    public ImageView[] imageViews; // Array of ImageView objects
    private List<Kutya> kutyak = new ArrayList<>();

    @FXML
    public void initialize() {

        Image image = new Image("C:/Users/szajb/Pictures/pista.jpg"); // Fájl elérési útja
        // ImageView beállítása az Image objektumra
        roka.setImage(image);
        roka.setFitHeight(80);
        roka.setFitWidth(80);
        GridPane.setColumnIndex(roka,roka_location_col);
        GridPane.setRowIndex(roka,roka_location_row);

        Image image2 = new Image("C:/Users/szajb/Pictures/Screenshots/brigisuni.png"); // Fájl elérési útja
        // ImageView beállítása az Image objektumra

        Kutya kutyus = new Kutya(0,7,"kutya",1);
        Kutya kutyus2 = new Kutya(3,7,"kutya2",2);
        Kutya kutyus3 = new Kutya(5,7,"kutya3",3);
        Kutya kutyus4 = new Kutya(7,7,"kutya4",4);

        kutyak.add(kutyus);
        kutyak.add(kutyus2);
        kutyak.add(kutyus3);
        kutyak.add(kutyus4);

        imageViews = new ImageView[] {kutya,kutya2,kutya3,kutya4};

        int szam = -1;
        for (ImageView kutya : imageViews) {
            szam++;
                kutya.setImage(image2);
                kutya.setFitWidth(kutyak.get(szam).getWidth());
                kutya.setFitHeight(kutyak.get(szam).getHeight());
                GridPane.setColumnIndex(kutya,kutyak.get(szam).getColumnLocation());
                GridPane.setRowIndex(kutya,kutyak.get(szam).getRowLocation());

        }

        aktualis.setText(""+this.kutya_id);


    }
    @FXML
    private Label aktualis;
    private boolean isImageClickable = false; // Változó a kattintás figyelésének be- és kikapcsolásához
    public int kutya_id;
    @FXML
    private void handleImageClick(MouseEvent event3) {
        if (isImageClickable) {
            ImageView imageView = (ImageView) event3.getSource(); // Get the source of the event (the clicked ImageView)
            String viewName = (String) imageView.getUserData(); // Get the userData property of the ImageView

            // Now you can use the viewName in your logic
             aktualis.setText(viewName);
            this.kutya_id = parseInt(viewName);
            System.out.println(kutya_id);
            // Show an alert with the viewName
            //Alert alert = new Alert(Alert.AlertType.INFORMATION, "Clicked ImageView: " + viewName, ButtonType.OK);
            //alert.showAndWait();
        }

    }
    @FXML
    protected void onHelloButtonClick() {
        int lepes=GridPane.getColumnIndex(roka);
        lepes = lepes+1;
        GridPane.setColumnIndex(roka,lepes);
    }

    private int roka_location_col =2;
    private int roka_location_row =0;
    public boolean move(Scene scene,HelloController controller){
        boolean vege = false;
        scene.setOnKeyPressed(event -> {
            if (controller.move_roka(event)) {
                scene.setOnKeyPressed(null); // Az eseménykezelő eltávolítása a scene-ről
                isImageClickable = true; // Aktiváljuk az onclick eseményt
                System.out.println("Válassz kutyát amivel szeretnél lépni:" );
                scene.setOnKeyPressed(event1 -> {
                    if (controller.move_kutya(event1)) {
                        scene.setOnKeyPressed(null); // Az eseménykezelő eltávolítása a scene-ről
                        move(scene,controller);
                    }

                });
            }
            // Itt végezd el a betű beolvasásával kapcsolatos műveleteket, például a karakter feldolgozását vagy kezelését



        });

        return false;

    }



    public boolean move_kutya(KeyEvent event){
        System.out.println("oké léptem"+this.kutya_id);
        ImageView aktualis_kutya = imageViews[this.kutya_id];
        System.out.println("Aktualis kutya:" + aktualis_kutya);
        System.out.println("na:"+ kutyak.get(this.kutya_id));
        int col_location= kutyak.get(this.kutya_id).getColumnLocation();
        int row_location = kutyak.get(this.kutya_id).getRowLocation();
        switch(event.getCode()){
            case A:
                if(col_location-1<0 || row_location-1 <0) {
                    teszt.setText("Erre nem mehetsz mert kimész a páláyról!!");
                    return false;

                }
                else{
                    teszt.setText("Balra megyünk");
                    kutyak.get(this.kutya_id).setColumnLocation(col_location-1);
                    kutyak.get(this.kutya_id).setRowLocation(row_location-1);
                    GridPane.setColumnIndex(aktualis_kutya,col_location-1);
                    GridPane.setRowIndex(aktualis_kutya,row_location-1);
                    isImageClickable = false; // DeAktiváljuk az onclick eseményt
                    return true;
                }

            case D:
                if(col_location+1>7 || row_location-1 <0) {
                    teszt.setText("Erre nem mehetsz mert kimész a páláyról!!");
                    return false;
                }
                else{
                    teszt.setText("Jobbra megyünk");
                    kutyak.get(this.kutya_id).setColumnLocation(col_location+1);
                    kutyak.get(this.kutya_id).setRowLocation(row_location-1);
                    GridPane.setColumnIndex(aktualis_kutya,col_location+1);
                    GridPane.setRowIndex(aktualis_kutya,row_location-1);
                    isImageClickable = false; // DeAktiváljuk az onclick eseményt
                    return true;
                }

            default:
                break;
        }


        isImageClickable = false; // DeAktiváljuk az onclick eseményt
        return true;

    }
    public boolean move_roka(KeyEvent event){
        System.out.println(event.getCode());
        switch(event.getCode()){
            //case W:
                //if(roka_location_row+1>4) {
                  //  teszt.setText("Erre nem mehetsz mert kimész a páláyról!!");
                //}
                //else{
                    //teszt.setText("Előre megyünk");
                    //roka_location_row = roka_location_row+1;
                  //  GridPane.setRowIndex(roka,roka_location_row);
                //}
                //break;
            case Q:
                if(roka_location_row-1<0 || roka_location_col-1<0) {
                    teszt.setText("Erre nem mehetsz balra hátra mert kimész a páláyról!!");
                }
                else{
                    teszt.setText("Balra Hátra megyünk");
                    roka_location_row = roka_location_row-1;
                    roka_location_col = roka_location_col-1;
                    GridPane.setRowIndex(roka,roka_location_row);
                    GridPane.setColumnIndex(roka,roka_location_col);
                    return true;
                }
                break;
            case E:
                if(roka_location_row-1<0 ||roka_location_col+1>7) {
                    teszt.setText("Erre nem mehetsz jobbra hátra mert kimész a páláyról!!");
                }
                else{
                    teszt.setText("Hátra megyünk");
                    roka_location_row = roka_location_row-1;
                    roka_location_col = roka_location_col+1;
                    GridPane.setRowIndex(roka,roka_location_row);
                    GridPane.setColumnIndex(roka,roka_location_col);
                    return true;

                }
                break;
            case A:
                if(roka_location_col-1<0 || roka_location_row+1 >7) {
                    teszt.setText("Erre nem mehetsz mert kimész a páláyról!!");
                }
                else{
                    teszt.setText("Balra megyünk");
                    roka_location_col = roka_location_col-1;
                    roka_location_row = roka_location_row+1;
                    GridPane.setColumnIndex(roka,roka_location_col);
                    GridPane.setRowIndex(roka,roka_location_row);
                    return true;
                }
                break;
            case D:
                if(roka_location_col+1>7 || roka_location_row+1 >7) {
                    teszt.setText("Erre nem mehetsz mert kimész a páláyról!!");
                }
                else{
                    teszt.setText("Jobbra megyünk");
                    roka_location_col = roka_location_col+1;
                    roka_location_row = roka_location_row+1;
                    GridPane.setColumnIndex(roka,roka_location_col);
                    GridPane.setRowIndex(roka,roka_location_row);
                    return true;

                }
                break;
            default:
                break;
        }
        return false;

    }

}