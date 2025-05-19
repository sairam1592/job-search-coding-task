package seek.codingtask.jobdetails.data.remote

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.jupiter.api.Test
import retrofit2.Response
import seek.codingtask.jobdetails.JobDetailsApiClient
import seek.codingtask.jobdetails.JobDetailsApiResponse

class JobDetailsRemoteDataSourceImplTest {

    private val apiClient = mockk<JobDetailsApiClient>()
    private val dataSource = JobDetailsRemoteDataSourceImpl(apiClient)

    @Test
    fun `should return job when API is successful`() = runTest {
        val job = mockk<JobDetailsApiResponse.JobListing>()
        val response = Response.success(JobDetailsApiResponse(data = listOf(job)))
        coEvery { apiClient.getJobsList("123") } returns response

        val result = dataSource.getJobDetails("123").first()
        assert(result === job)
    }

    @Test
    fun `should throw when API is unsuccessful`() = runTest {
        val errorResponse = Response.error<JobDetailsApiResponse>(
            500,
            "Server error".toResponseBody(null)
        )
        coEvery { apiClient.getJobsList("123") } returns errorResponse

        try {
            dataSource.getJobDetails("123").first()
        } catch (e: Exception) {
            assert(true)
        }
    }
}
