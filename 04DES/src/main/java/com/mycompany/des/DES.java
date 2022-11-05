/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.des;

/**
 *
 * @author alan_
 *
 * Vamos a programar bajo un modo de cifrado por bloques de 64 bits
 * vamos a manejar una llave privada de 64 bits
 * Se debe tomar parqa la inicializacion de la llave 56 bit
 * exactamente tal y como nos dice la teoria porque 
 * 
 * Las tablas de permutacion y cajas de sustitucion
 * se van a cargar por parte del proveedor de servicios 
 * Para porder hacer todo lo que vamos a usar librerias crypto y secutiry
 * */
import java.security.*;
//Es para porder generar las claves
import javax.crypto.spec.*;
import javax.crypto.*;
import java.io.*;

public class DES {
    public static void main(String[]args) throws Exception{
    //Vamos a comprobar la entrada de un archivo o fichero para cifrar
        if(args.length !=1){
            //No hay archivos
            mensajeAyuda();
            System.exit(1);
        }   
            /*Lo primero que tenemos que hacer es cargar una instancia del proyecto del tipo de cifrado
            para eso esta la parte de las librerias*/
            
            System.out.println("1.- Generar la clave DES: ");
            
            //Vamos a ocupar la clase KeyGenerator
            KeyGenerator generadorDES = KeyGenerator.getInstance("DES");
            //Inicializamos la llave
            generadorDES.init(56);
            
            //Vamos a generar las claves
            SecretKey clave=generadorDES.generateKey();
            //Estamos creando las 16 subllaves
            
            System.out.println("La clave es: " + clave);
            
            /*Recordemos que en la criptografia moderna solo se ocupan bits o bytes*/
            
            //La llave que se ha generado no tiene formato
            mostrarBytes(clave.getEncoded());
            System.out.println("");
            
            /* Vamos a cifrar oara ello debemos aplicar 
            los estandares vistos en clase PKCS para ello tenemos
            que crear una instancia del algoritmo des en el modo de 
            cifrado (Una es por bloques y otra es por flujo)
            
            ALGORITMO DES
            MODO ECB (ELECTRONIC CODE BOOK)
            RELLENO PKCS5
            */
            System.out.println("2.- Cifrar con DES y el archivo: " + args[0] + " , dejar el resultado en: "
            + args[0] + ".cifrado");
           
            //instancia
            Cipher cifrado = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cifrado.init(Cipher.ENCRYPT_MODE, clave);
            
            //El problema es como vamos a leer los bloques del mensaje
            
            byte[] buffer=new byte[1000];
            byte[] bufferCifrado;
            
            //Generamos los archivos
            
            FileInputStream in=new FileInputStream(args[0]);
            FileOutputStream out=new FileOutputStream(args[0]+".cifrado");
            
            //lectura
            
            int bytesleidos= in.read(buffer, 0, 1000);
            //Mientras no este al final del archivo
            while(bytesleidos != -1){
            //pasar el texto claro leido al cifrador
            bufferCifrado= cifrado.update(buffer,0,bytesleidos);
            //Generar la salida
            out.write(bufferCifrado);
            bytesleidos=in.read(buffer,0,1000);
            }
            //reunir todos los bloques
            bufferCifrado=cifrado.doFinal();
            out.write(bufferCifrado);
            
            in.close();
            out.close();

            //vamo a descifrar :)
            System.out.println("descifrar con des des eñ archivo"
                + args[0]+".cifrado" + " , vamos a ver resultado "+"en el archivp"
                + args[0] +".descifrado");
            
            //descifrad0
            cifrado.init(Cipher.DECRYPT_MODE, clave);
            
            //bufer ara emtrada y salida de bits
            byte[] bufferPlano;
            
            in = new FileInputStream(args[0]+".cifrado");
            out = new FileOutputStream(args[0]+".descifrado");
            
            //lectura a cada elemento
            bytesleidos = in.read(buffer,0,1000);
            
            while(bytesleidos!=-1){
                bufferPlano=cifrado.update(buffer,0,bytesleidos);
                out.write(bufferPlano);
                bytesleidos=in.read(buffer,0,1000);
            }
            bufferPlano=cifrado.doFinal();
            in.close();
            out.close();
        

    }

    private static void mensajeAyuda() {
        System.out.println("ejemplo de cifrado DES");
        System.out.println("la clave solo puede ser de 8 caracteres");
        System.out.println("compruebe el archivo cargado");
        System.out.println("ñam ñam n-n/");
    }

    private static void mostrarBytes(byte[] buffer) {
        System.out.write(buffer,0,buffer.length);
    }
    
    
}
