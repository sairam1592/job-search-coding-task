package seek.codingtask.searchresults.data

import com.seek.android.core.common.errors.DomainException
import com.seek.android.core.common.errors.ErrorReason
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import seek.codingtask.searchresults.domain.SearchResultsItem
import seek.codingtask.searchresults.domain.SearchResultsRepository

class SearchResultsRemoteRepository(
    private val dataSource: SearchNetworkDataSource,
) : SearchResultsRepository {
    override fun getSearchResults(keywords: String): Flow<List<SearchResultsItem>> {
        return flow {
            try {
                val result = dataSource.getJobsList(keywords)
                val data = result.body()
                if (result.isSuccessful && data != null) {
                    emit(
                        data.data.map {
                            SearchResultsItem(
                                jobId = it.id,
                                title = it.title,
                                description = it.teaser,
                            )
                        },
                    )
                } else {
                    throw DomainException(ErrorReason.Errored())
                }
            } catch (e: Throwable) {
                throw DomainException(ErrorReason.Errored())
            }
        }
    }
}
