package libros.BD.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class SugerenciasDAO {

    Connection conn;

    public SugerenciasDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean InsertarSugerencias(sugerencias sugiere)
    {
        try {
            String query = "INSERT into sugerencias"
                    + " (nombre, email, titulo, contenido, url,comentarios,libro)"
                    + " values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(1, sugiere.getNombre());
            st.setString(2, sugiere.getEmail());
            st.setString(3, sugiere.getTitulo());
            st.setString(4, sugiere.getContenido());
            st.setString(5, sugiere.getUrl());
            st.setString(6, sugiere.getComentarios());
            st.setString(7, sugiere.getLibro());
            st.execute();
            //data.add(transaction);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public ObservableList<sugerencias> verSugerencias()
    {
        ObservableList<sugerencias> transactions = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM sugerencias";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            sugerencias p = null;
            while(rs.next()) {
                p = new sugerencias(
                        rs.getInt("id"),
                        rs.getString("nombre"), rs.getString("email"),
                        rs.getString("email"), rs.getString("titulo"),
                        rs.getString("url"),rs.getString("contenido"),rs.getString("comentarios") );
                transactions.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return transactions;
    }

    }


