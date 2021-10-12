package cn.jinty.design.struct.adaptor;

/**
 * MP4播放器
 *
 * @author Jinty
 * @date 2020/7/15.
 */
public class Mp4Player implements AdvancedMediaPlayer {

    @Override
    public void playVlc(String fileName) {

    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Mp4 is playing now: fileName=" + fileName);
    }
}
