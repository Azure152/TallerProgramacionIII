package co.edu.uniquindio.poo.ui.contenidos.administrador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import co.edu.uniquindio.poo.ui.App;
import co.edu.uniquindio.poo.ui.componentes.Boton;
import co.edu.uniquindio.poo.ui.componentes.CampoEntrada;
import co.edu.uniquindio.poo.ui.utilidades.Colores;
import co.edu.uniquindio.poo.ui.utilidades.Cursores;
import co.edu.uniquindio.poo.ui.utilidades.Fuentes;
import co.edu.uniquindio.poo.usuarios.Estudiante;

public class EstudiantesAdministrador extends JPanel {
    private App app;
    private String busqueda;
    private CampoEntrada campoBusqueda;
    private Boton botonBusqueda;
    private Boton crearEstudianteBtn;
    private JDialog modal;
    private Boton crearEstudianteModalBtn;
    private CampoEntrada crearEstudianteId;
    private CampoEntrada crearEstudianteNombre;
    private CampoEntrada crearEstudianteApellido;
    private CampoEntrada crearEstudianteFechaNacimiento;
    // private CampoEntrada crearEstudianteNombre;

    public EstudiantesAdministrador(App app) {
        this(app, "");
    }

    public EstudiantesAdministrador(App app, String busqueda) {
        this.app = app;
        this.busqueda = busqueda;

        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.setBackground(Colores.FONDO);

        this.asignarComponentes();
        this.asignarListeners();
    }

    public void asignarComponentes() {
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

        campoBusqueda = new CampoEntrada();
        campoBusqueda.setText(this.busqueda);
        campoBusqueda.setTextoMarcador("Busca estudiante por identificacion");
        campoBusqueda.setSize(400, 40);
        campoBusqueda.setLocation(20, 20);
        campoBusqueda.setFont(Fuentes.PREDETERMINADA);
        campoBusqueda.setBackground(null);

        botonBusqueda = new Boton();
        botonBusqueda.setText("Buscar");
        botonBusqueda.setSize(150, 40);
        botonBusqueda.setLocation(430, 20);
        botonBusqueda.setBackground(Colores.PRIMARY);
        botonBusqueda.setForeground(Color.WHITE);
        botonBusqueda.setFont(app.getFont());
        botonBusqueda.setCursor(Cursores.POINTER_CURSOR);

        var sep = new JSeparator(SwingConstants.HORIZONTAL);
        sep.setBounds(20, 80, 560, 1);
        sep.setForeground(Color.LIGHT_GRAY);

        Boton crearEstudiante = new Boton();
        crearEstudiante.setBounds(20, 100, 560, 50);
        crearEstudiante.setText("+ crear estudiante");
        crearEstudiante.setBackground(Colores.PRIMARY);
        crearEstudiante.setForeground(Color.WHITE);
        crearEstudiante.setFont(Fuentes.PREDETERMINADA);
        crearEstudiante.setCursor(Cursores.POINTER_CURSOR);

        JPanel listaAsignaturas = this.crearListaEstudiantes();

        panel.add(campoBusqueda);
        panel.add(botonBusqueda);
        panel.add(sep);
        panel.add(crearEstudiante);
        panel.add(listaAsignaturas);

        crearEstudianteBtn = crearEstudiante;

        return panel;
    }

    private JPanel crearListaEstudiantes() {
        JPanel lista = new JPanel();
        lista.setBounds(0, 180, 600, 420);
        lista.setBackground(null);
        lista.setLayout(new BorderLayout());

        JPanel elementos = new JPanel();
        elementos.setLayout(new BoxLayout(elementos, BoxLayout.PAGE_AXIS));
        elementos.setBackground(Colores.FONDO);

        Collection<Estudiante> estudiantes = this.app
            .aplicativo()
            .obtenerEstudiantesPorNombre(this.busqueda);

        for (Estudiante est: estudiantes) {
            elementos.add(this.crearElementoEstudiante(est));
        }

        JScrollPane scrollPane = new JScrollPane(elementos);
        scrollPane.setBorder(null);
        scrollPane.setBackground(null);

        lista.add(scrollPane, BorderLayout.CENTER);

        return lista;
    }

    private JPanel crearElementoEstudiante(Estudiante est) {
        Dimension dimension = new Dimension(550, 70);
        JPanel contenedor = new JPanel();
        contenedor.setLayout(new BorderLayout());
        contenedor.setBackground(null);
        contenedor.setPreferredSize(dimension);
        contenedor.setMinimumSize(dimension);
        contenedor.setMaximumSize(dimension);
        contenedor.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel contenido = new JPanel();
        contenido.setFont(Fuentes.PREDETERMINADA);
        contenido.setLayout(null);
        contenido.setCursor(Cursores.POINTER_CURSOR);

        var cabecera = new JPanel();
        cabecera.setBounds(10, 10, 400, 50);
        cabecera.setLayout(new BoxLayout(cabecera, BoxLayout.PAGE_AXIS));
        cabecera.setBackground(null);

        var nombre = new JLabel(est.getNombreCompleto());
        nombre.setFont(contenido.getFont().deriveFont(14f).deriveFont(Font.BOLD));
        nombre.setHorizontalAlignment(SwingConstants.LEFT);

        var identificacion = new JLabel(est.getIdentificacion());
        identificacion.setForeground(Color.GRAY);
        identificacion.setFont(contenido.getFont());

        var promedio = new JLabel(
            String.valueOf(
                this.app.aplicativo().obtenerPromedioGlobalEstudiante(est.getIdentificacion())
            )
        );

        promedio.setBounds(460, 20, 55, 20);
        promedio.setHorizontalAlignment(SwingConstants.RIGHT);
        promedio.setVerticalAlignment(SwingConstants.CENTER);
        promedio.setFont(contenido.getFont().deriveFont(18f));

        cabecera.add(nombre);
        cabecera.add(identificacion);
        contenido.add(cabecera);
        contenido.add(promedio);
        contenedor.add(contenido, BorderLayout.CENTER);

        this.asignarListenersEstudiante(contenido, est);

        return contenedor;
    }

    private void asignarListenersEstudiante(JPanel est, Estudiante es) {
        est.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                app.mostrarEstudianteAdministrador(es);
            }
        });
    }

    public void asignarListeners() {
        crearEstudianteBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { crearEstudianteModal(); }
        });

        botonBusqueda.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { buscarEstudiantes(); }
        });
    }

    private void buscarEstudiantes() {
        app.estudiantesAdministrador(this.campoBusqueda.getText());
    }

    public void crearEstudianteModal() {
        JDialog modal = new JDialog(this.app, "Crear estudiante", true);
        modal.setSize(500, 300);
        modal.setLocationRelativeTo(null);
        modal.setLayout(new BorderLayout());

        JPanel contenido = new JPanel();
        contenido.setBackground(Colores.FONDO);
        contenido.setLayout(null);

        CampoEntrada campoIdentificacion = new CampoEntrada();
        campoIdentificacion.setBounds(20, 30, 440, 30);
        campoIdentificacion.setTextoMarcador("Numero identificacion");

        CampoEntrada campoNombre = new CampoEntrada();
        campoNombre.setBounds(20, 70, 440, 30);
        campoNombre.setTextoMarcador("Nombre(s)");

        CampoEntrada campoApellido = new CampoEntrada();
        campoApellido.setBounds(20, 110, 440, 30);
        campoApellido.setTextoMarcador("Apellidos(s)");

        CampoEntrada campoFechaNacimiento = new CampoEntrada();
        campoFechaNacimiento.setBounds(20, 150, 440, 30);
        campoFechaNacimiento.setTextoMarcador("Fecha de nacimiento (dd-mm-yyyy)");

        // JLabel labelHabilitable = new JLabel();
        // labelHabilitable.setText("Es habilitable: ");
        // labelHabilitable.setBounds(20, 150, 90, 30);

        // JCheckBox habilitableCheckBox = new JCheckBox();
        // habilitableCheckBox.setBounds(110, 150, 20, 30);
        // habilitableCheckBox.setBackground(null);

        Boton crear = new Boton();
        crear.setBounds(20, 190, 440, 40);
        crear.setBackground(Colores.PRIMARY);
        crear.setText("Crear");
        crear.setFont(Fuentes.PREDETERMINADA);
        crear.setForeground(Color.WHITE);
        crear.setFocusable(true);

        contenido.add(campoIdentificacion);
        contenido.add(campoNombre);
        contenido.add(campoApellido);
        contenido.add(campoFechaNacimiento);
        // contenido.add(labelHabilitable);
        // contenido.add(habilitableCheckBox);
        contenido.add(crear);
        modal.add(contenido, BorderLayout.CENTER);

        crearEstudianteModalBtn = crear;
        crearEstudianteId = campoIdentificacion;
        crearEstudianteNombre = campoNombre;
        crearEstudianteApellido = campoApellido;
        crearEstudianteFechaNacimiento = campoFechaNacimiento;
        this.modal = modal;

        this.asignarModalListeners();

        modal.setVisible(true);
    }

    private void asignarModalListeners() {
        crearEstudianteModalBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { crearEstudiante(); }
        });
    }

    private void crearEstudiante() {
        try {
            String id = crearEstudianteId.getText();
            String nombre = crearEstudianteNombre.getText();
            String apellido = crearEstudianteApellido.getText();
            String fechaNacimiento = crearEstudianteFechaNacimiento.getText();

            if (id.isBlank() || nombre.isBlank() || apellido.isBlank() || fechaNacimiento.isBlank()) {
                throw new RuntimeException();
            }

            Estudiante estudiante = new Estudiante(id, nombre, apellido, LocalDate.parse(fechaNacimiento));
            app.aplicativo().addEstudiante(estudiante);
            app.estudiantesAdministrador();
            modal.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                app,
                "Error al crear estudiante, verifique la integridad de los campos.",
                "Error.",
                JOptionPane.ERROR_MESSAGE
            );
        }

    }
}
