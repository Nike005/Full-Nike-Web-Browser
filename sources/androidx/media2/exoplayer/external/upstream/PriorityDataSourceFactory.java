package androidx.media2.exoplayer.external.upstream;

import androidx.media2.exoplayer.external.upstream.DataSource;
import androidx.media2.exoplayer.external.util.PriorityTaskManager;

public final class PriorityDataSourceFactory implements DataSource.Factory {
    private final int priority;
    private final PriorityTaskManager priorityTaskManager;
    private final DataSource.Factory upstreamFactory;

    public PriorityDataSourceFactory(DataSource.Factory factory, PriorityTaskManager priorityTaskManager2, int i) {
        this.upstreamFactory = factory;
        this.priorityTaskManager = priorityTaskManager2;
        this.priority = i;
    }

    public PriorityDataSource createDataSource() {
        return new PriorityDataSource(this.upstreamFactory.createDataSource(), this.priorityTaskManager, this.priority);
    }
}
