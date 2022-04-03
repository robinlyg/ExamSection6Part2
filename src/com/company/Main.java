package com.company;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

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

        System.out.println(myPlayList.getSize());
        do {

            choice = myPlayList.menu();

            switch (choice) {
                case 1:// Play all songs
                        myPlayList.playAll();

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
                    myPlayList.removeSong(myPlayList.getSong(title));

                    break;
                case 5: //remove multiple song
                    myPlayList.removeMultiple();
                    break;

                case 6:
                    myPlayList.shuffle();
                    break;

                default:
                    System.out.println("Thank you");
                    System.exit(0);
            }
        } while (choice > 0 && choice < 7);

    }
}
