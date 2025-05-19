package seek.codingtask

import android.app.Application
import com.seek.android.core.common.tracking.TrackingTool
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import seek.codingtask.jobdetails.JobDetailsApiClient
import seek.codingtask.jobdetails.presentation.views.compose.JobDetailsScreen
import seek.codingtask.jobdetails.presentation.viewmodel.JobDetailsViewModel
import seek.codingtask.jobdetails.data.repository.JobDetailsRepositoryImpl
import seek.codingtask.jobdetails.domain.repository.JobDetailsRepository
import seek.codingtask.jobdetails.domain.usecase.GetJobDetailsUseCase
import seek.codingtask.searchform.presentation.SearchFormScreen
import seek.codingtask.searchform.presentation.SearchFormViewModel
import seek.codingtask.searchresults.data.SearchNetworkDataSource
import seek.codingtask.searchresults.data.SearchResultsRemoteRepository
import seek.codingtask.searchresults.domain.GetSearchResults
import seek.codingtask.searchresults.domain.SearchResultsRepository
import seek.codingtask.searchresults.presentation.SearchResultsScreen
import seek.codingtask.searchresults.presentation.SearchResultsViewModel
import seek.codingtask.utils.NoOpTrackingTool
import timber.log.Timber

open class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initDependencyGraph()
        Timber.plant(Timber.DebugTree())
    }

    protected open fun initDependencyGraph() {
        startKoin {
            androidContext(this@MainApplication)
            modules(
                module {
                    single<TrackingTool> { NoOpTrackingTool() }
                },
                module {
                    factory<SearchNetworkDataSource> {
                        val retrofit =
                            Retrofit.Builder()
                                .baseUrl("https://jobsearch-api.cloud.seek.com.au")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build()

                        retrofit.create(SearchNetworkDataSource::class.java)
                    }

                    single<SearchResultsRepository> {
                        SearchResultsRemoteRepository(dataSource = get())
                    }
                },
                module {
                    factoryOf(::GetSearchResults)
                },
                module {
                    scope<SearchFormScreen> {
                        viewModelOf(::SearchFormViewModel)
                    }

                    scope<SearchResultsScreen> {
                        viewModelOf(::SearchResultsViewModel)
                    }
                    
                    scope<JobDetailsScreen> {
                        viewModelOf(::JobDetailsViewModel)
                    }
                },

                module {
                    factory<JobDetailsApiClient> {
                        Retrofit.Builder()
                            .baseUrl("https://jobsearch-api.cloud.seek.com.au")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(JobDetailsApiClient::class.java)
                    }

                    single<JobDetailsRepository> {
                        JobDetailsRepositoryImpl(apiClient = get())
                    }

                    factory { GetJobDetailsUseCase(repository = get()) }
                }
            )
        }
    }
}
