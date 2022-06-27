package EX1;

/*Executa l'exercici 3 del nivell anterior parametritzant
tots els mètodes en un fitxer de configuració.

Pots utilitzar un fitxer Java Properties, o bé la
llibreria Apache Commons Configuration si ho prefereixes.

De l'exercici anterior, parametritza el següent:

Directori a llegir.
Nom i directori del fitxer TXT resultant.
*/

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class App {

    public static void main(String[] args) throws IOException {

        //Creo un objeto Properties para poder acceder a la información del archivo
        Properties properties = new Properties();

        //Accedo a través de getProperty a la información del archivo
        try{
            FileWriter fileWriter = new FileWriter( properties.getProperty("Directorio_Archivo","/Users/Rosario/Pruebas/Ex1N3.txt" ));
            listFiles(properties.getProperty("Directorio","/Users/Rosario/Pruebas" ),fileWriter);
            fileWriter.close();
        } catch(Exception e){
            System.out.println("error");
        }
    }

    //////////FUNCIONES
    private static void writeFileTxt(String dataFile, FileWriter fileWriter) throws IOException {
        fileWriter.write(dataFile + "\n");
    }

    private static File[] listAlphabetical(String namePath) {
        File file = new File(namePath);
        File[] files = file.listFiles();
        Arrays.sort(files);
        return files;
    }

    public static void listFiles(String pathName, FileWriter fileWriter) throws IOException {
        //Llamada al método que ordena alfabéticamente.
        File[] files = listAlphabetical(pathName);
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (!file.isDirectory()) {
                    //Llamada al método que escribe el archivo.
                    writeFileTxt("F "+ file.getName() + printDate(file), fileWriter);
                } else {
                    writeFileTxt("D "+ file.getName() + printDate(file), fileWriter);
                    //recursividad
                    listFiles(file.getAbsolutePath(), fileWriter);
                }
            }
        }
    }

    public static String printDate(File file) {
        long lastModified = file.lastModified();
        Date date = new Date(lastModified);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        String day = Integer.toString(calendar.get(Calendar.DATE));
        String month = Integer.toString(calendar.get(Calendar.MONTH));
        String year = Integer.toString(calendar.get(Calendar.YEAR));
        return " Fecha de la última modificación " + day + "/" + month + "/" + year;
    }
}
