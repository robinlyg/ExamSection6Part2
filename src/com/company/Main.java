package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        int lastPlayed = 0;
        String lastSelected;

        Song one = new Song("First Class");
        Song two = new Song("Willow");
        Song three = new Song("Welcome to New York");
        Song four = new Song("Problems III");
        Song five = new Song("Motion Sickness");
        Song six = new Song("Little Wanderer");

        Playlist myPlayList = new Playlist();
        myPlayList.addSong(one);
        myPlayList.addSong(two);
        myPlayList.addSong(three);
        myPlayList.addSong(four);
        myPlayList.addSong(five);
        myPlayList.addSong(six);

        int choice = 1;


        do {

            choice = myPlayList.menu();

            switch (choice) {
                case 1:// Play all songs

                    int selection = 0;
                    for (int i = 0; i < (myPlayList.getSize() * 8); i++) {
                        //selection =0;
                        System.out.println(myPlayList.getIndex(i % (myPlayList.getSize()-1)));
                        lastSelected = myPlayList.getIndex(i).getSongTitle();

                        System.out.println("Continue playing y for \"yes\" n for \"no\"");
                        selection = 0;
                        if (keyboard.nextLine().equalsIgnoreCase("n")) {
                            selection = myPlayList.playAllMenu();
                            if (selection == 5)
                                break;
                        }
                        switch (selection) {
                            case 1: //play last selected
                                System.out.println(myPlayList.playLastSelected(lastSelected));
                                break;
                            case 2: //overwrite the last playing song (next)
                                myPlayList.overWrite(i % (myPlayList.getSize()-1));
                                break;
                            case 3: //repeat last played song
                                myPlayList.loopLastPlayed(i % (myPlayList.getSize())-1);
                                break;
                            case 4: //repeat last selected song
                                myPlayList.loopLastSelected(lastSelected);
                                break;

                        }
                    }

                    break;
                case 2: //add a song

                    System.out.println("What song title would you like to add? ");
                    String title = keyboard.nextLine();
                    ;
                    Song song = new Song(title);
                    myPlayList.addSong(song);

                    break;
                case 3: // add several song

                    myPlayList.addMultiple();

                    break;
                case 4: //Remove a song

                    System.out.println("What song title would you like to add? ");
                    title = keyboard.nextLine();
                    ;
                    myPlayList.removeSong(myPlayList.getSong(title));

                    break;
                case 5: //remove multiple song

                    myPlayList.removeMultiple();
                    break;

                case 6:
                    myPlayList.shuffle();

                    break;

                default:
                    System.exit(0);
            }
        } while (choice > 0 && choice < 7);
        System.out.println("Thank you");
    }
}
