package libros.BD.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UsuariosDAO {


    Connection conn;

    public UsuariosDAO(Connection conn) {
        this.conn = conn;
    }


    public Boolean insertarUsuario(usuarios insertUsuario) {
        try {
            String query = "insert into usuarios "
                    + " (nombre, email, nombre_usuario, contraseña, tipo_user)"
                    + " values (?, ?, ?, ?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(1, insertUsuario.getNombre());
            st.setString(2, insertUsuario.getEmail());
            st.setString(  3, insertUsuario.getNombre_usuario());
            st.setString(4,insertUsuario.getContraseña());
            st.setString(5, String.valueOf(insertUsuario.getTipo_user()));
            st.execute();
            //data.add(transaction);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

   /* public usuarios validarUsuario(String pusuario, String ppassword)
    {
        usuarios objeto=null;
        try {
            String query = "SELECT nombre_usuario,tipo_user FROM usuarios WHERE nombre_usuario='" + pusuario + "' AND contraseña=('" + ppassword + "')";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next())
            {
                char tipo = rs.getString("tipo_user").charAt(0);
                String nombre=rs.getString("nombre_user");
                objeto=new usuarios(rs.getString("nombre_usuario"),rs.getString("tipo_user"));

                return tipo;
            }
            else
                return null;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
*/

   public usuarios validar3(String usuario, String contraseña)
   {
       usuarios objeto=null;
       try {
           String query = "SELECT * FROM usuarios WHERE nombre_usuario='" + usuario + "' AND contraseña=('" + contraseña + "')";
           Statement st = conn.createStatement();
           ResultSet rs = st.executeQuery(query);
           if (rs.next())
           {
               //char tipo = rs.getString("tipo_user").charAt(0);
               //String nombre=rs.getString("nombre_user");
               objeto=new usuarios(rs.getString("nombre_usuario"),rs.getString("contraseña"),
                       rs.getString("nombre"),rs.getString("email"),rs.getString("tipo_user").charAt(0),
                       rs.getDate("fecha_nac"));


               return objeto;
           }
           else
               return null;
       } catch (Exception e)
       {
           e.printStackTrace();
           return null;
       }
   }

    public usuarios validar2()
    {
        List<usuarios> movimiento = new ArrayList<usuarios>();
        usuarios objeto = null;
        try {
            char tipo_user;
            String nombre,nombre_usuario,email,contraseña;
            //char tipo_user;
            java.util.Date fecha_nac;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM usuarios");
            while (rs.next())
            {
                objeto=new usuarios();
                nombre=rs.getString("nombre");
                nombre_usuario=rs.getString("nombre_usuario");
                email=rs.getString("email");
                contraseña=rs.getString("contraseña");
                tipo_user= rs.getString("tipo_user").charAt(0);
                fecha_nac=rs.getDate("fecha_nac");

                objeto.setNombre(nombre);
                objeto.setNombre_usuario(nombre_usuario);
                objeto.setEmail(email);
                objeto.setContraseña(contraseña);
                objeto.setTipo_user(tipo_user);
                objeto.setFecha_nac((Date) fecha_nac);
                movimiento.add(objeto);

            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(LibrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objeto;
    }







    public Boolean actualizar(usuarios pusuarios,String identificador) {
        try {
            String query = "update usuarios "
                    + " set nombre = ?, email = ?, fecha_nac = ?, nombre_usuario = ?, contraseña = ?"
                    + " where nombre_usuario=('" + identificador + "')";
            System.out.println(query + "updating....");
            PreparedStatement st =  conn.prepareStatement(query);

            st.setString(1, pusuarios.getNombre());
            System.out.println(pusuarios.getNombre());
            st.setString(2, pusuarios.getEmail());
            st.setDate(  3, pusuarios.getFecha_nac());
            st.setString(4, pusuarios.getNombre_usuario());
            st.setString(5, pusuarios.getContraseña());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }


}
