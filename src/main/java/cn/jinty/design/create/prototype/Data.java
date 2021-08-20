package cn.jinty.design.create.prototype;

/**
 * 数据
 *
 * @author Jinty
 * @date 2020/7/15.
 */
public class Data implements Cloneable{

    private String id;
    private String name;

    @Override
    public Object clone(){
        Object obj = null;
        try{
            obj = super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return obj;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
