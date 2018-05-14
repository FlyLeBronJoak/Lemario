package lemarionuevo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Joaquin Sierra
 */
public class LemarioNuevo {

    ArrayList<String> lemario = new ArrayList();

    public void cargaFicheroLemario() {
        File fichero = new File("src/lemarionuevo/lemario-20101017.txt");
        //Array[] arrayFichero = new Array[1];
        try {
            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);
            String linea;

            while ((linea = br.readLine()) != null) {
                linea = limpiarAcentos(linea);
                lemario.add(linea);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(LemarioNuevo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LemarioNuevo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean buscar(String palabra) {
        if (lemario.contains(palabra)) {
            return true;
        }
        return false;
    }

    public boolean escaleraDePalabras(ArrayList<String> escalera) {
        boolean palabra = true;
        boolean palabra2 = true;
        int pal;
        for (int i = 0; i < escalera.size() - 1 && palabra2; i++) {
            palabra = buscar(escalera.get(i));
            if (!palabra) {
                return false;
            }
            pal = 0;
            if (escalera.get(i + 1).length() == escalera.get(i).length()) {
                for (int j = 0; j < escalera.get(i).length(); j++) {
                    if (escalera.get(i).charAt(j) != escalera.get(i + 1).charAt(j)) {
                        pal++;
                    }
                    if (pal > 1) {
                        palabra2 = false;
                    }
                    if (!palabra) {
                        palabra2 = false;
                    }
                }
            }
        }
        return palabra2;
    }

    public static String limpiarAcentos(String cadena) {
        String limpio = null;
        if (cadena != null) {
            String valor = cadena;
            valor = valor.toUpperCase();

            limpio = Normalizer.normalize(valor, Normalizer.Form.NFD);
            limpio = Normalizer.normalize(limpio, Normalizer.Form.NFC);
        }
        return limpio;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<String> listaPalabras = new ArrayList();
        LemarioNuevo ejercicio = new LemarioNuevo();
        ejercicio.cargaFicheroLemario();

        listaPalabras.add("CASA");
        listaPalabras.add("CASO");
        listaPalabras.add("PASO");
        listaPalabras.add("PATO");
        listaPalabras.add("PATA");
        listaPalabras.add("MATA");
        listaPalabras.add("CATA");
        listaPalabras.add("RATA");
        listaPalabras.add("RATO");

        System.out.println(ejercicio.escaleraDePalabras(listaPalabras));
    }
}
