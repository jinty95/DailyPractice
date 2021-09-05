package test.cn.jinty.design.struct.adaptor;

import cn.jinty.design.struct.adaptor.MediaAdaptor;

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
        mediaAdaptor.play("MP4","hello.mp4");
        mediaAdaptor.play("VLC","far far away.vlc");
        mediaAdaptor.play("MP3","we are the world.mp3");
    }

}
