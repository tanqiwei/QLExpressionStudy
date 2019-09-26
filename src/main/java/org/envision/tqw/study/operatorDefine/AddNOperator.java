package org.envision.tqw.study.operatorDefine;

import com.ql.util.express.Operator;

/**
 * @ClassName: AddNOperator
 * @Description:
 *      多元操作符
 *      4 addN (1,2,3)
 *      4+1+2+3
 * @Author: qiwei.tan
 * @Date: 2019/8/29 15:35
 * @Version: 1.0
 */
public class AddNOperator extends Operator {

    public Object executeInner(Object[] list) throws Exception {
        int r = 0;
        for(int i=0;i<list.length;i++){
            r = r + (Integer)list[i];
        }
        return r;
    }
}
