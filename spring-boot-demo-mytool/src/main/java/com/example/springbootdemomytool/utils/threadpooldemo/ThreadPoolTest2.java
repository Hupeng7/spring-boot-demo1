package com.example.springbootdemomytool.utils.threadpooldemo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.IntStream;

import static com.example.springbootdemomytool.utils.java8.ConcurrentUtils.sleep;
import static com.example.springbootdemomytool.utils.java8.ConcurrentUtils.stop;

/**
 * @ClassName ThreadPoolDemo3
 * @Description synchronized保证线程安全
 * 注意一般的自增线程不安全,修饰词加static,还是线程不安全的
 * @Author hup
 * @Date 2020/9/14 15:39
 * @Version 1.0
 */
public class ThreadPoolTest2 {
    int count = 0;
    int count1 = 0;
    int count2 = 0;

    public static void main(String[] args) {
        ThreadPoolTest2 threadPoolTest2 = new ThreadPoolTest2();
        threadPoolTest2.doThread();
        threadPoolTest2.doThread1();
        threadPoolTest2.doThread2();

        System.out.println("------------------StampedLock------------------");
        threadPoolTest2.doThread4();
        System.out.println("------------------StampedLock 乐观锁------------------");
        threadPoolTest2.doThread5();
        System.out.println("------------------StampedLock 乐观锁------------------");
        threadPoolTest2.doThread6();
    }

    private void doThread() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        IntStream.range(0, 10000)
                .forEach(i -> {
                    executorService.submit(this::incrementSync);
                });
        stop(executorService);
        System.out.println("count:" + count);
    }

    private void doThread1() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        IntStream.range(0, 10000)
                .forEach(i -> {
                    executorService.submit(this::increment);
                });
        stop(executorService);
        System.out.println("count1:" + count1);
    }

    /**
     * 线程安全
     */
    void incrementSync() {
        synchronized (this) {
            count = count + 1;
        }
    }

    /**
     * 线程不安全
     */
    void increment() {
        count1 = count1 + 1;
    }

    /**
     * 互斥锁 ReentrantLock
     */
    private void doThread2() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ReentrantLock lock = new ReentrantLock();

        executorService.submit(() -> {
            lock.lock();
            try {
                count2++;
                sleep(1);
            } finally {
                lock.unlock();
            }
        });

        executorService.submit(() -> {
            System.out.println("Locked: " + lock.isLocked());
            System.out.println("Held by me: " + lock.isHeldByCurrentThread());
            // tryLock()方法是lock()方法的替代，它尝试拿锁而不阻塞当前线程。
            // 在访问任何共享可变变量之前，必须使用布尔值结果来检查锁是否已经被获取。
            boolean locked = lock.tryLock();
            System.out.println("Lock acquired: " + locked);
        });
        System.out.println("count2: " + count2);
        stop(executorService);
    }

    /**
     * ReadWriteLock接口规定了锁的另一种类型，包含用于读写访问的一对锁。
     */
    private void doThread3() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Map<String, String> map = new HashMap<>();
        ReadWriteLock lock = new ReentrantReadWriteLock();

        executorService.submit(() -> {
            lock.writeLock().lock();
            try {
                sleep(1);
                map.put("foo", "bar");
            } finally {
                lock.writeLock().unlock();
            }
        });
        stop(executorService);
    }

    /**
     * Java8自带了一种新的锁，叫StampedLock,它同样支持读写锁。
     * 与ReadWriteLock不同的是，StampedLock的锁方法会返回表示为long的标记。
     * 可以使用这些标记来释放锁，或者检查锁是否有效。
     * 此外，StampedLock支持另一种叫做乐观锁（optimistic locking）的模式
     */
    private void doThread4() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        HashMap<String, String> map = new HashMap<>();
        StampedLock lock = new StampedLock();

        /**
         * 通过readLock()或writeLock()来获取读锁或写锁会返回一个标记，
         * 它可以在稍后用于在finally块中解锁。
         * 要记住StampedLock并没有实现重入特性。
         * 每次调用加锁都会返回一个新的标记，并且在没有可用的锁时阻塞，
         * 即使相同线程已经拿锁了。所以要额外注意不要出现死锁。
         */
        executorService.submit(() -> {
            long stamp = lock.writeLock();
            try {
                sleep(1);
                map.put("foo", "bar");
            } finally {
                lock.unlockWrite(stamp);
            }
        });

        Runnable readTask = () -> {
            long stamp = lock.readLock();
            try {
                System.out.println(map.get("foo"));
                sleep(1);
            } finally {
                lock.unlockRead(stamp);
            }
        };
        executorService.submit(readTask);
        executorService.submit(readTask);

        stop(executorService);
    }

    /**
     * 乐观锁，乐观的读锁通过调用 tryOptimisticRead()获取，它总是返回一个标记而不阻塞的当前线程，
     * 无论锁是否真正可用
     */
    private void doThread5() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        StampedLock lock = new StampedLock();

        executorService.submit(() -> {
            long stamp = lock.tryOptimisticRead();
            try {
                System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
                sleep(1);
                System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
                sleep(2);
                System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
            } finally {
                lock.unlock(stamp);
            }
        });

        executorService.submit(() -> {
            long stamp = lock.writeLock();
            try {
                System.out.println("Write lock acquired");
                sleep(2);
            } finally {
                lock.unlock(stamp);
                System.out.println("Write done");
            }
        });

        stop(executorService);
    }

    int count6 = 0;

    private void doThread6() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        StampedLock lock = new StampedLock();

        executorService.submit(() -> {
            long stamp = lock.readLock();

            try {
                if (count6 == 0) {
                    stamp = lock.tryConvertToWriteLock(stamp);
                    if (stamp == 0L) {
                        System.out.println("Could not convert to write lock");
                        stamp = lock.writeLock();
                    }
                    count6 = 23;
                }
                System.out.println("count6: " + count6);
            } finally {
                lock.unlock(stamp);
            }
        });
        stop(executorService);
    }


}

