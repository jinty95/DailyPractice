package cn.jinty.design.create.singleton;

/**
 * 单例模式
 *
 * @author Jinty
 * @date 2020/7/15.
 */
public class Singleton {

    //唯一实例(加volatile防止与此变量相关的指令重排)
    private static volatile Singleton singleton;

    //私有构造器
    private Singleton() {
        if (singleton != null) {
            throw new RuntimeException("单例对象已经存在");
        }
    }

    //共有方法获取唯一实例
    public static Singleton getInstance() {
        //双检锁
        if (singleton == null) {
            //懒加载创建实例，加锁保证只创建一个
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

}
