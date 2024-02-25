package co.edu.uniquindio.poo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.OptionalDouble;

import co.edu.uniquindio.poo.asignaturas.Asignatura;
import co.edu.uniquindio.poo.usuarios.Estudiante;

public class Aplicativo 
{
    /**
     * estudiantes
     */
    private Collection<Estudiante> estudiantes;

    /**
     * asignaturas
     */
    private Collection<Asignatura> asignaturas;

    /**
     * contraseña del administrador
     */
    private String claveAdministrador;

    /**
     * construye una instancia de Aplicativo
     */
    public Aplicativo(String clave)
    {
        this.estudiantes = new ArrayList<>();
        this.asignaturas = new ArrayList<>();
        this.claveAdministrador = clave; 
    }

    /**
     * agrega un estudiante a la lista
     * 
     * @param estudiante estudiante a agregar
     */
    public void addEstudiante(Estudiante estudiante)
    {
        this.validarEstudiante(estudiante);

        this.estudiantes.add(estudiante);
    }

    /**
     * valida que el estudiante no se encuentre registrado
     * 
     * @param estudiante estudiante a validar
     */
    private void validarEstudiante(Estudiante estudiante)
    {
        if (this.hallarEstudiante(estudiante.getIdentificacion()).isPresent()) {
            throw new RuntimeException("el estudiante ya se encuentra registrado");
        }
    }

    /**
     * halla un estudiante
     * 
     * @param identificacion numero de identificacion del estudiante
     * 
     * @return Optinal con estudiante si esta presente
     */
    private Optional<Estudiante> hallarEstudiante(String identificacion)
    {
        return estudiantes.stream().filter((es) -> {
            return es.getIdentificacion().equals(identificacion);
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
    public Estudiante obtenerEstudiante(String identificacion)
    {
        var estudiante = this.hallarEstudiante(identificacion);

        if (estudiante.isEmpty()) {
            throw new RuntimeException(
                "El estudiante no se encuentra registrado en el aplicativo"
            );
        }

        return estudiante.get();
    }

    /**
     * agrega una asignatura a la lista
     * 
     * @param asignatura asignatura a agregar
     */
    public void addAsignatura(Asignatura asignatura)
    {
        this.asignaturas.add(asignatura);
    }

    /**
     * valida que la asignatura no se encuentre registrada
     * 
     * @param asignatura asignatura a validar
     */
    public void validarAsignatura(Asignatura asignatura)
    {
        if (this.hallarAsignatura(asignatura.getCodigo()).isPresent()) {
            throw new RuntimeException("la asignatura ya se encuentra registrada");
        }
    }

    /**
     * halla una asignaura usando el codigo
     * 
     * @param codigo codigo de la asignatura
     * 
     * @return Optional con la asignatura coincidente
     */
    public Optional<Asignatura> hallarAsignatura(String codigo)
    {
        return this.asignaturas.stream().filter(a -> a.getCodigo().equals(codigo)).findAny();
    }

    /**
     * obtiene los asignaturas de un estudiante
     * 
     * @param identificacion numero de identificacion
     * 
     * @return asignaturas de estudiante
     */
    public Collection<Asignatura> getEstudianteAsignaturas(String identificacion)
    {
        this.obtenerEstudiante(identificacion);

        return this.asignaturas.stream().filter((a) -> {
            return a.hallarEstudiante(identificacion).isPresent();
        }).toList();
    }

    /**
     * calcula el promedio de las asignaturas de un estudiante
     * 
     * @param identificacion numero de identificacion
     * 
     * @return promedio
     */
    public OptionalDouble calcularPromedioGlobalEstudiante(String identificacion)
    {
        return this.asignaturas.stream().filter((a) -> {
            return a.hallarEstudiante(identificacion).isPresent();
        }).map((a) -> {
            return a.obtenerEstudiante(identificacion).getCalificacion().calcularPromedio();
        }).mapToDouble((c) -> (double) c).average();
    }

    /**
     * compara la clave del aplicativo con una dada
     * 
     * @param clave clave a comparar
     * 
     * @return boolean que representa si la clave coincide
     */
    public boolean comprobarClaveAdministrador(String clave)
    {
        return this.claveAdministrador.equals(clave);
    }
}