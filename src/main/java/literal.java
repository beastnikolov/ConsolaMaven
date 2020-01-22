import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "literal")

public class literal {
    
    @Id
    @Column(name = "lit_id")
    private int lit_id;

    @Column(name = "idi_cod")
    private String idi_cod;

    @Column(name = "lit_clau")
    private String lit_clau;

    @Column(name = "lit_text")
    private String lit_text;

    public literal() {}

    public literal(int lit_id, String idi_cod, String lit_clau, String lit_text) {
        this.setLit_id(lit_id);
        this.setIdi_cod(idi_cod);
        this.setLit_clau(lit_clau);
        this.setLit_text(lit_text);
    }

    public int getLit_id() {
        return lit_id;
    }

    public void setLit_id(int lit_id) {
        this.lit_id = lit_id;
    }

    public String getIdi_cod() {
        return idi_cod;
    }

    public void setIdi_cod(String idi_cod) {
        this.idi_cod = idi_cod;
    }

    public String getLit_clau() {
        return lit_clau;
    }

    public void setLit_clau(String lit_clau) {
        this.lit_clau = lit_clau;
    }

    public String getLit_text() {
        return lit_text;
    }

    public void setLit_text(String lit_text) {
        this.lit_text = lit_text;
    }
}
