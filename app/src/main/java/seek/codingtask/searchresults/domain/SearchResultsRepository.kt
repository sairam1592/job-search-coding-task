package seek.codingtask.searchresults.domain

import kotlinx.coroutines.flow.Flow

interface SearchResultsRepository {
    fun getSearchResults(keywords: String): Flow<List<SearchResultsItem>>
}
