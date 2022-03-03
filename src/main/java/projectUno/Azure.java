package projectUno;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Azure {

    public Connection conn;
    private View view = new View();

    public Azure() {
        try {
            FileInputStream propertiesInput = new FileInputStream("C:\\Users\\Mario\\Documents\\Revature\\sql.properties");

            Properties props = new Properties();
            props.load(propertiesInput);

            String url = (String) props.get("url");
            String username = (String) props.get("username");
            String password = (String) props.get("password");

            if (conn == null) {
                try {
                    conn = DriverManager.getConnection(url, username, password);
                } catch (SQLException e) {
                    System.out.println("l");
                    e.printStackTrace();
                }
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Give a command to the Azure's servers
    public void GiveCommand(String command) throws SQLException {
        //prepare and run command
        PreparedStatement st = conn.prepareStatement(command);//you pass the 'AzureSQL table' command as a function parameter
        st.execute();
    }


    //Select function
    public ResultSet Select(String command) throws SQLException {
        PreparedStatement st = conn.prepareStatement(command);

        return st.executeQuery();
    }

    //useful to print the ResultSet returned by the select function
    public void printResultSet(ResultSet ris) throws SQLException {
        ResultSetMetaData rsmd = ris.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        for(int i = 1; i <= columnsNumber; i++)
            System.out.print(rsmd.getColumnName(i) + ((i != columnsNumber) ? " , " : "\n"));


        while (ris.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = ris.getString(i);
                System.out.print(columnValue + " ");
            }
            System.out.println("");
        }
    }

    //close the connection
    public boolean close() {
        try {
            conn.close();
            return true;
        }catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
