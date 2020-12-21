package br.ufrgs.rmpestano.rsa;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Encryption {

    public static String encrypt(String message) {
        BigInteger p;
        BigInteger q;
        BigInteger e;

            p = new BigInteger("5700734181645378434561188374130529072194886062117");
            q = new BigInteger("35894562752016259689151502540913447503526083241413");
            e = new BigInteger("33445843524692047286771520482406772494816708076993");

        RSA rsa = new RSAImpl(p, q, e);

        System.out.println(rsa);

        List<BigInteger> encryption;
        List<BigInteger> signed;
        List<BigInteger> decimalMessage;

            encryption = rsa.encryptMessage(message);
            decimalMessage = rsa.messageToDecimal(message);

        List<BigInteger> decrypt = rsa.decrypt(encryption);
        System.out.println();
        System.out.println("message(plain text)   = " + Utils.bigIntegerToString(decimalMessage));
        System.out.println("message(decimal)      = " + Utils.bigIntegerSum(decimalMessage));
        System.out.println("encripted(decimal)    = " + Utils.bigIntegerSum(encryption));
        System.out.println("decrypted(plain text) = " + Utils.bigIntegerToString(decrypt));
        System.out.println("decrypted(decimal)    = " + Utils.bigIntegerSum(decrypt));
    return Utils.bigIntegerSum(encryption);
    }
    public static String decrypt(String encMessage){
        BigInteger p;
        BigInteger q;
        BigInteger e;

        p = new BigInteger("5700734181645378434561188374130529072194886062117");
        q = new BigInteger("35894562752016259689151502540913447503526083241413");
        e = new BigInteger("33445843524692047286771520482406772494816708076993");

        RSA rsa = new RSAImpl(p, q, e);

        List<BigInteger> encrypted = new ArrayList<BigInteger>();
        encrypted.add(new BigInteger(encMessage));
        List<BigInteger> decrypt = rsa.decrypt(encrypted);
        return Utils.bigIntegerToString(decrypt);
    }
}



