package com.example.springbootdemomytool.utils.threaddemo.threaddemo02;

/**
 * @ClassName ThreadSafeOrNot
 * @Description 如何判断一个程序是否会有线程安全问题？
 * @Author H
 * @Date 2023/1/11 18:14
 * @Version 1.0
 */
public class ThreadSafeOrNot {
    /**
     * 如何解决线程安全问题呢？
     * 想要解决问题，就要知道哪些原因会导致问题：（而且这些原因也是以后我们判断一个程序是否会有线程安全问题的依据）
     * A:是否是多线程环境
     * B:是否有共享数据
     * C:是否有多条语句操作共享数据
     * 我们来回想一下我们的程序有没有上面的问题呢？
     * A:是否是多线程环境            是
     * B:是否有共享数据              是
     * C:是否有多条语句操作共享数据   是
     * 由此可见我们的程序出现问题是正常的，因为它满足出问题的条件。
     * <p>
     * 接下来才是我们要想想如何解决问题呢？
     * A和B的问题我们改变不了，我们只能想办法去把C问题改变一下。
     * 思想：
     * 把多条语句操作共享数据的代码部分给包起来（锁起来），让某个线程在执行的时候，别人不能来执行。
     * （即：把多个语句操作共享数据的代码给锁起来，让任意时刻只能有一个线程执行即可）
     * 问题是：我们不知道怎么包？但是Java给我们提供了：同步机制
     * <p>
     * 生活举例：
     * 火车上厕所
     * 医院挂号看病
     * 同步代码块：
     * synchronized（对象）{
     * 需要同步的代码
     * }
     * A: 对象是什么呢？
     * 我们可以随便创建一个对象先试试，例如：new Object();
     * B: 需要同步的代码是哪些呢？
     * 把多条语句操作共享数据的代码部分给包起来（锁起来）。
     * 注意：
     * 同步可以解决安全问题的根本原因就在那个对象上。该对象如同锁的功能。
     * 多个线程必须是同一把锁。
     */

    public static void main(String[] args) {
        // 创建资源对象
        SellTicket sellTicket = new SellTicket();

        // 创建三个线程对象
        Thread t1 = new Thread(sellTicket, "窗口1");
        Thread t2 = new Thread(sellTicket, "窗口2");
        Thread t3 = new Thread(sellTicket, "窗口3");

        // 启动线程
        t1.start();
        t2.start();
        t3.start();
    }


}
