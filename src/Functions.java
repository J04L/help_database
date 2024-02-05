import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
public class Functions {
    protected static final ArrayList<String> programs = new ArrayList<>(Arrays.asList("create", "insert", "update", "manual", "exit"));
    //nombre de los programs a usar
    protected String file;
    //nombre del archivo
    protected  Pattern[] comandsPattern;
    //patrón de cada comando de cada funcionalidad (Insert, Create, Update...)
    protected String[] comands;
    //comandos sin filtro (no se ha comprobado si hay errores de formulación)
    public static final Pattern filePattern = Pattern.compile("^.*\\.txt$"); //patrón de los archivos

    public static String getFile(ArrayList<String> functionList /*lista de comandos sin el nombre de la función*/){
        //se comprueba si se ha insertado un archivo y que el nombre siga con el patrón
        String file = null;
        try{
            if (filePattern.matcher(functionList.get(0)).matches()) {
                //¿el primer elemento coincide con el patrón de archivos?

                file = functionList.get(0);
                //si conicide le quitamos las comillas

                functionList.remove(0);
                //y eliminamos el nombre del archivo de la lista de comandos para mayor organización
            }
        } catch (Exception e) {
        }
        return file;
    }
    public int findPattern(String comand){
        int i=0;
        //contador
        while (i<comandsPattern.length){
            if(comandsPattern[i].matcher(comand).matches()) return i;
            //devuelve la posición del comando en la lista de comandos de la funcionalidad
            i++;
        }
        return -1;
    }
    public boolean machesAnyPattern(){
        for (String comand : comands){
            boolean maches = false;
            //almacena si coincide o no
            int i =0;
            //contador
            while (i < comandsPattern.length) {
                maches = comandsPattern[i].matcher(comand).matches();
                if (maches) break;
                //si el comando coincide con el patrón break;
                i++;
            }
            if (!maches) return false;
            //si termina el bulce y el comando no coincide con ningún patrón return false;
        }
        return true;
        //si termina el bucle y todos los comandos han coincidido con alguno devuelve true;
    }
}
