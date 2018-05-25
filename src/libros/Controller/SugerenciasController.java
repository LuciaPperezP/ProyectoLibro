package libros.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import libros.BD.dao.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SugerenciasController implements Initializable {
    @FXML
    TextField txtNombreCompleto, txtCorreo, txtTitulo, txtUrl;
    @FXML
    TextArea txtAreaComentarios,txtAreaContenido;
    @FXML
    Button btnEnviar, btnCancelar;

    private SugerenciasDAO sugerenciasDAO = new SugerenciasDAO(MySQL.getConnection());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnEnviar.setOnAction(handler);
        btnCancelar.setOnAction(handler);

    }

    EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>()
    {
        @Override
        public void handle(ActionEvent event)
        {
            if(event.getSource()==btnEnviar)
            {
                enviarSugerencia();
            }
            else if(event.getSource()==btnCancelar)
            {
                cerrarVentana();
            }

        }
    };

    private void cerrarVentana()
    {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    public void mostrarMensaje(String mensaje)
    {
        Alert alert1=new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Mensaje de notificación");
        alert1.setContentText(mensaje);
        alert1.show();
    }

    private void enviarSugerencia()
    {

        try
        {

            sugerencias sugiere=new sugerencias();
            sugiere.setId(0);
            sugiere.setNombre(txtNombreCompleto.getText());
            sugiere.setEmail(txtCorreo.getText());
            sugiere.setTitulo(txtTitulo.getText());
            sugiere.setContenido(txtAreaContenido.getText());
            sugiere.setUrl(txtUrl.getText());
            sugiere.setContenido(txtAreaComentarios.getText());
            sugiere.setLibro("aqui deberia ir el libro");

           sugerenciasDAO.InsertarSugerencias(sugiere);//llamar consulta para insertar nuevo registro
            System.out.println(sugiere.getTitulo());
            mostrarMensaje("La sugerencia ha sigo registrada. Gracias por ayudarnos a mejorar! :)");
            cerrarVentana();
        }
        catch (Exception e)
        {
            mostrarMensaje("Error en el registro");
        }


    }
}
