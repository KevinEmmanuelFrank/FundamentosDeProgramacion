package RegistroDePersonas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class Persona {

    private String nombre;
    private String apellido;
    private int dni;
    private LocalDate fecha;
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public Persona(String nombre, String apellido, int dni, LocalDate fecha) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fecha = fecha;
    }

    public Persona() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Persona crearPersona() {
        Persona pers = new Persona();
        boolean personaFlag = true;

        String nom = JOptionPane.showInputDialog("Ingrese el nombre");
        String apel = JOptionPane.showInputDialog("Ingrese el apellido");
        String dn = JOptionPane.showInputDialog("Ingrese el DNI (debe tener 8 dígitos sin puntos)");
        String fech = JOptionPane.showInputDialog("Ingrese la fecha de nacimiento (en formato dd/mm/yyyy)\nPor ejemplo: 03/02/1995");

        if (validarDatosIngresados(nom, apel, dn, fech)) {

            int dnI = Integer.parseInt(dn);
            LocalDate fecH = LocalDate.parse(fech, formato);
            pers = new Persona(nom, apel, dnI, fecH);
            personaFlag = validarPersona(pers);

        }
        if (!personaFlag) {

            return pers;
        } else {
            pers.setNombre("");
            return pers;
        }
    }

    public boolean validarPersona(Persona pers) {
        String[] opciones = {"Son Correctos", "Cancelar"};

        boolean flag = true;
        int eleccion = JOptionPane.showOptionDialog(
                null,
                "¿Son correctos los datos ingresados?\n"
                + "Nombre: " + pers.getNombre()
                + "\nApellido: " + pers.getApellido()
                + "\nDni: " + pers.getDni()
                + "\nFecha: " + pers.getFecha().format(formato),
                "Datos Ingresados",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                "Son Correctos"
        );

        switch (eleccion) {
            case 0:
                return false;
            case 1:
                return true;
            default:
                return false;
        }
    }

    private boolean validarDatosIngresados(String nom, String apel, String dn, String fech) {
        boolean validador = true;

        if (nom == null || nom.equals("") || apel == null || apel.equals("")) {
            JOptionPane.showMessageDialog(null, "Nombre o Apellido quedaron vacios, intente nuevamente", "Error", JOptionPane.ERROR_MESSAGE);
            validador = false;
        }

        boolean test = false;
        try {
            int dniTest = Integer.parseInt(dn);
        } catch (Exception e) {
            test = true;
        }
        if (test || (dn.length() != 8)) {
            JOptionPane.showMessageDialog(null, "El DNI se cargo incorrectamente", "Error", JOptionPane.ERROR_MESSAGE);
            validador = false;
        }
        
          boolean test2 = false;
        try {
           LocalDate fecH = LocalDate.parse(fech, formato);
        } catch (Exception e) {
            test2 = true;
        }
        if (test2) {
            JOptionPane.showMessageDialog(null, "La fecha se cargó incorrectamente", "Error", JOptionPane.ERROR_MESSAGE);
            validador = false;
        }
        
        return validador;
    }
}
