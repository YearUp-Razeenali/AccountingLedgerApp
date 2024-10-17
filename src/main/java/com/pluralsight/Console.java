package com.pluralsight;

import java.util.Scanner;

public class Console {

    static Scanner scanner = new Scanner(System.in);

    public static String PromptForString(String prompt){
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static String PromptForString(){
        return scanner.nextLine();
    }

    public static boolean PromptForYesNo(String prompt){
        System.out.print(prompt + " ( Y for Yes, N for No ) ?");
        String userinput = scanner.nextLine();

        return
                (
                        userinput.equalsIgnoreCase("Y")
                                ||
                                userinput.equalsIgnoreCase("YES")
                );

    }

    public static short PromptForShort(String prompt){
        System.out.print(prompt);
        String value = scanner.nextLine();
        short userinput = Short.parseShort(value);
        return  userinput;
    }

    public static int PromptForInt(String prompt){
        System.out.print(prompt);
        String value = scanner.nextLine();
        int userinput = Integer.parseInt(value);
        return userinput;
    }

    public static int PromptForInt(){
        String value = scanner.nextLine();
        return Integer.parseInt(value);
    }

    public static double PromptForDouble(String prompt){
        System.out.print(prompt);
        String userInputs = scanner.nextLine();
        double userinput = Double.parseDouble(userInputs);
        return userinput;
    }

    public static byte PromptForByte(String prompt){
        System.out.print(prompt);
        String value = scanner.nextLine();
        byte userinput = Byte.parseByte(value);
        return userinput;
    }

    public static byte PromptForByte(){
        String value = scanner.nextLine();
        byte userinput = Byte.parseByte(value);
        return userinput;
    }

    public static float PromptForFloat(String prompt){
        System.out.print(prompt);
        String value = scanner.nextLine();
        float userinput =Float.parseFloat(value);
        return  userinput;
    }

    public static void animation() throws InterruptedException {
        for (int i = 0; i < 3; i++){
            System.out.print(".");
            Thread.sleep(500);
        }
        System.out.println();
    }

    public static void Loadinganimation() throws InterruptedException {
        System.out.print("Loading");
        for (int i = 0; i < 3; i++){
            System.out.print(".");
            Thread.sleep(1000);
        }
        System.out.print(".\n");
        printLine("-",101);
    }

    public static void printLine(String symbol, int i) {
        for (int j = 0 ; j < i; j++ ){
            System.out.print(symbol);
        }
        System.out.println();
    }
    public static void printLine(String symbol) {
        for (int j = 0 ; j < 101; j++ ){
            System.out.print(symbol);
        }
        System.out.println();
    }

}