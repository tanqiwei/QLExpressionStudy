package org.envision.tqw.study.operatorDefine;

import com.ql.util.express.Operator;

/**
 * @ClassName: JoinOperator
 * @Description:
 *      自定义连接操作符(二元或多元运算符【支持成函数列表调用】）
 *      1 join 2 join 3 ===> [1,2,3]
 *      返回ArrayList数组
 * @Author: qiwei.tan
 * @Date: 2019/8/29 11:39
 * @Version: 1.0
 */
public class JoinOperator extends Operator {
    public Object executeInner(Object[] list) throws Exception {
        Object opdata1 = list[0];
        Object opdata2 = list[1];
        if(opdata1 instanceof java.util.List){
            ((java.util.List)opdata1).add(opdata2);
            return opdata1;
        }else{
            java.util.List result = new java.util.ArrayList();
            result.add(opdata1);
            result.add(opdata2);
            if(list.length > 2){
                int index = -1;
                for(Object obj:list)
                    if(++index < 2)
                        continue;
                    else
                        result.add(obj);
            }
            return result;
        }
    }
}
