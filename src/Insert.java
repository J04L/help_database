import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.regex.Pattern;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class Insert extends Functions {
    private final String defFile = "insertValues.txt";
    private String eat;
    public Insert(String[] comands, String file){
        setFile(file);
        eat = ", ";
        this.comands = comands;
        comandsPattern = new Pattern[]{
                Pattern.compile("^-s\\(.*\\)"),
                Pattern.compile("-i")
        };
    }
    public boolean run() {
        if(machesAnyPattern()){
            comandsExecuter();
            return true;
        }
        return false;
    }
    public void valuesReader(){
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
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
        }catch (IOException e){
            System.out.println("-----[ERROR] No existe el fichero " + file);
        }
    }
    private void printInsert(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Table name --> ");
        String table = scan.nextLine();
        System.out.print("Colums --> ");
        String colums = scan.nextLine();
        System.out.println("--------------------------------------------------------------------------------------------------------" +
                "\ninsert into " + table + (colums.equals("none")? "" : "(" + colums + ")") + " values");
    }
    public void comandsExecuter(){
        boolean putInsert = false;
        for(String comand : comands){
            switch (findPattern(comand)){
                case 0:
                    setEat(comand);
                    break;
                case 1:
                    putInsert = true;
                    break;
                case 2: break;
            }
        }
        if (file.equals(defFile)) valuesWriter();
        System.out.println("--------------------------------------------------------------------------------------------------------");
        if(putInsert) printInsert();
        valuesReader();
        System.out.println("--------------------------------------------------------------------------------------------------------");

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
            System.out.println("write-->");
            while(true){
                String row = scan.nextLine();

                if (row.equals("exit")) break;
                writer.write(row + "\n");
            }
        }catch (Exception e){
            System.out.println("-----[ERROR]");
        }
    }
}
