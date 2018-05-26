package libros.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import libros.BD.dao.*;

import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;



public class InfoLibroController implements Initializable {

    @FXML
    ImageView Imgn;
    @FXML
    Button btnOk, lbURLCompra, btnDescargar;
    @FXML
    Label lbTitulo,lbAutor,lbSubCategoria;
    @FXML
    Label lbAño,lbEditor,lbPaginas,lbIdioma,lbFecha,lbTamaño,lbLicenca;
    @FXML
    TextArea lbContenido;
    LibrosDAO librosDAO = new LibrosDAO(MySQL.getConnection());//Consulta para libros
    AutorDAO autorDAO=new AutorDAO(MySQL.getConnection());
    IdiomaDAO idiomaDAO=new IdiomaDAO(MySQL.getConnection());
    SubcategoriaDAO subcategoriaDAO=new SubcategoriaDAO(MySQL.getConnection());
    private URI urlDescarga;
    private URI urlCompra;
    //Aqui se le asigna el idLibro
    int idBook=31;
    @Override

    public void initialize(URL location, ResourceBundle resources) {
        InfoLib();
        btnOk.setOnAction(handler);
        btnDescargar.setOnAction(handler);
        lbURLCompra.setOnAction(handler);
    }


    EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == btnOk) {
                closeStage();

            } else if (event.getSource() == btnDescargar) {
                try {
                    urlDescarga = new URI(librosDAO.obtenidLibros(idBook).getDescarga());
                } catch (URISyntaxException ex) {}
                Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
                if ( desktop != null && desktop.isSupported(Desktop.Action.BROWSE) ) {
                    try {
                        desktop.browse( urlDescarga );
                    } catch ( Exception ex ) {
                        System.err.println( ex.getMessage() );
                    }
                }

            } else if(event.getSource()==lbURLCompra){
                try {
                    urlCompra = new URI(librosDAO.obtenidLibros(idBook).getUrl());
                } catch (URISyntaxException ex) {}
                Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
                if ( desktop != null && desktop.isSupported(Desktop.Action.BROWSE) ) {
                    try {
                        desktop.browse( urlCompra );
                    } catch ( Exception ex ) {
                        System.err.println( ex.getMessage() );
                    }
                }
            }
        }
    };


    private void closeStage() {
        Stage stage = (Stage) btnOk.getScene().getWindow();
        stage.close();
    }

    private void InfoLib(){

        Libros librosaux=librosDAO.obtenidLibros(idBook);
        String nombreAu=autorDAO.buscaAutorid(idBook);
        String idiomaDisp=idiomaDAO.buscaLibroid(idBook);
        String subcate=subcategoriaDAO.buscaSubcategoriaid(idBook);

        lbSubCategoria.setText(subcate);
        lbTitulo.setText(librosaux.getTitulo());
        lbIdioma.setText(idiomaDisp);
        lbLicenca.setText(librosaux.getLicencia());
        lbAño.setText(librosaux.getAño());
        lbLicenca.setText(librosaux.getLicencia());
        lbAutor.setText(nombreAu);
        lbEditor.setText(librosaux.getEditor());
        String tamaño=String.valueOf(librosaux.getTamaño());
        lbTamaño.setText(tamaño);
        String paginas=String.valueOf(librosaux.getPaginas());
        lbPaginas.setText(paginas);
        lbFecha.setText(librosaux.getFecha_public().toString());
        lbURLCompra.setText(librosaux.getUrl());
        /**setEditable(false) evita que el usuario edite la caja de texto*/
        lbContenido.setEditable(false);
        lbContenido.setText(librosaux.getContenido());

        System.out.println("ABRIR IMAGEN ");
        if(librosaux.getImagen()==null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Error campo imagen vacío");
        alert.show();
    }
        Imgn.setImage(librosaux.getImagen());
        Imgn.setFitHeight(350);
        Imgn.setFitWidth(250);
}


}