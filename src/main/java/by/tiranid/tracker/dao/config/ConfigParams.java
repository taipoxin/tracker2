package by.tiranid.tracker.dao.config;


public class ConfigParams {

    // DataConfig
    public static final String username = "tiranid";
    public static final String password = "6559520";
    public static final String db_url = "jdbc:mysql://localhost:3306/test?verifyServerCertificate=false&useSSL=true";

    public static final String db_driver = "com.mysql.jdbc.Driver";

    public static final String hibernate_dialect = "org.hibernate.dialect.MySQLDialect";
    public static final String show_sql = "true";
    public static final String entitymanager_packages = "by.tiranid.tracker.model";
    public static final String hbm2ddl_auto = "update";

}
