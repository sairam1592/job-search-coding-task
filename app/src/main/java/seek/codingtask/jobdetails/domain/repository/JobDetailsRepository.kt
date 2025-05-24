package seek.codingtask.jobdetails.domain.repository

import kotlinx.coroutines.flow.Flow
import seek.codingtask.jobdetails.JobDetailsApiResponse

interface JobDetailsRepository {
    fun getJobDetails(jobId: String): Flow<JobDetailsApiResponse.JobListing>
}
