import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDb {
    private static final String URL = "jdbc:postgresql://localhost:5432/retail";
    private static final String USER = "ais";
    private static final String PASSWORD = "mysecretpassword";

    public static void main(String[] args) throws SQLException {
        insertProduct();
        getAllProducts();
    }

    private static void insertProduct() throws SQLException {
        String name = "AMD Atlon 33333";
        Integer price = 20;
        String category = "Processor";
        Integer stock = 2;
        //String sql = String.format("insert into product (name, price, category, stock) values ('%s', %d, '%s', %d)", name, price, category, stock);
        String sql = "insert into product (name, price, category, stock) values (?, ?, ?, ?)";
        try (var connection = DriverManager.getConnection(URL, USER, PASSWORD);) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, price);
            statement.setString(3, category);
            statement.setInt(4, stock);

            var result = statement.executeUpdate();
            System.out.println(result);
        }
    }

    private static void getAllProducts() throws SQLException {
        try (var connection = DriverManager.getConnection(URL, USER, PASSWORD);) {
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
        }

        System.out.println("Connection closed");
    }
}
