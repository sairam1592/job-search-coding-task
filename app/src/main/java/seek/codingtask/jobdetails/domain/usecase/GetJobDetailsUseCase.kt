package seek.codingtask.jobdetails.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import seek.codingtask.jobdetails.data.mapper.JobDetailsDtoToDomainMapper
import seek.codingtask.jobdetails.domain.model.JobDetailsItem
import seek.codingtask.jobdetails.domain.repository.JobDetailsRepository

class GetJobDetailsUseCase(
    private val repository: JobDetailsRepository,
    private val mapper: JobDetailsDtoToDomainMapper
) {
    operator fun invoke(jobId: String): Flow<JobDetailsItem> = repository.getJobDetails(jobId).map { mapper(it) }
}
