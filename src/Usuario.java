public class Usuario {
    private String nombre;
    private String espacio;
    private String descripcion;
    private int capacidad;
    private boolean tieneOrdenador;
    private boolean tienePizarra;
    private boolean tieneProyecto;

    public Usuario(String nombre, String espacio, String descripcion, int capacidad,
                   boolean tieneOrdenador, boolean tienePizarra, boolean tieneProyecto) {
        this.nombre = nombre;
        this.espacio = espacio;
        this.descripcion = descripcion;
        this.capacidad = capacidad;
        this.tieneOrdenador = tieneOrdenador;
        this.tienePizarra = tienePizarra;
        this.tieneProyecto = tieneProyecto;
    }

    // Métodos getter/setter

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspacio() {
        return espacio;
    }

    public void setEspacio(String espacio) {
        this.espacio = espacio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public boolean isTieneOrdenador() {
        return tieneOrdenador;
    }

    public void setTieneOrdenador(boolean tieneOrdenador) {
        this.tieneOrdenador = tieneOrdenador;
    }

    public boolean isTienePizarra() {
        return tienePizarra;
    }

    public void setTienePizarra(boolean tienePizarra) {
        this.tienePizarra = tienePizarra;
    }

    public boolean isTieneProyecto() {
        return tieneProyecto;
    }

    public void setTieneProyecto(boolean tieneProyecto) {
        this.tieneProyecto = tieneProyecto;
    }

    // Otros métodos para interactuar con la base de datos SQLite
}
