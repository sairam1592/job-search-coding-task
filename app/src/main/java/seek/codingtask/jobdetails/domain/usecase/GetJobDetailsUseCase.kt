package seek.codingtask.jobdetails.domain.usecase

import kotlinx.coroutines.flow.Flow
import seek.codingtask.jobdetails.domain.model.JobDetailsItem
import seek.codingtask.jobdetails.domain.repository.JobDetailsRepository

class GetJobDetailsUseCase(
    private val repository: JobDetailsRepository
) {
    operator fun invoke(jobId: String): Flow<JobDetailsItem> = repository.getJobDetails(jobId)
}
