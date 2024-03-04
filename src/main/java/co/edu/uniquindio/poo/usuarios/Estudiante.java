package co.edu.uniquindio.poo.usuarios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.text.DateFormatter;

public class Estudiante extends Usuario
{
    /**
     * fecha de nacimento
     */
    private LocalDate nacimiento;

    /**
     * construye una instancia de Estudiante
     * 
     * @param identificacion numero de identificacion
     * @param nombre nombre(s)
     * @param apellido apellido(s)
     * @param fechaNacimiento fecha de nacimiento
     */
    public Estudiante(
        String identificacion,
        String nombre,
        String apellido,
        LocalDate fechaNacimiento
    ) {
        super(identificacion, nombre, apellido);
        this.nacimiento = fechaNacimiento;
    }

    /**
     * establece la fecha de naciemiento usando un string
     * 
     * @param fecha string de la nueva fecha
     */
    public void setFechaNacimiento(String fecha)
    {
        this.setFechaNacimiento(LocalDate.parse(
            fecha,
            DateTimeFormatter.ofPattern("yyyy-LL-dd")
        ));
    }

    /**
     * establece la fecha de nacimiento
     * 
     * @param fecha nueva fecha
     */
    public void setFechaNacimiento(LocalDate fecha)
    {
        this.nacimiento = fecha;
    }

    /**
     * obtiene la fecha de nacimiento
     * 
     * @return fecha de nacimiento
     */
    public LocalDate getFechaNacimiento()
    {
        return nacimiento;
    }
}
