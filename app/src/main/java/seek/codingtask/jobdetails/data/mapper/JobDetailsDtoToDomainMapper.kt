package seek.codingtask.jobdetails.data.mapper

import seek.codingtask.jobdetails.JobDetailsApiResponse
import seek.codingtask.jobdetails.domain.model.JobDetailsItem

class JobDetailsDtoToDomainMapper {
    operator fun invoke(dto: JobDetailsApiResponse.JobListing): JobDetailsItem = JobDetailsItem(
        advertiser = dto.advertiser.description,
        bulletPoints = dto.bulletPoints,
        jobId = dto.id,
        title = dto.title,
        descriptions = dto.teaser,
        classification = dto.classifications.firstOrNull()?.classification?.description.orEmpty(),
        companyName = dto.companyName
    )
}
