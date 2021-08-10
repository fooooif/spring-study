package jpabook.jpashop.inheritence;

public class ValueMain {

    public static void main(String[] args) {


       int a = 10;
       int b = 10;
       int c = a;
        System.out.println(System.identityHashCode(a));
       a = 20;
        System.out.println("a == b" + (a == b));
        System.out.println(System.identityHashCode(a));
        System.out.println(System.identityHashCode(c));

        Address address = new Address("a", "b", "c");
        Address address2 = new Address("a", "b", "c");
        System.out.println(address == address2);
    }
}
