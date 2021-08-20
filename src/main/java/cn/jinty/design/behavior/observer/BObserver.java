package cn.jinty.design.behavior.observer;

/**
 * 观察者 B
 *
 * @author Jinty
 * @date 2020/7/17.
 */
public class BObserver implements Observer{

    @Override
    public void notify(String msg) {
        System.out.printf("I am B Observer,I receive a new message [%s]%n", msg);
    }

}
