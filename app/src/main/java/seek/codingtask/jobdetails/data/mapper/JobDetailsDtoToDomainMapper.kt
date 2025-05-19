package seek.codingtask.jobdetails.data.mapper

import seek.codingtask.jobdetails.JobDetailsApiResponse
import seek.codingtask.jobdetails.domain.model.JobDetailsItem

class JobDetailsDtoToDomainMapper {
    operator fun invoke(dto: JobDetailsApiResponse.JobListing?): JobDetailsItem = JobDetailsItem(
        advertiser = dto?.advertiser?.description.orEmpty(),
        bulletPoints = dto?.bulletPoints.orEmpty().filterNotNull(),
        jobId = dto?.id.orEmpty(),
        title = dto?.title.orEmpty(),
        descriptions = dto?.teaser.orEmpty(),
        classification = dto?.classifications?.firstOrNull()?.classification?.description.orEmpty(),
        companyName = dto?.companyName.orEmpty()
    )
}
