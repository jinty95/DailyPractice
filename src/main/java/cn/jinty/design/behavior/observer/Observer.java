package cn.jinty.design.behavior.observer;

/**
 * 观察者接口
 *
 * @author Jinty
 * @date 2020/7/17.
 */
public interface Observer {

    /**
     * 观察者接收通知
     *
     * @param msg 通知
     */
    void notify(String msg);

}
