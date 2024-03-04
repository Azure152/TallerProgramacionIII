package co.edu.uniquindio.poo.ui.componentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Boton extends JPanel {
    private JLabel texto;
    private Color fondoColor;
    private Color textoColor;
    private Color hoverColor;
    private Color hoverTextoColor;

    public Boton() {
        this.setLayout(new BorderLayout());

        this.texto = new JLabel();
        this.fondoColor = Color.LIGHT_GRAY;
        this.textoColor = Color.BLACK;
        this.texto.setForeground(this.textoColor);
        this.texto.setHorizontalAlignment(SwingConstants.CENTER);
        this.texto.setVerticalAlignment(SwingConstants.CENTER);

        this.asignarComponentes();
        this.asignarListeners();

        this.mouseFuera();
    }

    public Boton(String texto) {
        this();
        this.texto.setText(texto);
    }

    public void setText(String txt) {
        this.texto.setText(txt);
    }

    private void asignarComponentes() {
        this.add(this.texto, BorderLayout.CENTER);
    }

    private void asignarListeners() {
        Boton self = this;

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { self.mouseDentro(); }

            @Override
            public void mouseExited(MouseEvent e) { self.mouseFuera(); }
        });
    }
    
    private void mouseDentro() {
        if (this.hoverColor != null) super.setBackground(this.hoverColor);

        if (this.hoverTextoColor != null) {
            this.texto.setForeground(this.hoverTextoColor);
        }
    }

    private void mouseFuera() {
        super.setBackground(this.fondoColor);
        this.texto.setForeground(this.textoColor);
    }

    @Override
    public void setFont(Font font) {
        super.setFont(font);
        if (this.texto != null) this.texto.setFont(font);
    }

    @Override
    public void setForeground(Color fg) {
        super.setForeground(fg);
        this.textoColor = fg;
        if (this.texto != null) this.texto.setForeground(fg);
    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        this.fondoColor = bg;
    }

    public void setHoverBackground(Color bg) {
        this.hoverColor = bg;
    }

    public void setHoverForeground(Color fg) {
        this.hoverTextoColor = fg;
    }
}
