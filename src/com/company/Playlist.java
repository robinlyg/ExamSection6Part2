//The playlist class makes a few important assumptions, that there is already songs in the list
//Any Loop or repeat method has been modified to not create an infinite loop but still demonstrates the repetition of a song
//Given the program is text based to continue playing the list we select 'y' to select a new option we use 'n' to generate a new
//menu that can be selected from.

package com.company;
import java.util.*;
public class Playlist {


    private LinkedList<Song> myPlaylist;
    private int currentSong;
    int size = 0;
    private Song lastPlayed;
    private Song lastSelected;
    Scanner keyboard = new Scanner(System.in);

    /**
     * constructor creates a new playlist with an empty LinkedList
     */
    public Playlist() {
        myPlaylist = new LinkedList<>();
    }

    /**
     *  adds a song to the back of the playlist and increases the size of the list
     * @param s the song to be added
     */
    public void addSong(Song s) {
        myPlaylist.add(s);
        size++;
    }

    /**
     * getSize
     * @return int size
     */
    public int getSize() {
        return size;
    }

    /**
     * addMultiple - adds multiple songs to the playlist and increases the size once for each song added
     */
    public void addMultiple() {
        String title = "";
        //ask user for song title in a do while loop, user can add however many songs when they are done they type "done"
        do {
            System.out.println("Please enter the song title you'd like to add (enter done to exit) ");
            title = keyboard.nextLine().toLowerCase();
            if (title.equalsIgnoreCase("done"))
                return;
            //we add to size for each song
            size++;
            //creat a new song objects
            Song newSong = new Song(title);
            //and add that song to the playlist
            myPlaylist.add(newSong);

        } while (!title.equalsIgnoreCase("done"));
    }

    /**
     * playLastSelected song - user must manually select a song to be played
     * @param title the title of the song to be selected
     * @return the selected song and set to lastSelected
     */
    public Song playLastSelected(String title) {
        //set current song to the index of the selected song, otherwise it reverts to the original place
        currentSong = getIndex(title);
        //assign last selected to title and return
        return lastSelected = getSong(title);

    }

    /**
     *loopLastSelected - loops the last selected song
     * choose to not implement an infinite loop
     */
    public void loopLastSelected() {
        for (int i = 0; i < 15; i++) {
            System.out.println(lastSelected);
        }
    }

    /**
     * getSong - returns the song with specified title
     * @param obj title of the song to be returned
     * @return song
     */
    public Song getSong(String obj) {
        for (Song s : myPlaylist) {
            if (s.title.equalsIgnoreCase(obj))
                return s;
        }

        return null;

    }

    /**
     * getIndex of song
     * @param obj title of song to find the index of
     * @return index of the obj
     */
    public int getIndex(String obj) {
        int count = 0;
        for (Song s : myPlaylist) {
            if (s.title.equalsIgnoreCase(obj))
            break;

            count++;
        }

        return count;
    }

    /**
     * overWrite - essentially playNext
     */
    public void overWrite() {
        //play/printout the next song so currentSong +1 but since our playAll loop continues beyond the index of
        //our playlist need to mod by the size
        System.out.println(myPlaylist.get((currentSong % (getSize()) +1)));
    }

    //loop last played song
    //take lastPlayedSong and loop

    /**
     * loopLastPlayed Song - loop through the last selected song
     * again  choose not to implement an infinite loop
     */
    public void loopLastPlayed() {
        for (int j = 0; j < 15; j++) {
            System.out.println(lastPlayed);

        }
    }

    /**
     * shuffle - shuffles the playlist without altering its original order
     */
    public void shuffle() {
        //using an array to track the random numbers generated
        ArrayList<Integer> prevPlay = new ArrayList<>(getSize());


        Random rng = new Random();
        int randIndex;

        //loop through the size of the playlist
        for (int i = 0; i < getSize(); i++) {
            //generate a random number
            do {
                randIndex = rng.nextInt(getSize());

            //check if the rng has already been used if so generate a new random number
            } while (prevPlay.contains(randIndex));

            //print/play the song at the random number index
            System.out.println(myPlaylist.get(randIndex));

            // add the successful rng to the array so its not used again
            prevPlay.add(randIndex);

        }
    }

    /**
     * removeSong - removes a song from the playlist
     * @param song the object to be removed
     * also must reduce the size of the list
     */
    public void removeSong(Song song) {
        myPlaylist.remove(song);
        size--;
    }


    /**
     * removeMultiple, allows the user to remove multiple songs at once
     * similar to the logic of add multiple
     */
    public void removeMultiple() {
        keyboard.nextLine();
        String title = "";
        while (!title.equalsIgnoreCase("done")) {
            System.out.println("Please enter the song title to remove (enter done to exit) ");
            title = keyboard.nextLine().toLowerCase();
            if (title.equalsIgnoreCase("done"))
                break;
            size--;
            myPlaylist.remove(getSong(title));

        }
    }


    /**
     * PlayAll is the main driver of the playlist. Its used to play/print the songs
     * Also provides the other options such as play last selected, overWrite current song,
     * loop last played, and loop last selected
     * given our limitations using a text format to continue playing the playlist we select 'y' or 'n' if
     * we want to make a new selection.
     */
    public void playAll() {keyboard.nextLine();
        for (int i = 0; i < (getSize()*8); i++) {
            currentSong = i;
            System.out.println(myPlaylist.get(i % (getSize())));

            System.out.println("Continue playing y for \"yes\" n for \"no\"");
            int selection = 0;
            if (keyboard.nextLine().equalsIgnoreCase("n")) {
                selection = playAllMenu();
                if (selection == 5)
                    break;
            }
            switch (selection) {
                case 1: //play last selected
                    keyboard.nextLine();
                    System.out.println("Enter title of selected song ");
                    String titleOfLastSelected = keyboard.nextLine().toLowerCase();
                    System.out.println(playLastSelected(titleOfLastSelected));
                    i = currentSong;
                    break;
                case 2: //overwrite the last playing song (next)
                    keyboard.nextLine();
                    overWrite();
                    i+=1;
                    break;
                case 3: //repeat last played song
                    keyboard.nextLine();
                    lastPlayed = myPlaylist.get(i % (getSize()));
                    loopLastPlayed();
                    break;
                case 4: //repeat last selected song
                    keyboard.nextLine();
                    if(lastSelected == null) {
                        System.out.println("Error no previously selected song, please enter song title");
                        String title = keyboard.nextLine().toLowerCase();
                        lastSelected = getSong(title);
                    }
                    loopLastSelected();
                    break;

            }

            lastPlayed = myPlaylist.get(i % (getSize()-1));
        }
    }


    /**
     * menu - is the main menu and returns an int to the main program to use in a switch statement
     * @return int choice
     */
    public int menu() {
        int choice = 0;
        System.out.println("Please make a selection from below:");
        System.out.println("Enter 1 to Play all songs. ");
        System.out.println("Enter 2 to add a song. ");
        System.out.println("Enter 3 to add several songs. ");
        System.out.println("Enter 4 to Remove a song. ");
        System.out.println("Enter 5 to remove multiple songs. ");
        System.out.println("Enter 6 to Shuffle songs. ");
        System.out.println("Enter 7 to Exit. ");

        choice = keyboard.nextInt();
        if (choice > 7 || choice < 1) {
            System.out.println("Please enter a valid entry 1 -5.");
            menu();
        }
        return choice;


    }

    /**
     * playAll menu is speciic to what you can do while the playlist is playing.
     * @return int choice
     */
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



}
