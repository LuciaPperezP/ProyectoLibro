package libros.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import libros.BD.dao.MySQL;
import libros.BD.dao.SugerenciasDAO;
import libros.BD.dao.sugerencias;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class VerSugerenciasController implements Initializable {
    @FXML
    Button btnOk;
    @FXML
    TableView<sugerencias>tblSugerencias;

    private SugerenciasDAO sugerenciasDAO = new SugerenciasDAO(MySQL.getConnection());

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        CargarSugerencias();
        btnOk.setOnAction(handler);
    }


    EventHandler<ActionEvent> handler= new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event)
        {
            if(event.getSource()==btnOk)
            {
                cerrarVentana();
            }

        }
    };


        private void CargarSugerencias()
    {
        tblSugerencias.setItems(sugerenciasDAO.verSugerencias());

    }

    private void cerrarVentana()
    {
        Stage stage = (Stage) btnOk.getScene().getWindow();
        stage.close();
    }

}
