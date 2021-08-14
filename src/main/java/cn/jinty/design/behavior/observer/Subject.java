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

    private String msg;

    private Set<Observer> set;

    public Subject(){
        set = new HashSet<>();
    }

    public void subscribe(Observer observer){
        set.add(observer);
    }

    public void unsubscribe(Observer observer){
        set.remove(observer);
    }

    public void setMsg(String msg){
        this.msg = msg;
        notifyObserver();
    }

    private void notifyObserver(){
        set.forEach(one -> {
            one.update(this.msg);
        });
    }
}
