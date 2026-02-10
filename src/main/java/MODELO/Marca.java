package MODELO;


public class Marca {
   private int id;
    private String sistema_op;
    private String gama;
    private String nombre_marca;

    public Marca() {}

    public Marca(int id, String sistema_op, String gama, String nombre_marca) {
        this.id = id;
        this.sistema_op = sistema_op;
        this.gama = gama;
        this.nombre_marca = nombre_marca;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getSistema_op() { return sistema_op; }
    public void setSistema_op(String sistema_op) { this.sistema_op = sistema_op; }

    public String getGama() { return gama; }
    public void setGama(String gama) { this.gama = gama; }

    public String getNombre_marca() { return nombre_marca; }
    public void setNombre_marca(String nombre_marca) { this.nombre_marca = nombre_marca; }
}
