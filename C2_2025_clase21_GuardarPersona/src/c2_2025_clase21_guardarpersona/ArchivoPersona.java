/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c2_2025_clase21_guardarpersona;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class ArchivoPersona {

    private static final String RUTA = "persona.dat";

    public static void guardar(Persona p) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA));
        oos.writeObject(p);
        oos.close();
    }

    public static Persona cargar() throws IOException, ClassNotFoundException {
        File f = new File(RUTA);
        if (!f.exists()) return null;

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
        Persona p = (Persona) ois.readObject();
        ois.close();
        return p;
    }
}
