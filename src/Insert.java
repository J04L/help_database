import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Insert extends Functions {
    private final String defFile = "insertValues.txt";
    private String eat;
    private boolean putInsert;
    public static final Pattern[] comandsPattern = {
            Pattern.compile("^-s\\(.*\\)"),
            Pattern.compile("-i")
    };
    public Insert(String[] comands, String file){
        setFile(file);
        eat = ", ";
        comandRead(comands);
    }
    public void run() {
        if (file.equals(defFile)) valuesWriter();
        valuesReader();
    }
    public void valuesReader(){
        Scanner scan = new Scanner(System.in);
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            System.out.println("----------------------------------------------");
            if (putInsert){
                System.out.print("Table name --> ");
                String table = scan.nextLine();
                System.out.print("Colums --> ");
                String colums = scan.nextLine();
                System.out.println("----------------------------------------------" +
                        "\ninsert into " + table + (colums.equals("none")? "" : "(" + colums + ")") + " values");
            }

            String row;
            while((row = reader.readLine()) != null){
                ArrayList<String> rowList = new ArrayList<>(Arrays.asList(row.split(eat)));
                System.out.print("(");
                for(String data : rowList){
                    try{
                        Float.parseFloat(data);
                        System.out.print(rowList.indexOf(data) == rowList.size()-1 ? data + "),\n": data +", ");
                    }catch(Exception e){
                        System.out.print("'" + data + "'");
                        System.out.print(rowList.indexOf(data) == rowList.size()-1 ? "),\n":", ");
                    }
                }
            }
            System.out.println("----------------------------------------------");
        }catch (IOException e){
            System.out.println("-----[ERROR] No existe el fichero " + file);
        }
    }

    private void comandRead(String[] comandsList){
        for(String comand : comandsList){
            switch (findPattern(comand)){
                case 0:
                    setEat(comand);
                    break;
                case 1:
                    putInsert = true;
                    break;
                case 2: break;
                case -1:
                    System.out.println("-----[ERROR] No existe el comando " + comand);
                    break;
            }
        }
    }
    private int findPattern(String comand){
        int i=0;
        while (i<comandsPattern.length){
            if(comandsPattern[i].matcher(comand).matches()) return i;
            i++;
        }
        return -1;
    }
    public static boolean machesAnyPattern(String[] comandsList){
        for (String comand : comandsList){
            boolean maches = false;
            int i =0;
            while (i < comandsPattern.length) {
                if (comandsPattern[i].matcher(comand).matches()) {
                    maches = true;
                    break;
                }
                i++;
            }
            if (!maches) return false;
        }
        return true;
    }
    public void setEat(String comand){
        this.eat = comand.substring(2).replaceAll("[()]", "");
    }
    public void setFile(String file){
        this.file = file==null?defFile:file;
    }
    private void valuesWriter(){
        Scanner scan = new Scanner(System.in);
        try(FileWriter writer = new FileWriter(file)){
            while(true){
                System.out.print("- ");
                String row = scan.nextLine();

                if (row.equals("exit")) break;
                writer.write(row + "\n");
            }
        }catch (Exception e){
            System.out.println("-----[ERROR]");
        }
    }
}
