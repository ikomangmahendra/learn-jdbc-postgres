import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDb {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/retail";
        String user = "ais";
        String password = "mysecretpassword";
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("Connected to database");

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from product");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            Integer price = resultSet.getInt("price");
            String category = resultSet.getString("category");
            Integer stock = resultSet.getInt("stock");
            // int price = resultSet.getInt("price");
            System.out.println(String.format("Product: %d - %s %d %s %d",
                id, name, price, category, stock));
        }

        connection.close();
        System.out.println("Connection closed");
    }
}
