package huyue.util;

/**
 * Created with IntelliJ IDEA.
 * Description: ThreadLocalHolder 用于作为 线程本地保存变量
 * User: HHH.Y
 * Date: 2020-08-07
 */
public class ThreadLocalHolder {
    // 一般情况下, 需要 ThreadLocal 的生命周期很长, 所以将其定义成一个静态的变量
    private static ThreadLocal<Integer> TOTAL = new ThreadLocal<>();

    public static ThreadLocal<Integer> getTOTAL() {
        return TOTAL;
    }

}
