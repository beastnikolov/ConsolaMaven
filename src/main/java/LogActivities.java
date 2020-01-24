import org.hibernate.Session;
import sun.rmi.runtime.Log;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LogActivities {
    private Session _session;
    private boolean logActive;
    private String insertMethod = "";
    private int contLogs = 0;
    private ArrayList<String > logArray = new ArrayList<>();

    public LogActivities(Session _session) {
        this._session = _session;
    }

    public void writeLog(String comando, String parametros) { //Escribe logs a la base de datos
        if (logActive && insertMethod.equals("CLASSIC")) { //Lee el archivo de configuración, si está en CLASSIC las inserta con cada comando ejecutado
            log log = new log();
            log.setLog_texte(comando + " - " + parametros);
            log.setLog_data(new java.sql.Date(System.currentTimeMillis()));
            _session.beginTransaction();
            _session.save(log);
            _session.getTransaction().commit();
            _session.clear();

        }
    }

    public void writeLogBLOCK() { // Si el método de inserción está puesto en bloques, inserta un bloque entero de logs configurado desde el fichero config.txt con un array que se carga con los logs a guardar
        for (int i = 0; i < logArray.size(); i++) {
            log log = new log();
            log.setLog_texte(logArray.get(i));
            log.setLog_data(new java.sql.Date(System.currentTimeMillis()));
            _session.beginTransaction();
            _session.save(log);
            _session.flush();
            _session.evict(log);
            _session.getTransaction().commit();
            _session.clear();
        }
        System.err.println("Se han insertado " + logArray.size() + " logs.");
        logArray.clear();
    }

    public void clearLog() { // Limpia los logs de la tabla logs
        _session.beginTransaction();
        _session.createSQLQuery("truncate table log").executeUpdate();
        _session.getTransaction().commit();
        _session.clear();
    }

    public boolean isLogActive() {
        return logActive;
    }

    public void setLogActive(boolean logActive) {
        this.logActive = logActive;
    }

    public String getInsertMethod() {
        return insertMethod;
    }

    public void setInsertMethod(String insertMethod) {
        this.insertMethod = insertMethod;
    }

    public void setContLogs(int contLogs) { //Contador de número de logs que se insertarán
        this.contLogs = contLogs;
    }

    public void addLogtoArray(String logMessage) { //Añade los mensajes de los logs a un array que después se utilizará para la inserción de logs en bloque
        logArray.add(logMessage);
    }
}
