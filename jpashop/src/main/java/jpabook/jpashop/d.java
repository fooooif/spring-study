package jpabook.jpashop;

public abstract class d {
    public void aa(){}

    public void aaa(){
        System.out.println();
    }

}

class b extends d {
    @Override
    public void aa() {
        System.out.println();
    }

    @Override
    public void aaa() {
        System.out.println("a");
    }
}
