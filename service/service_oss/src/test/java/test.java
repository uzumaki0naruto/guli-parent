import org.junit.jupiter.api.Test;

import java.util.Random;

public class test {
    public static void main(String[] args) {
        Random random = new Random();
        int i = random.nextInt(9999-1000+1)+1000;
        System.out.println(i);
    }

}
