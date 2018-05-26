package libros.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import libros.BD.dao.MySQL;
import libros.BD.dao.UsuariosDAO;
import libros.BD.dao.usuarios;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    Label lboptions,lbEstado;
    @FXML
    Button btnOk,btnAceptar,btnCancelar;
    @FXML
    GridPane gridLogin;
    @FXML
    RadioButton rbRegistrado, rbVisitante;
    @FXML
    PasswordField txtContrasena;
    @FXML
    TextField txtUsuario;

    UsuariosDAO usuariosDAO=new UsuariosDAO(MySQL.getConnection());

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        lboptions.setFont(new Font("Tahoma", 22));
        gridLogin.setVisible(false);
        lbEstado.setVisible(false);

        btnOk.setOnAction(handler);
        btnAceptar.setOnAction(handler);
        btnCancelar.setOnAction(handler);

    }


    EventHandler<ActionEvent> handler= new EventHandler<ActionEvent>() //progamar evento de los botones
    {
        @Override
        public void handle(ActionEvent event)
        {
            if(event.getSource()==btnOk)
            {
                if (rbVisitante.isSelected())
                {
                    System.out.println("mostrar pantalla Visitante");
                    try
                   {
                        mostrarVentana(1,"",null);
                        cerrarVentana();
                   } catch (IOException e)
                    {}

                }
                else if(rbRegistrado.isSelected())
                {
                    gridLogin.setVisible(true);
                }

            }
            else if(event.getSource()==btnAceptar)
            {
                try
                {
                    verificarCuenta();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }

            }

            else if(event.getSource()==btnCancelar)
                System.exit(0);



        }
    };

     void verificarCuenta() throws IOException
    {
        String usuario=txtUsuario.getText();
        String contraseña=txtContrasena.getText();

        try {
            usuarios objetousuarios = usuariosDAO.validar3(usuario, contraseña);
            if (objetousuarios.getTipo_user() == 'U')
            {
                System.out.println(objetousuarios.getNombre_usuario() + objetousuarios.getContraseña());
                System.out.println(usuario + contraseña);
                System.out.println("Datos correctos,mostrar ventana usuario");
                mostrarVentana(2, usuario, objetousuarios);

                cerrarVentana();
            } else if (objetousuarios.getTipo_user() == 'A')
            {

                mostrarVentana(3, usuario, objetousuarios);
                cerrarVentana();
            }
        }
        catch (Exception e)
        {

            txtContrasena.setText("");
            txtUsuario.setText("");
            lbEstado.setFont(new Font("Tahoma", 15));
            lbEstado.setVisible(true);
        }

    }

    public void mostrarVentana(int parametro,String nombre,usuarios datosUsuario) throws IOException
    {
        Stage stage =new Stage();
        FXMLLoader loader=null;
        Parent parent;

        if(parametro==1)//visitante
        {
            VisitanteController visitantefx=new VisitanteController();
            stage.setTitle("Visitante");
            stage.setMaximized(true);
            loader=new FXMLLoader(getClass().getResource("../fxml/visitante.fxml"));
            loader.setController(visitantefx);
        }
        else if(parametro==2)//usuario
        {
            UsuarioController usuariofx=new UsuarioController(nombre,datosUsuario);
            stage.setTitle("Usuario");
            stage.setMaximized(true);
            loader=new FXMLLoader(getClass().getResource("../fxml/usuario.fxml"));
            loader.setController(usuariofx);
        }
        else if(parametro==3)//admin
        {
            AdministradorController adminfx = new AdministradorController(nombre,datosUsuario);
            stage.setTitle("Administrador");
            stage.setMaximized(true);
            loader = new FXMLLoader(getClass().getResource("../fxml/administrador.fxml"));
            loader.setController(adminfx);


        }
        try
        {
            parent= loader.load();
            parent.getStylesheets().add("/libros/css/estiloA.css");
            Scene scene=new Scene(parent,300,200);
            stage.setMaximized(true);
            stage.setScene(scene);
            stage.show();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }//fin ventana usuario

    private void cerrarVentana()
    {
        Stage stage = (Stage) lboptions.getScene().getWindow();
        stage.close();
    }





}
