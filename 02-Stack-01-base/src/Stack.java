/**
 * 支持泛型，通过接口，规范栈的行为
 * @param <E>
 */
public interface Stack<E> {
    //入栈
    void push(E e);
    //出栈
    E pop();
    //读取栈顶元素
    E peek();
    //判断是否为空
    boolean isEmpty();
    //栈内元素个数
    int getSize();
}
