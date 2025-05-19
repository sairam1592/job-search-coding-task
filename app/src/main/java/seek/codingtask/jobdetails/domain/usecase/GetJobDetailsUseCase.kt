package seek.codingtask.jobdetails.domain.usecase

import seek.codingtask.jobdetails.domain.model.JobDetailsItem
import seek.codingtask.jobdetails.domain.repository.JobDetailsRepository

class GetJobDetailsUseCase(
    private val repository: JobDetailsRepository
) {
    suspend operator fun invoke(jobId: String): JobDetailsItem {
        return repository.getJobDetails(jobId)
    }
}