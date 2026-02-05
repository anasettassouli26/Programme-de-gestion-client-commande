import java.util.Scanner;

public class Main {
    public static void menu() {
        try{
            Scanner sc=new Scanner(System.in);
            int choix = 0;
            do {
                System.out.println("--------Menu--------");
                System.out.println("1 - Gestion DataBase.");
                System.out.println("2 - Gestion Client.");
                System.out.println("3 - Gestion Commande.");
                System.out.println("0 - Quitter.");
                System.out.print("Enter your choix : ");
                try{
                    choix = sc.nextInt();
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
                switch (choix) {
                    case 1:
                        Database.menu();
                        break;
                    case 2:
                        Client.menu();
                        break;
                    case 3:
                        Commande.menu();
                        break;
                    case 0:
                        System.out.println("Bye Bye!");
                        break;
                    default:
                        System.out.println("Wrong choix");
                        break;
                }
            }while (choix != 0);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Authentification.authentification();
    }
}
