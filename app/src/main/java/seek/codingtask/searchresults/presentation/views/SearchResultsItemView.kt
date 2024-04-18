package seek.codingtask.searchresults.presentation.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.seek.android.core.presentation.ui.SeekPreview
import com.seek.android.core.presentation.ui.ThemedPreviews
import seek.braid.compose.components.Card
import seek.braid.compose.components.Text
import seek.braid.compose.theme.Spacings
import seek.braid.compose.theme.Typographies
import seek.codingtask.searchresults.domain.SearchResultsItem

@Composable
fun SearchResultsItemView(
    item: SearchResultsItem,
    onItemClicked: (SearchResultsItem) -> Unit,
) {
    Card(
        onClick = { onItemClicked(item) },
    ) {
        Column(
            Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(Spacings.large),
        ) {
            Text(item.title, Typographies.TextStandardStrong)
            Text(item.description, Typographies.TextStandard)
        }
    }
}

@ThemedPreviews
@Composable
private fun Preview() =
    SeekPreview {
        SearchResultsItemView(
            SearchResultsItem(
                "1234",
                "Some job title",
                "Some job description, lorem ipsum dolor sit amet.",
            ),
            {},
        )
    }
