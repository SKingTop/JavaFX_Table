/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import javafx.stage.Stage;


/**
 *
 * @author student
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button Start_button;
    @FXML
    private Button Exit_button;
    @FXML
    private Button Random_button;
    @FXML
    private Label Task_label;
    @FXML
    private Label Max_label;
    @FXML
    private Label SUM_label;
    @FXML
    private Label MaxValue_label;
    @FXML
    private Label SumValue_label;
    @FXML
    private TableView<String[]> table;
    @FXML
    private Label Min_label;
    @FXML
    private Label MinValue_label;
    @FXML
    private Button Try_button;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Utils.buildTable(table, 8, 6, 30, 90, true, null);
        //Utils.buildTable(table, 8, 6, 24, 90, true, new String[] {"1","2","3","4","5","6"}); // Вариант с шапкой
        Random_button_Action(null);
        Start_button.setDisable(true);
        //table.getItems().add(new String[6]); // Так можно добавить строку
        //table.getItems().remove(table.getItems().size()-1); // Так можно удалить строку
        //table.refresh(); // Обновить таблицу на экране
        
    }    
    
    @FXML
    private void Try_button_Action(ActionEvent event) {
         // Расчет суммы чисел таблицы и проверка условия 
        double sum = 0;
        int result = 0;
        for (int row = 0; row < table.getItems().size(); row++) {
            for (int col = 0; col < table.getColumns().size(); col++) {
                sum = sum + Double.parseDouble(table.getItems().get(row)[col]);
            }
        }
        if (sum > 100){
            Try_button.setText("OK! PRESS NEXT BUTTON");
            //Start_button.setVisible(true);
            Start_button.setDisable(false);
            SumValue_label.setText(String.valueOf(sum)+" > 100");
        } 
        else {
            Try_button.setText("DO RANDOM AGAIN!");
            Start_button.setDisable(true);
            SumValue_label.setText(String.valueOf(sum)+" < 100");
        }
        
    }
    
    @FXML
    private void Start_button_Action(ActionEvent event) {
       
        //Поиск максимального элемента
        double max = 0;
        double valmax = 0;
        int maxrow = 0, maxcol = 0;
        for (int row = 0; row < table.getItems().size(); row++) {
            for (int col = 0; col < table.getColumns().size(); col++) {
                valmax = Double.parseDouble(table.getItems().get(row)[col]);
                  if (max < valmax){
                      max = valmax;
                      maxrow = row;
                      maxcol = col;
                  }
                  else {
                      max = max;
                      maxrow = maxrow;
                      maxcol = maxcol;
                  }
            }
        }
        MaxValue_label.setText(String.valueOf(max)+" [Row "+String.valueOf(maxrow+1)+", Col "+String.valueOf(maxcol+1)+"]");
        //Поиск минимального элемента
        double min = max;
        double valmin = 0;
        int minrow = 0, mincol = 0;
        for (int row = 0; row < table.getItems().size(); row++) {
            for (int col = 0; col < table.getColumns().size(); col++) {
                valmin = Double.parseDouble(table.getItems().get(row)[col]);
                  if (min > valmin){
                      min = valmin;
                      minrow = row;
                      mincol = col;
                  }
                  else {
                      min = min;
                      minrow = minrow;
                      mincol = mincol;
                  }
            }
        }
        MinValue_label.setText(String.valueOf(min)+" [Row "+String.valueOf(minrow+1)+", Col "+String.valueOf(mincol+1)+"]");
        //Обмен местами
        table.getItems().get(maxrow)[maxcol] = String.valueOf(min);
        table.getItems().get(minrow)[mincol] = String.valueOf(max);
        table.refresh();
    }


    @FXML
    private void Random_button_Action(ActionEvent event) {
        Try_button.setText("Let's Try");
        // Заполнение таблицы случайными числами
        for (int row = 0; row < table.getItems().size(); row++) {
            for (int col = 0; col < table.getColumns().size(); col++) {
                int rand = (int) Math.round(-100 + Math.random() * 201);
                table.getItems().get(row)[col] = String.valueOf(rand);
            }
        }
        table.refresh();
        MinValue_label.setText("");
        MaxValue_label.setText("");
        SumValue_label.setText("");
        Start_button.setDisable(true);
    }

    @FXML
    private void Exit_button_Action(ActionEvent event) {
        Stage stage = (Stage) Exit_button.getScene().getWindow();
        stage.close();
    }
    
    }
    

