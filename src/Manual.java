import java.util.regex.Pattern;

public class Manual extends Functions{

    public Manual(String[] comands){
        this.comands = comands;
        setComandsPattern();
    }
    private void setComandsPattern() {
        Pattern[] comands = new Pattern[programs.size()];
        for (int i =0; i < comands.length; i++){
            comands[i] = Pattern.compile(programs.get(i).substring(0,3));
        }
        comandsPattern = comands;
    }

    public void findGuide(){
        for(String guide : comands){
            switch (findPattern(guide)) {
                case 0:
                    System.out.println("En proceso de creación...");
                    break;
                case 1:
                    manInsert();
                    break;
                case 2:
                    System.out.println("En proceso de creación....");
                    break;
                case 3:
                    System.out.println("En proceso de creación..");
                    break;
            }
        }
    }
    private void manInsert(){
        System.out.println("""
                [0] insert ----------> utilizar insert
                [1] "ejemplo.txt" ---> archivo a leer (opcional)
                [x] -s(, ) ----------> establecer separador (predeterminado: (,\\s))
                [x] -i --------------> poner nombre de la tabla y columnas
                
                aclaraciones:
                -los [ ] que hay al principio son solo para mostrar el orden que has de seguir
                no tienes que ponerlos en consola
                -si no poner archivo se rellenará y leerá todo de insertValues.txt
                
                ejemplos:
                insert -s(-) --> accedes a insert e indicas que la frase me-haría-una-paja
                                 te la separe así: ('me', 'haría', 'una', 'paja'),
                insert "pizza.txt" -i -s(,) --> accedes a insert lee el texto que hay en pizza.txt
                                                y separa:
                                                me,apetece,comerme,una,polla
                                                pensabas,que,iba,a,decir,pizza?
                                                
                                                pero primero te pide el nombre de la tabla y el de
                                                las columnas (si en columnas pones "none" no imprime
                                                ninguna columna)
                                                
                                                en conjunto quedaría así --->
                                                insert into pepinillos(rabano, rabioli, ribosoma) values
                                                ('me', 'apetece', 'comerme', 'una', 'polla'),
                                                ('pensabas', 'que', 'iba', 'a', 'decir', 'pizza?')
                insesrt -s(.) "pandillerto.txt" --> daría error porque el nombre del archivo no está
                                                    en la segunda posición""");
    }

    public boolean run() {
       if (machesAnyPattern()){
          findGuide();
           return true;
       }
       return false;

    }
}
