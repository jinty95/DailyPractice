package cn.jinty.design.adaptor;

/**
 * 播放器适配器
 *
 * @author Jinty
 * @date 2020/7/15.
 */
public class MediaAdaptor implements MediaPlayer{

    private AdvancedMediaPlayer advancedMediaPlayer;

    @Override
    public void play(String audioType, String fileName) {
        if("Vlc".equalsIgnoreCase(audioType)){
            advancedMediaPlayer = new VlcPlayer();
            advancedMediaPlayer.playVlc(fileName);
        }else if("Mp4".equalsIgnoreCase(audioType)){
            advancedMediaPlayer = new Mp4Player();
            advancedMediaPlayer.playMp4(fileName);
        }else{
            System.out.println("Invalid Media Type: "+audioType);
        }
    }

}
