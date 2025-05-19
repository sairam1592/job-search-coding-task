package seek.codingtask.jobdetails.domain.model

data class JobDetailsItem(
    val advertiser: String,
    val bulletPoints: List<String>,
    val jobId: String,
    val title: String,
    val descriptions: String,
    val classification: String,
    val companyName: String
)
