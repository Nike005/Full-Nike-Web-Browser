package com.appsgeyser.sdk.server.implementation;

import com.appsgeyser.sdk.rateme.RatingDialog;

/* renamed from: com.appsgeyser.sdk.server.implementation.-$$Lambda$AppsgeyserServerClient$JTuO0Q45p1yPOFk-SNl1jHXqmso  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$AppsgeyserServerClient$JTuO0Q45p1yPOFkSNl1jHXqmso implements RatingDialog.Builder.RatingThresholdFailedListener {
    public static final /* synthetic */ $$Lambda$AppsgeyserServerClient$JTuO0Q45p1yPOFkSNl1jHXqmso INSTANCE = new $$Lambda$AppsgeyserServerClient$JTuO0Q45p1yPOFkSNl1jHXqmso();

    private /* synthetic */ $$Lambda$AppsgeyserServerClient$JTuO0Q45p1yPOFkSNl1jHXqmso() {
    }

    public final void onThresholdFailed(RatingDialog ratingDialog, float f, boolean z) {
        AppsgeyserServerClient.lambda$initRatingDialog$0(ratingDialog, f, z);
    }
}
