import java.sql.*;

public class Connector {
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String database = "consolasquema";
    private String hostname = "localhost";
    private String port = "3306";
    private String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";
    private Connection con;


    public Connection conectarSQL() { //Conexión a la base de datos
        con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url,"root","root");
            con.setAutoCommit(false);
            System.err.println("Connected to mySQL Database");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public void getMessageDB(String language,String messageCode) { //Saca el mensaje deseado de la base de datos con el lenguaje y el código de mensaje
        String query = "SELECT lit_text FROM literal WHERE lit_clau = '" + messageCode + "' AND idi_cod = '" + language +"'";
        Statement st = null;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                System.err.println(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void commitChanges() { //Hace el commit de los cambios a la base de datos
        try {
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollbackChanges() { //Hace un rollback de los cambios realizados
        try {
            con.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() { //Cierra la conexión a la base de datos
        try {
            con.close();
            System.err.println("Connection to mySQL has been closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Statement getStatement() { // Devuelve un Statement para ser utilizado en la clase log
        Statement st = null;
        try {
            st = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return st;
    }

    public PreparedStatement getPreparedStatement() { // Devuelve un PreparedStatement para ser utilizado en la clase log
        String blockquery = "INSERT INTO log (log_texte) VALUES (?)";
        PreparedStatement pSt = null;
        try {
            pSt = con.prepareStatement(blockquery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pSt;
    }

}
