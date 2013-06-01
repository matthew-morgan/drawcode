package drawcode.utils;
/**
 * Created with IntelliJ IDEA.
 * User: Matthew
 * Date: 30/05/13
 * Time: 11:37
 * To change this template use File | Settings | File Templates.
 */
public class TwoItemRing<T> {
    T item1 = null;
    T item2 = null;

    public void add(T newItem){
        item2 = item1;
        item1 = newItem;
    }

    public T getFirst(){
        return item2;  //item2 was the first item added
    }

    public T getSecond(){
        return item1;
    }
}
