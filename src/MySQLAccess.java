import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class MySQLAccess {
    private Connection connect = null;
    private Statement statement = null;

    public void initDB() {
        try {

            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Setup the connection with the DB

            connect = DriverManager.getConnection("jdbc:mysql://10.2.8.73:3306/","root","123456");
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();

            System.out.println("Initial success");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void createDB() throws SQLException {
        try {
            String a = "drop database if exists IPAddressInquiry;";
            String b = "create database IPAddressInquiry;";

            String c = "create table IPAddressInquiry.ipaddress (\n" +
                    "  ipa varchar(20),\n" +
                    "  ipb varchar(20),\n" +
                    "  address varchar(120)\n" +
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
        }



    }

    public void insertIntoDatabase(String ipa, String ipb, String address ) throws Exception {

        try {
            String sql = "insert into IPAddressInquiry.ipaddress values(" + "'" + ipa + "'" + "," + "'" + ipb + "'" + "," + "'" + address + "');";
            statement.execute(sql);
        } catch (Exception e) {
            System.out.println("insert failed");
            statement.execute("drop database if exists IPAddressInquiry;");
            throw e;
        }

    }

    // You need to close the resultSet
    public void close() {
        try {

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