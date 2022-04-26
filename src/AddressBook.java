import java.io.*;
import java.util.*;

public class AddressBook {
    public static void main(String[] args) {
        HashMap<String, Object> myMapAddressBook = new HashMap<>(); // HashMap para guardar los datos en la agenda

        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion;
        load(myMapAddressBook);
        while (!salir) { //menu

            System.out.println("1. Mostrar los contactos en la agenda");
            System.out.println("2. Registrar nuevo contacto");
            System.out.println("3. Eliminar un contacto");
            System.out.println("4. Salir");

            try {
                System.out.println("Elija una opcion");
                opcion = sn.nextInt();

                switch (opcion) {
                    case 1 -> list(myMapAddressBook); // mostrar los contactos de la agenda
                    case 2 -> create(myMapAddressBook); // crear un nuevo contacto
                    case 3 -> delete(myMapAddressBook); // borrar un contacto
                    case 4 -> {
                        save(myMapAddressBook);
                        salir = true;
                    }
                    default -> System.out.println("Elija una opcion valida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Elija una opcion valida");
                sn.next();
            }
        }
    }

    // delete: borrar un contacto
    public static void delete(HashMap<String, Object> myMapAddressBook){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String telefono = null;
        System.out.println("Ingrese el teléfono:");

        try {
            telefono = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        myMapAddressBook.remove(telefono);
    }

    // create: crear un nuevo contacto
    public static void create(HashMap<String, Object> myMapAddressBook){

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String telefono = null;
        String nombre = null;
        System.out.println("Ingrese el teléfono:");

        try {
            telefono = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Ingrese el nombre del contacto:");
        try {
            nombre = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(telefono != null && nombre != null)
            myMapAddressBook.put(telefono, nombre);
    }

    //list: mostrar los contactos de la agenda
    public static void list(HashMap<String, Object> myMapAddressBook) {
        System.out.println("\n ******Agenda******");
        for (Map.Entry<String, Object> entry : myMapAddressBook.entrySet()) {
            String output = String.format("%s:%s", entry.getKey(), entry.getValue());
            System.out.println(output);
        }
    }

    // save: guardar los cambios en el archivo
    public static void save(HashMap<String, Object> myMapAddressBook){
        String outputFilename = "src/archivos/salida.txt"; //almacenamiento de información en archivo de texto
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(outputFilename));
            for (Map.Entry<String, Object> entry : myMapAddressBook.entrySet()) {
                String output = String.format("%s,%s", entry.getKey(), entry.getValue() + "\r\n");
                bufferedWriter.write(output);
            }
        } catch(IOException e) {
            System.out.println("Programa detenido debido a una excepcion" + e.getMessage());
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                System.out.println("Programa detenido debido a una excepcion" + e.getMessage());
            }
        }
    }

    // load: carga los contactos del archivo
    public static void load(HashMap<String, Object> myMapAddressBook){
        String inputFilename = "src/archivos/entrada.txt"; //lectura de información en archivo de texto
        BufferedReader bufferedReader = null;
        String Numero;
        String Nombre;
        try {
            bufferedReader = new BufferedReader(new FileReader(inputFilename));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                int coma = line.indexOf(',');
                Numero = line.substring(0, coma);
                Nombre = line.substring(coma+1);
                myMapAddressBook.put(Numero, Nombre);
            }
        } catch(IOException e) {
            System.out.println("Programa detenido debido a una excepcion" + e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                System.out.println("Programa detenido debido a una excepcion" + e.getMessage());
            }
        }
    }
}
