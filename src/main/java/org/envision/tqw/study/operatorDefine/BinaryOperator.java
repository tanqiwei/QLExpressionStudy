package org.envision.tqw.study.operatorDefine;

import com.ql.util.express.Operator;

/**
 * @ClassName: BinaryOperator
 * @Description:
 *      定义二元操作符,该操作符实现
 *      a + ( b + b)
 * @Author: qiwei.tan
 * @Date: 2019/8/26 16:05
 * @Version: 1.0
 */
public class BinaryOperator extends Operator {


    public Object executeInner(Object[] list) throws Exception {
        int a = (Integer)list[0];
        int b = (Integer)list[1];
        return a + b + b;
    }
}
