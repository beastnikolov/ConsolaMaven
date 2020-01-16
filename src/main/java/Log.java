import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Log {
    private Connector con;
    private boolean logActive;
    private String insertMethod;
    private ArrayList<String > logArray = new ArrayList<String>();
    private int contLogs = 0;

    public Log(Connector con) {
        this.con = con;
    }

    public void writeLog(String comando, String parametros) { //Escribe logs a la base de datos
        if (logActive && insertMethod.equals("CLASSIC")) { //Lee el archivo de configuración, si está en CLASSIC las inserta con un statement normal, si no hace un preparedStatement cada X logs
            String query = "INSERT INTO log (log_texte) VALUES ('" + comando + " | " + parametros + "')";
            Statement st = null;
            st = con.getStatement();
            if ( st != null) {
                try {
                    st.executeUpdate(query);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else if (contLogs > 5){ //Si lo hemos puesto por bloques en la configuración mete los logs de par en par
            PreparedStatement pSt = con.getPreparedStatement();
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
        }
    }

    public void setLogActive(boolean logActive) { //Pone la escritura de logs en true
        this.logActive = logActive;
    }

    public void clearLog() { //Se usa para limpiar todos los logs de la tabla
        String query = "DELETE FROM log;";
        Statement st = null;
        st = con.getStatement();
        if (st != null) {
            try {
                st.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void setInsertMethod(String insertMethod) { //Pone el método de inserción de logs, si en bloques o normal
        this.insertMethod = insertMethod;
    }

    public void setContLogs(int contLogs) { //Contador de número de logs que se insertarán
        this.contLogs = contLogs;
    }

    public void addLogtoArray(String logMessage) { //Añade los mensajes de los logs a un array que después se utilizará para la inserción de logs en bloque
        logArray.add(logMessage);
    }
}
