package com.company;

public class Main {

    public static void main(String[] args) {

        Song one = new Song("First Class");
        Song two = new Song("Willow");
        Song three = new Song("Welcome to New York");

        Playlist myPlayList = new Playlist();
        myPlayList.addSong(one);
        myPlayList.addSong(two);
        myPlayList.addSong(three);

        myPlayList.playAll();
        System.out.println();
        myPlayList.shuffle();
        myPlayList.playAll();

        //myPlayList.addMultiple();
        //myPlayList.playAll();

        System.out.println();
        myPlayList.removeSong(one);
        myPlayList.playAll();

        System.out.println();
        myPlayList.repeat("Willow");

    }
}
