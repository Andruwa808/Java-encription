package com.gorichko.andrew.encryptors;

import com.gorichko.andrew.Main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Hill {
    private Scanner in;

    public void HashHill(){
        System.out.println("Метод шифрування Хілла");
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
                cipherEncryption();
                Hill hill = new Hill();
                hill.HashHill();
                break;
            case 2:
                System.out.println("Дешифрування");
                cipherDecryption();
                Hill hill1 = new Hill();
                hill1.HashHill();
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

    private void cipherDecryption() {
        System.out.print("Введіть повідомлення: ");
        String msg = in.nextLine();
        msg = msg.replaceAll("\\s" , "");
        msg = msg.toUpperCase();

        int lenChk = 0;
        if (msg.length() % 2 != 0){
            msg += "0";
            lenChk = 1;
        }

        int[][] msg2D = new int[2][msg.length()];
        int itr1 = 0;
        int itr2 = 0;
        for (int i = 0; i < msg.length(); i++){
            if (i%2 == 0){
                msg2D[0][itr1] = ((int)msg.charAt(i)) - 65;
                itr1++;
            } else {
                msg2D[1][itr2] = ((int)msg.charAt(i)) - 65;
                itr2++;
            }
        }

        System.out.print("Введіть ключ з 4 букв: ");
        String key = in.nextLine();
        key = key.replaceAll("\\s","");
        key = key.toUpperCase();

        int[][] key2D = new int[2][2];
        int itr3 = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                key2D[i][j] = (int)key.charAt(itr3)-65;
                itr3++;
            }
        }

        int deter = key2D[0][0] * key2D[1][1] - key2D[0][1] * key2D[1][0];
        deter = moduloFunc(deter, 26);

        int mulInverse = -1;
        for (int i = 0; i < 26; i++) {
            int tempInv = deter * i;
            if (moduloFunc(tempInv, 26) == 1){
                mulInverse = i;
                break;
            } else {
                continue;
            }
        }


        int swapTemp = key2D[0][0];
        key2D[0][0] = key2D[1][1];
        key2D[1][1] = swapTemp;

        key2D[0][1] *= -1;
        key2D[1][0] *= -1;

        key2D[0][1] = moduloFunc(key2D[0][1], 26);
        key2D[1][0] = moduloFunc(key2D[1][0], 26);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                key2D[i][j] *= mulInverse;
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                key2D[i][j] = moduloFunc(key2D[i][j], 26);
            }
        }

        String decrypText = "";
        int itrCount = msg.length() / 2;
        if (lenChk == 0){
            for (int i = 0; i < itrCount; i++) {
                int temp1 = msg2D[0][i] * key2D[0][0] + msg2D[1][i] * key2D[0][1];
                decrypText += (char)((temp1 % 26) + 65);
                int temp2 = msg2D[0][i] * key2D[1][0] + msg2D[1][i] * key2D[1][1];
                decrypText += (char)((temp2 % 26) + 65);
            }
        } else {
            for (int i = 0; i < itrCount-1; i++) {
                int temp1 = msg2D[0][i] * key2D[0][0] + msg2D[1][i] * key2D[0][1];
                decrypText += (char)((temp1 % 26) + 65);
                int temp2 = msg2D[0][i] * key2D[1][0] + msg2D[1][i] * key2D[1][1];
                decrypText += (char)((temp2 % 26) + 65);
            }
        }

        System.out.println("");
        System.out.println("Дешифрований текст: " + decrypText);
        System.out.println("");

    }

    private void cipherEncryption() {
        System.out.print("Введіть повідомлення: ");
        String msg = in.nextLine();
        msg = msg.replaceAll("\\s" , "");
        msg = msg.toUpperCase();

        int lenChk = 0;
        if (msg.length() % 2 != 0){
            msg += "0";
            lenChk = 1;
        }

        int[][] msg2D = new int[2][msg.length()];
        int itr1 = 0;
        int itr2 = 0;
        for (int i = 0; i < msg.length(); i++){
            if (i%2 == 0){
                msg2D[0][itr1] = ((int)msg.charAt(i)) - 65;
                itr1++;
            } else {
                msg2D[1][itr2] = ((int)msg.charAt(i)) - 65;
                itr2++;
            }
        }


        System.out.print("Введіть ключ з 4 букв: ");
        String key = in.nextLine();
        key = key.replaceAll("\\s","");
        key = key.toUpperCase();

        int[][] key2D = new int[2][2];
        int itr3 = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                key2D[i][j] = (int)key.charAt(itr3)-65;
                itr3++;
            }
        }

        int deter = key2D[0][0] * key2D[1][1] - key2D[0][1] * key2D[1][0];
        deter = moduloFunc(deter, 26);

        int mulInverse = -1;
        for (int i = 0; i < 26; i++) {
            int tempInv = deter * i;
            if (moduloFunc(tempInv, 26) == 1){
                mulInverse = i;
                break;
            } else {
                continue;
            }
        }

        if (mulInverse == -1){
            System.out.println("Недійсний ключ");
            System.exit(1);
        }

        String encrypText = "";
        int itrCount = msg.length() / 2;
        if (lenChk == 0){
            for (int i = 0; i < itrCount; i++) {
                int temp1 = msg2D[0][i] * key2D[0][0] + msg2D[1][i] * key2D[0][1];
                encrypText += (char)((temp1 % 26) + 65);
                int temp2 = msg2D[0][i] * key2D[1][0] + msg2D[1][i] * key2D[1][1];
                encrypText += (char)((temp2 % 26) + 65);
            }
        } else {
            for (int i = 0; i < itrCount-1; i++) {
                int temp1 = msg2D[0][i] * key2D[0][0] + msg2D[1][i] * key2D[0][1];
                encrypText += (char)((temp1 % 26) + 65);
                int temp2 = msg2D[0][i] * key2D[1][0] + msg2D[1][i] * key2D[1][1];
                encrypText += (char)((temp2 % 26) + 65);
            }
        }

        System.out.println("");
        System.out.println("Шифрований текст: " + encrypText);
        try (FileWriter writer = new FileWriter("cypher2.txt", false)) {
            writer.write(encrypText);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("");

    }

    public int moduloFunc(int a, int b){
        int result = a % b;
        if (result < 0){
            result += b;
        }
        return result;
    }

}
