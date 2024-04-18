package seek.codingtask.searchform

import com.seek.android.core.test.instrumentation.ScreenshotTest
import com.seek.android.core.test.instrumentation.takeScreenshot
import org.junit.Test
import seek.codingtask.R
import seek.codingtask.searchform.presentation.SearchFormScreen
import seek.codingtask.searchform.presentation.SearchFormUiState

class SearchFormScreenshots : ScreenshotTest() {
    @Test
    fun searchForm_empty() {
        setMviComponentWithMockState(
            SearchFormScreen(),
            SearchFormUiState.Form("")
        )

        composeTestRule.takeScreenshot("SearchForm-Empty")
    }

    @Test
    fun searchForm_withData() {
        setMviComponentWithMockState(
            SearchFormScreen(),
            SearchFormUiState.Form("Android Software Developer")
        )

        composeTestRule.takeScreenshot("SearchForm-Data")
    }

    @Test
    fun searchForm_withTooLongError() {
        setMviComponentWithMockState(
            SearchFormScreen(),
            SearchFormUiState.Form(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.".take(50),
                R.string.search_form_error_max_chars
            )
        )

        composeTestRule.takeScreenshot("SearchForm-TooLongError")
    }

    @Test
    fun searchForm_withBlankError() {
        setMviComponentWithMockState(
            SearchFormScreen(),
            SearchFormUiState.Form(
                "",
                R.string.search_form_error_blank
            )
        )

        composeTestRule.takeScreenshot("SearchForm-BlankError")
    }
}
