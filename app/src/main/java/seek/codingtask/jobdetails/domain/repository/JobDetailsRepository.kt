package seek.codingtask.jobdetails.domain.repository

import seek.codingtask.jobdetails.domain.model.JobDetailsItem

interface JobDetailsRepository {
    suspend fun getJobDetails(jobId: String): JobDetailsItem
}