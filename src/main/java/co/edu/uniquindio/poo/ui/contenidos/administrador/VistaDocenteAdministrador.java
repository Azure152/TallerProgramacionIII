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
import co.edu.uniquindio.poo.usuarios.Docente;


public class VistaDocenteAdministrador extends JPanel {
    private App app;
    private Docente docente;
    private Boton eliminarDocenteBtn;
    private Boton modificarDocenteBtn;
    private CampoEntrada modificarDocenteId;
    private CampoEntrada modificarDocenteNombre;
    private CampoEntrada modificarDocenteApellido;

    public VistaDocenteAdministrador(App app, Docente docente) {
        this.app = app;
        this.docente = docente;

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
        
        JLabel titulo = new JLabel("Docente");
        titulo.setBounds(20, 20, 560, 20);
        titulo.setFont(Fuentes.PREDETERMINADA.deriveFont(20f).deriveFont(Font.BOLD));

        CampoEntrada identificacion = new CampoEntrada();
        identificacion.setBounds(20, 60, 560, 40);
        identificacion.setTextoMarcador("identificacion");
        identificacion.setText(this.docente.getIdentificacion());
        identificacion.setEnabled(false);

        CampoEntrada nombre = new CampoEntrada();
        nombre.setBounds(20, 110, 560, 40);
        nombre.setTextoMarcador("Nombre(s)");
        nombre.setText(this.docente.getNombre());

        CampoEntrada apellido = new CampoEntrada();
        apellido.setBounds(20, 160, 560, 40);
        apellido.setTextoMarcador("Apellido(s)");
        apellido.setText(this.docente.getApellido());

        Boton modificar = new Boton();
        modificar.setText("Modificar");
        modificar.setBounds(20, 210, 180, 40);
        modificar.setCursor(Cursores.POINTER_CURSOR);
        modificar.setBackground(Colores.PRIMARY);
        modificar.setForeground(Color.WHITE);

        Boton eliminar = new Boton();
        eliminar.setText("Eliminar");
        eliminar.setBounds(210, 210, 180, 40);
        eliminar.setCursor(Cursores.POINTER_CURSOR);
        eliminar.setBackground(Colores.PELIGRO);
        eliminar.setForeground(Color.WHITE);

        Object[][] datosTabla = app.aplicativo()
            .getDocenteAsignaturas(docente.getIdentificacion())
            .stream().map(a -> new String[] {a.getCodigo(), a.getNombre()})
            .toList().toArray(new String[][] {});

        String[] columnas =  new String[] {"Codigo", "Nombre"};
        
        JTable asignaturas = new JTable(datosTabla, columnas);
        asignaturas.setDefaultEditor(Object.class, null);

        JScrollPane contenedorTabla = new JScrollPane(asignaturas);
        contenedorTabla.setBounds(20, 260, 560, 320);

        panel.add(titulo);
        panel.add(identificacion);
        panel.add(nombre);
        panel.add(apellido);
        panel.add(modificar);
        panel.add(eliminar);
        panel.add(contenedorTabla);

        modificarDocenteId = identificacion;
        modificarDocenteNombre = nombre;
        modificarDocenteApellido = apellido;
        modificarDocenteBtn = modificar;
        eliminarDocenteBtn = eliminar;

        return panel;
    }

    private void asignarListeners() {
        modificarDocenteBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { modificarDocente(); }
        });

        eliminarDocenteBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { eliminarDocente(); }
        });
    }

    private void modificarDocente() {
        try {
            String id = modificarDocenteId.getText();
            String nombre = modificarDocenteNombre.getText();
            String apellido = modificarDocenteApellido.getText();

            if (id.isBlank() || nombre.isBlank() || apellido.isBlank()) {
                throw new RuntimeException();
            }

            Docente docente = app.aplicativo().obtenerDocente(id);
            docente.setNombre(nombre);
            docente.setApellido(apellido);

            app.mostrarDocenteAdministrador(docente);

            JOptionPane.showMessageDialog(
                app,
                "La informacion del docente ha sido actualizada.",
                "Informacion actualizada",
                JOptionPane.INFORMATION_MESSAGE
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                app,
                "Error al modificar docente, verifique la integridad de los campos.",
                "Error.",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void eliminarDocente() {
        try {
            String id = modificarDocenteId.getText();

            app.aplicativo().removerDocente(id);
            app.docentesAdministrador();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                app,
                "Ocurrio un error al remover al docente, puede que no se encuentre registrado",
                "Error.",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
