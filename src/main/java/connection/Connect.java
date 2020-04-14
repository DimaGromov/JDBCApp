package connection;

public class Connect {
    private final String DB_DRIVER;
    private final String URL;
    private final String USER_NAME;
    private final String PASSWORD;

    public Connect(String DB_DRIVER, String URL, String USER_NAME, String PASSWORD) {
        this.DB_DRIVER = DB_DRIVER;
        this.URL = URL;
        this.USER_NAME = USER_NAME;
        this.PASSWORD = PASSWORD;
    }

    public String getDB_DRIVER() {
        return DB_DRIVER;
    }

    public String getURL() {
        return URL;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }
}
