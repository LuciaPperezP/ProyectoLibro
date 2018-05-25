package libros.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import libros.BD.dao.MySQL;
import libros.BD.dao.UsuariosDAO;
import libros.BD.dao.usuarios;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class RegistrarUsuarioController implements Initializable {
    @FXML
    RadioButton rbUsuario, rbAdmin;
    @FXML
    Label lbTipo, lbEstadoContraseña,lbTituloRegistrar,lbConfirmarContraseña;
    @FXML
    Button btnRegistrar, btnCancelar;
    @FXML
    TextField txtNombre,txtEmail,txtUsuario;
   @FXML
   PasswordField txtContraseña, txtConfirmarContraseña;
   @FXML
   DatePicker dpDate;
   int i=0;

    int tipo = 0;

    private UsuariosDAO usuariosDAO = new UsuariosDAO(MySQL.getConnection());
    usuarios objUsuarios;



    public RegistrarUsuarioController(int tipoVentana,usuarios infoUsuarios)//constructor
    {
        this.tipo = tipoVentana;
        this.objUsuarios=infoUsuarios;
        //tipo 3 para ediatr perfil
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lbEstadoContraseña.setVisible(false);
        //txtNombre.setPromptText("hola");

        if (tipo == 1){  //Agregar registro desde usuario
            rbUsuario.setVisible(false);
            rbAdmin.setVisible(false);
            lbTipo.setVisible(false);
        }
        else if(tipo==3)//editar perfil
        {
            txtUsuario.setText(objUsuarios.getNombre_usuario());
            txtEmail.setText(objUsuarios.getEmail());
            txtNombre.setText(objUsuarios.getNombre());
            txtContraseña.setText(objUsuarios.getContraseña());
            txtConfirmarContraseña.setVisible(false);
            lbConfirmarContraseña.setVisible(false);

            rbUsuario.setVisible(false);
            rbAdmin.setVisible(false);
            lbTituloRegistrar.setText("Editar perfil");
            btnRegistrar.setText("Guardar cambios");
            i=5;
        }
        btnRegistrar.setOnAction(handler);
        btnCancelar.setOnAction(handler);


    }

    EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>()
    {
        @Override
        public void handle(ActionEvent event)
        {
            if(event.getSource()==btnRegistrar&&tipo==1)
            {
                String cont1=txtContraseña.getText();
                String cont2=txtConfirmarContraseña.getText();

                if(cont1.equals(cont2))//valida que las contraseñas coincidan
                {
                    registrarUsuario();
                }
                else
                    {
                        lbEstadoContraseña.setVisible(true);
                    }
            }
            else if(event.getSource()==btnRegistrar&&tipo==3) //si el boon es de registro y la ventanaviene de editar perfil
            {
                System.out.println("llega aqui");
                       editarPerfil();
            }

            else if(event.getSource()==btnCancelar)
            {
                cerrarVentana();

            }

        }
    };

    private void registrarUsuario()
    {
        try
        {
            usuarios objUsuarios = new usuarios("","","","",'D',null);
            objUsuarios.setNombre(txtNombre.getText());
            objUsuarios.setEmail(txtEmail.getText());
            objUsuarios.setNombre_usuario(txtUsuario.getText());
            objUsuarios.setContraseña(txtContraseña.getText());
            if(rbAdmin.isSelected())
            {
                objUsuarios.setTipo_user('A');
            }
            else if(rbUsuario.isSelected()||tipo==1)
            {
                objUsuarios.setTipo_user('U');
            }
            objUsuarios.setFecha_nac(Date.valueOf(dpDate.getValue()));
            usuariosDAO.insertarUsuario(objUsuarios);//llamar consulta para insertar nuevo registro
            showMessage("Registro insertado");
            cerrarVentana();
        }
        catch (Exception e)
        {
            showMessage("Error en el registro");
        }
    }




    public void showMessage(String mensaje)
    {
        Alert alert1=new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Mensaje de notificación");
        alert1.setContentText(mensaje);
        alert1.show();
    }

    private void cerrarVentana()
    {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }



    private void editarPerfil()
    {

        try {
            usuarios objEditar = new usuarios();
            objEditar.setNombre_usuario(txtUsuario.getText());
            objEditar.setNombre(txtNombre.getText());
            objEditar.setFecha_nac(Date.valueOf(dpDate.getValue()));
            objEditar.setEmail(txtEmail.getText());
            objEditar.setContraseña(txtContraseña.getText());


            usuariosDAO.actualizar(objEditar,objUsuarios.getNombre_usuario()); //llamar consulta de actualizacion
            showMessage("Su información ha sido actualizada");
            cerrarVentana();


        }
        catch (Exception e)
        {
            showMessage("Error en la actualización");
        }

    }

}

