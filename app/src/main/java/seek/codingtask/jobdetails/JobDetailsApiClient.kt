package seek.codingtask.jobdetails

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface JobDetailsApiClient {
    @GET("/v5/search")
    suspend fun getJobsList(
        @Query("jobId") jobId: String,
    ): Response<JobDetailsApiResponse>
}

data class JobDetailsApiResponse(val data: List<JobListing>?) {
    data class JobListing(
        val id: String?,
        val title: String?,
        val teaser: String?,
        val advertiser: IdDescription?,
        val bulletPoints: List<String?>?,
        val companyName: String?,
        val classifications: List<Classifications>?
    )

    data class IdDescription(
        val id: String?,
        val description: String?
    )

    data class Classifications(
        val classification: IdDescription?,
        val subClassification: IdDescription?
    )
}
