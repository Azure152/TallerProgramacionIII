package co.edu.uniquindio.poo.ui.contenidos.administrador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import co.edu.uniquindio.poo.asignaturas.Asignatura;
import co.edu.uniquindio.poo.asignaturas.AsignaturaEstudiante;
import co.edu.uniquindio.poo.asignaturas.Calificacion;
import co.edu.uniquindio.poo.ui.App;
import co.edu.uniquindio.poo.ui.componentes.Boton;
import co.edu.uniquindio.poo.ui.componentes.CampoEntrada;
import co.edu.uniquindio.poo.ui.utilidades.Colores;
import co.edu.uniquindio.poo.ui.utilidades.Cursores;
import co.edu.uniquindio.poo.ui.utilidades.Fuentes;
import co.edu.uniquindio.poo.usuarios.Docente;
import co.edu.uniquindio.poo.usuarios.Estudiante;

public class VistaAsignaturaAdministrador extends JPanel {
    private App app;
    private Asignatura asg;
    private Boton cerrarAsignatura;
    private Boton actualizarAsignatura;
    private Boton removerAsignatura;
    private CampoEntrada modificarAsignaturaNombre;
    private CampoEntrada modificarAsignaturaCodigo;
    private CampoEntrada modificarAsignaturaCapacidad;
    private JComboBox<String> modificarAsignaturaDocente;
    private Boton mostrarEstudiante;
    private JTable tablaEstudiantes;
    private JDialog modal;
    private CampoEntrada nota1;
    private CampoEntrada nota2;
    private CampoEntrada nota3;
    private CampoEntrada nota4;
    private CampoEntrada notaHabilitacion;
    private Boton agregarEstudiante;
    private JComboBox agregarEstudianteCB;
    private Boton agregarEstudianteModalBtn;
    private Boton cambiarNotasBtn;
    private Boton removerEstudiante;

    public VistaAsignaturaAdministrador(App app, Asignatura asg) {
        this.app = app;
        this.asg = asg;

        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.setBackground(Colores.FONDO);

        this.asignarComponentes();
        this.asignarListeners();
    }

    private void asignarComponentes() {
        this.add(this.crearBarraLateral());
        this.add(this.crearPanelContenido());
    }

    private JPanel crearBarraLateral() {
        JPanel barra = new JPanel();
        barra.setBackground(Colores.FONDO_DARK);
        barra.setPreferredSize(new Dimension(200, 600));
        barra.setLayout(new BoxLayout(barra, BoxLayout.PAGE_AXIS));

        JLabel titulo = new JLabel();
        titulo.setText("Panel administrativo");
        titulo.setBorder(new EmptyBorder(15, 10, 15, 10));
        titulo.setFont(Fuentes.PREDETERMINADA.deriveFont(16f).deriveFont(Font.BOLD));

        barra.add(titulo);
        barra.add(this.crearContenidoBarraLateral());
        barra.add(Box.createVerticalStrut(10));

        Arrays.stream(
            new String[] {"Everybody", "else is a", "returned"}
        ).iterator().forEachRemaining((name) -> {
            JLabel label = new JLabel("@ " + name);
            label.setBorder(new EmptyBorder(5, 10, 10, 10));
            label.setFont(Fuentes.PREDETERMINADA);
            barra.add(label);
        });

        return barra;
    }

    private JPanel crearContenidoBarraLateral() {
        JPanel contenido = new JPanel();
        contenido.setBackground(null);
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.PAGE_AXIS));
        contenido.setPreferredSize(new Dimension(200, 600));
        contenido.setMaximumSize(new Dimension(200, 0));

        var sidebarEl1 = new JLabel();
        sidebarEl1.setText("Asignaturas");
        sidebarEl1.setFont(Fuentes.PREDETERMINADA);
        sidebarEl1.setBorder(new EmptyBorder(10, 15, 10, 10));
        sidebarEl1.setForeground(Colores.PRIMARY);

        var sidebarEl2 = new JLabel();
        sidebarEl2.setText("Estudiantes");
        sidebarEl2.setFont(Fuentes.PREDETERMINADA);
        sidebarEl2.setBorder(new EmptyBorder(10, 15, 10, 10));

        var sidebarEl3 = new JLabel();
        sidebarEl3.setText("Docentes");
        sidebarEl3.setFont(Fuentes.PREDETERMINADA);
        sidebarEl3.setBorder(new EmptyBorder(10, 15, 10, 10));

        contenido.add(sidebarEl1);
        contenido.add(sidebarEl2);
        contenido.add(sidebarEl3);

        sidebarEl1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { app.asignaturasAdministrador(); }
        });

        sidebarEl2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { app.estudiantesAdministrador(); }
        });

        sidebarEl3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { app.docentesAdministrador(); }
        });

        return contenido;
    }

    private JPanel crearPanelContenido() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(600, 600));
        panel.setBackground(Colores.FONDO);
        panel.setLayout(null);

        var cabecera = new JPanel();
        cabecera.setBounds(20, 10, 400, 30);
        cabecera.setLayout(new BoxLayout(cabecera, BoxLayout.LINE_AXIS));
        cabecera.setBackground(null);

        var nombre = new JLabel(this.asg.getNombre());
        nombre.setFont(Fuentes.PREDETERMINADA.deriveFont(20f).deriveFont(Font.BOLD));
        nombre.setHorizontalAlignment(SwingConstants.LEFT);

        var codigo = new JLabel(this.asg.getCodigo());
        codigo.setFont(Fuentes.PREDETERMINADA.deriveFont(14f));
        codigo.setForeground(Color.GRAY);

        CampoEntrada campoNombre = new CampoEntrada();
        campoNombre.setBounds(20, 60, 560, 40);
        campoNombre.setTextoMarcador("Nombre de la asignatura");
        campoNombre.setText(this.asg.getNombre());

        CampoEntrada campoCodigo = new CampoEntrada();
        campoCodigo.setBounds(20, 110, 560, 40);
        campoCodigo.setTextoMarcador("codigo de la asignatura");
        campoCodigo.setText(this.asg.getCodigo());

        CampoEntrada campoCapacidad = new CampoEntrada();
        campoCapacidad.setBounds(20, 160, 560, 40);
        campoCapacidad.setTextoMarcador("capcidad maxima de estudiantes");
        campoCapacidad.setText((String.valueOf(this.asg.getCupos())));

        var aplDocentes = this.app.aplicativo().docentes().stream().map((d) -> {
            return d.getIdentificacion() + " : " + d.getNombreCompleto();
        }).toList();

        var arl = (new ArrayList<String>());
        arl.addAll(aplDocentes);

        int currentIndex;

        if (this.asg.getDocente().isPresent()) {
            currentIndex = arl.indexOf(
                this.asg.getDocente().get().getIdentificacion() + " : " + this.asg.getDocente().get().getNombreCompleto()
            );
        } else {
            currentIndex = -1;
        }

        String[] docentes = arl.toArray(new String[] {});


        JComboBox<String> docente = new JComboBox<>(docentes);
        docente.setBounds(20, 210, 410, 40);
        docente.setBackground(null);
        docente.setSelectedIndex(currentIndex);

        JLabel labelHabilitable = new JLabel();
        labelHabilitable.setText("Es habilitable: ");
        labelHabilitable.setBounds(470, 210, 90, 40);

        JCheckBox habilitableCheckBox = new JCheckBox();
        habilitableCheckBox.setBounds(560, 210, 20, 40);
        habilitableCheckBox.setBackground(null);
        habilitableCheckBox.setSelected(this.asg.isHabilitable());
        habilitableCheckBox.setEnabled(false);

        Boton actualizarBtn =  new Boton();
        actualizarBtn.setBounds(20, 260, 180, 40);
        actualizarBtn.setText("Actualizar asignatura");
        actualizarBtn.setBackground(Colores.PRIMARY);
        actualizarBtn.setForeground(Color.WHITE);
        actualizarBtn.setCursor(Cursores.POINTER_CURSOR);

        Boton eliminarBtn = new Boton();
        eliminarBtn.setBounds(210, 260, 180, 40);
        eliminarBtn.setBackground(Colores.PELIGRO);
        eliminarBtn.setText("Eliminar asignatura");
        eliminarBtn.setForeground(Color.WHITE);
        eliminarBtn.setCursor(Cursores.POINTER_CURSOR);

        Boton cerrarBtn = new Boton();
        cerrarBtn.setBounds(400, 260, 180, 40);
        cerrarBtn.setBackground(Colores.PELIGRO);
        cerrarBtn.setText("Cerrar asignatura");
        cerrarBtn.setForeground(Color.WHITE);
        cerrarBtn.setCursor(Cursores.POINTER_CURSOR);

        JSeparator sep = new JSeparator();
        sep.setForeground(Color.LIGHT_GRAY);
        sep.setBounds(20, 310, 560, 1);

        var datosTabla = app.aplicativo()
            .hallarAsignatura(asg.getCodigo()).get()
            .getEstudiantes().stream().map((es) -> {
                return new Object[] {
                    es.getEstudiante().getIdentificacion(),
                    es.getEstudiante().getNombreCompleto(),
                    es.getCalificacion().calcularPromedio(),
                    es.getCalificacion().obtenerCalificacionFinal()
                };
            })
            .toList().toArray(new Object[][] {});

        String[] columnas = new String[] {"Identificacion", "nombre", "promedio", "nota final"};

        JTable estudiantes = new JTable(datosTabla, columnas);
        estudiantes.setDefaultEditor(Object.class, null);
        estudiantes.getTableHeader().setReorderingAllowed(false);

        JScrollPane contenedorTabla = new JScrollPane(estudiantes);
        contenedorTabla.setBounds(20, 320, 400, 260);

        Boton agregar = new Boton();
        agregar.setText("Agregar");
        agregar.setBounds(430, 340, 150, 30);
        agregar.setCursor(Cursores.POINTER_CURSOR);

        Boton mostrar = new Boton();
        mostrar.setText("Mostrar");
        mostrar.setBounds(430, 380, 150, 30);
        mostrar.setCursor(Cursores.POINTER_CURSOR);

        Boton eliminarEnTabla = new Boton();
        eliminarEnTabla.setText("Liberar cupo");
        eliminarEnTabla.setBounds(430, 420, 150, 30);
        eliminarEnTabla.setCursor(Cursores.POINTER_CURSOR);

        cabecera.add(nombre);
        cabecera.add(Box.createHorizontalStrut(10));
        cabecera.add(codigo);
        panel.add(cabecera);
        panel.add(campoNombre);
        panel.add(campoCodigo);
        panel.add(campoCapacidad);
        panel.add(docente);
        panel.add(labelHabilitable);
        panel.add(habilitableCheckBox);
        panel.add(actualizarBtn);
        panel.add(eliminarBtn);
        panel.add(cerrarBtn);
        panel.add(sep);
        panel.add(contenedorTabla);
        panel.add(agregar);
        panel.add(mostrar);
        panel.add(eliminarEnTabla);

        if (asg.isCerrada()) {
            cerrarBtn.setVisible(false);
            eliminarEnTabla.setEnabled(false);
        }

        if (asg.calcularCuposLibres() == 0) {
            agregar.setCursor(Cursores.DEFAULT_CURSOR);
            agregar.setEnabled(false);
        }

        agregarEstudiante = agregar;
        mostrarEstudiante = mostrar;
        removerEstudiante = eliminarEnTabla;
        tablaEstudiantes = estudiantes;
        modificarAsignaturaNombre = campoNombre;
        modificarAsignaturaCodigo = campoCodigo;
        modificarAsignaturaCapacidad = campoCapacidad;
        modificarAsignaturaDocente = docente;
        actualizarAsignatura = actualizarBtn;
        removerAsignatura = eliminarBtn;
        cerrarAsignatura = cerrarBtn;

        return panel;
    }


    private void asignarListeners() {
        actualizarAsignatura.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { modificarAsignatura(); }
        });

        removerAsignatura.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { removerAsignatura(); }
        });

        cerrarAsignatura.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { cerrarAsignatura(); }
        });

        agregarEstudiante.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { agregarEstudianteModal(); }
        });

        mostrarEstudiante.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { calificacionModal(); }
        });

        removerEstudiante.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { removerEstudiante(); }
        });
    }

    private void modificarAsignatura() {
        try {
            String nombre = modificarAsignaturaNombre.getText();
            String codigo = modificarAsignaturaCodigo.getText();
            int capacidad = Integer.valueOf(modificarAsignaturaCapacidad.getText());

            Optional<Docente> docente = app.aplicativo().hallarDocente(
                String.valueOf(modificarAsignaturaDocente.getSelectedItem()).split(":")[0].trim()
            );

            if ( asg.isAbierta() ) {
                asg.setDocente(docente.isPresent() ? docente.get() : null);
            }

            asg.setNombre(nombre);
            asg.setCodigo(codigo);
            asg.setCupos(capacidad);

            app.mostrarAsignaturaAdministrador(asg);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                app,
                "Error al modificar la asignatura, verifique la integridad de los campos.",
                "Error.",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void removerAsignatura() {
        try {
            app.aplicativo().removerAsignatura(asg.getCodigo());
            app.asignaturasAdministrador();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                app,
                "Ocurrio un error al remover la asignatura, puede que no se encuentre registrada",
                "Error.",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void removerEstudiante() {
        if (asg.isCerrada()) return;

        try {
            String id = obtenerIdentificacionEstudianteTabla();

            if (id == null) return;

            asg.removerEstudiante(id);
            app.mostrarAsignaturaAdministrador(asg);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                app,
                "Ocurrio un error al remover al estudiante, puede que no se encuentre registrado",
                "Error.",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void cerrarAsignatura() {
        asg.cerrar();
        app.mostrarAsignaturaAdministrador(asg);
    }

    private void calificacionModal() {
        AsignaturaEstudiante estudiante = obtenerEstudianteTabla();

        if (estudiante == null) return;

        Calificacion calificacion = estudiante.getCalificacion();

        JDialog modal = new JDialog(this.app, "Calificaciones", true);
        modal.setSize(500, 300);
        modal.setLocationRelativeTo(null);
        modal.getContentPane().setBackground(Colores.FONDO);
        modal.setLayout(null);

        JLabel nota1Label = new JLabel();
        nota1Label.setText("Nota 1: ");
        nota1Label.setBounds(20, 10, 100, 30);

        CampoEntrada campoNota1 = new CampoEntrada();
        campoNota1.setBounds(100, 10, 360, 30);
        campoNota1.setText(String.valueOf(calificacion.getNota(0)));
        
        JLabel nota2Label = new JLabel();
        nota2Label.setText("Nota 2: ");
        nota2Label.setBounds(20, 50, 100, 30);

        CampoEntrada campoNota2 = new CampoEntrada();
        campoNota2.setBounds(100, 50, 360, 30);
        campoNota2.setText(String.valueOf(calificacion.getNota(1)));

        JLabel nota3Label = new JLabel();
        nota3Label.setText("Nota 3: ");
        nota3Label.setBounds(20, 90, 100, 30);

        CampoEntrada campoNota3 = new CampoEntrada();
        campoNota3.setBounds(100, 90, 360, 30);
        campoNota3.setText(String.valueOf(calificacion.getNota(2)));

        JLabel nota4Label = new JLabel();
        nota4Label.setText("Nota 4: ");
        nota4Label.setBounds(20, 130, 100, 30);

        CampoEntrada campoNota4 = new CampoEntrada();
        campoNota4.setBounds(100, 130, 360, 30);
        campoNota4.setText(String.valueOf(calificacion.getNota(3)));

        JLabel notaHabilitacionLabel = new JLabel();
        notaHabilitacionLabel.setText("nota de habilitacion: ");
        notaHabilitacionLabel.setBounds(20, 170, 150, 30);

        CampoEntrada campoNotaHabilitacion = new CampoEntrada();
        campoNotaHabilitacion.setBounds(150, 170, 310, 30);

        if (calificacion.getNotaHabilitacion().isPresent()) {
            campoNotaHabilitacion.setText(
                String.valueOf(calificacion.getNotaHabilitacion().get())
            );
        }

        Boton actualizar = new Boton();
        actualizar.setText("Actualizar");
        actualizar.setBackground(Colores.PRIMARY);
        actualizar.setBounds(20, 210, 440, 40);
        actualizar.setForeground(Color.WHITE);
        actualizar.setCursor(Cursores.POINTER_CURSOR);

        modal.add(nota1Label);
        modal.add(nota2Label);
        modal.add(nota3Label);
        modal.add(nota4Label);
        modal.add(campoNota1);
        modal.add(campoNota2);
        modal.add(campoNota3);
        modal.add(campoNota4);
        modal.add(notaHabilitacionLabel);
        modal.add(campoNotaHabilitacion);
        modal.add(actualizar);

        nota1 = campoNota1;
        nota2 = campoNota2;
        nota3 = campoNota3;
        nota4 = campoNota4;
        notaHabilitacion = campoNotaHabilitacion;
        cambiarNotasBtn = actualizar;
        this.modal = modal;

        this.asignarModalListeners();

        if (! asg.isHabilitable()) {
            notaHabilitacionLabel.setVisible(false);
            campoNotaHabilitacion.setVisible(false);
            actualizar.setLocation(20, 180);
        }

        if (asg.isCerrada()) {
            nota1.setEnabled(false);
            nota2.setEnabled(false);
            nota3.setEnabled(false);
            nota4.setEnabled(false);

            if (
                calificacion.calcularPromedio() < 2
                || calificacion.calcularPromedio() > 3
            ) {
                campoNotaHabilitacion.setEnabled(false);
            }
        } else {
            campoNotaHabilitacion.setEnabled(false);
        }

        modal.setVisible(true);
    }

    private void agregarEstudianteModal() {
        if (asg.getCupos() == asg.calcularCuposUsados()) {
            return;
        }

        JDialog modal = new JDialog(this.app, "Calificaciones", true);
        modal.setSize(500, 200);
        modal.setLocationRelativeTo(null);
        modal.getContentPane().setBackground(Colores.FONDO);
        modal.setLayout(null);

        var estudiantes = app
            .aplicativo()
            .obtenerEstudiantesNoRegistrados(asg.getCodigo())
            .stream().map((es) -> {
                return es.getIdentificacion() + " : " + es.getNombreCompleto();
            }).toList().toArray(new String[] {});

        JComboBox<String> estudiante = new JComboBox<>(estudiantes);
        estudiante.setBounds(20, 20, 440, 50);
        estudiante.setBackground(null);

        Boton agregar = new Boton();
        agregar.setText("Agregar Estudiante");
        agregar.setBounds(20, 90, 440, 40);
        agregar.setBackground(Colores.PRIMARY);
        agregar.setForeground(Color.WHITE);
        agregar.setCursor(Cursores.POINTER_CURSOR);

        modal.add(estudiante);
        modal.add(agregar);

        agregarEstudianteModalBtn = agregar;
        agregarEstudianteCB = estudiante;
        this.modal = modal;

        this.asignarModalAgregarEstudianteListeners();

        modal.setVisible(true);
    }

    private void asignarModalListeners() {
        cambiarNotasBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { cambiarNotas(); }
        });
    }

    private void asignarModalAgregarEstudianteListeners() {
        agregarEstudianteModalBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { agregarEstudiante(); }
        });
    }

    private void agregarEstudiante() {
        if (asg.isCerrada()) {
            JOptionPane.showMessageDialog(
                app,
                "No es posible agregar mas estudiantes, la asignatura ha sido cerrada.",
                "Error.",
                JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        try {
            Optional<Estudiante> estudiante = app.aplicativo().hallarEstudiante(
                String.valueOf(agregarEstudianteCB.getSelectedItem()).split(":")[0].trim()
            );

            if (estudiante.isEmpty()) return;
            
            asg.addEstudiante(estudiante.get());
            app.mostrarAsignaturaAdministrador(asg);
            modal.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                app,
                "Ocurrio un error al agregar el estudiante, puede que no se encuentre registrado/a",
                "Error.",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void cambiarNotas() {
        try {
            AsignaturaEstudiante estudiante = obtenerEstudianteTabla();
            Calificacion calificacion = estudiante.getCalificacion();

            calificacion.setNota(0, Float.valueOf(nota1.getText()));
            calificacion.setNota(1, Float.valueOf(nota2.getText()));
            calificacion.setNota(2, Float.valueOf(nota3.getText()));
            calificacion.setNota(3, Float.valueOf(nota4.getText()));

            String notaH = notaHabilitacion.getText();

            if (asg.isHabilitable() && ! notaH.isBlank()) {
                calificacion.setNotaHabilitacion(Float.parseFloat(notaH));
            }

            app.mostrarAsignaturaAdministrador(asg);
            modal.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                app,
                "Error al modificar notas, verifique la integridad de los campos.",
                "Error.",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private AsignaturaEstudiante obtenerEstudianteTabla() {
        int columnaSeleccionada = tablaEstudiantes.getSelectedRow();

        if (columnaSeleccionada == -1) {
            JOptionPane.showMessageDialog(
                app,
                "Debe seleccionar una fila.",
                "Error.",
                JOptionPane.ERROR_MESSAGE
            );

            return null;
        }

        return asg.obtenerEstudiante(
            String.valueOf(tablaEstudiantes.getValueAt(columnaSeleccionada, 0))
        );
    }

    private String obtenerIdentificacionEstudianteTabla() {
        int columnaSeleccionada = tablaEstudiantes.getSelectedRow();

        if (columnaSeleccionada == -1) {
            JOptionPane.showMessageDialog(
                app,
                "Debe seleccionar una fila.",
                "Error.",
                JOptionPane.ERROR_MESSAGE
            );

            return null;
        }

        return String.valueOf(
            tablaEstudiantes.getValueAt(columnaSeleccionada, 0)
        );
    }
}
