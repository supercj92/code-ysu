package com.cfysu.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @Author canglong
 * @Date 2023/10/26
 */
public class FlowController<Input, Output> {

    private int targetRtAve;
    private int targetSuccessRate;
    private List<FlowRule> flowRules;

    private long invokeCount = 0;
    private int failCount = 0;
    private long rtTotal = 0;

    public FlowController(int rt, int successRate) {
        this.targetRtAve = rt;
        this.targetSuccessRate = successRate;
        flowRules = new ArrayList<>();
        flowRules.add(new RTFlowRule());
    }

    public Output invoke(Function<Input, Output> function, Input input) {

        long currentRtAve = (rtTotal / invokeCount);
        long currentSuccessRate = (invokeCount - failCount) / invokeCount;
        for (FlowRule flowRule : flowRules) {
            boolean res = flowRule.check(new FlowContext(targetRtAve, targetSuccessRate), currentRtAve,
                currentSuccessRate);
            if (!res) {
                throw new RuntimeException("blocked by flow rule");
            }
        }

        long start = System.currentTimeMillis();
        invokeCount++;
        Output output = null;
        try {
            output = function.apply(input);
        } catch (Exception e) {
            failCount++;
        }
        long rt = System.currentTimeMillis() - start;
        rtTotal += rt;

        return output;
    }

    public static class FlowContext {
        private long targetRtAve;
        private int targetSuccessRate;

        public FlowContext(long rtAve, int successRate) {
            this.targetRtAve = rtAve;
            this.targetSuccessRate = successRate;
        }
    }

    interface FlowRule {
        boolean check(FlowContext flowContext, long currentRT, long currentSuccessRate);
    }

    public class RTFlowRule implements FlowRule {
        @Override
        public boolean check(FlowContext flowContext, long currentRT, long currentSuccessRate) {
            if (currentRT <= flowContext.targetRtAve) {
                return true;
            }
            return false;
        }
    }
}
