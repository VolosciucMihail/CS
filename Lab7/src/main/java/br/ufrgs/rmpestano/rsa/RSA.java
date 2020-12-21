/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrgs.rmpestano.rsa;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

public interface RSA extends Serializable {


    BigInteger encrypt(BigInteger bigInteger);


    List<BigInteger> encryptMessage(final String message);

    BigInteger decrypt(BigInteger encrypted);


    List<BigInteger> decrypt(List<BigInteger> encryption);


    List<BigInteger> messageToDecimal(final String message);

}
