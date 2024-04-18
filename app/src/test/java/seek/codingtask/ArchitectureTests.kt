package seek.codingtask

import com.seek.android.core.common.test.konsist.ArchitectureTestSuite
import com.seek.android.core.common.test.konsist.KonsistRule
import junit.framework.TestSuite
import com.seek.android.core.data.test.AndroidPlatform as DataPlatform
import com.seek.android.core.domain.test.AndroidPlatform as DomainPlatform
import com.seek.android.core.presentation.test.AndroidPlatform as PresentationPlatform

object ArchitectureTests : ArchitectureTestSuite() {
    override val rules: List<KonsistRule> =
        DataPlatform.Data.rules
            .plus(DomainPlatform.Domain.rules)
            .plus(PresentationPlatform.Presentation.rules)

    @JvmStatic
    fun suite(): TestSuite {
        return runTests()
    }
}
