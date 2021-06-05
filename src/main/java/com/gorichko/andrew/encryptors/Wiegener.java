package com.gorichko.andrew.encryptors;

import com.gorichko.andrew.Main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Wiegener {
    private String message, mappedKey;
    private Scanner in;


    public void HashWiegener(){
        System.out.println("Метод шифрування Віженера");
        System.out.println(" 1. Шифрування");
        System.out.println(" 2. Дешифрування");
        System.out.println(" 3. Повернутись в меню");
        System.out.println("");
        System.out.print("Введіть цифру: ");

        in = new Scanner(System.in);
        int choice = in.nextInt();
        in.nextLine();

        switch (choice){
            case 1:
                System.out.println("Шифрування");
                msgAndKey();
                cipherEncryption(message, mappedKey);
                Wiegener wiegener1 = new Wiegener();
                wiegener1.HashWiegener();
                break;
            case 2:
                System.out.println("Дешифрування");
                msgAndKey();
                cipherDecryption(message, mappedKey);
                Wiegener wiegener2 = new Wiegener();
                wiegener2.HashWiegener();
                break;
            case 3:
                Main main = new Main();
                main.init();
                break;
            default:
                System.out.println("Помилка. Введений пункт меню не існує.");
                Wiegener wiegener = new Wiegener();
                wiegener.HashWiegener();
        }
    }

    private void cipherDecryption(String message, String mappedKey) {
        int[][] table = createVigenereTable();
        String decryptedText = "";

        for (int i = 0; i < message.length(); i++) {
            if(message.charAt(i) == (char)32 && mappedKey.charAt(i) == (char)32){
                decryptedText += " ";
            } else {
                decryptedText += (char)(65 + itrCount((int)mappedKey.charAt(i), (int)message.charAt(i)));
            }
        }

        System.out.println("");
        System.out.println("Дешифрований текст: " + decryptedText);
        System.out.println("");
    }

    private int itrCount(int key, int msg) {
        int counter = 0;
        String result = "";
        for (int i = 0; i < 26; i++) {
            if(key+i > 90){
                result += (char)(key+(i-26));

            } else {
                result += (char)(key+i);
            }
        }

        for (int i = 0; i < result.length(); i++) {
            if(result.charAt(i) == msg){
                break;
            } else {
                counter++;
            }
        }
        return counter;
    }

    private void cipherEncryption(String message, String mappedKey) {
        int[][] table = createVigenereTable();
        String encryptedText = "";
        for (int i = 0; i < message.length(); i++) {
            if(message.charAt(i) == (char)32 && mappedKey.charAt(i) == (char)32){
                encryptedText += " ";
            } else {
                encryptedText += (char)table[(int)message.charAt(i)-65][(int)mappedKey.charAt(i)-65];
            }
        }

        System.out.println("");
        System.out.println("Шифрований текст: " + encryptedText);
        try (FileWriter writer = new FileWriter("cypher3.txt", false)) {
            writer.write(encryptedText);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("");
    }

    private int[][] createVigenereTable() {
        int[][] tableArr = new int[26][26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                int temp;
                if((i+65)+j > 90){
                    temp = ((i+65)+j) -26;
                    tableArr[i][j] = temp;
                } else {
                    temp = (i+65)+j;
                    tableArr[i][j] = temp;
                }
            }
        }

        return tableArr;
    }

    private void msgAndKey() {
        System.out.print("Введіть повідомлення: ");
        String msg = in.nextLine();
        msg = msg.toUpperCase();

        System.out.print("Введіть ключ: ");
        String key = in.next();
        in.nextLine();
        key = key.toUpperCase();

        String keyMap = "";

        for (int i = 0, j = 0; i < msg.length(); i++) {
            if(msg.charAt(i) == (char)32){
                keyMap += (char)32;

            } else {
                if(j < key.length()){
                    keyMap += key.charAt(j);
                    j++;
                } else {
                    j = 0;
                    keyMap += key.charAt(j);
                    j++;

                }
            }
        }

        message = msg;
        mappedKey = keyMap;
    }
}
