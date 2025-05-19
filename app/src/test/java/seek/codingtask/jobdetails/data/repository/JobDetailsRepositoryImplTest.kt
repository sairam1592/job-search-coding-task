package seek.codingtask.jobdetails.data.repository

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import seek.codingtask.jobdetails.JobDetailsApiResponse
import seek.codingtask.jobdetails.data.remote.JobDetailsRemoteDataSource

class JobDetailsRepositoryImplTest {

    private val remoteDataSource = mockk<JobDetailsRemoteDataSource>()
    private val repository = JobDetailsRepositoryImpl(remoteDataSource)

    @Test
    fun `should return job details from remote data source`() = runTest {
        val listing = mockk<JobDetailsApiResponse.JobListing>()
        coEvery { remoteDataSource.getJobDetails("123") } returns flowOf(listing)

        val result = repository.getJobDetails("123").first()
        assert(result === listing)
    }
}
