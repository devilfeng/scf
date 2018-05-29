package org.mydotey.scf.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author koqizhao
 *
 * May 21, 2018
 */
public class PipelineValueFilter<V> implements Function<V, V> {

    private List<Function<V, V>> _filters;

    public PipelineValueFilter(List<Function<V, V>> filters) {
        Objects.requireNonNull(filters, "filters is null");

        _filters = new ArrayList<>();
        filters.forEach(f -> {
            if (f != null)
                _filters.add(f);
        });

        if (_filters.isEmpty())
            throw new IllegalArgumentException("filters is empty");
    }

    @Override
    public V apply(V t) {
        for (Function<V, V> filter : _filters) {
            t = filter.apply(t);
            if (t == null)
                return null;
        }

        return t;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (this.getClass() != obj.getClass())
            return false;

        PipelineValueFilter other = (PipelineValueFilter) obj;
        return Objects.equals(_filters, other._filters);
    }

    @Override
    public String toString() {
        return String.format("%s { filters: %s }", getClass().getSimpleName(), _filters);
    }

}
