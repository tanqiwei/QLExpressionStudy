package org.envision.tqw.study.startTest.SceneTest.Scene1;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import org.envision.tqw.study.operatorDefine.SujectOperator;
import org.envision.tqw.study.util.FileUtil;
import org.envision.tqw.study.util.QLUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SceneThree
 * @Description:
 *      场景三，针对物流
 * @Author: qiwei.tan
 * @Date: 2019/9/2 16:30
 * @Version: 1.0
 */
public class SceneThree {
    /**
     * 分成配置范例
     * @throws Exception
     */
    @Test
    public void testRating( ) throws Exception {
        Map logisticsOrder = new HashMap();
        Map tcOrder = new HashMap();
        Map goodsOrder = new HashMap();
        Map subjectValue = new HashMap();
        //设置物流订单信息
        logisticsOrder.put("重量",4);
        logisticsOrder.put("仓储TP","玄难");
        logisticsOrder.put("物流TP","云殊");
        logisticsOrder.put("包装TP","千绝");
        //建立计算器
        ExpressRunner runner = new ExpressRunner(true,true);
        //增加自定义函数
        runner.addFunction("费用科目",new SujectOperator("费用科目"));
        //装载分成规则
        String express = FileUtil.readFileByPath("script/Scene/SceneThreeTestNoDynamic.txt");
        //设置上下文
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        context.put("物流订单", logisticsOrder);
        context.put("交易订单", tcOrder);
        context.put("仓储订单", goodsOrder);
        context.put("费用科目", subjectValue);

        //执行指令
        QLUtil.RunExpressNoOutput(runner,express,context,false,true);
        //输出分成结果
        System.out.println("----------分成结果----------------");
        for(Object item : subjectValue.values()){
            System.out.println(item);
        }
    }

    /**
     * 分成配置范例,通过动态属性来实现
     */
    @Test
    public void testRating1( ) throws Exception {
        Map logisticsOrder = new HashMap();
        Map tcOrder = new HashMap();
        Map goodsOrder = new HashMap();
        //设置物流订单信息
        logisticsOrder.put("重量",4);
        logisticsOrder.put("仓储TPID","玄难");
        logisticsOrder.put("物流TPID","云殊");
        logisticsOrder.put("包装TPID","千绝");
        //建立计算器
        ExpressRunner runner = new ExpressRunner();
        //增加自定义函数
        runner.addFunction("费用科目",new SujectOperator("费用科目"));
        //装载分成规则
        String express = FileUtil.readFileByPath("script/Scene/SceneThreeTestDynamic.txt");
        //设置上下文
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        context.put("物流订单", logisticsOrder);
        context.put("交易订单", tcOrder);
        context.put("仓储订单", goodsOrder);
        SubjectMananger subjectMananger = new SubjectMananger();
        context.put("费用", subjectMananger);
        //执行指令
        QLUtil.RunExpressNoOutput(runner,express,context,false,true);
        //输出分成结果
        System.out.println("----------分成结果----------------");
        for(Object item : subjectMananger.getSubjectValues()){
            System.out.println(item);
        }
    }



}

class SubjectMananger extends HashMap {
    public Object get(Object userName){
        UserSubject userSubject = (UserSubject)super.get(userName);
        if(userSubject == null){
            userSubject = new UserSubject(userName);
            super.put(userName, userSubject);
        }
        return userSubject;
    }
    public Object  put(String userName, Object userSubject){
        throw new RuntimeException("不运行的方法");
    }
    public List<SubjectValue> getSubjectValues(){
        List<SubjectValue> result = new ArrayList<SubjectValue>();
        for(Object f : this.values()){
            UserSubject item = (UserSubject)f;
            for(Object t : item.entrySet()){
                Map.Entry  me = (Map.Entry)t;
                SubjectValue value = new SubjectValue();
                value.userId = item.getUserId();
                value.subjectId = me.getKey();
                value.value = ((Number)me.getValue()).doubleValue();
                result.add(value);
            }
        }
        return result;
    }
}

class UserSubject extends HashMap {
    Object userId;
    public UserSubject(Object aUserId){
        super();
        this.userId = aUserId;
    }
    public Double get(String subjectId){
        Double value = (Double)super.get(subjectId);
        if(value == null){
            return 0d;
        }
        return value;
    }
    public Object  put(String subjectId, Object value){
        return super.put(subjectId, value);
    }
    public Object getUserId() {
        return userId;
    }
}
