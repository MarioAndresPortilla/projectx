package projectUno;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

public class MainTest{

    @Test
    public static void main(String[] args) throws SQLException {
        Azure connection = new Azure();
        Controller input = new Controller();

        System.out.println("WELCOME TO AZURE DATABASE CONNECTION!");
        while(true) {
            if(input.menu() == 0) {
                break;
            }
        }

        connection.close();
    }
}


