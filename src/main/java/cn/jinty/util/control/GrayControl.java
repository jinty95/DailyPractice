package cn.jinty.util.control;

/**
 * 灰度控制
 * 含义：当功能发生变更时，控制部分用户访问原功能，部分用户访问新功能
 * 作用：控制新功能的影响范围，待稳定运行后逐步扩大灰度，最终实现平稳过渡到新功能
 *
 * @author Jinty
 * @date 2023/4/26
 **/
public abstract class GrayControl {

    /**
     * 是否开启灰度控制
     *
     * @return 是否
     */
    protected abstract boolean isControlled();

    /**
     * 是否被选中
     *
     * @param key 关键词
     * @return 是否
     */
    protected abstract boolean isSelected(String key);

    /**
     * 是否使用新功能
     * 1、没有开启灰度控制，使用新功能
     * 2、开启灰度控制且关键词被选中，使用新功能
     * 3、开启灰度控制且关键词未选择，使用原功能
     *
     * @param key 关键词
     * @return 是否
     */
    public boolean toNewFeature(String key) {
        if (!isControlled()) {
            return true;
        }
        return isSelected(key);
    }

    /**
     * 是否使用原功能
     *
     * @param key 关键词
     * @return 是否
     */
    public boolean toOldFeature(String key) {
        return !toNewFeature(key);
    }

}
