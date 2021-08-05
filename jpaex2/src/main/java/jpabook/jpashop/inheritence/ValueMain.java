package jpabook.jpashop.inheritence;

public class ValueMain {

    public static void main(String[] args) {

        Integer a = 10;
        Integer b = a;

        
        System.out.println(System.identityHashCode(a));
        System.out.println(System.identityHashCode(b));
        a = 20;


        System.out.println(System.identityHashCode(a));
        System.out.println(System.identityHashCode(b));

    }
}
