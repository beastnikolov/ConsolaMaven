import org.hibernate.Session;
import sun.rmi.runtime.Log;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class LogActivities {
    private Session _session;
    private boolean logActive;
    private String insertMethod = "";

    public LogActivities(Session _session) {
        this._session = _session;
    }

    public void writeLog(String comando, String parametros) { //Escribe logs a la base de datos
        if (logActive && insertMethod.equals("CLASSIC")) { //Lee el archivo de configuración, si está en CLASSIC las inserta con un statement normal, si no hace un commit cada X logs
            log log = new log();
            log.setLog_texte(comando + " - " + parametros);
            log.setLog_data(new java.sql.Date(System.currentTimeMillis()));
            _session.beginTransaction();
            _session.save(log);
            _session.getTransaction().commit();
            _session.clear();

        } //else if (contLogs > 5){ //Si lo hemos puesto por bloques en la configuración mete los logs de par en par
            /*PreparedStatement pSt = con.getPreparedStatement();
            for (int i = 0; i < logArray.size(); i++) {
                try {
                    pSt.setString(1,logArray.get(i));
                    pSt.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Se han insertado " + logArray.size() + " logs.");
            logArray.clear();

             */
    }

    public void clearLog() {
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
}
