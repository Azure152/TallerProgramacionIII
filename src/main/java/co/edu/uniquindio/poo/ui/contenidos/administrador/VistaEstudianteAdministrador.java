package co.edu.uniquindio.poo.ui.contenidos.administrador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import co.edu.uniquindio.poo.ui.App;
import co.edu.uniquindio.poo.ui.componentes.Boton;
import co.edu.uniquindio.poo.ui.componentes.CampoEntrada;
import co.edu.uniquindio.poo.ui.utilidades.Colores;
import co.edu.uniquindio.poo.ui.utilidades.Cursores;
import co.edu.uniquindio.poo.ui.utilidades.Fuentes;
import co.edu.uniquindio.poo.usuarios.Estudiante;

public class VistaEstudianteAdministrador extends JPanel {
    private App app;
    private Estudiante est;
    private Boton eliminarEstudianteBtn;
    private Boton modifcarEstudianteBtn;
    private CampoEntrada modifcarEstudianteId;
    private CampoEntrada modifcarEstudianteNombre;
    private CampoEntrada modifcarEstudianteApellido;
    private CampoEntrada modifcarEstudianteFechNacimiento;

    public VistaEstudianteAdministrador(App app, Estudiante est) {
        this.app = app;
        this.est = est;

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
        // barra.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
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

        var sidebarEl2 = new JLabel();
        sidebarEl2.setText("Estudiantes");
        sidebarEl2.setFont(Fuentes.PREDETERMINADA);
        sidebarEl2.setBorder(new EmptyBorder(10, 15, 10, 10));
        sidebarEl2.setForeground(Colores.PRIMARY);

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
        
        JLabel titulo = new JLabel("Estudiante");
        titulo.setBounds(20, 20, 560, 20);
        titulo.setFont(Fuentes.PREDETERMINADA.deriveFont(20f).deriveFont(Font.BOLD));

        CampoEntrada identificacion = new CampoEntrada();
        identificacion.setBounds(20, 60, 560, 40);
        identificacion.setTextoMarcador("identificacion");
        identificacion.setText(this.est.getIdentificacion());
        identificacion.setEnabled(false);

        CampoEntrada nombre = new CampoEntrada();
        nombre.setBounds(20, 110, 560, 40);
        nombre.setTextoMarcador("Nombre(s)");
        nombre.setText(this.est.getNombre());

        CampoEntrada apellido = new CampoEntrada();
        apellido.setBounds(20, 160, 560, 40);
        apellido.setTextoMarcador("Apellido(s)");
        apellido.setText(this.est.getApellido());

        CampoEntrada fechaNacimiento = new CampoEntrada();
        fechaNacimiento.setBounds(20, 210, 560, 40);
        fechaNacimiento.setText(this.est.getFechaNacimiento().toString());
        fechaNacimiento.setTextoMarcador("yyyy-mm-dd");

        Boton modificar = new Boton();
        modificar.setText("Modificar");
        modificar.setBounds(20, 260, 180, 40);
        modificar.setCursor(Cursores.POINTER_CURSOR);
        modificar.setBackground(Colores.PRIMARY);
        modificar.setForeground(Color.WHITE);

        Boton eliminar = new Boton();
        eliminar.setText("Eliminar");
        eliminar.setBounds(210, 260, 180, 40);
        eliminar.setCursor(Cursores.POINTER_CURSOR);
        eliminar.setBackground(Colores.PELIGRO);
        eliminar.setForeground(Color.WHITE);
        
        var asigData = app.aplicativo()
            .getEstudianteAsignaturas(est.getIdentificacion())
            .stream().map((a) -> {
                return new String[] {
                    a.getCodigo(),
                    a.getNombre(),
                    String.valueOf(a.obtenerEstudiante(est.getIdentificacion())
                        .getCalificacion().calcularPromedio())
                };
            }).toList().toArray(new String[][] {});

        String[] columnas = new String[] {"Codigo", "nombre", "promedio"};

        JTable asignaturas = new JTable(asigData, columnas);
        // asignaturas.setCellSelectionEnabled(false);
        asignaturas.setDefaultEditor(Object.class, null);

        JScrollPane contenedorTabla = new JScrollPane(asignaturas);
        contenedorTabla.setBounds(20, 320, 560, 260);

        panel.add(titulo);
        panel.add(identificacion);
        panel.add(nombre);
        panel.add(apellido);
        panel.add(fechaNacimiento);
        panel.add(modificar);
        panel.add(eliminar);
        panel.add(contenedorTabla);

        modifcarEstudianteId = identificacion;
        modifcarEstudianteNombre = nombre;
        modifcarEstudianteApellido = apellido;
        modifcarEstudianteFechNacimiento = fechaNacimiento;
        modifcarEstudianteBtn = modificar;
        eliminarEstudianteBtn = eliminar;

        return panel;
    }

    private void asignarListeners() {
        modifcarEstudianteBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { modificarEstudiante(); }
        });

        eliminarEstudianteBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { removerEstudiante(); }
        });
    }

    private void modificarEstudiante() {
        try {
            String id = modifcarEstudianteId.getText();
            String nombre = modifcarEstudianteNombre.getText();
            String apellido = modifcarEstudianteApellido.getText();
            String fechaNacimiento = modifcarEstudianteFechNacimiento.getText();

            if (id.isBlank() || nombre.isBlank() || apellido.isBlank() || fechaNacimiento.isBlank()) {
                throw new RuntimeException();
            }

            Estudiante estudiante = app.aplicativo().obtenerEstudiante(id);
            estudiante.setFechaNacimiento(fechaNacimiento);
            estudiante.setNombre(nombre);
            estudiante.setApellido(apellido);

            app.mostrarEstudianteAdministrador(estudiante);

            JOptionPane.showMessageDialog(
                app,
                "La informacion del estudiante ha sido actualizada.",
                "Informacion actualizada",
                JOptionPane.INFORMATION_MESSAGE
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                app,
                "Error al modificar estudiante, verifique la integridad de los campos.",
                "Error.",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void removerEstudiante() {
        try {
            app.aplicativo().removerEstudiante(modifcarEstudianteId.getText());
            app.estudiantesAdministrador();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                app,
                "Ocurrio un error al remover al estudiante, puede que no se encuentre registrado",
                "Error.",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}