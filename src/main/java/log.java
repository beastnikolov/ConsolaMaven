import org.hibernate.Session;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

@Entity
@Table(name = "log")

public class log {

    @Id
    @Column(name = "log_id")
    private int log_id;

    @Column(name = "log_texte")
    private String log_texte;

    @Column(name = "log_data")
    private Date log_data;

    public log() {}

    public log(int log_id, String log_texte, Date log_data) {
        this.setLog_id(log_id);
        this.setLog_texte(log_texte);
        this.setLog_data(log_data);

    }

    public int getLog_id() {
        return log_id;
    }

    public void setLog_id(int log_id) {
        this.log_id = log_id;
    }

    public String getLog_texte() {
        return log_texte;
    }

    public void setLog_texte(String log_texte) {
        this.log_texte = log_texte;
    }

    public Date getLog_data() {
        return log_data;
    }

    public void setLog_data(Date log_data) {
        this.log_data = log_data;
    }
}
