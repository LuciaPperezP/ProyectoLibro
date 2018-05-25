package libros.Controller;

import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import libros.BD.dao.*;

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddLibroController implements Initializable

    {

        @FXML
        Button btnSeleccionar, BtnSave, BtnCancel;
        @FXML
        TextField txtURLCompar, txtURLDescarga, txtEtiq, txtTitulo, txtAutor, txtAño, txtEditor, txtPag, txtTam;
        @FXML
        ComboBox<Idioma> cbIdioma;
        @FXML
        RadioButton rbLibre,rbPrueba,rbCompra;
        @FXML
        ComboBox<Categoria> cbCate;
        @FXML
        ComboBox<Subcategoria> cbSubcate;
        @FXML
        DatePicker dpDate;
        @FXML
        TextArea txaContenido;
        @FXML
        Label lblruta;
        @FXML
        ImageView Imgn;
        String categ_name;
        char id_idiom;
        int id_cate, id_sub;
        LibrosDAO transactionDAO = new LibrosDAO(MySQL.getConnection());//Consulta para libros
        AutorDAO autorDAO=new AutorDAO(MySQL.getConnection());//Consulta para autores

        public void initialize(URL location, ResourceBundle resources) {
        llenaCBidioma();
        llenaCBCategoria();
        cbSubcate.setDisable(true);
        btnSeleccionar.setOnAction(handler);
        BtnSave.setOnAction(handler);
        BtnCancel.setOnAction(handler);
        cbCate.setOnAction(handler);
        cbIdioma.setOnAction(handler);

    }


        EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert;

                if (event.getSource() == btnSeleccionar) {
                    seleccionar_pdf();
                } else if (BtnSave == event.getSource()) {
                    Libros libros = new Libros();
                    libros.setTitulo(txtTitulo.getText());//***Titulo
                    libros.setAño(txtAño.getText());//++Año
                    libros.setEditor(txtEditor.getText());//++Editor
                    libros.setPaginas(Integer.valueOf(txtPag.getText()));//****
                    LocalDate localDate = dpDate.getValue();
                    libros.setFecha_public(Date.valueOf(localDate));///+++++++++
                    libros.setTamaño(Float.valueOf(txtTam.getText()));//////77

                    if (rbCompra.isSelected())
                    {
                        libros.setLicencia("Compra");
                    }
                    if (rbPrueba.isSelected()){
                        libros.setLicencia("Prueba");
                    }
                    if (rbLibre.isSelected()){
                        libros.setLicencia("Libre");
                    }

                    libros.setUrl(txtURLCompar.getText());
                    libros.setDescarga(txtURLDescarga.getText());
                    libros.setEtiquetas(txtEtiq.getText());
                    libros.setContenido(txaContenido.getText());//************

                    if (cbSubcate.getSelectionModel().getSelectedIndex() > -1)
                    {
                        id_sub = cbSubcate.getValue().getId_sub();
                        id_cate=cbSubcate.getValue().getId_cat();
                    }
                    libros.setId_cate(id_cate);
                    libros.setId_sub(id_sub);

                    //Inserta libro y obtiene el id
                    int id_libro=transactionDAO.Insert(lblruta.getText(), libros);
                    //El id del libro se utiliza para relacionar el autor en y ell libro en la tabla escribe
                    AddAutor(id_libro);
                    //El id del libro se usa para udentificar en que idiomas está disponible
                    idiomaDisponible(id_libro);


                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Información");
                    alert1.setHeaderText(null);
                    alert1.setContentText("Libro registrado");

                    alert1.showAndWait();
                    closeStage();


                } else
                    if (BtnCancel == event.getSource()) {
                    closeStage();
                } else
                    if (cbCate.getSelectionModel().getSelectedIndex() > -1) {
                    //Obtiene el id de la categoría seleccionada
                    categ_name = cbCate.getValue().getNombre();
                    //El Combobox de subcategoría se habilita
                    cbSubcate.setDisable(false);
                    //Llena con los nombres de las subcategoría según la categoría seleccionada
                    llenaCBSubcategoria(categ_name);
                }
                else
                    if (cbIdioma.getSelectionModel().getSelectedIndex() > -1) {
                    //Obtiene el id del idioma seleccionado
                    id_idiom = cbIdioma.getValue().getId_idioma();
                        System.out.println(id_idiom);
                }
            }
        };

        public void idiomaDisponible(int idlibro){

            idiomDisponible disponible=new idiomDisponible();

            //Obtiene el id del libro
            disponible.setId_book(idlibro);

            //Obtiene el id del idioma
            disponible.setId_idioma(id_idiom);

            //Con los id´s
            transactionDAO.DisponibleIdioma(disponible);
        }

        public void AddAutor(int idlibro){
            Autor autor=new Autor();
            Escribe escribe=new Escribe();

            if(autorDAO.buscaAutor(txtAutor.getText())!=null)
            {
                escribe.setId_libro(idlibro);
                int autor_id = autorDAO.buscaAutor(txtAutor.getText()).getId_autor();
                escribe.setId_autor(autor_id);

                autorDAO.EscribeLibro(escribe);
            }
            else
            {
                //Ahgrega autor
                autor.setNombre(txtAutor.getText());
                autorDAO.insertAutor(autor);
                    escribe.setId_libro(idlibro);
                    int autor_id = autorDAO.buscaAutor(txtAutor.getText()).getId_autor();
                    escribe.setId_autor(autor_id);
                    autorDAO.EscribeLibro(escribe);
            }
        }
        public void llenaCBidioma() {
        cbIdioma.setItems(transactionDAO.Idiomas());
        //Convierte ComboBox toString a StringConverter
        cbIdioma.setConverter(new StringConverter<Idioma>() {

            @Override
            public String toString(Idioma p) {
                return (p.getNom_idioma());
            }

            @Override
            public Idioma fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }

        public void llenaCBCategoria() {
        cbCate.setItems(transactionDAO.Categorias());
        //Convierte ComboBox toString a StringConverter
        cbCate.setConverter(new StringConverter<Categoria>() {

            @Override
            public String toString(Categoria p) {
                return (p.getNombre());
            }

            @Override
            public Categoria fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }


        public void llenaCBSubcategoria(String subcatName) {
        cbSubcate.setItems(transactionDAO.Subcategorias(subcatName));
        //Convierte ComboBox toString a StringConverter
        cbSubcate.setConverter(new StringConverter<Subcategoria>() {

            @Override
            public String toString(Subcategoria p) {
                return (p.getNombre_sub());
            }

            @Override
            public Subcategoria fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }


        private void closeStage() {
        Stage stage = (Stage) BtnCancel.getScene().getWindow();
        stage.close();
    }

        public void seleccionar_pdf() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");

        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        // Obtener la imagen seleccionada
        File imgFile = fileChooser.showOpenDialog(null);

        // Mostar la imagen
        if (imgFile != null) {
            System.out.println("file:" + imgFile.getAbsolutePath());
            lblruta.setText(imgFile.getAbsolutePath());
            Image image = new Image("file:" + imgFile.getAbsolutePath());
            Imgn.setImage(image);
            Imgn.setFitHeight(19);
            Imgn.setFitWidth(25);

        }

    }
}
