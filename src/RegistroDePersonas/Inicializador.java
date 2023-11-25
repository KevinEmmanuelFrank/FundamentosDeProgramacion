package RegistroDePersonas;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Inicializador {

    public void iniciar() {
        List<Persona> miLista = new ArrayList<>();
        Persona persona = new Persona();
        boolean flag = true;
        boolean carga = false;
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String[] opciones = {"Ingresar Datos", "Mostrar Datos", "Salir"};

        while (flag) {
            int eleccion = JOptionPane.showOptionDialog(
                    null,
                    "¿Qué acción desea realizar?",
                    "Registro de Personas",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    "Ingresar Datos"
            );

            switch (eleccion) {
                case 0:
                    persona = persona.crearPersona();
                    if (!(persona.getNombre().equals(""))) {
                        miLista.add(persona);
                        carga = true;
                    }
                    break;
                case 1:
                    if (carga) {
                        StringBuilder mensaje = new StringBuilder("Información de Personas:\n");
                        for (Persona lista : miLista) {
                            mensaje.append("Nombre: ").append(lista.getNombre())
                                    .append(" - Apellido: ").append(lista.getApellido())
                                    .append(" - DNI: ").append(lista.getDni())
                                    .append(" - Fecha: ").append(lista.getFecha().format(formato)).append("\n");
                        }

                        JOptionPane.showMessageDialog(null, mensaje.toString(), "Lista de Personas", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Aún no se cargaron datos", "Lista de Personas", JOptionPane.ERROR_MESSAGE);

                    }
                    break;

                case 2:
                    System.exit(0);
                    flag = false;
                    break;

                default:
                    flag = false;
                    break;
            }
        }
    }
}
