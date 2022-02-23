package xproject;
import java.sql.*;


public class DatabaseService extends Driver {

    public void Insert(String table , Object[] values ) throws SQLException {    //Method to Insert a new object in the table

        String command = "INSERT INTO " + table + " VALUES ";   //writes first part of the prepareStatement
        boolean autoliteral = true;
        //write the values to the command
        for ( int i = 0; i < values.length;i++) {
            if( values[i] instanceof String && autoliteral)
                //if the value is a string it assumes it is a literal, this may cause problem, if this is undesired
                command += "\'" + values[i] + "\'";
            else
                command += values[i];

            command += ((i < values.length - 1) ? " , " : " );");
        }
        //prepare and run command
        PreparedStatement st = conn.prepareStatement(command);
        st.execute();
    }

                        //Method to delete an object in a table
    public void Delete(String table , String condition ) throws SQLException {
        String command = "DELETE  FROM " + table + " WHERE " + condition + ";";
        PreparedStatement st = conn.prepareStatement(command);
        st.execute();
    }
    //Method to get ResultSet from select method (
    public ResultSet Select(String table, String columns) throws SQLException {
        PreparedStatement st = conn.prepareStatement(" SELECT * FROM " + table +";");
        return st.executeQuery();
    }
    //Method to get ResultSet from select method
    public ResultSet Select(String table,String columns,String condition) throws SQLException {
        PreparedStatement st = conn.prepareStatement("SELECT * FROM " + table + " WHERE " + condition + ";");
        return st.executeQuery();
    }

    public void printAliensResultset(ResultSet Aliens, String column) throws SQLException{
            while(Aliens.next()){
            System.out.println(Aliens.getString(column));
        }

    }


    //Method to print the ResultSet returned by the select method
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
}

