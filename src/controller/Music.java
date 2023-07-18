/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import sun.audio.AudioData;
import sun.audio.AudioStream;

/**
 *
 * @author Le Chi Khiem - CE171515
 */
class Music {

    public AudioStream startMusic() {
        AudioStream BGM = null;

        try {
            InputStream test = new FileInputStream("C:\\Users\\PC\\Documents\\All_file_myself\\PikachuGameProject\\music\\start.wav");
            BGM = new AudioStream(test);
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (IOException err) {
            System.out.println(err.toString());
        }
        return BGM;
    }

    public AudioStream winningMusic() {
        AudioStream BGM = null;
        AudioData MD;

        try {
            InputStream test = new FileInputStream("C:\\Users\\PC\\Documents\\All_file_myself\\PikachuGameProject\\music\\win.wav");
            BGM = new AudioStream(test);

            //MD = BGM.getData();
            //loop = new ContinuousAudioDataStream(MD);
        } catch (FileNotFoundException e) {
            System.out.print(e.toString());
        } catch (IOException error) {
            System.out.print(error.toString());
        }
        return BGM;
    }

    public AudioStream loseMusic() {
        AudioStream BGM = null;
        AudioData MD;

        try {
            InputStream test = new FileInputStream("C:\\Users\\PC\\Documents\\All_file_myself\\PikachuGameProject\\music\\lose.wav");
            BGM = new AudioStream(test);

            //MD = BGM.getData();
            //loop = new ContinuousAudioDataStream(MD);
        } catch (FileNotFoundException e) {
            System.out.print(e.toString());
        } catch (IOException error) {
            System.out.print(error.toString());
        }
        return BGM;
    }

    public AudioStream warningMusic() {
        AudioStream BGM = null;
        AudioData MD;

        try {
            InputStream test = new FileInputStream("C:\\Users\\PC\\Documents\\All_file_myself\\PikachuGameProject\\music\\warning.wav");
            BGM = new AudioStream(test);

            //MD = BGM.getData();
            //loop = new ContinuousAudioDataStream(MD);
        } catch (FileNotFoundException e) {
            System.out.print(e.toString());
        } catch (IOException error) {
            System.out.print(error.toString());
        }
        return BGM;
    }
}
