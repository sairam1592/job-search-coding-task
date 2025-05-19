package seek.codingtask.jobdetails.data.mapper

import org.junit.jupiter.api.Test
import seek.codingtask.jobdetails.JobDetailsApiResponse

class JobDetailsDtoToDomainMapperTest {

    private val mapper = JobDetailsDtoToDomainMapper()

    @Test
    fun `should map DTO to Domain model`() {
        val dto = JobDetailsApiResponse.JobListing(
            id = "id",
            title = "title",
            teaser = "desc",
            advertiser = JobDetailsApiResponse.IdDescription("1", "SEEK"),
            bulletPoints = listOf("a", "b"),
            companyName = "SEEK",
            classifications = listOf(
                JobDetailsApiResponse.Classifications(
                    classification = JobDetailsApiResponse.IdDescription("c", "Tech"),
                    subClassification = JobDetailsApiResponse.IdDescription("s", "Backend")
                )
            )
        )

        val result = mapper(dto)

        assert(result.title == "title")
        assert(result.advertiser == "SEEK")
        assert(result.classification == "Tech")
    }
}
