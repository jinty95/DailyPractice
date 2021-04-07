package cn.jinty.design.observer;

/**
 * 观察者 B
 *
 * @author Jinty
 * @date 2020/7/17.
 */
public class BObserver implements Observer{

    private String msg;

    @Override
    public void update(String msg) {
        this.msg = msg;
        System.out.println(String.format("I am B Observer,I receive a new message [%s]",this.msg));
    }
}
