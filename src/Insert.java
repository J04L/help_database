import java.util.Scanner;
import java.util.Vector;
import java.util.Arrays;
public class Insert {
    private final String eat;

    public Insert(){
        eat = ", ";
    }
    public Insert(String eat){
        this.eat = eat;
    }
    public  void insertNSS(Vector<String> datas, boolean insert) {
        System.out.println("----------------------------------------------");
        if (insert){
            Scanner scan = new Scanner(System.in);
            System.out.print("Table name: ");
            String table = scan.nextLine();
            System.out.println("----------------------------------------------\n" +
                    "insert into " + table + " values");
        }

        for(String row : datas){
            Vector<String> col = new Vector<>(Arrays.asList(row.split(eat)));
            System.out.print("(");
            for(String data : col){
                try{
                    Float.parseFloat(data);
                    System.out.print(col.indexOf(data) == col.size()-1 ? data + "),\n": data +", ");
                }catch(Exception e){
                    System.out.print("'" + data + "'");
                    System.out.print(col.indexOf(data) == col.size()-1 ? "),\n":", ");
                }
            }
        }
        System.out.println("----------------------------------------------");
    }
}
