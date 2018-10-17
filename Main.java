import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

//condition effacer

public class Main extends Application {

   public static ArrayList<User> listUti = new ArrayList<>();

    public static void main(String[] args) { launch(args);}

    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static String hashcode(String originalString){
       try{
           MessageDigest digest = MessageDigest.getInstance("SHA-256");
           byte[] encodedhash = digest.digest(
                   originalString.getBytes(StandardCharsets.UTF_8));
           return bytesToHex(encodedhash);

       }catch (NoSuchAlgorithmException e){

       }
        return "erreur";
    }



    @Override
    public void start(Stage primaryStage) throws Exception {

        try{
            List<String> list = Files.readAllLines(Paths.get("fichier.csv"));
            String[] tab;

            for(int i = 0; i < list.size(); i++) {
                tab = list.get(i).split(",");
                User utilisateur = new User();
                utilisateur.setPrenom(tab[0]);
                utilisateur.setNom(tab[1]);
                utilisateur.setUsername(tab[2]);
                utilisateur.setPassword(tab[3]);
                utilisateur.setGenre(tab[4]);
                utilisateur.setAge(Integer.parseInt(tab[5]));
                listUti.add(utilisateur);
            }

        }catch (NoSuchFileException e){
            System.out.println("Fichier introuvable");
        }

        //fenêtre
        primaryStage.setWidth(400);
        primaryStage.setHeight(600);
        primaryStage.setTitle("Laboratoire 5");
        primaryStage.setResizable(false);

        //scène 1
        TextField nomUti = new TextField();
        nomUti.setPromptText("Nom d'utilisateur");
        nomUti.setTranslateX(125);
        nomUti.setTranslateY(245);
        Label uti = new Label("Nom d'utilisateur");
        uti.setTranslateX(125);
        uti.setTranslateY(225);

        PasswordField password = new PasswordField();
        password.setPromptText("Mot de passe");
        password.setTranslateX(125);
        password.setTranslateY(300);
        Label motpass = new Label("Mot de passe");
        motpass.setTranslateX(125);
        motpass.setTranslateY(280);

        Button connec = new Button("Se connecter");
        connec.setTranslateX(125);
        connec.setTranslateY(350);

        Button sing = new Button("S'inscrire");
        sing.setTranslateX(215);
        sing.setTranslateY(350);

        Text erreur = new Text("");
        erreur.setFill(Color.RED);
        erreur.setTranslateX(125);
        erreur.setTranslateY(400);

        //scène2
        TextField prenom = new TextField();
        prenom.setPromptText("Prénom");
        prenom.setTranslateX(125);
        prenom.setTranslateY(40);
        Label firstName = new Label("Prénom");
        firstName.setTranslateY(20);
        firstName.setTranslateX(125);

        TextField nom = new TextField();
        nom.setPromptText("Nom de famille");
        nom.setTranslateY(95);
        nom.setTranslateX(125);
        Label lastName = new Label("Nom de famille");
        lastName.setTranslateX(125);
        lastName.setTranslateY(75);

        TextField userName = new TextField();
        userName.setPromptText("Nom d'utilisateur");
        userName.setTranslateX(125);
        userName.setTranslateY(150);
        Label userName2 = new Label("Nom d'utilisateur");
        userName2.setTranslateX(125);
        userName2.setTranslateY(130);

        PasswordField password2 = new PasswordField();
        password2.setTranslateX(125);
        password2.setTranslateY(205);
        password2.setPromptText("Mot de passe");
        Label passeword3 = new Label("Mot de passe");
        passeword3.setTranslateY(185);
        passeword3.setTranslateX(125);

        PasswordField confirm = new PasswordField();
        confirm.setPromptText("Mot de passe");
        confirm.setTranslateY(260);
        confirm.setTranslateX(125);
        Label confirm2 = new Label("Confirmer le mot de passe");
        confirm2.setTranslateX(125);
        confirm2.setTranslateY(240);

        Label genre = new Label("Genre");
        genre.setTranslateX(125);
        genre.setTranslateY(295);
        ToggleGroup groupe = new ToggleGroup();
        RadioButton homme = new RadioButton("Homme");
        homme.setToggleGroup(groupe);
        homme.setTranslateX(125);
        homme.setTranslateY(315);
        RadioButton femme = new RadioButton("Femme");
        femme.setTranslateX(200);
        femme.setTranslateY(315);
        femme.setToggleGroup(groupe);
        RadioButton autre = new RadioButton("Autre");
        autre.setTranslateX(275);
        autre.setTranslateY(315);
        autre.setToggleGroup(groupe);

        Label age = new Label("Âge");
        age.setTranslateX(125);
        age.setTranslateY(340);

        Spinner spinner = new Spinner(18, 100, 18);
        spinner.setTranslateX(125);
        spinner.setTranslateY(360);

        CheckBox condi = new CheckBox("J'accepte les conditions d'utilisation");
        condi.setTranslateX(125);
        condi.setTranslateY(400);

        Text info = new Text("");
        info.setTranslateX(125);
        info.setTranslateY(500);
        info.setFill(Color.RED);

        Button inscription = new Button("S'inscrire");
        inscription.setTranslateX(125);
        inscription.setTranslateY(450);

        Button erase = new Button("Effacer");
        erase.setTranslateY(450);
        erase.setTranslateX(200);

        Button retour = new Button("Retour");
        retour.setTranslateX(260);
        retour.setTranslateY(450);

        //scene3
        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.setTranslateX(165);
        progressIndicator.setTranslateY(225);
        Label text = new Label("Chargement du contenu");
        text.setTranslateY(300);
        text.setTranslateX(125);

        Group group1 = new Group(nomUti, uti, motpass, password, connec, sing, lastName, erreur);

        Group group2 = new Group(prenom, firstName, nom, lastName, userName, userName2, password2, passeword3, confirm, confirm2,
                genre, homme, femme, autre, age, spinner, condi, inscription, erase, retour, info);

        Group group3 = new Group(progressIndicator, text);

        Scene scene1 = new Scene(group1);
        Scene scene2 = new Scene(group2);
        Scene scene3 = new Scene(group3);

        sing.setOnAction(event -> primaryStage.setScene(scene2));

        connec.setOnAction(event -> {
            String motdepasse =  hashcode(password.getText());
            for(int i = 0; i < listUti.size(); i++){
                if (nomUti.getText().equals(listUti.get(i).getUsername())
                        && motdepasse.equals(listUti.get(i).getPassword())){
                    primaryStage.setScene(scene3);
                }
                else {
                    erreur.setText("La connexion a échouée");
                }
            }
        });

        retour.setOnAction(event -> primaryStage.setScene(scene1));

        inscription.setOnAction(event -> {

            if (prenom.getText().isEmpty() == true){
                info.setText("Prénom manquant");
            }
            else if (nom.getText().isEmpty() == true){
                info.setText("Nom de famille manquant");
            }
            else if (userName.getText().isEmpty() == true){
                info.setText("Nom d'utilisateur manquant");
            }
            else if (verification(userName.getText()) == true){
                info.setText("Nom d'utilisateur déjà existant");
            }
            else if (password2.getText().isEmpty() == true){
                info.setText("Mot de passe manquant");
            }
            else if (confirm.getText().isEmpty() == true){
                info.setText("Confirmation du mot de passe manquante");
            }
            else if (password2.getText().equals(confirm.getText()) == false){
                info.setText("Confirmation du mot de passe invalide");
            }
            else if (femme.isSelected() == false && homme.isSelected() == false && autre.isSelected() == false){
                info.setText("Genre manquant");
            }
            else if (condi.isSelected() == false){
                info.setText("Conditions d'utilisation manquantes");

            }else{
                String value = "";

                if (femme.isSelected() == true){
                    value = "femme";
                }
                else if (homme.isSelected() == true){
                    value = "homme";
                }
                else if (autre.isSelected() == true){
                    value = "autre";
                }

                User user = new User();
                user.setPrenom(prenom.getText());
                user.setNom(nom.getText());
                user.setUsername(userName.getText());
                user.setPassword(hashcode(password2.getText()));
                user.setAge(spinner.getValue().hashCode());
                user.setGenre(value);
                listUti.add(user);
                primaryStage.setScene(scene3);
                info.setText("");

                String fichier = "";
                for (int i = 0; i < listUti.size(); i++){
                    fichier = fichier + listUti.get(i).fichier();
                }
                try{
                    Files.write(Paths.get("fichier.csv"), fichier.getBytes());

                }catch (Exception e){

                }

            }

        });

        erase.setOnAction(event -> {

            //try catch, car NullpointerException si le genre n'est pas coché et qu'on veut effacer
            try{
                prenom.clear();
                nom.clear();
                userName.clear();
                password2.clear();
                confirm.clear();
                groupe.getSelectedToggle().setSelected(false);
                spinner.getValueFactory().setValue(18);
                condi.setSelected(false);

            }catch (NullPointerException e){

                spinner.getValueFactory().setValue(18);
                condi.setSelected(false);
            }
        });

        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    private static boolean verification(String name){
        boolean verif = false;
        for (int i = 0; i < listUti.size(); i++){
            if (listUti.get(i).getUsername().equals(name)){
                verif = true;
            }
        }
        return verif;
    }
}
