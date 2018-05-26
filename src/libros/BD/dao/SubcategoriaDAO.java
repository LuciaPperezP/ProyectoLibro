package libros.BD.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SubcategoriaDAO {
    Connection conn;
    public SubcategoriaDAO(Connection conn)
    {
        this.conn = conn;
    }

    public String buscaSubcategoriaid(int idLibro) {
        String nombre=null;
        try {
            String query = "select Nombre from subcategoria sub inner join libros lib on lib.id_cate=sub.id_cat AND lib.id_sub=sub.id_sub where lib.idbook="+idLibro;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
                nombre=rs.getString("Nombre");
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return nombre;
    }



}