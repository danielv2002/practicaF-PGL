import controller.Server;
import Model.User;

import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class main {
    public static void main(String[] args) {
        String cuerpo;
        String encabezados;
        String url;
        String version;
        int id;
        String metodo ;
        Scanner sc = new Scanner(System.in);
        // Obtén la cadena de entrada desde la consola
        System.out.println(" ingrese la linea de solicitud");
        String requestL = sc.next();

        // Define la expresión regular para la gramática BNF de las solicitudes HTTP
        String httpRegex = "^(GET|POST|PUT|DELETE)\\s(.+)\\sHTTP\\/1\\.[01]\\r\\n((.+:.+\\r\\n)*)\\r\\n(.*)\\r\\n[1-100]?";
        // Compila la expresión regular en un objeto Pattern
        Pattern pattern = Pattern.compile(httpRegex);

        // Crea un objeto Matcher para la cadena de entrada
        Matcher matcher = pattern.matcher(requestL);

        // Verifica si la cadena de entrada cumple con la gramática
        if (matcher.matches()) {
            System.out.println("ingrese \n1. para ver usuarios\n2. para crear un usuario\n3. para editar un usuario\n4. para eliminar un usuario\n5.para salir");
            int n = sc.nextInt();
            switch (n) {
                case 1:
                    System.out.println("1. ver lista de usuarios\n2.buscar un usuario");
                    int x = sc.nextInt();
                    switch (x) {
                        case 1:
                            metodo = matcher.group(1);
                            url = matcher.group(2);
                            version = matcher.group(3);
                            encabezados = matcher.group(4);
                            cuerpo = matcher.group(5);

                            Server pet = new Server(metodo, url, encabezados, cuerpo);
                            System.out.println(pet);
                            break;
                        case 2:
                            metodo = matcher.group(1);
                            url = matcher.group(2);
                            version = matcher.group(3);
                            encabezados = matcher.group(4);
                            cuerpo = matcher.group(5);
                            id = Integer.parseInt(matcher.group(6));
                            pet = new Server(metodo, url, encabezados, cuerpo, id);
                            System.out.println(pet);
                            break;

                    }
                case 2:
                    metodo = matcher.group(1);
                    url = matcher.group(2);
                    version = matcher.group(3);
                    encabezados = matcher.group(4);
                    cuerpo = matcher.group(5);

                    Server pet = new Server(metodo, url, encabezados, cuerpo);
                    System.out.println(pet);
                    break;
                case 3 | 4:
                    metodo = matcher.group(1);
                    url = matcher.group(2);
                    version = matcher.group(3);
                    encabezados = matcher.group(4);
                    cuerpo = matcher.group(5);
                    id = Integer.parseInt(matcher.group(6));
                    pet = new Server(metodo, url, encabezados, cuerpo, id);
                    System.out.println(pet);
                    break;
                case 5:
                    System.out.println("adios");
                    break;


            }
        } else if (matcher.group(1) !="GET" && matcher.group(1)!="POST" && matcher.group(1)!="PUT" &&  matcher.group(1)!="DELETE"){

            System.out.println("400 mala peticion");
        }else if(matcher.group(3)!="HTTP/1.0" && matcher.group(3)!="HTTP/1.1"){
            System.out.println("404 recurso no encontrado");
        } else if(matcher.group(5).isEmpty()){
            System.out.println("500 solicitud no procesada");
        }

    }
}





