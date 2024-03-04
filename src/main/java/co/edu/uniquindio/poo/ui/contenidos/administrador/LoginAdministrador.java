package co.edu.uniquindio.poo.ui.contenidos.administrador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import co.edu.uniquindio.poo.ui.App;
import co.edu.uniquindio.poo.ui.componentes.Boton;
import co.edu.uniquindio.poo.ui.componentes.CampoEntrada;
import co.edu.uniquindio.poo.ui.utilidades.Colores;
import co.edu.uniquindio.poo.ui.utilidades.Cursores;
import co.edu.uniquindio.poo.ui.utilidades.Fuentes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginAdministrador extends JPanel {
    private App app;
    private Boton autenticacionBoton;
    private JLabel cambioLogin;
    private CampoEntrada campoClave;

    public LoginAdministrador(App app) {
        this.app = app;

        this.setSize(800, 600);
        this.setPreferredSize(new Dimension(800, 600));
        this.setMinimumSize(new Dimension(800, 600));
        this.setMaximumSize(new Dimension(800, 600));
        this.setBackground(Colores.PRIMARY);
        this.setLayout(null);

        this.asignarComponentes();
        this.asignarListeners();
    }

    private void asignarComponentes() {
        this.add(this.crearContenedor());
    }

    private JPanel crearContenedor() {
        JPanel contenedor = new JPanel();
        contenedor.setLayout(null);
        contenedor.setBackground(Colores.FONDO);
        contenedor.setSize(600, 200);
        contenedor.setLocation(
            (this.getWidth() - contenedor.getWidth()) / 2,
            (this.getHeight() - contenedor.getHeight()) / 2
        );

        this.asignarComponentesContenedor(contenedor);

        return contenedor;
    }

    private void asignarComponentesContenedor(JPanel contenedor) {
        campoClave = new CampoEntrada(new JPasswordField());
        campoClave.setBounds(50, 52, 500, 40);
        campoClave.setTextoMarcador("Palabra clave de aplicativo");
        campoClave.setBorder(new MatteBorder(0, 0, 2, 0, Colores.PRIMARY));
        campoClave.setFont(Fuentes.PREDETERMINADA);
        campoClave.setHorizontalAlignment(SwingConstants.CENTER);
        // campoIdentificacion.setFont();

        autenticacionBoton = new Boton();
        autenticacionBoton.setText("Autenticar");
        autenticacionBoton.setBounds(50, 110, 500, 40);
        autenticacionBoton.setBackground(Colores.PRIMARY);
        autenticacionBoton.setForeground(Color.WHITE);
        autenticacionBoton.setFont(Fuentes.PREDETERMINADA);
        autenticacionBoton.setCursor(Cursores.POINTER_CURSOR);
        // autenticacionBoton.setHoverBackground(getBackground());

        cambioLogin = new JLabel();
        cambioLogin.setText(" - Estudiante ->");
        cambioLogin.setFont(Fuentes.PREDETERMINADA.deriveFont(Font.ITALIC));
        cambioLogin.setForeground(Colores.PRIMARY);
        cambioLogin.setBounds(240, 155, 120, 20);
        cambioLogin.setHorizontalAlignment(SwingConstants.CENTER);
        cambioLogin.setCursor(Cursores.POINTER_CURSOR);

        contenedor.add(campoClave);
        contenedor.add(autenticacionBoton);
        contenedor.add(cambioLogin);
    }

    private void asignarListeners() {
        LoginAdministrador self = this;

        this.cambioLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { app.loginEstudiante(); }
        });

        this.autenticacionBoton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { self.autenticar(); }
        });
    }

    private void autenticar() {
        String clave = this.campoClave.getText();
        
        if (this.app.aplicativo().comprobarClaveAdministrador(clave)) {
            this.app.asignaturasAdministrador();
        } else {
            JOptionPane.showMessageDialog(
                app,
                "Credenciales incorrectas",
                "Error de autenticacion",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
