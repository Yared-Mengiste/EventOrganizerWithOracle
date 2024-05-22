import java.sql.SQLException;
import java.sql.*;

public class Main {
    public static void main(String[] arr){
        try {
            AppGuiPart m = new AppGuiPart("Yared", "1234");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
