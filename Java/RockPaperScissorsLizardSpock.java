import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        // on créer une liste de joueur, composé des joueurs encore dans la partie
        ArrayList<Player> players = new ArrayList<Player>();
        for (int i = 0; i < N; i++) {
            int NUMPLAYER = in.nextInt();
            String SIGNPLAYER = in.next();
            System.err.println(NUMPLAYER +" " +SIGNPLAYER);
            players.add(new Player(NUMPLAYER, SIGNPLAYER));
        }

        // tant qu'il y a des joueurs dans la liste on continue les affrontements
        while (players.size()>1){
            // on créer une liste contenant tous les perdant lors d'une ronde, permettant ensuite de les retirer de la liste de joueur
            ArrayList<Integer> playersLoose = new ArrayList<Integer>();
            //chaque paire de joueur s'affronte
            for (int i = 0; i<players.size(); i+=2){
                // on appelle la fonction de combat pour connaitre le vainqueur, 
                // ce dernier voit le perdant ajouté a sa liste de personne vaincu, 
                // ensuite le perdant est ajouté a la liste des perdants.
                int winner = fight(players.get(i), players.get(i+1));
                if (winner==1){
                    players.get(i).addDefeatedPlayer(players.get(i+1).getNumPlayer());
                    playersLoose.add(i+1);
                }else if(winner==2){
                    players.get(i+1).addDefeatedPlayer(players.get(i).getNumPlayer());
                    playersLoose.add(i);
                }
            }
            // après une ronde, on retire les perdants de la liste des joueurs. En commencant par la fin pour respecter les index
            for (int i = playersLoose.size()-1; i >= 0; i--){
                int toRemove = playersLoose.get(i);
                players.remove(toRemove);
            }
        }
        System.out.println(players.get(0).getNumPlayer());
        players.get(0).showDefeatedPlayer();

        
    }

    // Méthode permettant de savoir quel joueur gagne lors d'une manche, renvoi 1 si il s'agit du premier, 2 si il s'agit du deuxième, 0 en cas d'erreur
    public static int fight(Player p1, Player p2){
        String s1 = p1.getSignPlayer();
        String s2 = p2.getSignPlayer();
        if (p1.getSignPlayer().equals(p2.getSignPlayer())){
            if (p1.getNumPlayer()<p2.getNumPlayer()){
                return 1;
            }else{
                return 2;
            }
        }
        switch (s1){
            case "R":
                if (s2.equals("L")|| s2.equals("C")){
                    return 1;
                }else if(s2.equals("S")|| s2.equals("P")){
                    return 2;
                }
                break;

            case "P":
                if (s2.equals("R")|| s2.equals("S")){
                    return 1;
                }else if(s2.equals("L")|| s2.equals("C")){
                    return 2;
                }
                break;

            case "C":
                if (s2.equals("L")|| s2.equals("P")){
                    return 1;
                }else if(s2.equals("S")|| s2.equals("R")){
                    return 2;
                }
                break;

            case "L":
                if (s2.equals("S")|| s2.equals("P")){
                    return 1;
                }else if(s2.equals("R")|| s2.equals("C")){
                    return 2;
                }
                break;

            case "S":
                if (s2.equals("R")|| s2.equals("C")){
                    return 1;
                }else if(s2.equals("L")|| s2.equals("P")){
                    return 2;
                }
                break;
            default:
                System.out.println("Erreur");
                break;
        }
        return 0;
    }
}

// Classe joueur contenant le numéro du joueur, son signe, ainsi que la liste des joueurs qu'il a vaincu
class Player {
    private int numPlayer;
    private String signPlayer;
    private ArrayList<Integer> defeatedPlayers = new ArrayList<Integer>();
    public Player (int numPlayer, String signPlayer){
        this.numPlayer = numPlayer;
        this.signPlayer = signPlayer;
    }

    public int getNumPlayer(){
        return this.numPlayer;
    }

    public String getSignPlayer(){
        return this.signPlayer;
    }

    public void addDefeatedPlayer(int numPlayer){
        this.defeatedPlayers.add(numPlayer);
    }

    public void showDefeatedPlayer(){
        for (int i=0; i<this.defeatedPlayers.size(); i++){
            System.out.print(this.defeatedPlayers.get(i));
            if (i != this.defeatedPlayers.size()-1){
                System.out.print(" ");
            }
        }
    }
}
