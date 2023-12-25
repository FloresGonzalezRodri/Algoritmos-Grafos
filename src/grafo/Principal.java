/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafo;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author LENOVO
 */
public class Principal 
{
    public static void main(String[] args)
    {
        char opcion;
        int idInicio, idDestino, longitud;
        Scanner scanner=new Scanner(System.in);
        System.err.print("id del primer nodo: ");
        idInicio=scanner.nextInt();
        Nodo nodo=new Nodo(idInicio);
        do
        {            
            System.out.println("a. Anadir nodo");
            System.out.println("b. Eliminar nodo");
            System.out.println("c. Recorrer");
            System.err.println("d. Salir");
            opcion=scanner.next().charAt(0);
            
            switch(opcion)
            {
                case 'a':
                    System.err.print("id del nodo origen: ");
                    idInicio=scanner.nextInt();
                    System.err.print("id del nodo a enlazar: ");
                    idDestino=scanner.nextInt();
                    System.err.print("longitud:");
                    longitud=scanner.nextInt();
                    nodo.enlazar(longitud,idInicio, idDestino);
                    break;
                case 'b':
                    System.err.print("id del nodo origen: ");
                    idInicio=scanner.nextInt();
                    System.err.print("id del nodo a eliminar: ");
                    idDestino=scanner.nextInt();
                    nodo.desenlazar(idInicio,idDestino);
                    break;
                case 'c':
                    nodo.recorrer();
                    break;
            }
        }
        while(opcion!='d');      
    }
    
    
 
}
