import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

/*
----COMANDOS----
exit --> salir del modo escritura
    -i --> al salir agrega "insert into 'table_name' values"
    -s --> al salir inserta un separador (predeterminado: [, ])
 */
public class Main {
    public static Scanner scan = new Scanner(System.in);
    private static final ArrayList<String> programs = new ArrayList<>(Arrays.asList("create", "insert", "update"));
    public static void main(String[] args) {
        selectProgram();
    }

    private static String[] comandReader(String comandLine) {
        return comandLine.replaceAll(" ", "").split("-");
    }
    private static String[] checkProgram(){
        String[] comands;
        while (true){
            System.out.print("------>>> ");
            comands = comandReader(scan.nextLine());
            if (programs.contains(comands[0])) return comands;
            System.out.println("-----[ERROR] función " + comands[0] + " no registrada");
        }
    }
    private static void selectProgram(){
        String[] function = checkProgram();
        switch(programs.indexOf(function[0])){
            case 0:
                System.out.println("En proceso de creación...");
                break;
            case 1:
                String[] comands = Arrays.copyOfRange(function,1, function.length);
                if(Insert.machesAnyPattern(comands)){
                    Insert insert = new Insert(comands);
                    insert.run();
                }
                else {
                    System.out.println("-----[ERROR] algún comando incorrecto");
                    selectProgram();
                }
                break;
            case 2:
                System.out.println("En proceso de creación....");
                break;
        }
    }
}