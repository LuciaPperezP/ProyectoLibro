package libros.BD.dao;

public class sugerencias {

    private  int id;
    private String nombre;
    private String email;
    private String titulo;
    private String contenido;
    private String url;
    private String comentarios;
    private String libro;

    public sugerencias() {
    }

    public sugerencias(int id, String nombre, String email, String titulo, String contenido, String url, String comentarios,String libro) {
        this.id=id;
        this.nombre=nombre;
        this.email=email;
        this.titulo=titulo;
        this.contenido=contenido;
        this.url=url;
        this.comentarios=comentarios;
        this.libro=libro;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getLibro() {
        return libro;
    }

    public void setLibro(String libro) {
        this.libro = libro;
    }
}
