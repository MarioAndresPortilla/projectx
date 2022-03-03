package projectUno;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.log4j.Logger;

public class Controller {

    private View view = new View();
    private Model model = new Model();
    private Scanner input = new Scanner(System.in);

    private String table = "";
    private String selected = "";
    private String condition = "";
    private String data = "";
    private String tmp = "";
    private ArrayList<String> newInsert = new ArrayList<String>();
    private String y = "";
    private int x = 0;
    private String except = "";
    private String except2 = "";

    public int menu() throws SQLException {

        println("\nWhat do you want to do?");
        println("0) Quit");
        println("1) View a table");
        println("2) Insert object in a table");
        println("3) Remove object from a table");
        println("4) Add a new table.");
        println("5) Drop a table.");

        x = input.nextInt();

        table = "";
        selected = "";
        data = "";
        switch(x) {
            case 0:
                return 0;
            case 1:
                print("Insert the table that you want to see --> ");
                table = myInput();
                print("Insert the thing that you want to see in the table --> ");
                selected = myInput();
                println("");
                try {
                    model.Show(table, selected);//call the function to see a table

                }catch(Exception e) {		//catching any possible error
                    except = "com.microsoft.sqlserver.jdbc.SQLServerException: Invalid object name '"+table+"'.";
                    if (e.toString().equals(except)) { //compare the exception to this string ^^^ to see what type of exception is
                        view.tables();		//case of inexistent table
                    }else {
                        except = "com.microsoft.sqlserver.jdbc.SQLServerException: Invalid column name '"+selected+"'.";
                        if (e.toString().equals(except)) { //compare the exception to this string ^^^ to see what type of exception is
                            view.paraters();		//case of wrong parameters
                        }else {
                            view.connecting();		//case of missing connection
                        }
                    }
                }
                break;
            case 2:
                print("Insert the table that you want to update --> ");
                table = myInput();
                newInsert.clear(); //clean all the array before use it
                while(true) { //loop to insert all type of a table (example: zombie_id,zombie_type,weak... etc etc...)
                    print("Insert type (in the exact order!) (!!INSERT * TO EXIT THE STATEMENT!!)--> ");
                    y = myInput();
                    if(y.equals("*")) {
                        break;
                    }
                    newInsert.add(y);	//i preferred to use an ArrayList because the model.insert convert more then one object in a string
                }
                println("");
                try {
                    model.Insert(table, newInsert); //call the function to insert an object in a table
                }catch(Exception e) {		//catching any possible error
                    except = "com.microsoft.sqlserver.jdbc.SQLServerException: Invalid object name '"+table+"'.";
                    if (e.toString().equals(except)) { //compare the exception to this string ^^^ to see what type of exception is
                        view.tables();		//case of inexistent table
                    }else {
                        view.paraters();		//case of wrong parameters
                    }
                }
                break;
            case 3:
                print("Insert the table that you want to update --> ");
                table = myInput();
                print("Insert the condition to delete a thing in the table --> ");
                condition = myInput();
                println("");
                try {
                    model.Delete(table, condition); //call the function to delete an object in a table
                }catch(Exception e) {		//catching any possible error
                    except = "com.microsoft.sqlserver.jdbc.SQLServerException: Invalid object name '"+table+"'.";
                    if (e.toString().equals(except)) { //compare the exception to this string ^^^ to see what type of exception is
                        view.tables();		//case of inexistent table
                    }else {
                        except = "com.microsoft.sqlserver.jdbc.SQLServerException: An expression of non-boolean type specified in a context where a condition is expected, near '"+condition+"'.";
                        if (e.toString().equals(except)) { //compare the exception to this string ^^^ to see what type of exception is
                            view.paraters();		//case of wrong parameters
                        }else{
                            except2 = "com.microsoft.sqlserver.jdbc.SQLServerException: Invalid column name '"+condition+"'.";
                            if (e.toString().equals(except2)) {		//compare the exception to this string ^^^ to see what type of exception is
                                view.wrong();		//case of wrong condition
                            }else {
                                view.connecting();		//case of missing connection
                            }
                        }
                    }
                }
                break;
            case 4:
                print("Insert the name of the table that you want to create --> ");
                table = myInput();
                println("Insert the data (ONLY ONE WORDS AT TIME) that make up the new table (with the exact syntax)-(example: 'id int NOT NULL, name varchar(20)')");
                while(true) { //loop to insert all type of a table (example: id int NOT NULL, name varchar(20)... etc etc...)
                    print("Insert argument (!!INSERT * TO EXIT THE STATEMENT!!)--> ");
                    tmp = y;			//saving the last taked input to use it for the catch statement
                    y = myInput();
                    if(y.equals("*")) {
                        break;
                    }
                    data += y+" ";	//concatenate string one by one with a blank space between every single string
                }
                println("");
                try {
                    model.Create(table, data);//call the function to create a table
                }catch(Exception e) {		//catching any possible error
                    except = "com.microsoft.sqlserver.jdbc.SQLServerException: The definition for column '"+tmp+"' must include a data type.";
                    except2 = "com.microsoft.sqlserver.jdbc.SQLServerException: Column, parameter, or variable #1: Cannot find data type "+tmp+".";
                    if (e.toString().equals(except) || e.toString().equals(except2)) { //compare the exception to this string ^^^ to see what type of exception is
                        view.paraters();		//case of wrong parameters
                    }else {
                        view.connecting();		//case of missing connection
                    }
                }
                break;
            case 5:
                print("Insert the table that you want to drop (Pay attention, this command is irreversible!!) --> ");
                table = myInput();
                try {
                    model.Drop(table);//call the function to drop a table
                }catch(Exception e) {		//catching any possible error
                    except = "com.microsoft.sqlserver.jdbc.SQLServerException: Invalid object name '"+table+"'.";
                    if (e.toString().equals(except)) { //compare the exception to this string ^^^ to see what type of exception is
                        view.tables();		//case of wrong parameters
                    }else {
                        view.connecting();		//case of missing connection
                    }
                }
                break;
            default:
                println("Not valid number!");
                break;
        }


        return 1;
    }

    public static String myInput() {		//rude function to bypass spaces exception.
        @SuppressWarnings("resource")
        Scanner in = new Scanner(System.in);
        String x = "";
        while(true) {
            try {
                x = new String();
                x = in.next(); //user input
                return x;
            }catch(Exception e) {		//try to see if the user typed spaces (because jvm doesn't like it)
                print("Inconsistent use of spaces, try again --> ");
            }
        }
    }

    public static void print(Object o) {
        System.out.print(o);
    }

    public static void println(Object o) {
        System.out.println(o);
    }

}
