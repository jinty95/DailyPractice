package test.cn.jinty.design.behavior.observer;

import cn.jinty.design.behavior.observer.AObserver;
import cn.jinty.design.behavior.observer.BObserver;
import cn.jinty.design.behavior.observer.Observer;
import cn.jinty.design.behavior.observer.Subject;

/**
 * 测试
 *
 * @author Jinty
 * @date 2020/7/17.
 */
public class Test {

    public static void main(String[] args) {
        Observer A = new AObserver();
        Observer B = new BObserver();
        Subject subject = new Subject();
        subject.subscribe(A);
        subject.subscribe(B);
        subject.setMsg("今天是7月17号");
        subject.unsubscribe(B);
        subject.setMsg("今天是个好日子");
    }

}
