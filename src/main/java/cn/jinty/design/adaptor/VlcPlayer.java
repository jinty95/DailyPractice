package cn.jinty.design.adaptor;

/**
 * Vlc播放器
 *
 * @author Jinty
 * @date 2020/7/15.
 */
public class VlcPlayer implements AdvancedMediaPlayer{

    @Override
    public void playVlc(String fileName) {
        System.out.println("Vlc is playing now: fileName="+fileName);
    }

    @Override
    public void playMp4(String fileName) {

    }
}
