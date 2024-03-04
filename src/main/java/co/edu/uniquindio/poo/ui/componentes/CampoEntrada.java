package co.edu.uniquindio.poo.ui.componentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class CampoEntrada extends JPanel {
    private JTextField campo;
    private String textoMarcador;

    public CampoEntrada() {
        this(new JTextField());
    }

    public CampoEntrada(JTextField campo) {
        this.setLayout(new BorderLayout());
        this.setBorder(new MatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));

        this.asignarComponentes(campo);
        this.asignarListeners();

        this.dispatchEvent(new FocusEvent(this.campo, FocusEvent.FOCUS_GAINED));
    }

    protected void establecerCampo(JTextField campo) {
        this.campo = campo;
    }

    public void setTextoMarcador(String tx) {
        this.textoMarcador = tx;
        this.perdidaFoco();
    }

    public void setText(String text) {
        this.campo.setText(text);
        this.gananciaFoco();
    }

    public void setEnabled(boolean enabled) {
        this.campo.setEnabled(enabled);

        if (! this.campo.isEnabled()) {
            this.campo.setBackground(Color.decode("#f5f5f5"));
        }
    }

    public void gananciaFoco() {
        if (this.campo.getText().equals(this.textoMarcador)) {
            this.campo.setText("");
        }

        this.campo.setForeground(this.getForeground());
    }

    public void perdidaFoco() {
        if (! this.campo.getText().equals("")) return;

        this.campo.setText(this.textoMarcador);
        this.campo.setForeground(Color.LIGHT_GRAY);
    }

    public String getText() {
        String result = this.campo.getText();

        if (result.equals(this.textoMarcador)) {
            return "";
        }

        return result;
    }

    public void setHorizontalAlignment(int alignment) {
        this.campo.setHorizontalAlignment(alignment);
    }

    @Override
    public void setFont(Font font) {
        super.setFont(font);
        if (this.campo != null) this.campo.setFont(font);
    }

    private void asignarComponentes(JTextField campo) {
        this.campo = campo;
        this.campo.setBorder(
            new EmptyBorder(5, 10, 5, 10)
        );

        this.add(campo, BorderLayout.CENTER);
    }

    private void asignarListeners() {
        CampoEntrada self = this;

        this.campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) { self.gananciaFoco(); }

            @Override
            public void focusLost(FocusEvent e) { self.perdidaFoco(); }
        });
    }
}
