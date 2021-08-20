package cn.jinty.design.create.factory;

/**
 * 工厂生成器
 *
 * @author Jinty
 * @date 2020/7/15.
 */
public class FactoryProducer {

    /**
     * 生成工厂
     * @param name 类路径
     * @return 工厂
     */
    public static AbstractFactory getFactory(String name){
        AbstractFactory factory = null;
        try{
            factory = (AbstractFactory)Class.forName(name).newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return factory;
    }

}
