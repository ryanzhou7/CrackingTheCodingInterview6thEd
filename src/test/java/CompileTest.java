import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

public class CompileTest {

    @Test
    public void testCompile(){
        int i = 0;
        assertThat(i, is(0));

        i = 1;
        assertThat(i, is(1));
    }
}
