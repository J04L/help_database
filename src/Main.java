import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/*
----COMANDOS----
exit --> salir del modo escritura
    -i --> al salir agrega "insert into 'table_name' values"
    -s --> al salir inserta un separador (predeterminado: [, ])
 */
public class Main {
    public static Scanner scan = new Scanner(System.in);
    private static final ArrayList<String> programs = new ArrayList<>(Arrays.asList("create", "insert", "update", "manual"));
    public static void main(String[] args) {
        selectProgram();
    }
    private static ArrayList<String> chooseFunction(){
        while (true){
            System.out.print("------>>> ");
            ArrayList<String> comands = new ArrayList<>(Arrays.asList(scan.nextLine().split(" ")));
            if (programs.contains(comands.get(0))) return comands;
            System.out.println("-----[ERROR] función " + comands.get(0) + " no registrada");
        }
    }
    private static void selectProgram(){
        ArrayList<String> functionList = chooseFunction();
        String program = functionList.get(0);
        functionList.remove(0);

        String file = null;
        if(Functions.filePattern.matcher(functionList.get(0)).matches()){
            file = functionList.get(0).replaceAll("\"", "");
            functionList.remove(0);
        }


        switch(programs.indexOf(program)){
            case 0:
                System.out.println("En proceso de creación...");
                break;
            case 1:
                String[] comands = functionList.toArray(new String[0]);
                if(Insert.machesAnyPattern(comands)){
                    Insert insert = new Insert(comands, file);
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