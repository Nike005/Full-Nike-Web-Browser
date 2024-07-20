package acr.browser.lightning.fragment.anim;

import acr.browser.lightning.interpolator.BezierDecelerateInterpolator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HorizontalItemAnimator extends SimpleItemAnimator {
    private static final boolean DEBUG = false;
    /* access modifiers changed from: private */
    public final ArrayList<RecyclerView.ViewHolder> mAddAnimations = new ArrayList<>();
    /* access modifiers changed from: private */
    public final ArrayList<ArrayList<RecyclerView.ViewHolder>> mAdditionsList = new ArrayList<>();
    /* access modifiers changed from: private */
    public final ArrayList<RecyclerView.ViewHolder> mChangeAnimations = new ArrayList<>();
    /* access modifiers changed from: private */
    public final ArrayList<ArrayList<ChangeInfo>> mChangesList = new ArrayList<>();
    private TimeInterpolator mDefaultInterpolator;
    /* access modifiers changed from: private */
    public final ArrayList<RecyclerView.ViewHolder> mMoveAnimations = new ArrayList<>();
    /* access modifiers changed from: private */
    public final ArrayList<ArrayList<MoveInfo>> mMovesList = new ArrayList<>();
    private final ArrayList<RecyclerView.ViewHolder> mPendingAdditions = new ArrayList<>();
    private final ArrayList<ChangeInfo> mPendingChanges = new ArrayList<>();
    private final ArrayList<MoveInfo> mPendingMoves = new ArrayList<>();
    private final ArrayList<RecyclerView.ViewHolder> mPendingRemovals = new ArrayList<>();
    /* access modifiers changed from: private */
    public final ArrayList<RecyclerView.ViewHolder> mRemoveAnimations = new ArrayList<>();

    private static class MoveInfo {
        public final int fromX;
        public final int fromY;
        public final RecyclerView.ViewHolder holder;
        public final int toX;
        public final int toY;

        private MoveInfo(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4) {
            this.holder = viewHolder;
            this.fromX = i;
            this.fromY = i2;
            this.toX = i3;
            this.toY = i4;
        }
    }

    private static class ChangeInfo {
        public int fromX;
        public int fromY;
        public RecyclerView.ViewHolder newHolder;
        public RecyclerView.ViewHolder oldHolder;
        public int toX;
        public int toY;

        private ChangeInfo(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
            this.oldHolder = viewHolder;
            this.newHolder = viewHolder2;
        }

        private ChangeInfo(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, int i, int i2, int i3, int i4) {
            this(viewHolder, viewHolder2);
            this.fromX = i;
            this.fromY = i2;
            this.toX = i3;
            this.toY = i4;
        }

        public String toString() {
            return "ChangeInfo{oldHolder=" + this.oldHolder + ", newHolder=" + this.newHolder + ", fromX=" + this.fromX + ", fromY=" + this.fromY + ", toX=" + this.toX + ", toY=" + this.toY + '}';
        }
    }

    public void runPendingAnimations() {
        boolean z = !this.mPendingRemovals.isEmpty();
        boolean z2 = !this.mPendingMoves.isEmpty();
        boolean z3 = !this.mPendingChanges.isEmpty();
        boolean z4 = !this.mPendingAdditions.isEmpty();
        if (z || z2 || z4 || z3) {
            Iterator<RecyclerView.ViewHolder> it = this.mPendingRemovals.iterator();
            while (it.hasNext()) {
                animateRemoveImpl(it.next());
            }
            this.mPendingRemovals.clear();
            if (z2) {
                final ArrayList arrayList = new ArrayList();
                arrayList.addAll(this.mPendingMoves);
                this.mMovesList.add(arrayList);
                this.mPendingMoves.clear();
                C31941 r6 = new Runnable() {
                    public void run() {
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            MoveInfo moveInfo = (MoveInfo) it.next();
                            HorizontalItemAnimator.this.animateMoveImpl(moveInfo.holder, moveInfo.fromX, moveInfo.fromY, moveInfo.toX, moveInfo.toY);
                        }
                        arrayList.clear();
                        HorizontalItemAnimator.this.mMovesList.remove(arrayList);
                    }
                };
                if (z) {
                    ViewCompat.postOnAnimationDelayed(((MoveInfo) arrayList.get(0)).holder.itemView, r6, getRemoveDuration());
                } else {
                    r6.run();
                }
            }
            if (z3) {
                final ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(this.mPendingChanges);
                this.mChangesList.add(arrayList2);
                this.mPendingChanges.clear();
                C31952 r62 = new Runnable() {
                    public void run() {
                        Iterator it = arrayList2.iterator();
                        while (it.hasNext()) {
                            HorizontalItemAnimator.this.animateChangeImpl((ChangeInfo) it.next());
                        }
                        arrayList2.clear();
                        HorizontalItemAnimator.this.mChangesList.remove(arrayList2);
                    }
                };
                if (z) {
                    ViewCompat.postOnAnimationDelayed(((ChangeInfo) arrayList2.get(0)).oldHolder.itemView, r62, getRemoveDuration());
                } else {
                    r62.run();
                }
            }
            if (z4) {
                final ArrayList arrayList3 = new ArrayList();
                arrayList3.addAll(this.mPendingAdditions);
                this.mAdditionsList.add(arrayList3);
                this.mPendingAdditions.clear();
                C31963 r5 = new Runnable() {
                    public void run() {
                        Iterator it = arrayList3.iterator();
                        while (it.hasNext()) {
                            HorizontalItemAnimator.this.animateAddImpl((RecyclerView.ViewHolder) it.next());
                        }
                        arrayList3.clear();
                        HorizontalItemAnimator.this.mAdditionsList.remove(arrayList3);
                    }
                };
                if (z || z2 || z3) {
                    long j = 0;
                    long removeDuration = z ? getRemoveDuration() : 0;
                    long moveDuration = z2 ? getMoveDuration() : 0;
                    if (z3) {
                        j = getChangeDuration();
                    }
                    ViewCompat.postOnAnimationDelayed(((RecyclerView.ViewHolder) arrayList3.get(0)).itemView, r5, removeDuration + Math.max(moveDuration, j));
                    return;
                }
                r5.run();
            }
        }
    }

    public boolean animateRemove(RecyclerView.ViewHolder viewHolder) {
        resetAnimation(viewHolder);
        this.mPendingRemovals.add(viewHolder);
        return true;
    }

    private void animateRemoveImpl(final RecyclerView.ViewHolder viewHolder) {
        final ViewPropertyAnimatorCompat animate = ViewCompat.animate(viewHolder.itemView);
        this.mRemoveAnimations.add(viewHolder);
        animate.setDuration(getRemoveDuration()).alpha(0.0f).translationY((float) viewHolder.itemView.getHeight()).setInterpolator(new AccelerateInterpolator()).setListener(new VpaListenerAdapter() {
            public void onAnimationStart(View view) {
                HorizontalItemAnimator.this.dispatchRemoveStarting(viewHolder);
            }

            public void onAnimationEnd(View view) {
                animate.setListener((ViewPropertyAnimatorListener) null);
                ViewCompat.setAlpha(view, 1.0f);
                ViewCompat.setTranslationY(view, 0.0f);
                HorizontalItemAnimator.this.dispatchRemoveFinished(viewHolder);
                HorizontalItemAnimator.this.mRemoveAnimations.remove(viewHolder);
                HorizontalItemAnimator.this.dispatchFinishedWhenDone();
            }
        }).start();
    }

    public boolean animateAdd(RecyclerView.ViewHolder viewHolder) {
        resetAnimation(viewHolder);
        ViewCompat.setAlpha(viewHolder.itemView, 0.0f);
        ViewCompat.setTranslationY(viewHolder.itemView, (float) viewHolder.itemView.getHeight());
        this.mPendingAdditions.add(viewHolder);
        return true;
    }

    /* access modifiers changed from: private */
    public void animateAddImpl(final RecyclerView.ViewHolder viewHolder) {
        final ViewPropertyAnimatorCompat animate = ViewCompat.animate(viewHolder.itemView);
        this.mAddAnimations.add(viewHolder);
        animate.alpha(1.0f).translationY(0.0f).setInterpolator(new BezierDecelerateInterpolator()).setDuration(getAddDuration()).setListener(new VpaListenerAdapter() {
            public void onAnimationStart(View view) {
                HorizontalItemAnimator.this.dispatchAddStarting(viewHolder);
            }

            public void onAnimationCancel(View view) {
                ViewCompat.setTranslationY(view, 0.0f);
                ViewCompat.setAlpha(view, 1.0f);
            }

            public void onAnimationEnd(View view) {
                animate.setListener((ViewPropertyAnimatorListener) null);
                HorizontalItemAnimator.this.dispatchAddFinished(viewHolder);
                HorizontalItemAnimator.this.mAddAnimations.remove(viewHolder);
                HorizontalItemAnimator.this.dispatchFinishedWhenDone();
            }
        }).start();
    }

    public boolean animateMove(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4) {
        View view = viewHolder.itemView;
        int translationX = (int) (((float) i) + ViewCompat.getTranslationX(viewHolder.itemView));
        int translationY = (int) (((float) i2) + ViewCompat.getTranslationY(viewHolder.itemView));
        int i5 = i3 - translationX;
        int i6 = i4 - translationY;
        if (i5 == 0 && i6 == 0) {
            dispatchMoveFinished(viewHolder);
            return false;
        }
        resetAnimation(viewHolder);
        if (i5 != 0) {
            ViewCompat.setTranslationX(view, (float) (-i5));
        }
        if (i6 != 0) {
            ViewCompat.setTranslationY(view, (float) (-i6));
        }
        this.mPendingMoves.add(new MoveInfo(viewHolder, translationX, translationY, i3, i4));
        return true;
    }

    /* access modifiers changed from: private */
    public void animateMoveImpl(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4) {
        View view = viewHolder.itemView;
        final int i5 = i3 - i;
        final int i6 = i4 - i2;
        if (i5 != 0) {
            ViewCompat.animate(view).translationX(0.0f);
        }
        if (i6 != 0) {
            ViewCompat.animate(view).translationY(0.0f);
        }
        final ViewPropertyAnimatorCompat animate = ViewCompat.animate(view);
        this.mMoveAnimations.add(viewHolder);
        final RecyclerView.ViewHolder viewHolder2 = viewHolder;
        animate.setDuration(getMoveDuration()).setListener(new VpaListenerAdapter() {
            public void onAnimationStart(View view) {
                HorizontalItemAnimator.this.dispatchMoveStarting(viewHolder2);
            }

            public void onAnimationCancel(View view) {
                if (i5 != 0) {
                    ViewCompat.setTranslationX(view, 0.0f);
                }
                if (i6 != 0) {
                    ViewCompat.setTranslationY(view, 0.0f);
                }
            }

            public void onAnimationEnd(View view) {
                animate.setListener((ViewPropertyAnimatorListener) null);
                HorizontalItemAnimator.this.dispatchMoveFinished(viewHolder2);
                HorizontalItemAnimator.this.mMoveAnimations.remove(viewHolder2);
                HorizontalItemAnimator.this.dispatchFinishedWhenDone();
            }
        }).start();
    }

    public boolean animateChange(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, int i, int i2, int i3, int i4) {
        RecyclerView.ViewHolder viewHolder3 = viewHolder;
        RecyclerView.ViewHolder viewHolder4 = viewHolder2;
        if (viewHolder3 != viewHolder4) {
            float translationX = ViewCompat.getTranslationX(viewHolder3.itemView);
            float translationY = ViewCompat.getTranslationY(viewHolder3.itemView);
            float alpha = ViewCompat.getAlpha(viewHolder3.itemView);
            resetAnimation(viewHolder);
            int i5 = (int) (((float) (i3 - i)) - translationX);
            int i6 = (int) (((float) (i4 - i2)) - translationY);
            ViewCompat.setTranslationX(viewHolder3.itemView, translationX);
            ViewCompat.setTranslationY(viewHolder3.itemView, translationY);
            ViewCompat.setAlpha(viewHolder3.itemView, alpha);
            if (viewHolder4 != null) {
                resetAnimation(viewHolder4);
                ViewCompat.setTranslationX(viewHolder4.itemView, (float) (-i5));
                ViewCompat.setTranslationY(viewHolder4.itemView, (float) (-i6));
                ViewCompat.setAlpha(viewHolder4.itemView, 0.0f);
            }
            this.mPendingChanges.add(new ChangeInfo(viewHolder, viewHolder2, i, i2, i3, i4));
            return true;
        } else if (i - i3 != 0 || i2 - i4 != 0) {
            return animateMove(viewHolder, i, i2, i3, i4);
        } else {
            dispatchMoveFinished(viewHolder);
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void animateChangeImpl(final ChangeInfo changeInfo) {
        View view;
        RecyclerView.ViewHolder viewHolder = changeInfo.oldHolder;
        final View view2 = null;
        if (viewHolder == null) {
            view = null;
        } else {
            view = viewHolder.itemView;
        }
        RecyclerView.ViewHolder viewHolder2 = changeInfo.newHolder;
        if (viewHolder2 != null) {
            view2 = viewHolder2.itemView;
        }
        if (view != null) {
            final ViewPropertyAnimatorCompat duration = ViewCompat.animate(view).setDuration(getChangeDuration());
            this.mChangeAnimations.add(changeInfo.oldHolder);
            duration.translationX((float) (changeInfo.toX - changeInfo.fromX));
            duration.translationY((float) (changeInfo.toY - changeInfo.fromY));
            duration.alpha(0.0f).setListener(new VpaListenerAdapter() {
                public void onAnimationStart(View view) {
                    HorizontalItemAnimator.this.dispatchChangeStarting(changeInfo.oldHolder, true);
                }

                public void onAnimationEnd(View view) {
                    duration.setListener((ViewPropertyAnimatorListener) null);
                    ViewCompat.setAlpha(view, 1.0f);
                    ViewCompat.setTranslationX(view, 0.0f);
                    ViewCompat.setTranslationY(view, 0.0f);
                    HorizontalItemAnimator.this.dispatchChangeFinished(changeInfo.oldHolder, true);
                    HorizontalItemAnimator.this.mChangeAnimations.remove(changeInfo.oldHolder);
                    HorizontalItemAnimator.this.dispatchFinishedWhenDone();
                }
            }).start();
        }
        if (view2 != null) {
            final ViewPropertyAnimatorCompat animate = ViewCompat.animate(view2);
            this.mChangeAnimations.add(changeInfo.newHolder);
            animate.translationX(0.0f).translationY(0.0f).setDuration(getChangeDuration()).alpha(1.0f).setListener(new VpaListenerAdapter() {
                public void onAnimationStart(View view) {
                    HorizontalItemAnimator.this.dispatchChangeStarting(changeInfo.newHolder, false);
                }

                public void onAnimationEnd(View view) {
                    animate.setListener((ViewPropertyAnimatorListener) null);
                    ViewCompat.setAlpha(view2, 1.0f);
                    ViewCompat.setTranslationX(view2, 0.0f);
                    ViewCompat.setTranslationY(view2, 0.0f);
                    HorizontalItemAnimator.this.dispatchChangeFinished(changeInfo.newHolder, false);
                    HorizontalItemAnimator.this.mChangeAnimations.remove(changeInfo.newHolder);
                    HorizontalItemAnimator.this.dispatchFinishedWhenDone();
                }
            }).start();
        }
    }

    private void endChangeAnimation(List<ChangeInfo> list, RecyclerView.ViewHolder viewHolder) {
        for (int size = list.size() - 1; size >= 0; size--) {
            ChangeInfo changeInfo = list.get(size);
            if (endChangeAnimationIfNecessary(changeInfo, viewHolder) && changeInfo.oldHolder == null && changeInfo.newHolder == null) {
                list.remove(changeInfo);
            }
        }
    }

    private void endChangeAnimationIfNecessary(ChangeInfo changeInfo) {
        if (changeInfo.oldHolder != null) {
            endChangeAnimationIfNecessary(changeInfo, changeInfo.oldHolder);
        }
        if (changeInfo.newHolder != null) {
            endChangeAnimationIfNecessary(changeInfo, changeInfo.newHolder);
        }
    }

    private boolean endChangeAnimationIfNecessary(ChangeInfo changeInfo, RecyclerView.ViewHolder viewHolder) {
        boolean z = false;
        if (changeInfo.newHolder == viewHolder) {
            changeInfo.newHolder = null;
        } else if (changeInfo.oldHolder != viewHolder) {
            return false;
        } else {
            changeInfo.oldHolder = null;
            z = true;
        }
        ViewCompat.setAlpha(viewHolder.itemView, 1.0f);
        ViewCompat.setTranslationX(viewHolder.itemView, 0.0f);
        ViewCompat.setTranslationY(viewHolder.itemView, 0.0f);
        dispatchChangeFinished(viewHolder, z);
        return true;
    }

    public void endAnimation(RecyclerView.ViewHolder viewHolder) {
        View view = viewHolder.itemView;
        ViewCompat.animate(view).cancel();
        int size = this.mPendingMoves.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            } else if (this.mPendingMoves.get(size).holder == viewHolder) {
                ViewCompat.setTranslationY(view, 0.0f);
                ViewCompat.setTranslationX(view, 0.0f);
                dispatchMoveFinished(viewHolder);
                this.mPendingMoves.remove(size);
            }
        }
        endChangeAnimation(this.mPendingChanges, viewHolder);
        if (this.mPendingRemovals.remove(viewHolder)) {
            ViewCompat.setAlpha(view, 1.0f);
            dispatchRemoveFinished(viewHolder);
        }
        if (this.mPendingAdditions.remove(viewHolder)) {
            ViewCompat.setAlpha(view, 1.0f);
            dispatchAddFinished(viewHolder);
        }
        for (int size2 = this.mChangesList.size() - 1; size2 >= 0; size2--) {
            ArrayList arrayList = this.mChangesList.get(size2);
            endChangeAnimation(arrayList, viewHolder);
            if (arrayList.isEmpty()) {
                this.mChangesList.remove(size2);
            }
        }
        for (int size3 = this.mMovesList.size() - 1; size3 >= 0; size3--) {
            ArrayList arrayList2 = this.mMovesList.get(size3);
            int size4 = arrayList2.size() - 1;
            while (true) {
                if (size4 < 0) {
                    break;
                } else if (((MoveInfo) arrayList2.get(size4)).holder == viewHolder) {
                    ViewCompat.setTranslationY(view, 0.0f);
                    ViewCompat.setTranslationX(view, 0.0f);
                    dispatchMoveFinished(viewHolder);
                    arrayList2.remove(size4);
                    if (arrayList2.isEmpty()) {
                        this.mMovesList.remove(size3);
                    }
                } else {
                    size4--;
                }
            }
        }
        for (int size5 = this.mAdditionsList.size() - 1; size5 >= 0; size5--) {
            ArrayList arrayList3 = this.mAdditionsList.get(size5);
            if (arrayList3.remove(viewHolder)) {
                ViewCompat.setAlpha(view, 1.0f);
                dispatchAddFinished(viewHolder);
                if (arrayList3.isEmpty()) {
                    this.mAdditionsList.remove(size5);
                }
            }
        }
        this.mRemoveAnimations.remove(viewHolder);
        this.mAddAnimations.remove(viewHolder);
        this.mChangeAnimations.remove(viewHolder);
        this.mMoveAnimations.remove(viewHolder);
        dispatchFinishedWhenDone();
    }

    private void resetAnimation(RecyclerView.ViewHolder viewHolder) {
        clearInterpolator(viewHolder.itemView);
        endAnimation(viewHolder);
    }

    private void clearInterpolator(View view) {
        if (this.mDefaultInterpolator == null) {
            this.mDefaultInterpolator = new ValueAnimator().getInterpolator();
        }
        view.animate().setInterpolator(this.mDefaultInterpolator);
    }

    public boolean isRunning() {
        return !this.mPendingAdditions.isEmpty() || !this.mPendingChanges.isEmpty() || !this.mPendingMoves.isEmpty() || !this.mPendingRemovals.isEmpty() || !this.mMoveAnimations.isEmpty() || !this.mRemoveAnimations.isEmpty() || !this.mAddAnimations.isEmpty() || !this.mChangeAnimations.isEmpty() || !this.mMovesList.isEmpty() || !this.mAdditionsList.isEmpty() || !this.mChangesList.isEmpty();
    }

    /* access modifiers changed from: private */
    public void dispatchFinishedWhenDone() {
        if (!isRunning()) {
            dispatchAnimationsFinished();
        }
    }

    public void endAnimations() {
        int size = this.mPendingMoves.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            MoveInfo moveInfo = this.mPendingMoves.get(size);
            View view = moveInfo.holder.itemView;
            ViewCompat.setTranslationY(view, 0.0f);
            ViewCompat.setTranslationX(view, 0.0f);
            dispatchMoveFinished(moveInfo.holder);
            this.mPendingMoves.remove(size);
        }
        for (int size2 = this.mPendingRemovals.size() - 1; size2 >= 0; size2--) {
            dispatchRemoveFinished(this.mPendingRemovals.get(size2));
            this.mPendingRemovals.remove(size2);
        }
        int size3 = this.mPendingAdditions.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            RecyclerView.ViewHolder viewHolder = this.mPendingAdditions.get(size3);
            ViewCompat.setAlpha(viewHolder.itemView, 1.0f);
            dispatchAddFinished(viewHolder);
            this.mPendingAdditions.remove(size3);
        }
        for (int size4 = this.mPendingChanges.size() - 1; size4 >= 0; size4--) {
            endChangeAnimationIfNecessary(this.mPendingChanges.get(size4));
        }
        this.mPendingChanges.clear();
        if (isRunning()) {
            for (int size5 = this.mMovesList.size() - 1; size5 >= 0; size5--) {
                ArrayList arrayList = this.mMovesList.get(size5);
                for (int size6 = arrayList.size() - 1; size6 >= 0; size6--) {
                    MoveInfo moveInfo2 = (MoveInfo) arrayList.get(size6);
                    View view2 = moveInfo2.holder.itemView;
                    ViewCompat.setTranslationY(view2, 0.0f);
                    ViewCompat.setTranslationX(view2, 0.0f);
                    dispatchMoveFinished(moveInfo2.holder);
                    arrayList.remove(size6);
                    if (arrayList.isEmpty()) {
                        this.mMovesList.remove(arrayList);
                    }
                }
            }
            for (int size7 = this.mAdditionsList.size() - 1; size7 >= 0; size7--) {
                ArrayList arrayList2 = this.mAdditionsList.get(size7);
                for (int size8 = arrayList2.size() - 1; size8 >= 0; size8--) {
                    RecyclerView.ViewHolder viewHolder2 = (RecyclerView.ViewHolder) arrayList2.get(size8);
                    ViewCompat.setAlpha(viewHolder2.itemView, 1.0f);
                    dispatchAddFinished(viewHolder2);
                    arrayList2.remove(size8);
                    if (arrayList2.isEmpty()) {
                        this.mAdditionsList.remove(arrayList2);
                    }
                }
            }
            for (int size9 = this.mChangesList.size() - 1; size9 >= 0; size9--) {
                ArrayList arrayList3 = this.mChangesList.get(size9);
                for (int size10 = arrayList3.size() - 1; size10 >= 0; size10--) {
                    endChangeAnimationIfNecessary((ChangeInfo) arrayList3.get(size10));
                    if (arrayList3.isEmpty()) {
                        this.mChangesList.remove(arrayList3);
                    }
                }
            }
            cancelAll(this.mRemoveAnimations);
            cancelAll(this.mMoveAnimations);
            cancelAll(this.mAddAnimations);
            cancelAll(this.mChangeAnimations);
            dispatchAnimationsFinished();
        }
    }

    private static void cancelAll(List<RecyclerView.ViewHolder> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            ViewCompat.animate(list.get(size).itemView).cancel();
        }
    }

    private static class VpaListenerAdapter implements ViewPropertyAnimatorListener {
        public void onAnimationCancel(View view) {
        }

        public void onAnimationEnd(View view) {
        }

        public void onAnimationStart(View view) {
        }

        private VpaListenerAdapter() {
        }
    }
}