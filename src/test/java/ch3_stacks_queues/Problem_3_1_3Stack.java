package ch3_stacks_queues;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class Problem_3_1_3Stack {

    /*
    Implement 3 Stacks with an array.
     */

    private class MultiStack{
        private int[] data;
        private int numStack;
        private final static int ALLOCATION_MULTIPLIER = 2;
        private int[] stackIndex;
        public MultiStack(int numStack){
            this.numStack = numStack;
            data = new int[numStack];
            stackIndex = new int[numStack];
        }
        public void pushFor(int stackNum, int val){
            assert stackNum>=0 && stackNum < numStack;
            if(stackIndex[stackNum]>=data.length){
                int[] biggerArray = new int[data.length* ALLOCATION_MULTIPLIER];
                System.arraycopy(data, 0, biggerArray, 0, data.length);
                data = biggerArray;
            }
            data[stackIndex[stackNum]] = val;
            stackIndex[stackNum]+= numStack;
        }
        public int popFor(int stackNum){
            assert stackNum>=0 && stackNum < numStack;
            stackIndex[stackNum] -= numStack;
            int val = data[stackIndex[stackNum]];
            return val;
        }
        public boolean isEmptyFor(int stackNum){
            assert stackNum>=0 && stackNum < numStack;
            return stackIndex[stackNum] < numStack;
        }
    }


    @Test
    public void singleStackUseTest(){
        MultiStack multiStack = new MultiStack(3);
        assertThat(multiStack.isEmptyFor(0), is(true));
        multiStack.pushFor(0, 1);
        assertThat(multiStack.isEmptyFor(0), is(false));
        assertThat(multiStack.popFor(0), is(1));
        assertThat(multiStack.isEmptyFor(0), is(true));
    }


    @Test
    public void singleStackUseMultiPushTest(){
        int[] vals = {1, 2, 3, 4, 5, 6};
        MultiStack multiStack = new MultiStack(3);
        for(int i: vals){
            multiStack.pushFor(0, i);
        }
        for(int i = vals.length-1; i>0; i--){
            int actual = multiStack.popFor(0);
            assertThat(actual, is(vals[i]));
        }
    }
}

