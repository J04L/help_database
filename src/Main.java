import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
public class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        selectProgram();
    }
    private static ArrayList<String> chooseFunction(){
        while (true){
            System.out.print("------>>> ");
            ArrayList<String> comands = new ArrayList<>(Arrays.asList(scan.nextLine().split(" ")));
            //leemos la línea de comandos, la dividimos por espacios transformándola en una Lista

            if (Functions.programs.contains(comands.get(0))) return comands;
            //si el primer elemento de la lista coincide con alguno de los nombres de las funcionalidades devuelve la lista
            System.out.println("-----[ERROR] función " + comands.get(0) + " no registrada");
            //si no...
        }
    }
    private static void selectProgram(){
        while(true){
            ArrayList<String> functionList = chooseFunction();
            if (checkExit(functionList)) break;
            String programName = functionList.get(0);
            //se coge el nombre de la función elegida
            functionList.remove(0);
            //se elimina para mayor organización

            String file = Functions.getFile(functionList);
            //se coge el nombre del archivo (si hay)
            String[] comands = functionList.toArray(new String[0]);
            //cambiamos el tipo a String[] para mejor rendimiento

            switch (Functions.programs.indexOf(programName)) {
                case 0:
                    System.out.println("En proceso de creación...");
                    break;
                case 1:
                    Insert insert = new Insert(comands, file);
                    if (!insert.run()) System.out.println("----[ERROR] Sintax error");
                    //si no se ha podido ejecutar volvemos a hacer el método selectProgram
                    break;
                case 2:
                    System.out.println("En proceso de creación....");
                    break;
                case 3:
                    Manual manual = new Manual(comands);
                    if (!manual.run()) System.out.println("----[ERROR] Sintax error");
                    //si no se ha podido ejecutar volvemos a hacer el método selectProgram
                    break;
            }
        }
    }

    private static boolean checkExit(ArrayList<String> functionList) {
        if(functionList.size()>1) return false;
        else return functionList.contains("exit");
    }
}