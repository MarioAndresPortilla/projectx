package projectUno;
import java.sql.SQLException;
import java.util.ArrayList;

public class Model {

    Azure connection = new Azure();

    public void Show(String table, String selected) throws SQLException {
        String command = "SELECT " + selected + " FROM " + table +";";
        connection.printResultSet(connection.Select(command));
    }

    public void Insert(String table, ArrayList<String> values) throws SQLException {
        String command = "INSERT INTO " + table + " VALUES ( ";

        boolean autoliteral = true;
        //if autoliteral is true it assumes that every string is a literal
        //if autoliteral is false to specify a literal you will have to do this
        //!Azure only uses ' to represent string
        for ( int i = 0; i < values.size() ; i++) {
            if( values.get(i) instanceof String && autoliteral)
                //if the value is a string it assumes it is a literal, this may cause problem
                //if this is undesired
                command += "\'" + values.get(i) + "\'";
            else
                command += values.get(i);

            command += ((i < values.size() - 1) ? " , " : " );");
        }
        connection.GiveCommand(command);
    }

    public void Delete(String table, String condition) throws SQLException {
        String command = "DELETE FROM " + table + " WHERE "+condition;
        connection.GiveCommand(command);
    }

    public void Create(String table, String data) throws SQLException {
        String command = "CREATE TABLE " + table + " ( " + data + " ) ";
        connection.GiveCommand(command);
    }

    public void Drop(String table) throws SQLException {
        String command = "DROP TABLE " + table;
        connection.GiveCommand(command);
    }
}
