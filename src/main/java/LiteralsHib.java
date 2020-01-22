import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class LiteralsHib {
    private Session _session;
    private List<literal> result;

    public LiteralsHib() {
    }

        public void initLiteralDB() {
            _session = HibernateUtil.getSessionFactory().openSession();
        }

        public void updateResultSet(String currentLanguage) {
        result = (List<literal>)_session.createQuery("from literal where idi_cod = '" + currentLanguage + "'").list();
        }

        public void getLiteralDB(String messageCode) {
        for (literal l: result) {
            if (messageCode.equalsIgnoreCase(l.getLit_clau())) {
                System.err.println(l.getLit_text());
                return;
                }
            }
        }

}
