package libros.BD.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IdiomaDAO {
    Connection conn;
    public IdiomaDAO(Connection conn)
    {
        this.conn = conn;
    }

    public String buscaLibroid(int idLibro) {
        String nombre="";
        try {
            String query = "Select nombre from idioma ID INNER JOIN idiomDisponible IdD on ID.id=IdD.id_idiom " +
                    "INNER JOIN libros Lib on Lib.idbook=IdD.id_book  WHERE Lib.idbook="+idLibro+";";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
                nombre=nombre+rs.getString("nombre");
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return nombre;
    }
}