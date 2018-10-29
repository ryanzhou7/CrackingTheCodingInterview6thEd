package ch2_linked_lists;

import org.junit.Test;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class Problem_2_1_removeDupsTest {

    /*
    Write a method to remove duplicates from a unsorted singly linked list.
    What if you cannot use additional data structures?
     */

    private class Node<T>{
        T val;
        Node<T> next = null;
        Node(T val){
            this.val = val;
        }
    }

    private <T> void removeDups(Node<T> head){
        Set<T> existingNodes = new HashSet<>();
        existingNodes.add(head.val);
        while(head.next!=null){
            if(existingNodes.contains(head.next.val)){
                head.next = head.next.next;
            }
            else{
                existingNodes.add(head.next.val);
                head = head.next;
            }
        }
    }

    private <T> void removeDupsNoDataStructs(Node<T> head){
        Node current = head;
        while (current!=null){
            T val = (T) current.val;
            Node runner = current;
            while (runner.next!=null){
                if(val==runner.next.val){
                    runner.next = runner.next.next;
                }
                else{
                    runner = runner.next;
                }
            }
            current = current.next;
        }
    }

    // removeDups Tests
    @Test
    public void removeNothingTest(){
        int[] valsUnique = {6, 1, 2, 3, 4};
        Node head = createLinkedListFrom(valsUnique);
        removeDups(head);
        assertThat(hasSameVals(valsUnique, head), is(true));

        head = createLinkedListFrom(valsUnique);
    }

    @Test
    public void removeMultiUniqueDuplicatesTest(){
        int[] vals = {6, 1, 2, 3, 4, 1, 6, 2, 3};
        Node head = createLinkedListFrom(vals);

        int[] expected = {6, 1, 2, 3, 4};
        removeDups(head);
        assertThat(hasSameVals(vals, head), is(false));
        assertThat(hasSameVals(expected, head), is(true));
    }

    //removeDupsNoDataStructs Tests
    @Test
    public void removeNothingTest2(){
        int[] vals = {6, 1, 2, 3, 4};
        Node head = createLinkedListFrom(vals);
        removeDupsNoDataStructs(head);
        assertThat(hasSameVals(vals, head), is(true));
    }

    @Test
    public void removeMultiUniqueDuplicatesTest2(){
        int[] vals = {6, 1, 2, 3, 4, 1, 6, 2, 3};
        Node head = createLinkedListFrom(vals);

        int[] expected = {6, 1, 2, 3, 4};
        removeDupsNoDataStructs(head);
        assertThat(hasSameVals(vals, head), is(false));
        assertThat(hasSameVals(expected, head), is(true));
    }


    //Helper Test methods Test
    @Test
    public void hasSameValsTrueTest(){
        int[] vals = {1, 2, 3, 4, 5, 6, 7};
        Node head = createLinkedListFrom(vals);
        assertThat(hasSameVals(vals, head), is(true));
    }

    @Test
    public void hasSameValsFalseLongerArrayTest(){
        int[] vals = {1, 2, 3, 4, 5, 6, 7};
        Node head = createLinkedListFrom(vals);
        int[] extraNumberArray = new int[vals.length+1];
        System.arraycopy(vals, 0, extraNumberArray, 0, vals.length-1);
        extraNumberArray[extraNumberArray.length-1] = 8;
        assertThat(hasSameVals(vals, head), is(true));
    }

    @Test
    public void hasSameValsFalseShorterArrayTest(){
        int[] vals = {1, 2, 3, 4, 5, 6, 7};
        Node head = createLinkedListFrom(vals);
        int[] missingLastArray = new int[vals.length];
        System.arraycopy(vals, 0, missingLastArray, 0, vals.length-1);
        assertThat(hasSameVals(vals, head), is(true));
    }


    private boolean hasSameVals(int[] array, Node head){
        for(int val: array){
            if(head ==null || val!=(int)head.val) return false;
            head = head.next;
        }
        return head==null;
    }

    private void print(Node head){
        StringBuilder sb = new StringBuilder();
        while(head!=null){
            sb.append(head.val + " ");
            head = head.next;
        }
        System.out.println(sb.toString());
    }

    private Node createLinkedListFrom(int[] vals){
        Node head = new Node(vals[0]);
        Node current = head;
        for(int i = 1; i<vals.length; i++){
            current.next = new Node(vals[i]);
            current = current.next;
        }
        return head;
    }
}
