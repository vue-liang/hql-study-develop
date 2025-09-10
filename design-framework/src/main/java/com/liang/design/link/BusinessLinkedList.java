package com.liang.design.link;


public class BusinessLinkedList<T,D,R> extends LinkedList<ILogicHandler<T,D,R>> implements ILogicHandler<T,D,R> {
    @Override
    public R apply(T requestParameter, D dynamicContext) throws Exception {
        Node<ILogicHandler<T, D, R>> node = this.first;
        R apply;
        do{
            apply = node.item.apply(requestParameter, dynamicContext);
            if(apply != null) return apply;
            node = node.next;
        }while(node != null);
        return null;
    }
}
