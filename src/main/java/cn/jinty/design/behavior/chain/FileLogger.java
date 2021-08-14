package cn.jinty.design.behavior.chain;

/**
 * 文件日志
 *
 * @author Jinty
 * @date 2020/7/17.
 */
public class FileLogger extends Logger{

    public FileLogger(int level){
        super.level = level;
    }

    @Override
    public void write(int level, String msg) {
        System.out.println("FileLogger write : level="+level+",msg="+msg);
    }

}
