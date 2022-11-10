/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg09firmarsa;

/**
 *
 * @author alumno
 */

import javax.crypto.*;
import java.security.*;
import sun.misc.BASE64Encoder;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        
        //vamos a realizar la instancia correspondiente del algoritmo
        KeyPairGenerator generadorRSA = KeyPairGenerator.getInstance("RSA");
        
        //inicializamos la llave
        generadorRSA.initialize(4096);
        
        //crear las llaves
        KeyPair llaves = generadorRSA.generateKeyPair();
        
        //ustedes lo hacen con sha y yo con md5
        
        //texto
        byte [] dato = "habia un patito que decia miau miau, chocolate, mimir, 2 novias, y un play 5 porque el god of war no alcansamos la edicion coleccionista".getBytes("UTF-8");
        
        //preparamos la firma
        Signature firma = Signature.getInstance("MD5withRSA");
        
        //inicializo la llave privada
        firma.initSign(llaves.getPrivate());
        
        //actualizamos el documentio
        firma.update(dato);
        
        //ahora si firmamos el documento
        byte [] firmadocumento = firma.sign();
        
        System.out.println("la firma digital es" + new BASE64Encoder().encode(firmadocumento));
        
        
        //proceso de verificacion
        firma.initVerify(llaves.getPublic());

        //vamos a actualizar el estado del documento
        firma.update(dato);
        
        //vamos a ver su esta bien el documento, si es valido
         System.out.println("el documento es valido?");
         System.out.println(firma.verify(firmadocumento));
        
    }
    
}
