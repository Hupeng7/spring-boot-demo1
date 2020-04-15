package com.example.springbootdemomytool.utils.java8;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.IntStream;

import static com.example.springbootdemomytool.utils.java8.ConcurrentUtils.sleep;
import static com.example.springbootdemomytool.utils.java8.ConcurrentUtils.stop;

/**
 * @ClassName LockDemo
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/15 15:48
 * @Version 1.0
 */
public class LockDemo {

    int count = 0;

    void increment() {
        count = count + 1;
    }

    /**
     * 同步并发线程
     */
    @Test
    public void testLock() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        IntStream.range(0, 10000)
                .forEach(i -> executor.submit(this::increment));
        stop(executor);
        System.out.println("count: " + count);
    }

//    synchronized void increamentSync() {
//        count = count + 1;
//    }

    void incrementSync() {
        synchronized (this) {
            count = count + 1;
        }
    }

    /**
     * synchronized关键字支持线程同步
     */
    @Test
    public void testLockSync() {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 10000)
                .forEach(i -> executor.submit(this::incrementSync));
        stop(executor);
        System.out.println("count: " + count);
    }

    /**
     * ReentrantLock类是互斥锁，与通过synchronized访问的隐式监视器具有相同行为，但是具有扩展功能。
     * 就像它的名称一样，这个锁实现了重入特性，就像隐式监视器一样。
     */
    ReentrantLock lock = new ReentrantLock();

    void incrementLock() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    @Test
    public void testLock1() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> {
            lock.lock();
            try {
                sleep(1);
            } finally {
                lock.unlock();
            }
        });

        executor.submit(() -> {
            System.out.println("Locked: " + lock.isLocked());
            System.out.println("Held by me: " + lock.isHeldByCurrentThread());
            boolean locked = lock.tryLock();
            System.out.println("Lock acquired: " + locked);
        });

        stop(executor);
    }

    /**
     * ReadWriteLock接口规定了锁的另一种类型，包含用于读写访问的一对锁
     * 读写锁的理念是，只要没有任何线程写入变量，并发读取可变变量通常是安全的。
     * 所以读锁可以同时被多个线程持有，只要没有线程持有写锁。
     * 这样可以提升性能和吞吐量，因为读取比写入更加频繁。
     */
    @Test
    public void testLockReadWrite() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Map<String, String> map = new HashMap<>();
        ReadWriteLock lock = new ReentrantReadWriteLock();

        executor.submit(() -> {
            lock.writeLock().lock();
            try {
                System.out.println("wait 1s");
                sleep(1);
                map.put("foo", "bar");
            } finally {
                lock.writeLock().unlock();
            }
        });

        Runnable readTask = () -> {
            lock.readLock().lock();
            try {
                System.out.println("v: " + map.get("foo"));
                sleep(1);
            } finally {
                lock.readLock().unlock();
            }
        };

        executor.submit(readTask);
        executor.submit(readTask);

        stop(executor);
    }

    @Test
    public void testStampedLock() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Map<String, String> map = new HashMap<>();
        StampedLock lock = new StampedLock();

        executor.submit(() -> {
            long stamp = lock.writeLock();
            try {
                System.out.println("wait 1s");
                sleep(1);
                map.put("foo", "bar");
            } finally {
                lock.unlockWrite(stamp);
            }
        });

        Runnable readTask = () -> {
            long stamp = lock.readLock();
            try {
                System.out.println("read v: " + map.get("foo"));
                sleep(1);
            } finally {
                lock.unlockRead(stamp);
            }
        };

        executor.submit(readTask);
        executor.submit(readTask);

        stop(executor);
    }

    /**
     * 乐观锁在刚刚拿到锁之后是有效的。和普通的读锁不同的是，乐观锁不阻止其他线程同时获取写锁。在第一个线程暂停一秒之后，
     * 第二个线程拿到写锁而无需等待乐观的读锁被释放。此时，乐观的读锁就不再有效了。甚至当写锁释放时，乐观的读锁还处于无效状态。
     * 所以在使用乐观锁时，你需要每次在访问任何共享可变变量之后都要检查锁，来确保读锁仍然有效。
     */
    @Test
    public void testOptimisticLock() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        StampedLock lock = new StampedLock();

        executor.submit(() -> {
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

        executor.submit(() -> {
            long stamp = lock.writeLock();

            try {
                System.out.println("Write Lock acquired");
                sleep(2);
            } finally {
                lock.unlock(stamp);
                System.out.println("Write done");
            }
        });
        stop(executor);
    }

    /**
     * 第一个任务获取读锁，并向控制台打印count字段的当前值。但是如果当前值是零，我们希望将其赋值为23。
     * 我们首先需要将读锁转换为写锁，来避免打破其它线程潜在的并发访问。tryConvertToWriteLock()的调用不会阻塞，
     * 但是可能会返回为零的标记，表示当前没有可用的写锁。这种情况下，我们调用writeLock()来阻塞当前线程，直到有可用的写锁。
     */
    @Test
    public void testConvertToWriteLock() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        StampedLock lock = new StampedLock();

        executor.submit(() -> {
            long stamp = lock.readLock();
            try {
                if (count == 0) {
                    stamp = lock.tryConvertToWriteLock(stamp);
                    if (stamp == 0L) {
                        System.out.println("Could not convert to write lock");
                        stamp = lock.writeLock();
                    }
                    count = 23;
                }
                System.out.println("count: " + count);
            } finally {
                lock.unlock(stamp);
            }
        });
        stop(executor);
    }

    /**
     * 信号量
     * 除了锁之外，并发API也支持计数的信号量。不过锁通常用于变量或资源的互斥访问，信号量可以维护整体的准入许可。
     * 这在一些不同场景下，例如你需要限制你程序某个部分的并发访问总数时非常实用。
     * 下面是一个例子，演示了如何限制对通过sleep(5)模拟的长时间运行任务的访问：
     * 执行器可能同时运行10个任务，但是我们使用了大小为5的信号量，所以将并发访问限制为5。使用try-finally代码块在异常情况中合理释放信号量十分重要。
     * <p>
     * 信号量限制对通过sleep(5)模拟的长时间运行任务的访问，最大5个线程。
     * 每个随后的tryAcquire()调用在经过最大为一秒的等待超时之后，会向控制台打印不能获取信号量的结果。
     */
    @Test
    public void testSemaphore() {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Semaphore semaphore = new Semaphore(5);

        Runnable longRunningTask = () -> {
            boolean permit = false;
            try {
                permit = semaphore.tryAcquire(1, TimeUnit.SECONDS);
                if (permit) {
                    System.out.println("Semaphore acquired");
                    sleep(5);
                } else {
                    System.out.println("Could not acquire semaphore");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new IllegalStateException(e);
            } finally {
                if (permit) {
                    semaphore.release();
                }
            }
        };

        IntStream.range(0, 10)
                .forEach(i -> executor.submit(longRunningTask));

        stop(executor);
    }


}
