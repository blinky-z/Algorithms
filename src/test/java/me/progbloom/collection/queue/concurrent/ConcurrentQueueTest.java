package me.progbloom.collection.queue.concurrent;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ConcurrentQueueTest {

    private static final int THREAD_POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private static ExecutorService executorService;

    private final Random random = new Random();

    @BeforeAll
    static void beforeAll() {
        executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
    }

    @AfterAll
    static void afterAll() {
        executorService.shutdown();
    }

    private static Stream<Arguments> queueGenerator() {
        return Stream.of(
            arguments(new BlockingConcurrentQueue<Integer>()),
            arguments(new NonBlockingConcurrentQueue<Integer>()),
            arguments(new MichaelScottLockFreeConcurrentQueue<Integer>())
        );
    }

    @ParameterizedTest
    @MethodSource("queueGenerator")
    public void test(ConcurrentQueue<Integer> queue) throws ExecutionException, InterruptedException {
        final int tasksPerThread = 10000;

        ConcurrentLinkedQueue<Integer> expectedItems = new ConcurrentLinkedQueue<>();
        List<Future<?>> futures = new ArrayList<>();
        IntStream.range(0, THREAD_POOL_SIZE).forEach(curThread -> {
                futures.add(executorService.submit(() -> IntStream.range(0, tasksPerThread).forEach(curNum -> {
                    int curItem = random.nextInt();
                    expectedItems.add(curItem);
                    queue.add(curItem);
                })));
            }
        );

        for (Future<?> future : futures) {
            future.get();
        }

        // then
        assertThat(queue.size()).isEqualTo(expectedItems.size());

        List<Integer> actualItems = queue.getAll();
        assertThat(actualItems).doesNotContainNull();
        assertThat(actualItems).containsExactlyInAnyOrderElementsOf(expectedItems);
    }

    @ParameterizedTest
    @MethodSource("queueGenerator")
    public void testEmptyQueue(ConcurrentQueue<Integer> queue) {
        assertThat(queue.size()).isEqualTo(0);
        assertThat(queue.getAll()).hasSize(0);
    }

    @ParameterizedTest
    @MethodSource("queueGenerator")
    public void testSingleItemQueue(ConcurrentQueue<Integer> queue) {
        int item = 10;
        queue.add(item);

        assertThat(queue.size()).isEqualTo(1);
        assertThat(queue.getAll()).hasSize(1);
        assertThat(queue.getAll().get(0)).isEqualTo(item);
    }
}