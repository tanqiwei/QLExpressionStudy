package org.envision.tqw.study.startTest.SceneTest.Scene1;

/**
 * @ClassName: SubjectValue
 * @Description: TODO
 * @Author: qiwei.tan
 * @Date: 2019/9/2 16:47
 * @Version: 1.0
 */
public class SubjectValue {
    public Object userId;
    public Object subjectId;
    public double value;
    public String toString(){
        return "科目[" + userId + "," + subjectId +"] = " + value;
    }
}
