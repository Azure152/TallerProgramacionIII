package co.edu.uniquindio.poo.asignaturas;

import java.util.Arrays;
import java.util.Optional;

public class Calificacion
{
    /**
     * notas de la calificacion
     */
    private Float[] notas;

    /**
     * numero maximo de notas
     */
    private Integer maximoNotas;

    /**
     * valor de la nota de habilitacion
     */
    private Optional<Float> notaHabilitacion;

    /**
     * construye una instancia de Calificacion
     */
    public Calificacion()
    {
        this.maximoNotas = 4;
        this.notas = new Float[this.maximoNotas];
        this.notaHabilitacion = Optional.empty();

        Arrays.fill(this.notas, 0f);
    }

    /**
     * calcula el promedio de las notas
     * 
     * @return promedio de las notas
     */
    public Float calcularPromedio()
    {
        return this.sumarNotas() / this.maximoNotas;
    }

    /**
     * suma todas la notas
     * 
     * @return suma de las notas
     */
    private Float sumarNotas()
    {
        return (float) Arrays.stream(this.notas).mapToDouble(x -> {
            return Double.valueOf(x != null ? x : 0);
        }).sum();
    }

    /**
     * obtiene el valor final de la calificacion
     * 
     * @return calificacion final
     */
    public float obtenerCalificacionFinal()
    {
        if (this.notaHabilitacion.isPresent()) {
            return this.notaHabilitacion.get();
        }

        return this.calcularPromedio();
    }

    /**
     * establece el valor de una nota
     * 
     * @param numeroNota numero de la nota (1 para la primera nota, 2 para la segunda, etc)
     * @param valor nuevo valor para la nota
     */
    public void setNota(int numeroNota, float valor)
    {
        if (valor < 0f || valor > 5f) {
            throw new RuntimeException("valor de nota fuera de rango");
        }

        this.notas[numeroNota] = valor;
    }

    /**
     * establece el valor de la nota de habilitacion
     * 
     * @param valor nuevo valor para la nota de habilitacion
     */
    public void setNotaHabilitacion(float valor)
    {
        this.notaHabilitacion = Optional.of(valor);
    }

    /**
     * remueve el valor de la nota de habilitacion
     */
    public void limpiarNotaHabilitacion()
    {
        this.notaHabilitacion = null;
    }

    /**
     * obtiene una nota de la calificacion
     * 
     * @param numeroNota numero de la nota (1 para la primera nota, 2 para la segunda, etc)
     * 
     * @return valor de la nota
     */
    public Float getNota(int numeroNota)
    {
        return this.notas[numeroNota];
    }

    /**
     * obtiene un Optional con el valor de la nota de habilitacion si esta presente
     * 
     * @return valor de la nota de habilitacion
     */
    public Optional<Float> getNotaHabilitacion()
    {
        return this.notaHabilitacion;
    }

    /**
     * obtiene todas las notas
     * 
     * @return notas
     */
    public Float[] getNotas()
    {
        return this.notas;
    }
}