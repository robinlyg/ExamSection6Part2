package com.company;

import java.util.*;

public class Playlist {
    //linkedList circular data structure
    protected List<Song> myPlaylist;
    int size = 0;
    Scanner keyboard = new Scanner(System.in);


    public Playlist() {
        myPlaylist = new LinkedList<>();
    }

    public Playlist(List p) {

        myPlaylist = p;
    }

    // take multiple songs and add to list
    public void addSong(Song s) {
        myPlaylist.add(s);
        size++;
    }

    public int getSize() {
        return size;
    }

    public void addMultiple() {
        String title = "";
        Scanner keyboard = new Scanner(System.in);
        do {
            System.out.println("Please enter the song title you'd like to add (enter done to exit) ");
            title = keyboard.nextLine();
            if (title.equalsIgnoreCase("done"))
                return;

            size++;
            Song newSong = new Song(title);
            myPlaylist.add(newSong);

        } while (!title.equalsIgnoreCase("done"));
    }

    //play last selected song
    public Song playLastSelected(String title) {
        return getSong(title);

    }

    public void loopLastSelected(String title) {
        for (int i = 0; i < 15; i++) {
            System.out.println(getSong(title));
        }
    }


    public Song getSong(String obj) {
        for (Song s : myPlaylist) {
            if (s.title.equalsIgnoreCase(obj))
                return s;
        }

        return null;

    }

    //loop selected song - iterator should point to this song
    public void repeatLastSelected(String obj) {
        Song s = getSong(obj);
        int count = 0;
        while (count < 15) {
            System.out.println(s);
            count++;
        }
    }

    //overwrite the last playing song..next/prev??
    public void overWrite(int current) {
        if (current == myPlaylist.size())
            current = -1;

        myPlaylist.get(current + 1);
    }


    //shuffle
    public void shuffle() {
        ArrayList<Integer> prevPlay = new ArrayList<>(myPlaylist.size());

        // Collections.shuffle(newList);
        Random rng = new Random();
        int randIndex = rng.nextInt(myPlaylist.size());
        //int lastRand = 0;

        for (int i = 0; i < myPlaylist.size(); i++) {
            do {
                randIndex = rng.nextInt(myPlaylist.size());

            } while (prevPlay.contains(randIndex));

            System.out.println(myPlaylist.get(randIndex));
            prevPlay.add(randIndex);

        }
    }

    //remove
    public void removeSong(Song song) {
        myPlaylist.remove(song);
        size--;
    }


    public Song getIndex(int i) {
        return myPlaylist.get(i);
    }


    //remove multiple
    public void removeMultiple() {
        keyboard.nextLine();
        String title = "";
        //Scanner keyboard = new Scanner(System.in);
        while (!title.equalsIgnoreCase("done")) {
            System.out.println("Please enter the song title to remove (enter done to exit) ");
            title = keyboard.nextLine();
            if (title.equalsIgnoreCase("done"))
                break;
            size--;
            myPlaylist.remove(getSong(title));

        }
    }

    //loop last played song
    //take lastPlayedSong and loop
    public void loopLastPlayed(int i) {
        for (int j = 0; j < 15; j++) {
            System.out.println(myPlaylist.get(i) + " ");
        }
    }


    public int menu() {
        //Scanner keyboard = new Scanner(System.in);
        int choice = 0;
        System.out.println("Please make a selection from below:");
        System.out.println("Enter 1 to Play all songs. ");
        System.out.println("Enter 2 to add a song. ");
        System.out.println("Enter 3 to add several songs. ");
        System.out.println("Enter 4 to Remove a song. ");
        System.out.println("Enter 5 to remove multiple songs. ");
        System.out.println("Enter 6 to Shuffle songs. ");

        choice = keyboard.nextInt();
        if (choice > 6 || choice < 1) {
            System.out.println("Please enter a valid entry 1 -5.");
            menu();
        }
        return choice;


    }

    public int playAllMenu() {
        int choice = 0;
        System.out.println("Please make a selection from below:");
        System.out.println("Enter 1 to Play last selected song. ");
        System.out.println("Enter 2 to overwrite the last playing song (next). ");
        System.out.println("Enter 3 to repeat last played song. ");
        System.out.println("Enter 4 to repeat last selected song. ");
        System.out.println("Enter 5 to return to Main Menu. ");
        choice = keyboard.nextInt();

        if (choice > 6 || choice < 1) {
            menu();
        }
        return choice;
    }

    //loop all songs
    public void playAll() {
        //Scanner keyboard = new Scanner(System.in);
        int choice = 0;
        for (int i = 0; i < size; i++) {
            System.out.println(myPlaylist.get(i));
            System.out.println("Continue playing y for \"yes\" n for \"no\"");

            if (keyboard.nextLine().equalsIgnoreCase("n")) {
                return;
            }

        }
    }


}
