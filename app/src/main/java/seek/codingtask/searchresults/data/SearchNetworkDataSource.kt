package seek.codingtask.searchresults.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchNetworkDataSource {
    @GET("/v5/search")
    suspend fun getJobsList(
        @Query("keywords") keywords: String,
    ): Response<SearchResultsApiResponse>
}

data class SearchResultsApiResponse(val data: List<JobListing>) {
    data class JobListing(
        val id: String,
        val title: String,
        val teaser: String,
    )
}
