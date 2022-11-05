package RSAManita;

import java.math.BigInteger;
import java.util.*;
import java.io.*;

public class RSAManita 
{    
        //declaramos nuestras variables
        int tamprimo;
        BigInteger p,q,n;
        BigInteger fi;
        BigInteger e, d;
        
        //constructor
        public RSAManita(int tamprimo)
        {
            this.tamprimo = tamprimo;
        }
        
        //mtodo del tamaño primo
        public void generarPrimos()
        {
            p = new BigInteger(tamprimo, 10, new Random());
            //el otro primo diferente de p
            do q = new BigInteger(tamprimo, 10, new Random());
            while(q.compareTo(p)==0);
        }
        
        //vamos a generar las llaves e d
        public void generarClaves()
        {
            //n=pq
            //fi = (p-1)(q-1)
            n = p.multiply(q);
            fi = p.subtract(BigInteger.valueOf(1));
            fi = fi.multiply((q.subtract(BigInteger.valueOf(1))));
            
            //calcular e y d
            //debe ser co primo tal que 1 < e < fi(n)
            do e = new BigInteger(2*tamprimo, new Random());
                while((e.compareTo(fi) != -1 )
                    || (e.gcd(fi).compareTo(BigInteger.valueOf(1)) != 0 ));
            
            //calcular d es inverso multiplicativo fr e
            //d=e^1 mod fi
            d = e.modInverse(fi);
        }
        
        //cifer
        public BigInteger[] cifrar(String mensaje)
        {
            int i;
            byte[] temp = new byte[1];
            byte[] digitos = mensaje.getBytes();
            
            BigInteger[] bigdigitos =
                    new BigInteger[digitos.length];
            
            //recorrer el arreglo
            for(i = 0 ; i < bigdigitos.length ; i++){
                temp[0] = digitos[i];
                bigdigitos[i] = new BigInteger(temp);
            }
            
            //aplico cifrado
            BigInteger[] cifrado = 
                    new BigInteger[bigdigitos.length];
            
            //aplicar formula c=^e + mod n
            for(i = 0 ; i< bigdigitos.length ; i++){
                cifrado[i] = bigdigitos[i].modPow(e,n);
            }
            
            return cifrado;
        }
        
        
        //descifer
        public String descifrar(BigInteger[] cifrado)
        {
            int i;
            BigInteger[] descifrado = 
                    new BigInteger[cifrado.length];
            
            //descifrr md = c ^ d*mod n
            //recorrer el arreglo
            for(i = 0 ; i < descifrado.length ; i++)
            {
                descifrado[i] = cifrado[i].modPow(d,n);
            }
            
            //formato
            char[] charArray = new char[descifrado.length];
            
            //genero cadena
            for(i = 0 ; i< charArray.length ; i++)
            {
                charArray[i] = (char)(descifrado[i].intValue());
            }
            
            return (new String(charArray));
        }
        
        
        public static void main(String[] args)
        {
            // TODO code application logic here
            
        }
    
}
