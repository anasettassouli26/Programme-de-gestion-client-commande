import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Database {
    public static void menu(){
        int choix = 0;
        Scanner sc=new Scanner(System.in);
        do {
            System.out.println("------Menu Database------");
            System.out.println("1. Create Database.");
            System.out.println("2. Supprimer Database.");
            System.out.println("0. Quitter.");
            System.out.print("Enter your choix : ");
            try{
                choix = sc.nextInt();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            switch(choix){
                case 1:
                    createDatabase();
                    break;
                case 2:
                    supprimerDatabase();
                    break;
                case 0:
                    System.out.println("Bye Bye !");
                    break;
                default:
                    System.out.println("Wrong choice");
            }
        }while (choix!=0);
    }

    public static void createDatabase(){
        try{
            String url = "jdbc:mysql://localhost:3306/";
            String username = "root";
            String password = "Anas123";
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("create database if not exists DBCafe");
            System.out.println("Database created");
            conn.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void supprimerDatabase(){
        try{
            String url = "jdbc:mysql://localhost:3306/";
            String username = "root";
            String password = "Anas123";
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("drop database if exists DBCafe");
            System.out.println("Database dropped");
            conn.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
