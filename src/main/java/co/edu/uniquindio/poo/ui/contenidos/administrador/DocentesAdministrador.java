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
import co.edu.uniquindio.poo.usuarios.Docente;

public class DocentesAdministrador extends JPanel {
    private App app;
    private Boton crearDocente;
    private Boton crearDocenteModalBtn;
    private CampoEntrada crearDocenteIdentificacion;
    private CampoEntrada crearDocenteNombre;
    private CampoEntrada crearDocenteApellido;
    private JDialog crearDocenteModal;
    
    public DocentesAdministrador(App app) {
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

        var sidebarEl2 = new JLabel();
        sidebarEl2.setText("Estudiantes");
        sidebarEl2.setFont(Fuentes.PREDETERMINADA);
        sidebarEl2.setBorder(new EmptyBorder(10, 15, 10, 10));

        var sidebarEl3 = new JLabel();
        sidebarEl3.setText("Docentes");
        sidebarEl3.setFont(Fuentes.PREDETERMINADA);
        sidebarEl3.setBorder(new EmptyBorder(10, 15, 10, 10));
        sidebarEl3.setForeground(Colores.PRIMARY);

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
        campoBusqueda.setTextoMarcador("Buscar docente por identificacion");
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

        crearDocente = new Boton();
        crearDocente.setBounds(20, 100, 560, 50);
        crearDocente.setText("+ Crear docente");
        crearDocente.setBackground(Colores.PRIMARY);
        crearDocente.setForeground(Color.WHITE);
        crearDocente.setFont(Fuentes.PREDETERMINADA);
        crearDocente.setCursor(Cursores.POINTER_CURSOR);

        JPanel listaDocentes = this.crearListaDocentes();

        panel.add(campoBusqueda);
        panel.add(botonBusqueda);
        panel.add(sep);
        panel.add(crearDocente);
        panel.add(listaDocentes);

        return panel;
    }

    private JPanel crearListaDocentes() {
        JPanel lista = new JPanel();
        lista.setBounds(0, 180, 600, 420);
        lista.setBackground(null);
        lista.setLayout(new BorderLayout());

        JPanel elementos = new JPanel();
        elementos.setLayout(new BoxLayout(elementos, BoxLayout.PAGE_AXIS));
        elementos.setBackground(Colores.FONDO);

        // for (int i = 0; i < 5; i++) {
        //     elementos.add(this.crearElementoDocente());
        // }

        for (Docente docente: this.app.aplicativo().docentes()) {
            elementos.add(this.crearElementoDocente(docente));
        }

        JScrollPane scrollPane = new JScrollPane(elementos);
        scrollPane.setBorder(null);
        scrollPane.setBackground(null);

        lista.add(scrollPane, BorderLayout.CENTER);

        return lista;
    }

    private JPanel crearElementoDocente(Docente docente) {
        Dimension dimension = new Dimension(550, 55);
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
        cabecera.setBounds(10, 10, 520, 30);
        cabecera.setLayout(new BoxLayout(cabecera, BoxLayout.LINE_AXIS));
        cabecera.setBackground(null);

        var nombre = new JLabel(docente.getNombreCompleto());
        nombre.setFont(contenido.getFont().deriveFont(17f));
        nombre.setHorizontalAlignment(SwingConstants.LEFT);
        nombre.setVerticalAlignment(SwingConstants.CENTER);
        nombre.setPreferredSize(new Dimension(540, 30));

        var identificacion = new JLabel(docente.getIdentificacion());
        identificacion.setForeground(Color.GRAY);
        identificacion.setFont(contenido.getFont().deriveFont(12f));
        identificacion.setHorizontalAlignment(SwingConstants.RIGHT);
        identificacion.setVerticalAlignment(SwingConstants.CENTER);

        cabecera.add(nombre);
        cabecera.add(Box.createHorizontalStrut(5));
        cabecera.add(identificacion);
        contenido.add(cabecera);
        // contenido.add(promedio);
        contenedor.add(contenido, BorderLayout.CENTER);

        this.asignarListenersDocente(contenido, docente);

        return contenedor;
    }

    private void asignarListenersDocente(JPanel doc, Docente docente) {
        var self = this;

        doc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                app.mostrarDocenteAdministrador(docente);
            }
        });
    }

    private void asignarListeners() {
        var self = this;

        crearDocente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { self.modalCrearDocente(); }
        });

    }

    private void modalCrearDocente() {
        JDialog modal = new JDialog(this.app, "Crear Docente", true);
        modal.setSize(500, 250);
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

        Boton crear = new Boton();
        crear.setBounds(20, 150, 440, 40);
        crear.setBackground(Colores.PRIMARY);
        crear.setText("Crear");
        crear.setFont(Fuentes.PREDETERMINADA);
        crear.setForeground(Color.WHITE);
        crear.setFocusable(true);
        crear.setCursor(Cursores.POINTER_CURSOR);

        crearDocenteNombre = campoNombre;
        crearDocenteApellido = campoApellido;
        crearDocenteIdentificacion = campoIdentificacion;
        crearDocenteModalBtn = crear;
        crearDocenteModal = modal;

        contenido.add(campoIdentificacion);
        contenido.add(campoNombre);
        contenido.add(campoApellido);
        contenido.add(crear);
        modal.add(contenido, BorderLayout.CENTER);

        this.asignarListenersModal();

        modal.setVisible(true);
    }

    private void asignarListenersModal() {
        crearDocenteModalBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                crearDocenteAplicativo();
            }
        });
    }

    private void crearDocenteAplicativo() {
        try {
            String id = this.crearDocenteIdentificacion.getText();
            String nombre = this.crearDocenteNombre.getText();
            String apellido = this.crearDocenteApellido.getText();

            if (id.isBlank() || nombre.isBlank() || apellido.isBlank()) {
                throw new RuntimeException();
            }

            Docente docente = new Docente(id, nombre, apellido);
            this.app.aplicativo().addDocente(docente);
            this.crearDocenteModal.dispose();
            this.app.docentesAdministrador();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                app,
                "Error al crear docente, verifique la integridad de los campos.",
                "Error.",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
