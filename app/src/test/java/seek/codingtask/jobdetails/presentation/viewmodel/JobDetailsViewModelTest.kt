package seek.codingtask.jobdetails.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import seek.codingtask.jobdetails.domain.model.JobDetailsItem
import seek.codingtask.jobdetails.domain.usecase.GetJobDetailsUseCase
import seek.codingtask.jobdetails.presentation.JobDetailsUIState
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class JobDetailsViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()
    private val getJobDetailsUseCase = mockk<GetJobDetailsUseCase>()
    private lateinit var viewModel: JobDetailsViewModel

    private val dummyData = JobDetailsItem(
        jobId = "123",
        title = "Senior Android Dev",
        advertiser = "SEEK",
        descriptions = "Great opportunity to work remotely",
        classification = "Tech",
        companyName = "SEEK Ltd.",
        bulletPoints = listOf("Compose", "MVVM", "Remote")
    )

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should emit Success when use case returns data`() = runTest {
        coEvery { getJobDetailsUseCase("123") } returns flowOf(dummyData)

        val savedStateHandle = SavedStateHandle(mapOf("jobId" to "123"))
        viewModel = JobDetailsViewModel(savedStateHandle, getJobDetailsUseCase)

        assertTrue(viewModel.uiStateStream.value is JobDetailsUIState.Success)
        assertEquals(
            dummyData.title,
            (viewModel.uiStateStream.value as JobDetailsUIState.Success).data.title
        )
    }
}
