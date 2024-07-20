package org.apache.commons.lang3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ThreadUtils {
    public static final AlwaysTruePredicate ALWAYS_TRUE_PREDICATE = new AlwaysTruePredicate();

    public interface ThreadGroupPredicate {
        boolean test(ThreadGroup threadGroup);
    }

    public interface ThreadPredicate {
        boolean test(Thread thread);
    }

    public static Thread findThreadById(long j, ThreadGroup threadGroup) {
        if (threadGroup != null) {
            Thread findThreadById = findThreadById(j);
            if (findThreadById == null || !threadGroup.equals(findThreadById.getThreadGroup())) {
                return null;
            }
            return findThreadById;
        }
        throw new IllegalArgumentException("The thread group must not be null");
    }

    public static Thread findThreadById(long j, String str) {
        if (str != null) {
            Thread findThreadById = findThreadById(j);
            if (findThreadById == null || findThreadById.getThreadGroup() == null || !findThreadById.getThreadGroup().getName().equals(str)) {
                return null;
            }
            return findThreadById;
        }
        throw new IllegalArgumentException("The thread group name must not be null");
    }

    public static Collection<Thread> findThreadsByName(String str, ThreadGroup threadGroup) {
        return findThreads(threadGroup, false, new NamePredicate(str));
    }

    public static Collection<Thread> findThreadsByName(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("The thread name must not be null");
        } else if (str2 != null) {
            Collection<ThreadGroup> findThreadGroups = findThreadGroups(new NamePredicate(str2));
            if (findThreadGroups.isEmpty()) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            NamePredicate namePredicate = new NamePredicate(str);
            for (ThreadGroup findThreads : findThreadGroups) {
                arrayList.addAll(findThreads(findThreads, false, namePredicate));
            }
            return Collections.unmodifiableCollection(arrayList);
        } else {
            throw new IllegalArgumentException("The thread group name must not be null");
        }
    }

    public static Collection<ThreadGroup> findThreadGroupsByName(String str) {
        return findThreadGroups(new NamePredicate(str));
    }

    public static Collection<ThreadGroup> getAllThreadGroups() {
        return findThreadGroups(ALWAYS_TRUE_PREDICATE);
    }

    public static ThreadGroup getSystemThreadGroup() {
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        while (threadGroup.getParent() != null) {
            threadGroup = threadGroup.getParent();
        }
        return threadGroup;
    }

    public static Collection<Thread> getAllThreads() {
        return findThreads(ALWAYS_TRUE_PREDICATE);
    }

    public static Collection<Thread> findThreadsByName(String str) {
        return findThreads(new NamePredicate(str));
    }

    public static Thread findThreadById(long j) {
        Collection<Thread> findThreads = findThreads(new ThreadIdPredicate(j));
        if (findThreads.isEmpty()) {
            return null;
        }
        return findThreads.iterator().next();
    }

    private static final class AlwaysTruePredicate implements ThreadPredicate, ThreadGroupPredicate {
        public boolean test(Thread thread) {
            return true;
        }

        public boolean test(ThreadGroup threadGroup) {
            return true;
        }

        private AlwaysTruePredicate() {
        }
    }

    public static class NamePredicate implements ThreadPredicate, ThreadGroupPredicate {
        private final String name;

        public NamePredicate(String str) {
            if (str != null) {
                this.name = str;
                return;
            }
            throw new IllegalArgumentException("The name must not be null");
        }

        public boolean test(ThreadGroup threadGroup) {
            return threadGroup != null && threadGroup.getName().equals(this.name);
        }

        public boolean test(Thread thread) {
            return thread != null && thread.getName().equals(this.name);
        }
    }

    public static class ThreadIdPredicate implements ThreadPredicate {
        private final long threadId;

        public ThreadIdPredicate(long j) {
            if (j > 0) {
                this.threadId = j;
                return;
            }
            throw new IllegalArgumentException("The thread id must be greater than zero");
        }

        public boolean test(Thread thread) {
            return thread != null && thread.getId() == this.threadId;
        }
    }

    public static Collection<Thread> findThreads(ThreadPredicate threadPredicate) {
        return findThreads(getSystemThreadGroup(), true, threadPredicate);
    }

    public static Collection<ThreadGroup> findThreadGroups(ThreadGroupPredicate threadGroupPredicate) {
        return findThreadGroups(getSystemThreadGroup(), true, threadGroupPredicate);
    }

    public static Collection<Thread> findThreads(ThreadGroup threadGroup, boolean z, ThreadPredicate threadPredicate) {
        Thread[] threadArr;
        int enumerate;
        if (threadGroup == null) {
            throw new IllegalArgumentException("The group must not be null");
        } else if (threadPredicate != null) {
            int activeCount = threadGroup.activeCount();
            while (true) {
                int i = activeCount + (activeCount / 2) + 1;
                threadArr = new Thread[i];
                enumerate = threadGroup.enumerate(threadArr, z);
                if (enumerate < i) {
                    break;
                }
                activeCount = enumerate;
            }
            ArrayList arrayList = new ArrayList(enumerate);
            for (int i2 = 0; i2 < enumerate; i2++) {
                if (threadPredicate.test(threadArr[i2])) {
                    arrayList.add(threadArr[i2]);
                }
            }
            return Collections.unmodifiableCollection(arrayList);
        } else {
            throw new IllegalArgumentException("The predicate must not be null");
        }
    }

    public static Collection<ThreadGroup> findThreadGroups(ThreadGroup threadGroup, boolean z, ThreadGroupPredicate threadGroupPredicate) {
        ThreadGroup[] threadGroupArr;
        int enumerate;
        if (threadGroup == null) {
            throw new IllegalArgumentException("The group must not be null");
        } else if (threadGroupPredicate != null) {
            int activeGroupCount = threadGroup.activeGroupCount();
            while (true) {
                int i = activeGroupCount + (activeGroupCount / 2) + 1;
                threadGroupArr = new ThreadGroup[i];
                enumerate = threadGroup.enumerate(threadGroupArr, z);
                if (enumerate < i) {
                    break;
                }
                activeGroupCount = enumerate;
            }
            ArrayList arrayList = new ArrayList(enumerate);
            for (int i2 = 0; i2 < enumerate; i2++) {
                if (threadGroupPredicate.test(threadGroupArr[i2])) {
                    arrayList.add(threadGroupArr[i2]);
                }
            }
            return Collections.unmodifiableCollection(arrayList);
        } else {
            throw new IllegalArgumentException("The predicate must not be null");
        }
    }
}
