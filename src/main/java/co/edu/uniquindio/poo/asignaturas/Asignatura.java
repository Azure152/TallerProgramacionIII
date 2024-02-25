package co.edu.uniquindio.poo.asignaturas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import co.edu.uniquindio.poo.usuarios.Docente;
import co.edu.uniquindio.poo.usuarios.Estudiante;

public class Asignatura
{
    /**
     * nombre de la asigntura
     */
    private String nombre;

    /**
     * codigo
     */
    private String codigo;

    /**
     * cantidad maxima de estudiantes
     */
    private Integer cupos;

    /**
     * indicador para la habilitacion
     */
    private Boolean habilitable;

    /**
     * indicador del estado de apertura
     */
    private Boolean cerrada;

    /**
     * estudiantes de la asignatura
     */
    private Collection<AsignaturaEstudiante> estudiantes;

    /**
     * docente asignado
     */
    private Docente docente;

    /**
     * contruye una instancia de asignatura
     * 
     * @param nombre nombre
     * @param codigo codigo
     * @param cupos cantidad maxima de estudiantes
     * @param habilitable la asignatura es habilitable
     */
    public Asignatura(
        String nombre,
        String codigo,
        Integer cupos,
        Boolean habilitable
    ) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.cupos = cupos;
        this.habilitable = habilitable;
        this.cerrada = false;
        this.estudiantes = new ArrayList<>();
    }

    /**
     * agrega un nuevo estudiante a la asignatura
     * 
     * @param estudiante estudiante a agregar
     */
    public void addEstudiante(Estudiante estudiante)
    {
        this.addEstudiante(new AsignaturaEstudiante(estudiante));
    }

    /**
     * agrega un nuevo estudiante a la asignatura
     * 
     * @param estudiante estudiante a agregar
     */
    private void addEstudiante(AsignaturaEstudiante estudiante)
    {
        this.validarEstudiante(estudiante);
        this.validarEstadoApertura();
        this.validarCupos();

        this.addEstudiante(estudiante);
    }

    /**
     * valida que el estudiante no se encuentre registrado
     * 
     * @param estudiante estudiante a validar
     */
    private void validarEstudiante(AsignaturaEstudiante estudiante)
    {
        var id = estudiante.getEstudiante().getIdentificacion();
        var est = this.hallarEstudiante(id);

        if (est.isPresent()) {
            throw new RuntimeException(
                String.format("el estudiante [%s] ya se encuentra registrado", id)
            );
        }
    }

    /**
     * halla un estudiante usando su numero de identificacion
     * 
     * @param identificacion numero de identificacion del estudiante
     * 
     * @return Optional con el estudiante de la asignatura si se ha hallado
     */
    public Optional<AsignaturaEstudiante> hallarEstudiante(String identificacion)
    {
        return this.estudiantes.stream().filter((es) -> {
            return es.getEstudiante().getIdentificacion().equals(identificacion);
        }).findAny();
    }

    /**
     * obtiene un estudiante usando su numero de identificacion
     * 
     * @param identificacion numero de identificacion 
     * 
     * @return estudiante coincidente
     * 
     * @throws RuntimeException en caso de no encontrar al estudiante
     */
    public AsignaturaEstudiante obtenerEstudiante(String identificacion)
    {
        var estudiante = this.hallarEstudiante(identificacion);

        if (estudiante.isEmpty()) {
            throw new RuntimeException(
                "El estudiante no se encuentra registrado en la asignatura"
            );
        }

        return estudiante.get();
    }

    /**
     * valida que el estado de apertura de la asignatura sea "true"
     */
    private void validarEstadoApertura()
    {
        if (this.cerrada) {
            throw new RuntimeException("La asignatura ha sido cerrada.");
        }
    }

    /**
     * valida que el estado de apertura sea "false"
     */
    private void validarEstadoCerrado()
    {
        if (! this.cerrada) {
            throw new RuntimeException("La asignatura aun se encuentra abierta.");
        }
    }

    /**
     * valida el numero de cupo para un nuevo estudiante
     */
    private void validarCupos()
    {
        if (this.estudiantes.size() >= this.cupos) {
            throw new RuntimeException("numero maximo de cupos ocupados.");
        }
    }

    /**
     * valida que se permita la habilitacion
     */
    public void validarHabilitable()
    {
        if (! this.habilitable) {
            throw new RuntimeException("la asignatura no es habilitable.");
        }
    }

    /**
     * establece una nota para un estudiante
     * 
     * @param identificacion identificacion del estudiante
     * @param numeroNota numero de la nota a asignar (1 para la primera nota, 2 para la segunda, etc)
     * @param valor valor de la nota
     */
    public void setNota(String identificacion, int numeroNota, float valor)
    {
        this.validarEstadoApertura();

        this.obtenerEstudiante(identificacion).getCalificacion().setNota(numeroNota, valor);
    }

    /**
     * establece la nota de habilitacion de un estudiante
     * 
     * @param identificacion identificacion del estudiante
     * @param valor valor de la nota
     */
    public void setNotaHabilitacion(String identificacion, float valor)
    {
        this.validarHabilitable();
        this.validarEstadoCerrado();

        this.obtenerEstudiante(identificacion).getCalificacion().setNotaHabilitacion(valor);
    }

    /**
     * calcular la cantidad de cupos que no han sido ocupados
     * 
     * @return numero de cupos libres
     */
    public int calcularCuposLibres()
    {
        return this.cupos - this.estudiantes.size();
    }

    /**
     * calcula/obtiene la cantidad de cupos en uso
     * 
     * @return cantidad de cupos ocupados
     */
    public int calcularCuposUsados()
    {
        return this.estudiantes.size();
    }

    /**
     * obtiene el nombre
     * 
     * @return nombre
     */
    public String getNombre()
    {
        return this.nombre;
    }

    /**
     * obtiene el codigo
     * 
     * @return codigo
     */
    public String getCodigo()
    {
        return this.codigo;
    }

    /**
     * obtiene el numero de cupos
     * 
     * @return capacidad maxima de estudiantes
     */
    public int getCupos()
    {
        return this.cupos;
    }

    /**
     * obtiene el indicador sobre la habilitacion
     * 
     * @return indicador para materia habilitable
     */
    public boolean isHabilitable()
    {
        return this.habilitable;
    }

    /**
     * comprueba si la asignatura se encuentra cerrada
     * 
     * @return booleano que representa si la nota esta cerrada
     */
    public boolean isCerrada()
    {
        return this.cerrada;
    }

    /**
     * comprueba si la asignatura se encuentra abierta 
     * 
     * @return booleano que representa si la nota esta abierta 
     */
    public boolean isAbierta()
    {
        return this.cerrada;
    }

    /**
     * obtiene al docente asignado
     * 
     * @return Optional con el docente asignado si esta presente
     */
    public Optional<Docente> getDocente()
    {
        return Optional.ofNullable(this.docente);
    }
}