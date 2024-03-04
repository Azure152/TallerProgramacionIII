package co.edu.uniquindio.poo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.OptionalDouble;

import javax.swing.text.html.Option;

import co.edu.uniquindio.poo.asignaturas.Asignatura;
import co.edu.uniquindio.poo.usuarios.Docente;
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
     * lista de docentes
     */
    private Collection<Docente> docentes;

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
        this.docentes = new ArrayList<>();
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
    public Optional<Estudiante> hallarEstudiante(String identificacion)
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
     * remueve un estudiante de la lista del aplicativo
     * 
     * @param identificacion numero de identificacion
     */
    public void removerEstudiante(String identificacion)
    {
        Estudiante estudiante = obtenerEstudiante(identificacion);

        this.getEstudianteAsignaturas(
            estudiante.getIdentificacion()
        ).stream().iterator().forEachRemaining((a) -> {
            a.removerEstudiante(identificacion);
        });

        this.estudiantes.remove(estudiante);
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
     * obtiene a los estudiantes no registrados en una asignatura
     * 
     * @param codigo codigo de la asignatura
     * 
     * @returna estudiantes
     */
    public Collection<Estudiante> obtenerEstudiantesNoRegistrados(String codigo)
    {
        return this.estudiantes.stream().filter((es) -> {
            return ! this.getEstudianteAsignaturas(
                es.getIdentificacion()
            ).stream().filter((a) -> {
                return a.getCodigo().equals(codigo);
            }).findAny().isPresent();
        }).toList();
    }

    /**
     * obtiene las asignaturas del docente
     * 
     * @param identificacion numero de identficacion (docente)
     * 
     * @return asignturas
     */
    public Collection<Asignatura> getDocenteAsignaturas(String identificacion)
    {
        return this.asignaturas.stream().filter((a) -> {
            return a.getDocente().isPresent()
                && a.getDocente().get().getIdentificacion().equals(identificacion);
        }).toList();
    }

    /**
     * remueve una asignatura de la lista
     * 
     * @param codigo codigo de la asignatura
     */
    public void removerAsignatura(String codigo)
    {
        Asignatura asg = this.hallarAsignatura(codigo).get();

        this.asignaturas.remove(asg);
    }

    /**
     * añade un docente al aplicativo
     * 
     * @param docente docente a añadir
     */
    public void addDocente(Docente docente)
    {
        this.validarDocente(docente);

        this.docentes.add(docente);
    }

    /**
     * valida que el docente no se encutre registrado
     * 
     * @param docente docente a validar
     */
    private void validarDocente(Docente docente)
    {
        if (this.hallarDocente(docente.getIdentificacion()).isPresent()) {
            throw new RuntimeException("el docente ya se encutra registrado");
        }
    }

    /**
     * obtiene un docente usando el numero de identificacion
     * 
     * @param indentificacion numero de identificacion
     */
    public Docente obtenerDocente(String identificacion)
    {
        return this.hallarDocente(identificacion).get();
    }

    /**
     * busca un docente el la lista usando el numero de identificacion
     * 
     * @param docente
     */
    public Optional<Docente> hallarDocente(String identificacion)
    {
        return this.docentes.stream().filter((d) -> {
            return d.getIdentificacion().equals(identificacion);
        }).findAny();
    }

    /**
     * busca y remueve a un docente de la lista del aplicativo
     * 
     * @param identificacion
     */
    public void removerDocente(String identificacion)
    {
        Docente docente = this.obtenerDocente(identificacion);

        this.getDocenteAsignaturas(
            identificacion
        ).stream().iterator().forEachRemaining((d) -> {
            d.setDocente(null);
        });

        this.docentes.remove(docente);
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
     * obtiene el promedio global de un estudiante
     * 
     * @param identificacion numero de identificacion
     * 
     * @return promedio global
     */
    public float obtenerPromedioGlobalEstudiante(String identificacion)
    {
        OptionalDouble prom = this.calcularPromedioGlobalEstudiante(identificacion);

        return prom.isPresent() ? (float) prom.getAsDouble() : 0.0f;
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

    /**
     * obtiene las asignaturas del aplicativo
     * 
     * @return asignaturas registradas
     */
    public Collection<Asignatura> asignaturas()
    {
        return this.asignaturas;
    }

    /**
     * obtiene la lista de estudiantes del aplicativo
     * 
     * @return estudiantes
     */
    public Collection<Estudiante> estudiantes()
    {
        return this.estudiantes;
    }

    /**
     * lista de docentes
     * 
     * @return docentes
     */
    public Collection<Docente> docentes()
    {
        return this.docentes;
    }
}