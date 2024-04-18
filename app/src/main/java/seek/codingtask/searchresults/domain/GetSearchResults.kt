package seek.codingtask.searchresults.domain

import com.seek.android.core.domain.usecases.GetUseCase
import kotlinx.coroutines.flow.Flow

class GetSearchResults(
    private val repository: SearchResultsRepository,
) : GetUseCase<String, Flow<List<SearchResultsItem>>> {
    override fun perform(param: String): Flow<List<SearchResultsItem>> {
        return repository.getSearchResults(param)
    }
}
