package libros.Controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import libros.BD.dao.Libros;
import libros.BD.dao.LibrosDAO;
import libros.BD.dao.MySQL;
import libros.BD.dao.usuarios;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import javax.swing.plaf.basic.BasicButtonUI;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdministradorController implements Initializable {
    @FXML
    MenuItem itemRegistro, itemSesion;
    @FXML   //Moviste itemSesion aqui
    Button itemPerfil,btnVerSugerencias, btnAgregar, btnCiencia, btnComics, btnComputacion, btnNovelas, btnLengua, btnGeografia, btnMusica, btnTecnologia, btnNegocios, btnCocina, btnDrama, btnSalud, btnPolitica, btnHistoria, btnMatematicas, btnMedicina, btnFotografia;
    @FXML
    GridPane GPCatalogo;
    @FXML
    ScrollPane scrollLibros;
    @FXML Menu menuUsuario;
    String categoria;
    String nombreUsuario="";
    usuarios infoUsuario;
    @FXML
    Label nombreCategoria;
    private LibrosDAO librosDAO = new LibrosDAO(MySQL.getConnection());
    int libros=0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        menuUsuario.setText("Usuario:  "+nombreUsuario);
        itemRegistro.setOnAction(handler);
        itemSesion.setOnAction(handler);
        itemPerfil.setOnAction(handler);
        btnVerSugerencias.setOnAction(handler);
        btnAgregar.setOnAction(handler);
        btnCiencia.setOnAction(handler);
        btnCocina.setOnAction(handler);
        btnComputacion.setOnAction(handler);
        btnNovelas.setOnAction(handler);
        btnLengua.setOnAction(handler);
        btnGeografia.setOnAction(handler);
        btnMusica.setOnAction(handler);
        btnTecnologia.setOnAction(handler);
        btnCocina.setOnAction(handler);
        btnDrama.setOnAction(handler);
        btnSalud.setOnAction(handler);
        btnPolitica.setOnAction(handler);
        btnHistoria.setOnAction(handler);
        btnMatematicas.setOnAction(handler);
        btnMedicina.setOnAction(handler);
        btnFotografia.setOnAction(handler);
        btnComics.setOnAction(handler);
        btnNegocios.setOnAction(handler);



    }

    public AdministradorController(String nombre, usuarios datosUsuario)//constructor
    {
        this.infoUsuario=datosUsuario;
        this.nombreUsuario=nombre; // está jalando un objeto de tipo usuario cn toda la info del usuario que identificó en el login
    }


    EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == itemRegistro)
            {
                System.out.println("entra a metodo del menu");
                int tipoVentana = 2;
                mostrarVentanaRegistro(2,null);
            }
            else if(event.getSource()==itemPerfil)
            {
                mostrarVentanaRegistro(3,infoUsuario);
            }
            else if (itemSesion == event.getSource()) {
                mensajeConfirmacion();
            } else if (event.getSource() == btnVerSugerencias) {
                abrirVentana(1);
            } else if (event.getSource() == btnAgregar) {
                System.out.println("Entra al metodo para agregar libro");
                AddLibrosShow();

                ///////////////////////////////////////////////////
            } else if (event.getSource() == btnCiencia) {
                categoria = "Ciencia";
                try { limpiarGridPane(); construirGridPane(categoria); } catch(Exception e) { System.out.println(e); }


            } else if (event.getSource() == btnComics) {
                categoria = "Comics";
                try { limpiarGridPane(); construirGridPane(categoria); } catch(Exception e) { System.out.println(e); }


            } else if (event.getSource() == btnComputacion) {
                categoria = "Computación e Internet";
                try { limpiarGridPane(); construirGridPane(categoria); } catch(Exception e) { System.out.println(e); }


            } else if (event.getSource() == btnNovelas) {
                categoria = "Novelas";
                try { limpiarGridPane(); construirGridPane(categoria); } catch(Exception e) { System.out.println(e); }


            } else if (event.getSource() == btnLengua) {
                categoria = "Lengua y literatura";
                try { limpiarGridPane(); construirGridPane(categoria); } catch(Exception e) { System.out.println(e); }


            } else if (event.getSource() == btnGeografia) {
                categoria = "Geografía";
                try { limpiarGridPane(); construirGridPane(categoria); } catch(Exception e) { System.out.println(e); }


            } else if (event.getSource() == btnMusica) {
                categoria = "Música";
                try { limpiarGridPane(); construirGridPane(categoria); } catch(Exception e) { System.out.println(e); }


            } else if (event.getSource() == btnTecnologia) {
                categoria = "Tecnología";
                try { limpiarGridPane(); construirGridPane(categoria); } catch(Exception e) { System.out.println(e); }


            } else if (event.getSource() == btnNegocios) {
                categoria = "Negocios y econonomía";
                try { limpiarGridPane(); construirGridPane(categoria); } catch(Exception e) { System.out.println(e); }


            } else if (event.getSource() == btnCocina) {
                categoria = "Cocina";
                try { limpiarGridPane(); construirGridPane(categoria); } catch(Exception e) { System.out.println(e); }


            } else if (event.getSource() == btnDrama) {
                categoria = "Drama";
                try { limpiarGridPane(); construirGridPane(categoria); } catch(Exception e) { System.out.println(e); }

            } else if (event.getSource() == btnSalud) {
                categoria = "Salud y bienestar";
                try { limpiarGridPane(); construirGridPane(categoria); } catch(Exception e) { System.out.println(e); }


            } else if (event.getSource() == btnPolitica) {
                categoria = "Ciencias políticas";
                try { limpiarGridPane(); construirGridPane(categoria); } catch(Exception e) { System.out.println(e); }


            } else if (event.getSource() == btnHistoria) {
                categoria = "Historia";
                try { limpiarGridPane(); construirGridPane(categoria); } catch(Exception e) { System.out.println(e); }


            } else if (event.getSource() == btnMatematicas) {
                categoria = "Matemáticas";
                try { limpiarGridPane(); construirGridPane(categoria); } catch(Exception e) { System.out.println(e); }


            } else if (event.getSource() == btnMedicina) {
                categoria = "Medicina";
                try { limpiarGridPane(); construirGridPane(categoria); } catch(Exception e) { System.out.println(e); }


            } else if (event.getSource() == btnFotografia) {
                categoria = "Fotografía";
                try { limpiarGridPane(); construirGridPane(categoria); } catch(Exception e) { System.out.println(e); }
            }



        }
    };


    public void mostrarVentanaRegistro(int tipoVentana,usuarios infoUsuario) {
        Stage stage = new Stage();
        FXMLLoader loader;
        Parent parent;

            RegistrarUsuarioController registrarUsuariofx = new RegistrarUsuarioController(tipoVentana,infoUsuario);
            stage.setTitle("Registro");
            stage.setResizable(false);

            loader = new FXMLLoader(getClass().getResource("../fxml/registrarUsuario.fxml"));
            loader.setController(registrarUsuariofx);

        try {
            parent = loader.load();
            parent.getStylesheets().add("/libros/css/estiloA.css");
            Scene scene = new Scene(parent, 400, 450);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void abrirVentana(int parametro) {

        Stage stage = new Stage();
        FXMLLoader loader = null;
        Parent parent;

        if (parametro == 1)//ver table view con sugerencias
        {
            VerSugerenciasController versugerenciasfx = new VerSugerenciasController();
            stage.setTitle("Ver Sugerencias");
            stage.setResizable(false);

            loader = new FXMLLoader(getClass().getResource("../fxml/verSugerencias.fxml"));
            loader.setController(versugerenciasfx);
        }

        try {
            parent = loader.load();
            parent.getStylesheets().add("/libros/css/estiloA.css");
            Scene scene = new Scene(parent, 750, 500);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarMensaje(String mensaje) {
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Mensaje de notificación");
        alert1.setContentText(mensaje);
        alert1.show();
    }

    public void mensajeConfirmacion() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("¿Estás seguro de querer salir?");
        Optional<ButtonType> confirma = alert.showAndWait();

        if (confirma.get() == ButtonType.OK)
            System.exit(0);

    }

    public void AddLibrosShow() {
        Stage stage = new Stage();
        FXMLLoader loader;
        Parent parent;

        AddLibroController addLibro = new AddLibroController();
        stage.setTitle("Registrar libro");
        stage.setResizable(false);

        loader = new FXMLLoader(getClass().getResource("../fxml/addlibro2.fxml"));
        loader.setController(addLibro);

        try {
            parent = loader.load();
            parent.getStylesheets().add("/libros/css/estiloA.css");
            Scene scene = new Scene(parent, 750, 540);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void construirGridPane(String categoria)
    {
        int i;
        int j;
        int recorre = 0;
        GPCatalogo.setHgap(100);
        GPCatalogo.setVgap(50);
        nombreCategoria.setStyle("-fx-font-size: 25pt; -fx-text-fill:#1E1C1A;");
        nombreCategoria.setText("Categoría:  "+categoria);



        do {

         System.out.println("tamaño"+librosDAO.mostrarLibros(categoria).size());
             for (i = 0; i <= (librosDAO.mostrarLibros(categoria).size() + 1)/3; i++)//rrenglones
             {
                 for (j = 0; j < 3; j++)//columnas
                 {
                     Libros titulo2 = librosDAO.mostrarLibros(categoria).get(recorre);
                     GPCatalogo.add(llenarCadaCuadro(titulo2),j,i);
                     recorre += 1;
                     System.out.println(j+"      "+i);

                 }
             }
             scrollLibros.setContent(GPCatalogo);



         }

      while (recorre<=librosDAO.mostrarLibros(categoria).size());
     }


    private VBox llenarCadaCuadro(Libros plibros)
    {
        /***************Crea ImagenView********************/
        ImageView ejemploimg=new ImageView();
        /**************************************************/
        VBox cuadro = new VBox();
        Button ejemploimg=new Button();
        HBox hbotones = new HBox();
        Button btnEdit = new Button();
        Button btnDelete=new Button();
        Button btnInfo = new Button();

        cuadro.setAlignment(Pos.CENTER);
        /***************Asignas el tamaño de la imagen**********************************/
        ejemploimg.setFitWidth(200);
        ejemploimg.setFitHeight(300); //aqui cambias el tamaño del boton donde se muestra la portada
        /************************************************/
        String obtitulo = plibros.getTitulo();
        Label lbtitulo = new Label();
        lbtitulo.setText(obtitulo);
        /************************************************************/
        ejemploimg.setImage(plibros.getImagen());
        /******Obtiene el id del libro********/
        int idbookDetalle=plibros.getIdbook();

        /***********************************************************/
   
        //BOTON Mostrar INFROMACIÓN LIBRO
        btnInfo.setOnMouseClicked((EventHandler< javafx.scene.input.MouseEvent>) mouseEvent -> {
            try {
                mostrarInfoLibro(idbookDetalle);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //BOTON ELIMINA LIBRO
        btnDelete.setOnMouseClicked((EventHandler< javafx.scene.input.MouseEvent>) mouseEvent -> {
            Alert alert;
            alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Eliminar libro");
            alert.setHeaderText("Confirmar");
            alert.setContentText("¿Desea eliminar "+plibros.getTitulo()+" de libros?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                librosDAO.delete(idbookDetalle);
                limpiarGridPane(); construirGridPane(categoria);
            } else if (option.get() == ButtonType.CANCEL) {
            }
        });
        
        

        hbotones.setStyle("-fx-spacing:5; -fx-alignment:center;");
        lbtitulo.setStyle("-fx-font-size: 18pt; -fx-text-fill:#524942;");
        btnEdit.setStyle("-fx-background-color:#D2CDC9; -fx-border-color:transparent; -fx-width:300px; fx-height:300px;");
        btnDelete.setStyle("-fx-background-color:#D2CDC9; -fx-border-color:transparent; -fx-width:300px; fx-height:300px;");
        btnInfo.setStyle("-fx-background-color:#D2CDC9; -fx-border-color:transparent; -fx-width:300px; fx-height:300px;");

        FontAwesomeIconView editIcon= new FontAwesomeIconView(FontAwesomeIcon.EDIT);
        btnEdit.setGraphic(editIcon);
        FontAwesomeIconView deleteIcon= new FontAwesomeIconView(FontAwesomeIcon.TRASH_ALT);
        btnDelete.setGraphic(deleteIcon);
        FontAwesomeIconView infoIcon= new FontAwesomeIconView(FontAwesomeIcon.INFO_CIRCLE);
        btnInfo.setGraphic(infoIcon);

        editIcon.setGlyphSize(20);
        editIcon.setFill(Color.PURPLE);

        deleteIcon.setGlyphSize(20);
        deleteIcon.setFill(Color.BLACK);

        infoIcon.setGlyphSize(20);
        infoIcon.setFill(Color.BLUE);

        Tooltip ttedit=new Tooltip("Editar");
        btnEdit.setTooltip(ttedit);
        Tooltip ttdelete=new Tooltip("Eliminar");
        btnDelete.setTooltip(ttdelete);
        Tooltip ttinfo=new Tooltip("Información");
        btnInfo.setTooltip(ttinfo);





        hbotones.getChildren().addAll(btnEdit, btnDelete, btnInfo);
        cuadro.getChildren().addAll(ejemploimg,lbtitulo,hbotones);

        return cuadro;
    }


    private void limpiarGridPane()
    {
        Node node = GPCatalogo.getChildren().get(0);
        GPCatalogo.getChildren().clear();
       // GPCatalogo.getChildren().add(0,node);
    }
/******************************************************************/
    public void mostrarInfoLibro(int id) throws IOException
    {
        Stage stage =new Stage();
        FXMLLoader loader;
        Parent parent;


        InformLibroController infoLibrofx = new InformLibroController(id);
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

}



