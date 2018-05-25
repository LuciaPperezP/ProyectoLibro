package libros.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class VisitanteController implements Initializable
{
    @FXML
    Button btnRegistrarse, btnSugerencias;
    @FXML
    MenuItem itemSalir;
    int tipoVentana=0;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        btnRegistrarse.setOnAction(handler);
        btnSugerencias.setOnAction(handler);
        itemSalir.setOnAction(handler);

    }

    EventHandler<ActionEvent> handler= new EventHandler<ActionEvent>() //progamar evento de los botones
    {
        @Override
        public void handle(ActionEvent event)
        {
            if(event.getSource()==btnRegistrarse)
            {
                try
                {
                    mostrarVentana(1);
                } catch (IOException e)
                {}
            }
            else if(event.getSource()==btnSugerencias)
            {
                try
                {
                    System.out.println("reconoce el evento");
                    mostrarVentana(2);
                } catch (IOException e)
                {}
            }
            else if(event.getSource()==itemSalir)
            {
                mensajeConfirmacion();
            }

        }
    };



        public void mostrarVentana(int parametro) throws IOException
        {
            Stage stage =new Stage();
            FXMLLoader loader=null;
            Parent parent;

            if(parametro==1)//registro
            {
                RegistrarUsuarioController registrarUsuariofx = new RegistrarUsuarioController(parametro,null);
                stage.setTitle("Registro");
                stage.setResizable(false);

                loader = new FXMLLoader(getClass().getResource("../fxml/registrarUsuario.fxml"));
                loader.setController(registrarUsuariofx);
            }
            else if(parametro==2)
            {
                System.out.println("debe crear ventana");
                SugerenciasController sugerenciasfx = new SugerenciasController();
                stage.setTitle("Sugerencias");
                stage.setResizable(false);

                loader = new FXMLLoader(getClass().getResource("../fxml/sugerencias.fxml"));
                loader.setController(sugerenciasfx);
            }


        try
        {
            parent= loader.load();
            parent.getStylesheets().add("/libros/css/estiloA.css");
            Scene scene=new Scene(parent,400,450);
            stage.setScene(scene);
            stage.show();
        }


        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void mensajeConfirmacion()
    {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("¿Estás seguro de querer salir?");
        Optional<ButtonType> confirma= alert.showAndWait();

        if(confirma.get()==ButtonType.OK)
            System.exit(0);

    }
}
