package org.envision.tqw.study.operatorDefine;

import com.ql.util.express.Operator;

/**
 * @ClassName: ApproveOperator
 * @Description:
 *      审批流程
 * @see org.envision.tqw.study.startTest.SceneTest.Scene1.SceneTwo
 * @Author: qiwei.tan
 * @Date: 2019/9/2 15:14
 * @Version: 1.0
 */
public class ApproveOperator  extends Operator {
    int operater = 0;
    public ApproveOperator(int op){
        this.operater = op;
    }


    public Object executeInner(Object[] list) throws Exception {
        if(this.operater == 1){
            /**
             * list[0] 审批人
             * list[1] 审批金额
             */
            System.out.println(list[0] + "审批:金额:" + list[1]);
            if(((Integer)list[1]) > 6000)
                return false;
        }
        else if(this.operater == 2)
            System.out.println("报销入卡:金额:"+list[0]);
        else
            System.out.println("重填:申请人:"+list[0]);
        return true;
    }
}
