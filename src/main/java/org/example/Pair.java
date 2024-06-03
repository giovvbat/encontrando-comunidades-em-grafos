package org.example;

import java.util.Objects;

public class Pair {
    public Integer firstVertex;
    public Integer secondVertex;

    public Pair(Integer firstVertex, Integer secondVertex) {
        this.firstVertex = firstVertex;
        this.secondVertex = secondVertex;
    }

    public Integer getFirstVertex() {
        return firstVertex;
    }

    public Integer getSecondVertex() {
        return secondVertex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair pair)) return false;
        return Objects.equals(firstVertex, pair.firstVertex) && Objects.equals(secondVertex, pair.secondVertex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstVertex, secondVertex);
    }
}
