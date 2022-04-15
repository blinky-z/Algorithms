package me.progbloom.hashing.jump;

import org.jetbrains.annotations.NotNull;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * Хранит информацию о шарде.
 * <p>
 * Каждому шарду назначается свой ID внутри приложения и шарды пронумерованы последовательно. Такая последовательная нумерация
 * требуется для Jump Consistent Hashing алгоритма.
 */
public final class OrderedShard {

    /**
     * ID шарда
     */
    @NotNull
    private final Integer id;

    /**
     * Вес
     */
    @NotNull
    private final Integer weight;

    /**
     * data source
     */
    @NotNull
    private final DataSource dataSource;

    public OrderedShard(@NotNull Integer id,
                        @NotNull Integer weight,
                        @NotNull DataSource dataSource) {
        this.id = Objects.requireNonNull(id);
        this.weight = Objects.requireNonNull(weight);
        this.dataSource = Objects.requireNonNull(dataSource);
    }

    @NotNull
    public Integer getId() {
        return id;
    }

    @NotNull
    public Integer getWeight() {
        return weight;
    }

    @NotNull
    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderedShard shard = (OrderedShard) o;
        return id.equals(shard.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Shard{" +
                "id=" + id +
                ", weight=" + weight +
                '}';
    }
}
