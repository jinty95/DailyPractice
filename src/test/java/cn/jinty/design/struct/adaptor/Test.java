package cn.jinty.design.struct.adaptor;

/**
 * 测试
 *
 * @author Jinty
 * @date 2020/7/16.
 */
public class Test {

    public static void main(String[] args) {
        //适配器
        MediaAdaptor mediaAdaptor = new MediaAdaptor();
        mediaAdaptor.play("Mp4","hello.mp4");
        mediaAdaptor.play("Vlc","far far away.vlc");
        mediaAdaptor.play("Mp3","we are the world.mp3");
    }
}
