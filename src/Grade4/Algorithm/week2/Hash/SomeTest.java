package Grade4.Algorithm.week2.Hash;

public class SomeTest {
    public static void main(String[] args) {
        int n = 523;
        int  m = n/2;
        for(int i = 2; i < m; i++) {
            float x = (float) n/i;
            int y = n/i;
            if (x==y) {
                System.out.println(">> "+n+" can be devided by "+ i);
            }
        }
        System.out.println(">> if there is no devider, " +n+" is a Prime number");
    }
}
