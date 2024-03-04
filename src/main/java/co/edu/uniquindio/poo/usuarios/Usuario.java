package co.edu.uniquindio.poo.usuarios;

public abstract class Usuario
{
    /**
     * nombre(s)
     */
    private String nombre;

    /**
     * apellido(s)
     */
    private String apellido;

    /**
     * numero de identificacion
     */
    private String identificacion;

    /**
     * contruye una instancia de Usuario
     * 
     * @param identificacion numero de identificacion
     * @param nombre nombre(s)
     * @param apellido apellido(s)
     */
    public Usuario(String identificacion, String nombre, String apellido)
    {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    /**
     * establece el/los nombre(s)
     * 
     * @param nombre
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * establece el/los apellido(s)
     * 
     * @param nombre
     */
    public void setApellido(String apellido)
    {
        this.apellido = apellido;
    }

    /**
     * obtiene los nombres y apellidos
     * 
     * @return nombre(s) y apellido(s)
     */
    public String getNombreCompleto()
    {
        return this.nombre + ' ' + this.apellido;
    }

    /**
     * obtiene los nombres
     * 
     * @return nombre(s)
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * obtiene los apellidos
     * 
     * @return apellido(s)
     */
    public String getApellido()
    {
        return apellido;
    }

    /**
     * obtiene el numero de identificacion
     * 
     * @return numero de identificacion
     */
    public String getIdentificacion()
    {
        return identificacion;
    }
}