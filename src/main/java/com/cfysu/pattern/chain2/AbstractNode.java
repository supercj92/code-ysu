package com.cfysu.pattern.chain2;

/**
 * @Author canglong
 * @Date 2019/3/29
 */
public abstract class AbstractNode implements Node {

    protected Node next;
    protected String nodeName = this.getClass().getSimpleName();

    protected AbstractNode(){}
    protected AbstractNode(Node node){
        this.next = node;
    }

    @Override
    public Response process(Request request) {
        if(support(request)){
            request.markProcessed(nodeName);
            return doProcess(request);
        }

        if(next != null){
            return next.process(request);
        }
        return null;
    }

    /**
     * 此节点是否支持此请求
     * @param request
     * @return
     */
    public abstract boolean support(Request request);

    /**
     * 实际处理请求
     * @param request
     * @return
     */
    public abstract Response doProcess(Request request);

}
