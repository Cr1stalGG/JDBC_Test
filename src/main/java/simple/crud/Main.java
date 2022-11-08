package simple.crud;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    private static DBWorker worker;

    static {
        try {
            worker = new DBWorker();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String query = "select * from users;";

    static ResultSet resultSet;
    static Statement statement;

    private static void printUsers() throws SQLException {
        statement = worker.getConnection().createStatement();
        resultSet = statement.executeQuery(query);
        while(resultSet.next()){
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String pass = resultSet.getString(3);
            String status = resultSet.getString(4);

            System.out.println(String.format(" id: %s, username: %s, password: %s, status: %s ;", id, name, pass, status));
        }
    }
    private static void addUser() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        statement = worker.getConnection().createStatement();

        System.out.println("Input username: ");
        String username = scanner.next();

        System.out.println("Input password: ");
        String password = scanner.next();

        System.out.println("Input status: ");
        String status = scanner.next();

        statement.execute(String.format("insert into users (id, username, password, status) select max(id)+1 ,'%s', '%s', '%s' from users;", username, password, status));

    }
    private static void deleteUserById() throws SQLException {
        statement = worker.getConnection().createStatement();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Input id: ");
        int id = scanner.nextInt();

        statement.execute(String.format("delete from users where id = %s;", id));
    }
    private static void updateUser() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        statement = worker.getConnection().createStatement();

        System.out.println("Input id u want to update");
        int id = scanner.nextInt();

        System.out.println("Input username: ");
        String username = scanner.next();

        System.out.println("Input password: ");
        String password = scanner.next();

        System.out.println("Input status: ");
        String status = scanner.next();

        statement.executeUpdate(String.format("update users set username='%s', password='%s', status='%s' where id = %s;", username, password, status, id));

    }
    public static void main(String[] args) throws SQLException {
        String UI = "1. Add new user \n2. Get users \n3. Delete user by id \n4. Update user \n5. Quit";
        Scanner scanner = new Scanner(System.in);
        byte choose = -1;
        while (choose != 5){
            System.out.println(UI);
            choose = scanner.nextByte();
            switch (choose){
                case 1:
                    addUser();
                    break;
                case 2:
                    printUsers();
                    break;
                case 3:
                    deleteUserById();
                    break;
                case 4:
                    updateUser();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Error input data");
                    break;
            }
        }
        System.out.println("End;");

    }
}
