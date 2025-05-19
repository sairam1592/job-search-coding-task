package seek.codingtask.jobdetails.data.remote

import kotlinx.coroutines.flow.Flow
import seek.codingtask.jobdetails.JobDetailsApiResponse

interface JobDetailsRemoteDataSource {
    fun getJobDetails(jobId: String): Flow<JobDetailsApiResponse.JobListing>
}
