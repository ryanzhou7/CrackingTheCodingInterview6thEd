package ch1_arrays_and_strings;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class One_One_isUniqueTest {

    //Works for all Unicode values
    boolean isUnique(String string){
        boolean[] existingChars = new boolean[(int)Character.MAX_VALUE];
        char[] stringArray = string.toCharArray();
        for(char c: stringArray){
            int index = (int)c;
            if(existingChars[index]){
                return false;
            }
            existingChars[index] = true;
        }
        return true;
    }

    //No use of data structures optimization, assumes string range is 'a' - 'z'
    boolean isUniqueLowerAlpha(String string){
        if(string.length()>26)
            return false;
        if(string.length()<=1)
            return true;
        int toggledBits = 0;
        for(int i=0; i<string.length(); i++){
            int charVal = (int)string.charAt(i) -(int)'a';
            if(charVal<0||charVal>=26){
                throw new IllegalArgumentException("String contains chars outside of a-z range");
            }
            if(((1<<charVal)&toggledBits)>0){
                return false;
            }
            toggledBits |= (1<<charVal);
        }
        return true;
    }

    //------ isUniqueLowerAlpha Tests -----
    @Test
    public void uniqueAllCharStringTest(){
        String uniqueAllCharString = "abcdefghijklmnopqrstuvwxyz";
        boolean result = isUniqueLowerAlpha(uniqueAllCharString);
        assertThat(result, is(true));

    }

    @Test
    public void duplicateStringTest(){
        String uniqueAllCharString = "abcdefghijklmnopqrstuvwxyza";
        boolean result = isUniqueLowerAlpha(uniqueAllCharString);
        assertThat(result, is(false));

    }



    //------ isUnique Tests -----
    @Test
    public void uniqueAlphaStringTest(){
        String uniqueString = "abcdefghijklmn";
        boolean result = isUnique(uniqueString);
        assertThat(result, is(true));
    }

    @Test
    public void duplicateAlphaStringTest(){
        String uniqueString = "abcdefghijklmn";
        uniqueString += uniqueString;
        boolean result = isUnique(uniqueString);
        assertThat(result, is(false));
    }

    @Test
    public void duplicatesNumericStringTest(){
        String uniqueString = "0123456789";
        uniqueString += uniqueString;
        boolean result = isUnique(uniqueString);
        assertThat(result, is(false));
    }

}
