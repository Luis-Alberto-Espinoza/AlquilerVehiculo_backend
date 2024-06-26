package PP.alquilerVehiculo.excepciones;

public class ClienteServiceException extends Exception {


private ExceptionDetail detail;



    /**
     * Creates a new instance of <code>UsuarioServiceException</code> without detail message.
     */
    public ClienteServiceException() {
    }

    /**
     * Constructs an instance of <code>UsuarioServiceException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
//    public ClienteServiceException(String msg) {
//        super(msg);
//    }
//    public ClienteServiceException(String msg, Throwable e) {
//        super(msg, e);
//    }
    public ClienteServiceException(String msg,ExceptionDetail detail, Throwable e) {
        super(msg, e);
        setDetail(detail);
    }
    public ClienteServiceException(String msg, ExceptionDetail detail) {
        super(msg);
        setDetail(detail);
    }

    public ExceptionDetail getDetail() {
        return detail;
    }

    public void setDetail(ExceptionDetail detail) {
        this.detail = detail;
    }
}
