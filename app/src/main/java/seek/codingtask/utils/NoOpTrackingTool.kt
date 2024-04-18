package seek.codingtask.utils

import com.seek.android.core.common.tracking.Event
import com.seek.android.core.common.tracking.Screen
import com.seek.android.core.common.tracking.TrackingTool
import kotlinx.coroutines.flow.flowOf

class NoOpTrackingTool : TrackingTool {
    override val events = flowOf<Event>()
    override val screens = flowOf<Screen>()

    override fun trackEvent(event: Event) {
        // no op
    }

    override fun trackScreen(screen: Screen) {
        // no op
    }
}
