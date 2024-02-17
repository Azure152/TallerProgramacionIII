package co.edu.uniquindio.poo.usuarios;

import java.time.LocalDate;

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
     * obtiene la fecha de nacimiento
     * 
     * @return fecha de nacimiento
     */
    public LocalDate getFechaNacimiento()
    {
        return nacimiento;
    }
}
