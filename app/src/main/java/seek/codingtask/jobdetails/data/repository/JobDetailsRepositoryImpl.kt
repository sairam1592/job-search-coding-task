package seek.codingtask.jobdetails.data.repository

import com.seek.android.core.common.errors.DomainException
import com.seek.android.core.common.errors.ErrorReason
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import seek.codingtask.jobdetails.JobDetailsApiClient
import seek.codingtask.jobdetails.domain.model.JobDetailsItem
import seek.codingtask.jobdetails.domain.repository.JobDetailsRepository

class JobDetailsRepositoryImpl(
    private val apiClient: JobDetailsApiClient
) : JobDetailsRepository {

    override fun getJobDetails(jobId: String): Flow<JobDetailsItem> = flow {
        try {
            val response = apiClient.getJobsList(jobId)
            if (response.isSuccessful) {
                val job = response.body()?.data?.singleOrNull()
                    ?: throw DomainException(ErrorReason.Errored())

                emit(
                    JobDetailsItem(
                        advertiser = job.advertiser.description,
                        bulletPoints = job.bulletPoints,
                        jobId = job.id,
                        title = job.title,
                        descriptions = job.teaser,
                        classification = job.classifications.firstOrNull()?.classification?.description.orEmpty(),
                        companyName = job.companyName
                    )
                )
            } else {
                throw DomainException(ErrorReason.Errored())
            }
        } catch (_: Exception) {
            throw DomainException(ErrorReason.Errored())
        }
    }.flowOn(Dispatchers.IO)
}
