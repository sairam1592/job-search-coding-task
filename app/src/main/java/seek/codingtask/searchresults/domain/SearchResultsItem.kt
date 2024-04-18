package seek.codingtask.searchresults.domain

import java.io.Serializable

data class SearchResultsItem(
    val jobId: String,
    val title: String,
    val description: String,
) : Serializable
