import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;
/*
----COMANDOS----
exit --> salir del modo escritura
    -i --> al salir agrega "insert into 'table_name' values"
    -s --> al salir inserta un separador (predeterminado: [, ])
 */
public class Main {
    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {

        Vector<String> vector = new Vector<>(10, 5);
        Vector<String> exit = new Vector<>(Arrays.asList(setElements(vector).split(" ")));
        Insert insert;
        if (exit.contains("-s")){
            System.out.print("""
                    _____SEPARAR DATOS_____
                    --\\n para salto de linea
                    --\\s para espacio
                    --->>\s""");
            String eat = scan.next();
            insert = new Insert(eat);
        }
        else insert = new Insert();
        insert.insertNSS(vector, exit.contains("-i"));
    }

    private static String setElements(Vector<String> vector) {
        Pattern pattern = Pattern.compile("^exit\\s?.*");
        Matcher exit;
        System.out.print("___INSERT___\n");

        while(true){
            String row = scan.nextLine();
            exit = pattern.matcher(row);
            if (exit.matches()) return row;
            vector.add(row);
        }
    }
}