package seek.codingtask.searchresults.presentation.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.seek.android.core.presentation.extension.defaultBackAction
import com.seek.android.core.presentation.extension.withoutBottomPadding
import com.seek.android.core.presentation.ui.SeekPreview
import com.seek.android.core.presentation.ui.Spacer
import com.seek.android.core.presentation.ui.ThemedPreviews
import seek.braid.compose.components.BraidScaffold
import seek.braid.compose.components.TopNavigation
import seek.braid.compose.theme.Spacings
import seek.codingtask.R
import seek.codingtask.searchresults.domain.SearchResultsItem

@Composable
fun SearchResultsListView(
    items: List<SearchResultsItem>,
    onItemClicked: (SearchResultsItem) -> Unit,
) {
    BraidScaffold(
        toolbar = {
            TopNavigation(
                title = stringResource(R.string.search_results_title),
                startAction = defaultBackAction(),
            )
        },
    ) { defaultPadding ->
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(defaultPadding.withoutBottomPadding())
                .padding(horizontal = Spacings.medium),
            verticalArrangement = Arrangement.spacedBy(Spacings.medium),
        ) {

            item {
                Spacer(30.dp)
            }

            items(items) { item ->
                SearchResultsItemView(
                    item = item,
                    onItemClicked = onItemClicked,
                )
            }

            item {
                Spacer(defaultPadding.calculateBottomPadding())
            }
        }
    }
}

@ThemedPreviews
@Composable
private fun Preview() =
    SeekPreview {
        SearchResultsListView(
            listOf(
                SearchResultsItem(
                    "1234",
                    "Some job title",
                    "Some job description, lorem ipsum dolor sit amet.",
                ),
                SearchResultsItem(
                    "1234",
                    "Some job title",
                    "Some job description, lorem ipsum dolor sit amet.",
                ),
                SearchResultsItem(
                    "1234",
                    "Some job title",
                    "Some job description, lorem ipsum dolor sit amet.",
                ),
            ),
            {},
        )
    }
