package co.edu.uniquindio.poo.ui.contenidos.administrador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
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
import co.edu.uniquindio.poo.ui.App;
import co.edu.uniquindio.poo.ui.componentes.Boton;
import co.edu.uniquindio.poo.ui.componentes.CampoEntrada;
import co.edu.uniquindio.poo.ui.utilidades.Colores;
import co.edu.uniquindio.poo.ui.utilidades.Cursores;
import co.edu.uniquindio.poo.ui.utilidades.Fuentes;

public class AsignaturasAdministador extends JPanel {
    private App app;
    private Boton crearAsignaturaBtn;
    private JDialog crearAsignaturaModal;
    private Boton crearAsignaturaModalBtn;
    private CampoEntrada crearAsignaturaNombre;
    private CampoEntrada crearAsignaturaCodigo;
    private CampoEntrada crearAsignaturaCapacidad;
    private JCheckBox crearAsignaturaHabilitable;
    // private 

    public AsignaturasAdministador(App app) {
        this.app = app;

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

        var campoBusqueda = new CampoEntrada();
        campoBusqueda.setTextoMarcador("Busca por nombre de la asignatura");
        campoBusqueda.setSize(400, 40);
        campoBusqueda.setLocation(20, 20);
        campoBusqueda.setFont(Fuentes.PREDETERMINADA);
        campoBusqueda.setBackground(null);

        var botonBusqueda = new Boton();
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

        crearAsignaturaBtn = new Boton();
        crearAsignaturaBtn.setBounds(20, 100, 560, 50);
        crearAsignaturaBtn.setText("+ crear asignatura");
        crearAsignaturaBtn.setBackground(Colores.PRIMARY);
        crearAsignaturaBtn.setForeground(Color.WHITE);
        crearAsignaturaBtn.setFont(Fuentes.PREDETERMINADA);
        crearAsignaturaBtn.setCursor(Cursores.POINTER_CURSOR);

        JPanel listaAsignaturas = this.crearListaAsignaturas();

        panel.add(campoBusqueda);
        panel.add(botonBusqueda);
        panel.add(sep);
        panel.add(crearAsignaturaBtn);
        panel.add(listaAsignaturas);

        return panel;
    }

    private JPanel crearListaAsignaturas() {
        JPanel lista = new JPanel();
        lista.setBounds(0, 180, 600, 420);
        lista.setBackground(null);
        lista.setLayout(new BorderLayout());

        JPanel elementos = new JPanel();
        elementos.setLayout(new BoxLayout(elementos, BoxLayout.PAGE_AXIS));
        elementos.setBackground(Colores.FONDO);

        for (Asignatura asg: this.app.aplicativo().asignaturas()) {
            elementos.add(this.crearElementoAsignatura(asg));
        }

        JScrollPane scrollPane = new JScrollPane(elementos);
        scrollPane.setBorder(null);
        scrollPane.setBackground(null);

        lista.add(scrollPane, BorderLayout.CENTER);

        return lista;
    }

    private JPanel crearElementoAsignatura(Asignatura asg) {
        Dimension dimension = new Dimension(550, 80);
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
        cabecera.setBounds(10, 10, 400, 20);
        cabecera.setLayout(new BoxLayout(cabecera, BoxLayout.LINE_AXIS));
        cabecera.setBackground(null);

        var nombre = new JLabel(asg.getNombre());
        nombre.setFont(contenido.getFont().deriveFont(16f).deriveFont(Font.BOLD));
        nombre.setPreferredSize(new Dimension(0, 0));
        nombre.setHorizontalAlignment(SwingConstants.LEFT);

        var codigo = new JLabel(asg.getCodigo());
        codigo.setFont(contenido.getFont().deriveFont(12f));
        codigo.setForeground(Color.GRAY);

        var cupos = new JLabel(asg.calcularCuposUsados() + "/" + asg.getCupos());
        cupos.setBounds(475, 5, 55, 20);
        cupos.setHorizontalAlignment(SwingConstants.RIGHT);
        cupos.setFont(contenido.getFont());

        var docente = new JLabel(asg.getDocente().isPresent() ? asg.getDocente().get().getNombre() : "N/A");
        docente.setBounds(10, 30, 460, 15);
        docente.setVerticalAlignment(SwingConstants.CENTER);
        docente.setForeground(Color.GRAY);
        docente.setFont(contenido.getFont());

        var apertura = new JLabel();
        apertura.setText(asg.isAbierta() ? "Abierta" : "Cerrada");
        apertura.setBounds(10, 45, 460, 20);
        apertura.setForeground(apertura.getText().equals("Abierta") ? Color.decode("#2fcf5f") : Color.RED);

        cabecera.add(nombre);
        cabecera.add(Box.createHorizontalStrut(5));
        cabecera.add(codigo);
        contenido.add(cabecera);
        contenido.add(docente);
        contenido.add(apertura);
        contenido.add(cupos);
        contenedor.add(contenido, BorderLayout.CENTER);

        this.asignarListenersAsignatura(contenido, asg);

        return contenedor;
    }

    private void asignarListenersAsignatura(JPanel asig, Asignatura asg) {
        AsignaturasAdministador self = this;

        asig.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                app.mostrarAsignaturaAdministrador(asg);
            }
        });
    }

    public void asignarListeners() {
        AsignaturasAdministador self = this;

        crearAsignaturaBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                self.crearAsignaturaModal();
            }
        });
    }

    public void crearAsignaturaModal() {
        JDialog modal = new JDialog(this.app, "Crear asignatura", true);
        modal.setSize(500, 300);
        modal.setLocationRelativeTo(null);
        modal.setLayout(new BorderLayout());

        JPanel contenido = new JPanel();
        contenido.setBackground(Colores.FONDO);
        contenido.setLayout(null);

        CampoEntrada campoNombre = new CampoEntrada();
        campoNombre.setBounds(20, 30, 440, 30);
        campoNombre.setTextoMarcador("Nombre de la asignatura");

        CampoEntrada campoCodigo = new CampoEntrada();
        campoCodigo.setBounds(20, 70, 440, 30);
        campoCodigo.setTextoMarcador("Codigo");

        CampoEntrada campoCapacidad = new CampoEntrada();
        campoCapacidad.setBounds(20, 110, 440, 30);
        campoCapacidad.setTextoMarcador("Capacidad");

        JLabel labelHabilitable = new JLabel();
        labelHabilitable.setText("Es habilitable: ");
        labelHabilitable.setBounds(20, 150, 90, 30);

        JCheckBox habilitableCheckBox = new JCheckBox();
        habilitableCheckBox.setBounds(110, 150, 20, 30);
        habilitableCheckBox.setBackground(null);

        Boton crear = new Boton();
        crear.setBounds(20, 190, 440, 40);
        crear.setBackground(Colores.PRIMARY);
        crear.setText("Crear");
        crear.setFont(Fuentes.PREDETERMINADA);
        crear.setForeground(Color.WHITE);
        crear.setFocusable(true);
        crear.setCursor(Cursores.POINTER_CURSOR);

        contenido.add(campoNombre);
        contenido.add(campoCodigo);
        contenido.add(campoCapacidad);
        contenido.add(labelHabilitable);
        contenido.add(habilitableCheckBox);
        contenido.add(crear);
        modal.add(contenido, BorderLayout.CENTER);

        crearAsignaturaModalBtn = crear;
        crearAsignaturaNombre = campoNombre;
        crearAsignaturaCodigo = campoCodigo;
        crearAsignaturaCapacidad = campoCapacidad;
        crearAsignaturaHabilitable = habilitableCheckBox;
        crearAsignaturaModal = modal;

        this.asignarModalListeners();

        modal.setVisible(true);
    }

    private void asignarModalListeners() {
        crearAsignaturaModalBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { crearAsignatura(); }
        });
    }

    private void crearAsignatura() {
        try {
            String nombre = crearAsignaturaNombre.getText();
            String codigo = crearAsignaturaCodigo.getText();
            int capacidad = Integer.valueOf(crearAsignaturaCapacidad.getText());
            boolean habilitable = crearAsignaturaHabilitable.isSelected();

            Asignatura asg = new Asignatura(nombre, codigo, capacidad, habilitable);
            app.aplicativo().addAsignatura(asg);
            app.asignaturasAdministrador();
            crearAsignaturaModal.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                app,
                "Error al crear la asignatura, verifique la integridad de los campos.",
                "Error.",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}