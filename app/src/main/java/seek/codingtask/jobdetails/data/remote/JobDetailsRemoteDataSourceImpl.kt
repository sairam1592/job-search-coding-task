package seek.codingtask.jobdetails.data.remote

import com.seek.android.core.common.errors.DomainException
import com.seek.android.core.common.errors.ErrorReason
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import seek.codingtask.jobdetails.JobDetailsApiClient
import seek.codingtask.jobdetails.JobDetailsApiResponse
import timber.log.Timber
import java.io.IOException

class JobDetailsRemoteDataSourceImpl(
    private val apiClient: JobDetailsApiClient
) : JobDetailsRemoteDataSource {

    override fun getJobDetails(jobId: String): Flow<JobDetailsApiResponse.JobListing> = flow {
        try {
            val response = apiClient.getJobsList(jobId)
            if (response.isSuccessful) {
                val job = response.body()?.data?.singleOrNull()
                    ?: throw DomainException(ErrorReason.Errored())
                emit(job)
            } else {
                throw DomainException(ErrorReason.Errored())
            }
        } catch (e: IOException) {
            Timber.e(e, "Network IO exception")
            throw DomainException(ErrorReason.Errored())
        } catch (e: HttpException) {
            Timber.e(e, "HTTP error occurred")
            throw DomainException(ErrorReason.Errored())
        } catch (e: Exception) {
            Timber.e(e, "Unexpected error")
            throw DomainException(ErrorReason.Errored())
        }
    }.flowOn(Dispatchers.IO)
}
