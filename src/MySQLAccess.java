import java.security.KeyStore;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class MySQLAccess {
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;


    public void createDB() throws SQLException, ClassNotFoundException {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Setup the connection with the DB

            connect = DriverManager.getConnection("jdbc:mysql://10.2.8.73:3306/","root","123456");

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();

            String a = "drop database if exists IPAddressInquiry;";
            String b = "create database IPAddressInquiry;";

            String c = "create table IPAddressInquiry.ipaddress (\n" +
                    "  ipa varchar(20),\n" +
                    "  ipb varchar(20),\n" +
                    "  address varchar(100)\n" +
                    ");";

            String d = "use IPAddressInquiry;";



            // Result set get the result of the SQL query
            statement.execute(a);
            statement.execute(b);
            statement.execute(c);
            statement.execute(d);

            System.out.println("database create success");


        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }



    }

    public void insertIntoDatabase(String ipa, String ipb, String address ) throws Exception {

        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager.getConnection("jdbc:mysql://10.2.8.73:3306/","root","123456");

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();


            statement.execute("insert into IPAddressInquiry.ipaddress values(" + "'" + ipa + "'" +"," + "'" + ipb + "'" + "," + "'" + address + "'" + ");");

            System.out.println("insert success");

        } catch (Exception e) {
            System.out.println("insert failed");
            statement.execute("drop database if exists IPAddressInquiry;");
            throw e;
        } finally {
            close();
        }

    }

    // You need to close the resultSet
    public void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

}