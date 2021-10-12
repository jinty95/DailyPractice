package cn.jinty.design.struct.adaptor;

/**
 * 播放器适配器
 *
 * @author Jinty
 * @date 2020/7/15.
 */
public class MediaAdaptor implements MediaPlayer {

    @Override
    public void play(String audioType, String fileName) {
        AdvancedMediaPlayer advancedMediaPlayer;
        if ("VLC".equalsIgnoreCase(audioType)) {
            advancedMediaPlayer = new VlcPlayer();
            advancedMediaPlayer.playVlc(fileName);
        } else if ("MP4".equalsIgnoreCase(audioType)) {
            advancedMediaPlayer = new Mp4Player();
            advancedMediaPlayer.playMp4(fileName);
        } else {
            System.out.println("Invalid Media Type: " + audioType);
        }
    }

}
