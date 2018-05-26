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
import javafx.stage.Stage;
import libros.BD.dao.usuarios;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class UsuarioController implements Initializable
{
    @FXML
    Button btnejemplo, btnSugerencias;
    @FXML
    MenuItem itemSesion,itemPerfil;
    @FXML
            Menu menuUsuario,menuPerfil;
    String nombreUsuario="";
    usuarios infoUsuario;
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

        btnejemplo.setOnAction(handler);
        btnSugerencias.setOnAction(handler);
        itemSesion.setOnAction(handler);
        itemPerfil.setOnAction(handler);
        menuUsuario.setText("Usuario:  "+nombreUsuario);

    }

    public UsuarioController(String nombre, usuarios datosUsuario)//constructor
    {
      //  this.nombreUsuario = transUsuarios.getNombre_usuario();
            this.infoUsuario=datosUsuario;
            this.nombreUsuario=nombre; // está janado un objeto de tipo usuario cn toda la info del usuario que identicó en el login
    }


    EventHandler<ActionEvent> handler= new EventHandler<ActionEvent>()
    {

        @Override
        public void handle(ActionEvent event)
        {
            if(event.getSource()==btnejemplo)
            {
                try
                {
                    mostrarInfoLibro();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            else if(event.getSource()==itemSesion)
            {
                mensajeConfirmacion();
            }
            else if(event.getSource()==btnSugerencias)
            {
                try {
                    mostrarVentana(2,null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if(event.getSource()==itemPerfil)
            {
                try {
                    mostrarVentana(3,infoUsuario);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    };

    public void mostrarInfoLibro() throws IOException
    {
        Stage stage =new Stage();
        FXMLLoader loader;
        Parent parent;


            InfoLibroController infoLibrofx = new InfoLibroController();
            stage.setTitle("Info libro");
            stage.setResizable(false);

        loader = new FXMLLoader(getClass().getResource("../fxml/inforLibro.fxml"));
            loader.setController(infoLibrofx);

        try
        {
            parent= loader.load();
            parent.getStylesheets().add("/libros/css/estiloA.css");
            Scene scene=new Scene(parent,650,600);
            stage.setScene(scene);
            stage.show();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void mostrarVentana(int parametro,usuarios infoUsuario) throws IOException
    {
        Stage stage =new Stage();
        FXMLLoader loader=null;
        Parent parent;

         if(parametro==2)//sugerir libro
        {
            System.out.println("debe crear ventana");
            SugerenciasController sugerenciasfx = new SugerenciasController();
            stage.setTitle("Sugerencias");
            stage.setResizable(false);

            loader = new FXMLLoader(getClass().getResource("../fxml/sugerencias.fxml"));
            loader.setController(sugerenciasfx);
        }
        else if(parametro==3)
        {
            RegistrarUsuarioController editarUsuariofx = new RegistrarUsuarioController(3,infoUsuario);
            stage.setTitle("Ventana");
            stage.setResizable(false);

            loader = new FXMLLoader(getClass().getResource("../fxml/registrarUsuario.fxml"));
            loader.setController(editarUsuariofx);
        }

        try
        {
            parent= loader.load();
            parent.getStylesheets().add("/libros/css/estiloA.css");
            Scene scene=new Scene(parent,400,500);
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
