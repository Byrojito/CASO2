import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Opcion1 {

    public void opcion1(String archivo1, String... archivos) {
        // Unimos el primer archivo con el resto para procesarlos juntos
        int tamanoParametro = archivos.length + 1;
        String[] todosLosArchivos = new String[tamanoParametro];
        todosLosArchivos[0] = archivo1;
        System.arraycopy(archivos, 0, todosLosArchivos, 1, archivos.length);

        // Arrays para guardar los datos
        int[] tamanoPagina = new int[tamanoParametro];
        int[] numFilas = new int[tamanoParametro];
        int[] numColum = new int[tamanoParametro];
        int[] numReferencias = new int[tamanoParametro];
        int[] numPaginas = new int[tamanoParametro];

        System.out.println("Iniciando la lectura de archivos de configuración...\n");

        // --- Lectura del primer archivo (archivo1) ---
        System.out.println("== Leyendo archivo principal: " + archivo1 + " ==");
        try (BufferedReader br = new BufferedReader(new FileReader(archivo1))) {
            String linea;
            int contadorLineas = 0;
            while ((linea = br.readLine()) != null && contadorLineas < 5) {
                String[] partes = linea.split(":");
                if (partes.length == 2) {
                    String clave = partes[0].trim();
                    int valor = Integer.parseInt(partes[1].trim());

                    if ("TP".equals(clave)) {
                        tamanoPagina[0] = valor;
                    } else if ("NF".equals(clave)) {
                        numFilas[0] = valor;
                    } else if ("NC".equals(clave)) {
                        numColum[0] = valor;
                    } else if ("NR".equals(clave)) {
                        numReferencias[0] = valor;
                    } else if ("NP".equals(clave)) {
                        numPaginas[0] = valor;
                    }
                    contadorLineas++;
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al leer el archivo " + archivo1 + ": " + e.getMessage());
        }

        // --- Lectura de los archivos adicionales (archivos) ---
        for (int i = 1; i < tamanoParametro; i++) {
            System.out.println("\n== Leyendo archivo adicional: " + todosLosArchivos[i] + " ==");
            try (BufferedReader br = new BufferedReader(new FileReader(todosLosArchivos[i]))) {
                String linea;
                int contadorLineas = 0;
                while ((linea = br.readLine()) != null && contadorLineas < 5) {
                    String[] partes = linea.split(":");
                    if (partes.length == 2) {
                        String clave = partes[0].trim();
                        int valor = Integer.parseInt(partes[1].trim());

                        if ("TP".equals(clave)) {
                            tamanoPagina[i] = valor;
                        } else if ("NF".equals(clave)) {
                            numFilas[i] = valor;
                        } else if ("NC".equals(clave)) {
                            numColum[i] = valor;
                        } else if ("NR".equals(clave)) {
                            numReferencias[i] = valor;
                        } else if ("NP".equals(clave)) {
                            numPaginas[i] = valor;
                        }
                        contadorLineas++;
                    }
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error al leer el archivo " + todosLosArchivos[i] + ": " + e.getMessage());
            }
        }

    }
}