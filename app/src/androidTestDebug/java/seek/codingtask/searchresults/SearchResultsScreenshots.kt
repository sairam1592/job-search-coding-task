package seek.codingtask.searchresults

import com.seek.android.core.common.errors.ErrorReason
import com.seek.android.core.test.instrumentation.ScreenshotTest
import com.seek.android.core.test.instrumentation.takeScreenshot
import org.junit.Test
import seek.codingtask.searchresults.domain.SearchResultsItem
import seek.codingtask.searchresults.presentation.SearchResultsScreen
import seek.codingtask.searchresults.presentation.SearchResultsUiState

class SearchResultsScreenshots : ScreenshotTest() {
    @Test
    fun searchResults_loading() {
        setMviComponentWithMockState(
            SearchResultsScreen(),
            SearchResultsUiState.Results(emptyList())
        )

        composeTestRule.takeScreenshot("SearchResults-Loading")
    }

    @Test
    fun searchResults_empty() {
        setMviComponentWithMockState(
            SearchResultsScreen(),
            SearchResultsUiState.Results(emptyList())
        )

        composeTestRule.takeScreenshot("SearchResults-Empty")
    }

    @Test
    fun searchResults_withData() {
        setMviComponentWithMockState(
            SearchResultsScreen(),
            SearchResultsUiState.Results(
                listOf(
                    SearchResultsItem("1234", "Some job title", "Some job description"),
                    SearchResultsItem("1234", "Some job title", "Some job description"),
                    SearchResultsItem("1234", "Some job title", "Some job description"),
                    SearchResultsItem("1234", "Some job title", "Some job description"),
                    SearchResultsItem("1234", "Some job title", "Some job description")
                )
            )
        )

        composeTestRule.takeScreenshot("SearchResults-Empty")
    }

    @Test
    fun searchResults_withError() {
        setMviComponentWithMockState(
            SearchResultsScreen(),
            SearchResultsUiState.Error(ErrorReason.Errored())
        )

        composeTestRule.takeScreenshot("SearchResults-Error")
    }
}
