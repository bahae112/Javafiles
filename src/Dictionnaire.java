import java.io.*;
import java.util.ArrayList;

public class Dictionnaire {
    private static final String FILENAME = "dictionnaire.txt";
    private ArrayList<String[]> listeMots = new ArrayList<>();

    public void ajouterMot(String mot, String sens) throws IOException {
        listeMots.add(new String[]{mot, sens});
        if (listeMots.size() == 1) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, true))) {
                writer.write(mot + ":" + sens);
                writer.newLine();
            }
        } else {
            trierListe();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
                for (String[] motEtSens : listeMots) {
                    writer.write(motEtSens[0] + ":" + motEtSens[1]);
                    writer.newLine();
                }
            }
        }
    }
    public String rechercherMot(String motCherche) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(FILENAME, "r")) {
            String ligne;

            while ((ligne = raf.readLine()) != null) { // s'incremente automatiquement pas besoin de l'incrementer
                String[] motEtSens = ligne.split(":", 2);
                if (motEtSens.length == 2) {
                    String mot = motEtSens[0];
                    String sens = motEtSens[1];
                    if (mot.equalsIgnoreCase(motCherche)) {
                        return sens;
                    }
                }
            }
        }
        return null;
    }

    private void trierListe() {
        int n = listeMots.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (listeMots.get(j)[0].compareToIgnoreCase(listeMots.get(minIndex)[0]) < 0) {
                    minIndex = j;
                }
            }
            String[] temp = listeMots.get(minIndex);
            listeMots.set(minIndex, listeMots.get(i));
            listeMots.set(i, temp);
        }
    }
}
