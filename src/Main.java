import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Dictionnaire dictionnaire = new Dictionnaire();
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            System.out.println("1. Ajouter un mot et son sens");
            System.out.println("2. Rechercher le sens d'un mot");
            System.out.println("3. Quitter");
            System.out.print("Votre choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choix) {
                    case 1:
                        System.out.print("Entrez le mot : ");
                        String mot = scanner.nextLine();
                        System.out.print("Entrez le sens : ");
                        String sens = scanner.nextLine();
                        dictionnaire.ajouterMot(mot, sens);
                        System.out.println("Mot ajouté et dictionnaire mis à jour !");
                        break;

                    case 2:
                        System.out.print("Entrez le mot à rechercher : ");
                        String motCherche = scanner.nextLine();
                        String resultat = dictionnaire.rechercherMot(motCherche);
                        if (resultat != null) {
                            System.out.println("Sens du mot " + motCherche + " : " +  resultat);
                        } else {
                            System.out.println("mot n'est pas trouve dans le dictionnaire");
                        }
                        break;

                    case 3:
                        continuer = false;
                        System.out.println("vous avez quitter l'application");
                        break;

                    default:
                        System.out.println("choix invalide");
                }
            } catch (IOException e) {
                System.err.println("Erreur lors de l'accès au fichier : " + e.getMessage());
            }
        }

        scanner.close();
    }
}
