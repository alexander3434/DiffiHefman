import java.math.BigDecimal;
import java.util.Random;

public class KeyExchange {

    private static long computeKey(long base, int power, long mod) {
        return new BigDecimal(base) // преобразуем long в BigDecimal
                .pow(power) // возведение в степень
                .remainder(new BigDecimal(mod)) //остаток от деления remainder
                .longValue();
    }

    public static void main(String args[]) {
        int alicePublicKey = new Random().nextInt(200) + 1;
        int bobPublicKey = new Random().nextInt(200) + 1;

        int alicePrivateKey = new Random().nextInt(200) + 1;
        int bobPrivateKey = new Random().nextInt(200) + 1;

        System.out.println("Alice's public key is : " + alicePublicKey);
        System.out.println("Bob's public key is : " + bobPublicKey);
        System.out.println();

        System.out.println("Alice's private key is : " + alicePrivateKey);
        System.out.println("Bob's private key is : " + bobPrivateKey);
        System.out.println();

        long alicePartialKey = computeKey(alicePublicKey, alicePrivateKey, bobPublicKey);
        long bobPartialKey = computeKey(alicePublicKey, bobPrivateKey, bobPublicKey);

        System.out.println("Alice's partial key is : " + alicePartialKey);
        System.out.println("Bob's partial key is : " + bobPartialKey);
        System.out.println();

        long aliceFullKey = computeKey(bobPartialKey, alicePrivateKey, bobPublicKey);
        long bobFullKey = computeKey(alicePartialKey, bobPrivateKey, bobPublicKey);

        System.out.println("Alice's full key is : " + aliceFullKey);
        System.out.println("Bob's full key is : " + bobFullKey);
    }
}