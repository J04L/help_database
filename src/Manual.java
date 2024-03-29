import java.util.regex.Pattern;

public class Manual extends Functions{

    public Manual(String[] comands){
        this.comands = comands;
        setComandsPattern();
    }
    private void setComandsPattern() {
        comandsPattern = new Pattern[programs.size()];
        for (int i =0; i < programs.size(); i++){
            comandsPattern[i] = Pattern.compile(programs.get(i).substring(0,3));
        }
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
                    manManual();
                    break;
            }
        }
    }
    private void manInsert(){
        System.out.println("""
                --------------------------------------------------------------------------------------------------------
                                                              ____INSERT____
                [0] insert ----------> utilizar insert
                [1] ejemplo.txt -----> archivo a leer (opcional)
                [x] -s(__) ----------> establecer separador (predeterminado: (,\\s))
                [x] -i --------------> poner nombre de la tabla y columnas
                
                aclaraciones:
                -los [ ] que hay al principio son solo para mostrar el orden que has de seguir
                no tienes que ponerlos en consola
                -si no pones archivo se rellenará y leerá todo de un archivo auxiliar: insertValues.txt
                -\\s simboliza un espacio, tienes que utilizar \\s en vez de el espacio normal en la funcion -s
                
                ejemplos:
                insert -s(-) --> accedes a insert e indicas que la frase me-haría-una-paja
                                 te la separe así: ('me', 'haría', 'una', 'paja'),
                insert pizza.txt -i -s(,) --> accedes a insert lee el texto que hay en pizza.txt
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
                insesrt -s(.) pandillerto.txt --> daría error porque el nombre del archivo no está
                                                    en la segunda posición
                --------------------------------------------------------------------------------------------------------""");
    }
    private void manManual(){
        System.out.println("""
                --------------------------------------------------------------------------------------------------------
                                                              ____MANUAL____
                [0] manual ---> utilizar manual
                [x] cre ------> muestra el manual de la funcion Create
                [x] ins ------> muestra el manual de la función Insert
                [x] upd ------> muestra el manual de la función Update
                --------------------------------------------------------------------------------------------------------""");
    }

    public boolean run() {
       if (machesAnyPattern()){
          findGuide();
          return true;
       }
       return false;

    }
}
