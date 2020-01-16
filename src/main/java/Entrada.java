public class Entrada {
    private String _entrada;
    private String _error;
    private TipusEntrada _tipusEntrada;
    private String[] _parametres;
    public enum TipusEntrada {
        GOTO, GOLAST, LIST, UP, INFOFILE,
        INFODIR, HELP, CREATEDIR, CREATEFILE,
        SORTBY, DELETEDIR, DELETEFILE, EXIT, ERROR, LOG, LOG0, LOG1,CLEARLOG,LOAD
    }
    public enum tipusOrdenacio {
        NAME, DATE
    }
    public Entrada(String entrada) {
        this._entrada = entrada;
        carregarObjecte();
    }
    private void carregarObjecte() {
        String errorParametrosIncorrecto = "Numero de parametros incorrecto!";
        String auxTexte = this._entrada;

        if (auxTexte != null && !auxTexte.isEmpty()) {
            String[] elements = auxTexte.split(" ");
            switch (elements[0].toLowerCase()) {
                case "goto":
                    if (elements.length == 2) {
                        this._parametres = new String[1];
                        this._parametres[0] = elements[1];
                        this._tipusEntrada = TipusEntrada.GOTO;
                    } else {
                        this._tipusEntrada = TipusEntrada.ERROR;
                        this._error = errorParametrosIncorrecto;
                    }
                    break;
                case "golast":
                    if (elements.length == 1) {
                        this._tipusEntrada = TipusEntrada.GOLAST;
                    } else {
                        this._tipusEntrada = TipusEntrada.ERROR;
                        this._error = errorParametrosIncorrecto;
                    }
                    break;
                case "list":
                    if (elements.length == 1) {
                        this._tipusEntrada = TipusEntrada.LIST;
                    } else {
                        this._tipusEntrada = TipusEntrada.ERROR;
                        this._error = errorParametrosIncorrecto;
                    }
                    break;
                case "up":
                    if (elements.length == 1) {
                        this._tipusEntrada = TipusEntrada.UP;
                    } else {
                        this._tipusEntrada = TipusEntrada.ERROR;
                        this._error = errorParametrosIncorrecto;
                    }
                    break;
                case "help":
                    if (elements.length == 1) {
                        this._tipusEntrada = TipusEntrada.HELP;
                    } else {
                        this._tipusEntrada = TipusEntrada.ERROR;
                        this._error = errorParametrosIncorrecto;
                    }
                    break;
                case "exit":
                    if (elements.length == 1) {
                        this._tipusEntrada = TipusEntrada.EXIT;
                    } else {
                        this._tipusEntrada = TipusEntrada.ERROR;
                        this._error = errorParametrosIncorrecto;
                    }
                    break;
                case "log0":
                    if (elements.length == 1) {
                        this._tipusEntrada = TipusEntrada.LOG0;
                    } else {
                        this._tipusEntrada = TipusEntrada.ERROR;
                        this._error = errorParametrosIncorrecto;
                    }
                    break;
                case "log1":
                    if (elements.length == 1) {
                        this._tipusEntrada = TipusEntrada.LOG1;
                    } else {
                        this._tipusEntrada = TipusEntrada.ERROR;
                        this._error = errorParametrosIncorrecto;
                    }
                    break;
                case "clearlog":
                    if (elements.length == 1) {
                        this._tipusEntrada = TipusEntrada.CLEARLOG;
                    } else {
                        this._tipusEntrada = TipusEntrada.ERROR;
                        this._error = errorParametrosIncorrecto;
                    }
                    break;
                case "log":
                    if (elements.length > 1) {
                        this._parametres = new String[1];
                        this._parametres[0] = elements[1];
                        this._tipusEntrada = TipusEntrada.LOG;
                    } else {
                        this._tipusEntrada = TipusEntrada.ERROR;
                        this._error = errorParametrosIncorrecto;
                    }
                    break;
                case "load":
                    if (elements.length == 1) {
                        this._tipusEntrada = TipusEntrada.LOAD;
                    } else {
                        this._tipusEntrada = TipusEntrada.LOAD;
                        this._error = errorParametrosIncorrecto;
                    }
                    break;
                default:
                    this._tipusEntrada = TipusEntrada.ERROR;
                    break;
            }
        } else {
            this._tipusEntrada = TipusEntrada.ERROR;
            this._error = "Sin texto";
        }
    }

    public TipusEntrada obtenirTipusEntrada() {
        return this._tipusEntrada;
    }

    public String[] obtenirParametres() {
        return this._parametres;
    }

    public String obtenirError() {
        return this._error;
    }

}

