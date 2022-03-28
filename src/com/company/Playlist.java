package com.company;

import java.util.*;

public class Playlist {
    //linkedList circular data structure
    private DoubleLinkedList<Song> myPlaylist = new DoubleLinkedList<>();
    Iterator<Song> iter = myPlaylist.iterator();
    int size = 0;
    private int lastPlayed = 0;
    private int current = 0;


    public Playlist(){
        myPlaylist = new DoubleLinkedList<>();
    }
    // take multiple songs and add to list
    public void addSong(Song s)
    {
        myPlaylist.add(s);
        size++;
    }
    public int getSize(){
        return size;
    }
    public void addMultiple(){
        String title = "";
        Scanner keyboard = new Scanner(System.in);
        while(!title.equalsIgnoreCase("done"))
        {
            System.out.println("Please enter the song title you'd like to add (enter done to exit) ");
            title = keyboard.nextLine();
            if(title.equalsIgnoreCase("done"))
                break;
            size++;
            Song newSong = new Song(title);
            myPlaylist.add(newSong);

        }
    }

    //play last selected song
        //need helper select??
    public void playLastSelected(){}

    //overwrite the last playing song..next/prev??
    public void overWrite(){
        //if next current +1 get(index )

        //if prev lastSelected

        //if selectAny song - getSong(String)

    }


    //shuffle
    public void shuffle(){
             Collections.shuffle(this.myPlaylist);
    }

    //remove
    public void removeSong(Song song){
        myPlaylist.remove(song);
        size--;
    }

    //serves as selected song
    public Song getSong(String obj)
    {
        for (Song s: myPlaylist ) {
            if( s.title.equalsIgnoreCase(obj))
                return s;
        }

        return null;

    }
    public Song songIndexAt(int i){

    }
    /*public Song songIndexAt(int i){
        Iterator<Song> iterator = myPlaylist.listIterator(i);
        Song s =
        return iterator;
    }*/

    //remove multiple
    public void removeMultiple()
    {
        String title = "";
        Scanner keyboard = new Scanner(System.in);
        while(!title.equalsIgnoreCase("done"))
        {
            System.out.println("Please enter the song title to remove (enter done to exit) ");
            title = keyboard.nextLine();
            if(title.equalsIgnoreCase("done"))
                break;
         size--;
         myPlaylist.remove(getSong(title));

        }
    }

    //loop last played song
    //take lastPlayedSong and loop

    //loop all songs
    public void playAll(){

      /*  while(iter.hasNext()){
            Song nextElement = iter.next();
            System.out.println(nextElement.title);
        }*/

        for (int i = 0; i < size; i++) {
            System.out.println(myPlaylist.get(i));
        }
    }

    //loop selected song - iterator should point to this song
    public void repeat(String obj){
        Song s = getSong(obj);
        int count = 0;
        while(count < 15){
            System.out.println(s);
            count++;
        }
    }



}
