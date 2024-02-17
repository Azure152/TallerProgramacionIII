package co.edu.uniquindio.poo.asignaturas;

import co.edu.uniquindio.poo.usuarios.Estudiante;

public class AsignaturaEstudiante
{
    /**
     * estudiante
     */
    private Estudiante estudiante;

    /**
     * calificacion del estudiante
     */
    private Calificacion calificacion;

    /**
     * construye una instancia de AsignaturaEstudiante
     * 
     * @param estudiante estudiante de la asignatura
     */
    public AsignaturaEstudiante(Estudiante estudiante)
    {
        this.estudiante = estudiante;
        this.calificacion = new Calificacion();
    }

    /**
     * obtiene la instancia de estudiante
     * 
     * @return estudiante
     */
    public Estudiante getEstudiante()
    {
        return estudiante;
    }

    /**
     * obtiene la calificacion del estudiante 
     * 
     * @return calificacion
     */
    public Calificacion getCalificacion()
    {
        return calificacion;
    }
}