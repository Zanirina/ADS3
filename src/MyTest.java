public class MyTest {
    String s;
    String s2;

    public MyTest(String s , String s2) {
        this.s = s;
        this.s2 = s2;
    }

    @Override
    public int hashCode() {
        int h = 31;
        for (int i = 0; i < s.length(); i++) {
            h = 31 * h + s.charAt(i);
        }
        for (int i = 0; i < s2.length(); i++) {
            h = 31 * h + s2.charAt(i);
        }
        return h;
    }
}
