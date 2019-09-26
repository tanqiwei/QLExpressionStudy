package org.envision.tqw.study.BeanDefine;

/**
 * @ClassName: ObjectBean
 * @Description:
 *  用于测试QLExpress的Bean类
 * @Author: qiwei.tan
 * @Date: 2019/8/28 16:32
 * @Version: 1.0
 */
public class ObjectBean {
    private int amount;
    private int volume;
    public ObjectBean(){
        this.amount = 0;
        this.volume = 0;
    }
    public ObjectBean(int aAmount,int aVolume){
        this.amount =aAmount;
        this.volume = aVolume;
    }
    public int getAmount() {
        return amount;
    }
    public int getAmount(int a) {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public int getVolume() {
        return volume;
    }
    public void setVolume(int volume) {
        this.volume = volume;
    }

}
