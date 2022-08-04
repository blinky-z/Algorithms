package me.progbloom.algo.hashing.jump;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Objects;

/**
 * Шардирование с использованием Jump Consistent Hashing алгоритма
 *
 * @see <a href="https://arxiv.org/abs/1406.2294">White Paper</a>
 */
public final class JumpHashShardingStrategy {

    private JumpHashShardingStrategy() {
    }

    /**
     * Вычисляет шард по заданному ID пользователя-отправителя.
     *
     * @param senderId ID пользователя-отправителя
     * @param shards   шарды, отсортированные в порядке возрастания их ID
     * @return шард
     */
    @NotNull
    public static OrderedShard getShard(@NotNull String senderId,
                                        @NotNull Collection<OrderedShard> shards) {
        Objects.requireNonNull(senderId);
        Objects.requireNonNull(shards);
        if (shards.isEmpty()) {
            throw new IllegalArgumentException("Список шардов не может быть пустым");
        }

        // вычисляем hashCode
        final HashCode hashCode = Hashing.murmur3_128().hashString(senderId, Charsets.UTF_8);

        // вычисляем индекс для элемента, используя библиотечный метод консистентного хеширования
        // шарды имеют вес, то есть их можно рассматривать как Virtual Node
        final int totalWeight = shards.stream()
                .map(OrderedShard::getWeight)
                .reduce(0, Integer::sum);
        final int weightedKey = Hashing.consistentHash(hashCode, totalWeight);

        // ищем ближайший к вычисленному индексу шард по часовой стрелке
        int weightAccum = 0;
        for (OrderedShard shard : shards) {
            if (weightedKey <= weightAccum) {
                return shard;
            }
            weightAccum += shard.getWeight();
        }
        throw new IllegalStateException("Ошибка вычисления шарда. ID пользователя: " + senderId);
    }
}
