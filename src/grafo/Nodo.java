/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package grafo;

import java.util.ArrayList;
import java.util.Stack;


/**
 * Clase que define un nodo
 * @author Flores Gonzalez Rodrigo
 */
public class Nodo 
{
    int id;
    int distanciaAcumulada;    
    Nodo anterior;
    Stack<Arista> aristas;
    
    /**
     * Clase anidad para crear aristas siempre 
     *  y cuando se cree un nodo antes
     */
    protected class Arista 
    {
        int longitud;
        Nodo destino;
        
        public Arista(int longitud, Nodo destino) 
        {
            this.destino=destino;
            this.longitud = longitud;
        }
    }
    
    public Nodo(int id)
    {
        this.id=id; 
        aristas=new Stack<>();
    }   
    
    /**
     * Recorrer el grafo e imprimir las id de los nodos
     */
    public void recorrer()
    {
        ArrayList<Nodo> descubiertos=new ArrayList<>();
        descubiertos.add(this);
        Nodo clave;
        for(int i=0;i<descubiertos.size();i++)
        {
            clave=descubiertos.get(i);
            System.out.print(clave.id + ": ");
            
            for(Arista arista:clave.aristas)
            { 
                System.out.print(arista.destino.id + " ");
                if(!descubiertos.contains(arista.destino))
                    descubiertos.add(arista.destino);  
            } 
            System.out.println();
        }           
    }
    
    /**
     * Retornar el nodo que tiene un id dado
     * @param id del del nodo a buscar
     * @return Nodo en el grafo cuyo identificador es id
     */
    public Nodo buscar(int id)
    {
        ArrayList<Nodo> descubiertos=new ArrayList<>();
        descubiertos.add(this);
        for(int i=0;i<descubiertos.size();i++)
        {
            Nodo clave=descubiertos.get(i);
            System.out.print(clave.id + ": ");
            
            for(Arista arista:clave.aristas)
            { 
                if(arista.destino.id==id)
                    return clave;
                System.out.print(arista.destino.id + " ");
                if(!descubiertos.contains(arista.destino))
                    descubiertos.add(arista.destino);  
            } 
            System.out.println();
        } 
        return null;
    }
    
    public void enlazar(int longitud,int idInicio,int idDestino)
    {
        Nodo inicio=buscar(idInicio);
        if(inicio!=null)
            inicio.aristas.add(new Nodo.Arista(longitud, new Nodo(id)));      
    }
    
    
    public void desenlazar(int idInicio,int idDestino)
    {
        Nodo inicio=buscar(idInicio);
        if(inicio!=null)
            inicio.desenlazar(idDestino);
    }

    /**
     * Enlazar a este nodo un nodo cuyo id es id
     * con una arista
     * @param longitud de la arista
     * @param id del nodo a enlazar
     */
    public void enlazar(int longitud,int id)
    {
        aristas.add(new Arista(longitud, new Nodo(id)));      
    }
    
    /**
     * Desenlazar de este nodo, el nodo cuyo id es id
     * @param id del nodo a eliminar
     */
    public void desenlazar(int id)
    {
        Arista remover=null;
        for(Arista arista:aristas)
        {
            if(arista.destino.id==id)
            {
                aristas.remove(remover);
                return;
            } 
        }                     
    }
    
    /**
     * Encontrar la ruta mas corta al nodo destino utilizando 
     * el Algoritmo de Dijkstra, asignando a cada nodo de la ruta
     * el nodo que lo antecede
     * @param destino nodo destino para buscar ruta mas corta
     */
    public void dijkstra(Nodo destino)
    {
        distanciaAcumulada=0;
        anterior=null;
        ArrayList<Nodo> inexplorados=new ArrayList<>();
        ArrayList<Nodo> explorados=new ArrayList<>();
        inexplorados.add(this); 
        
        while(!inexplorados.isEmpty())
        {
            Nodo explorando=inexplorados.get(0);
            int i=0;
            for(Nodo inexplorado:inexplorados)
            {
                if(inexplorado.distanciaAcumulada<explorando.distanciaAcumulada)
                {
                    explorando=inexplorado;
                    i++;
                }
            }
            explorados.add(explorando);
            inexplorados.remove(i);
                for(Arista arista:explorando.aristas)
                {
                    if(inexplorados.contains(arista.destino) || explorados.contains(arista.destino))
                    {
                        if(explorando.distanciaAcumulada+arista.longitud<arista.destino.distanciaAcumulada)
                        {
                            arista.destino.distanciaAcumulada=explorando.distanciaAcumulada+arista.longitud;
                            arista.destino.anterior=explorando;
                        } 
                    }
                    else
                    {
                        arista.destino.distanciaAcumulada=explorando.distanciaAcumulada+arista.longitud;
                        arista.destino.anterior=explorando;
                        inexplorados.add(arista.destino);                       
                    }
                } 
            }          
    }
        
    /**
     * Imprimir la secuencia de nodos antecesores
     * apartir de un nodo dado
     * @param destino nodo apartir del que se imprime la ruta
     */
    public void camino(Nodo destino)
    {
        for(Nodo anterior=destino;anterior!=null;anterior=anterior.anterior)
            System.out.print(anterior.id);
    }
}
