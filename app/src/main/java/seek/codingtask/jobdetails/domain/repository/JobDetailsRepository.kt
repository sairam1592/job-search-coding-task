package seek.codingtask.jobdetails.domain.repository

import kotlinx.coroutines.flow.Flow
import seek.codingtask.jobdetails.domain.model.JobDetailsItem

interface JobDetailsRepository {
    fun getJobDetails(jobId: String): Flow<JobDetailsItem>
}
