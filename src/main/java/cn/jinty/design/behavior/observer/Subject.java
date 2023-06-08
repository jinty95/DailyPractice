package cn.jinty.design.behavior.observer;

import java.util.HashSet;
import java.util.Set;

/**
 * 主题 - 被观察者
 *
 * @author Jinty
 * @date 2020/7/17.
 */
public class Subject {

    //资源
    private String msg;

    //观察者集合
    private Set<Observer> observerSet;

    //构造器
    public Subject() {
        observerSet = new HashSet<>();
    }

    //注册观察者
    public void subscribe(Observer observer) {
        observerSet.add(observer);
    }

    //下线观察者
    public void unsubscribe(Observer observer) {
        observerSet.remove(observer);
    }

    //更新资源
    public void setMsg(String msg) {
        this.msg = msg;
        notifyObserver();
    }

    //发送通知
    private void notifyObserver() {
        observerSet.forEach(one -> one.notify(this.msg));
    }

}
