package co.edu.uniquindio.poo;

import java.time.LocalDate;

import co.edu.uniquindio.poo.asignaturas.Asignatura;
import co.edu.uniquindio.poo.ui.App;
import co.edu.uniquindio.poo.usuarios.Docente;
import co.edu.uniquindio.poo.usuarios.Estudiante;

public class Main {
    public static void main(String[] args) {

        App.iniciar(aplicativoFalso());
    }

    private static Aplicativo aplicativoFalso() {
        Aplicativo apl = new Aplicativo("1");

        Asignatura asg1 = new Asignatura("Calculo 43", "JKSD-1024", 22, false);
        Asignatura asg2 = new Asignatura("Lenguage maquina", "MSQ-322", 1, true);

        Docente doc1 = new Docente("123589", "Juan Camilo", "Ortega");
        Docente doc2 = new Docente("981230", "Jose Luis", "Alvarez");

        Estudiante est1 = new Estudiante("123456789", "Juan", "Perea", LocalDate.now());
        Estudiante est2 = new Estudiante("987654321", "Pablo", "Ramirez", LocalDate.now());

        asg1.addEstudiante(est1);
        asg1.addEstudiante(est2);
        asg1.setNota(est1.getIdentificacion(), 0, 5);

        asg2.addEstudiante(est2);
        asg2.setDocente(doc2);

        // asignaturas
        apl.addAsignatura(asg1);
        apl.addAsignatura(asg2);

        // docentes
        apl.addDocente(doc1);
        apl.addDocente(doc2);

        // estudiantes
        apl.addEstudiante(est1);
        apl.addEstudiante(est2);

        return apl;
    }
}
