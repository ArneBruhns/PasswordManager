package model;

import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Class PrimesVerifier
 */
public class PrimesVerifier {

    /**
     * Creats a new object of the PrimesVerifier.
     */
    public PrimesVerifier(){}

    /**
     * Check if an integer is a prime number.
     *
     * @param number ~ Reading in an integer value.
     * @return true, if the number is a prime number.
     */
    public boolean isPrim(int number){
        Predicate<Integer> isPrim = zahl -> Stream.iterate(2, n -> n +1 )
                .limit(zahl)
                .filter(i -> i < zahl)
                .noneMatch(i -> zahl % i == 0);

        return isPrim.test(number);
    }

}
