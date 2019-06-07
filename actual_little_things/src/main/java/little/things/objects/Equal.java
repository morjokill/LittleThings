package little.things.objects;

import com.google.common.base.Objects;

//Use Objects.equal to avoid NPE on null.equals()
public class Equal {
    public static void main(String[] args) {
        System.out.println(Objects.equal("a", "a")); // returns true
        System.out.println(Objects.equal(null, "a")); // returns false
        System.out.println(Objects.equal("a", null)); // returns false
        System.out.println(Objects.equal(null, null)); // returns true
    }
}
