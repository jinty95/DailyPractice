package cn.jinty.design.behavior.observer;

/**
 * 观察者 A
 *
 * @author Jinty
 * @date 2020/7/17.
 */
public class AObserver implements Observer{

    @Override
    public void notify(String msg) {
        System.out.printf("I am A Observer,I receive a new message [%s]%n", msg);
    }

}
