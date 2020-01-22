import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;



public class Consola {
    private Input input = new Input();
    private File currentDir;
    private Entrada tractarTexteEntrada;
    private Entrada.TipusEntrada tratarEntrada = null;
    private Entrada.TipusEntrada ultimaEntrada = null;
    private File directoriAnterior = new File("");
    private File configPath = new File("src\\main\\java\\config.txt"); //Configuración del programa
    private LiteralsHib literalsHib = new LiteralsHib(); //Clase para los literales de Hibernate
    private String language; //Lenguaje del programa
    private File auxDirectori;
    private boolean logActive; //Log activo o inactivo
    private Connector con = new Connector();
    private int contadorLogs = 0; // Variable usada para la inserción de logs en modo bloque, al tener X se ejecuta el preparedStatement
    private log log;
    private Session _session;
    private LogActivities logActivities;




    private void init() { // Inicializa el programa
        con.conectarSQL();
        literalsHib.initLiteralDB();
        literalsHib.updateResultSet(language);
        this._session = literalsHib.getLogSession();
        logActivities = new LogActivities(this._session);
        readConfig();
        setLog(logActive);
        currentDir = new File("");
        currentDir = new File(currentDir.getAbsolutePath());
        String escrito;
        String comanda = "";

        System.err.println("Consola Nikolov v1.0");
        while (ultimaEntrada != Entrada.TipusEntrada.EXIT) {
            //log.setContLogs(contadorLogs);
            System.out.println(">> " + currentDir.getAbsolutePath());
            if (comanda.equals("")) {
                escrito = input.cadena();
                tractarTexteEntrada = new Entrada(escrito);
            } else {
                escrito = comanda;
                tractarTexteEntrada = new Entrada(escrito);
                comanda = "";
            }

             switch (tractarTexteEntrada.obtenirTipusEntrada()) {
                 case GOTO:
                     if (currentDir.getPath()=="" ) {
                         auxDirectori = new File(tractarTexteEntrada.obtenirParametres()[0]);
                     } else {
                         if (tractarTexteEntrada.obtenirParametres()[0].contains(":")) {
                             auxDirectori = new File(tractarTexteEntrada.obtenirParametres()[0]);
                        } else {
                             auxDirectori = new File(currentDir.getPath() + currentDir.separator + tractarTexteEntrada.obtenirParametres()[0]);
                         }
                     }
                     if (auxDirectori.exists() && auxDirectori.isDirectory()) {
                        directoriAnterior = currentDir;
                        currentDir = auxDirectori;
                     } else {
                         literalsHib.getLiteralDB("DIR_NOTFOUND");
                     }
                     contadorLogs++;
                     //log.addLogtoArray("GOTO | " + tractarTexteEntrada.obtenirParametres()[0]);s
                     logActivities.writeLog("GOTO",tractarTexteEntrada.obtenirParametres()[0]);
                     break;
                 case GOLAST:
                     auxDirectori = new File(currentDir.getAbsolutePath());
                     currentDir = directoriAnterior;
                     directoriAnterior = currentDir;
                     contadorLogs++;
                     //log.addLogtoArray("GOLAST");
                     logActivities.writeLog("GOLAST","");
                     break;
                 case LIST:
                    String[] listaDirectorios = currentDir.list();
                    if (listaDirectorios.length == 0) {
                        literalsHib.getLiteralDB("DIR_EMPTY");
                    } else {
                        for (int i = 0; i < listaDirectorios.length; i++) {
                            if(currentDir.listFiles()[i].isDirectory()) {
                                System.out.println(listaDirectorios[i] + " >> Carpeta");
                            } else {
                                System.out.println(listaDirectorios[i] + " >> Archivo");
                            }
                        }
                    }
                     contadorLogs++;
                    //log.addLogtoArray("GOLAST");
                     logActivities.writeLog("LIST","");
                    break;
                 case HELP:
                     helpCommand();
                     contadorLogs++;
                     //log.addLogtoArray("HELP" );
                     logActivities.writeLog("HELP","");
                     break;
                 case EXIT:
                     con.commitChanges();
                     contadorLogs++;
                     con.getMessageDB(language,"EXIT");
                     con.closeConnection();
                     System.exit(0);
                     break;
                 case LOG0:
                     if (logActive) {
                         logActivities.setLogActive(false);
                         logActive = false;
                         literalsHib.getLiteralDB("LOG_OFF");
                     }
                     break;
                 case LOG1:
                     if (!logActive) {
                         logActivities.setLogActive(true);
                         logActive = true;
                         literalsHib.getLiteralDB("LOG_ON");
                     }
                     break;
                 case CLEARLOG:
                     con.rollbackChanges();
                     logActivities.clearLog();
                     literalsHib.getLiteralDB("LOG_CLEANED");
                     con.commitChanges();
                     break;
                 case LOG:
                     String mensaje = "";
                     String[] arrayPalabras = escrito.split(" ");

                     for (int i = 1; i < arrayPalabras.length; i++) {
                         mensaje = mensaje + " " + arrayPalabras[i];
                     }
                     contadorLogs++;
                     //log.addLogtoArray("CUSTOMLOG | " + mensaje);
                     logActivities.writeLog("CUSTOMLOG",mensaje);
                     break;
                 case LOAD:
                     break;
             }
        }

    }

    public void setLog(boolean state) { //Activa o desactiva la escritura de logs
        logActivities.setLogActive(state);
    }

    private void readConfig() { //Función para leer la configuración de la consola (config.txt) y asignarle los valores a cada variable
        int cont = 0;
        String[] config = null;
        String st = null;

        try {
            BufferedReader br = new BufferedReader(new FileReader(configPath));
            while ((st = br.readLine()) != null) {
                config = st.split(" ");
                if (cont == 0) {
                    language = config[2];
                } else if (cont == 1) {
                    if (config[2].equals("ON")) {
                        logActive = true;
                    } else {
                        logActive = false;
                    }
                } else if (cont == 2){
                    logActivities.setInsertMethod(config[2]);
                }
                cont++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void helpCommand() { // Funcion del comando help para no meterlo en el bucle principal
        System.out.println("Lista de comandos:");
        System.out.println("---------------------------------------------------");
        System.out.println(">> GOTO - Va al directorio especificado (si existe)");
        System.out.println(">> GOLAST - Va al último directorio visitado");
        System.out.println(">> LIST - Lista los elementos del directorio");
        System.out.println(">> UP - Va al directorio padre");
        System.out.println(">> HELP - Muestra todos los comandos actualmente funcionales");
        System.out.println(">> EXIT - Sale de la consola de comandos");
        System.out.println(">> LOG0 - Desactiva el guardado de logs");
        System.out.println(">> LOG1 - Activa el guardado de logs");
        System.out.println(">> CLEARLOG - Limpia el archivo de logs");
        System.out.println(">> LOG - Crea un log personalizado");
        System.out.println(">> LOAD - Carga un comando desde el archivo load.txt");
        System.out.println("---------------------------------------------------");
    }

    public static void main(String[] args) {
        Consola consola = new Consola();
        consola.init();
    }

}
