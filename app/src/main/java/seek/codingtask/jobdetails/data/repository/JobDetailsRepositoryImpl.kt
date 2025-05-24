package seek.codingtask.jobdetails.data.repository

import kotlinx.coroutines.flow.Flow
import seek.codingtask.jobdetails.JobDetailsApiResponse
import seek.codingtask.jobdetails.data.remote.JobDetailsRemoteDataSource
import seek.codingtask.jobdetails.domain.repository.JobDetailsRepository

class JobDetailsRepositoryImpl(
    private val remoteDataSource: JobDetailsRemoteDataSource
) : JobDetailsRepository {

    override fun getJobDetails(jobId: String): Flow<JobDetailsApiResponse.JobListing> = remoteDataSource.getJobDetails(jobId)
}
