package seek.codingtask.jobdetails.domain.usecase

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import seek.codingtask.jobdetails.JobDetailsApiResponse
import seek.codingtask.jobdetails.data.mapper.JobDetailsDtoToDomainMapper
import seek.codingtask.jobdetails.domain.repository.JobDetailsRepository

class GetJobDetailsUseCaseTest {

    private val repository = mockk<JobDetailsRepository>()
    private val mapper = JobDetailsDtoToDomainMapper()
    private val useCase = GetJobDetailsUseCase(repository, mapper)

    @Test
    fun `should map JobListing to JobDetailsItem`() = runTest {
        val apiModel = JobDetailsApiResponse.JobListing(
            id = "id",
            title = "title",
            teaser = "desc",
            advertiser = JobDetailsApiResponse.IdDescription("1", "adv"),
            bulletPoints = listOf("a", "b"),
            companyName = "SEEK",
            classifications = listOf(
                JobDetailsApiResponse.Classifications(
                    JobDetailsApiResponse.IdDescription("x", "Tech"),
                    JobDetailsApiResponse.IdDescription("y", "Other")
                )
            )
        )

        coEvery { repository.getJobDetails("123") } returns flowOf(apiModel)

        val result = useCase("123").first()

        assert(result.jobId == "id")
        assert(result.advertiser == "adv")
        assert(result.classification == "Tech")
    }
}
