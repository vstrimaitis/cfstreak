package io.cfstreak.cf.api

import io.cfstreak.cf.model.*
import io.ktor.client.HttpClient
import io.ktor.client.request.get

const val BASE_URL = "https://codeforces.com/api"

class CodeforcesApi(private val httpClient: HttpClient) {
    suspend fun getProblems(tags: List<String> = listOf()): List<Problem> {
        val suffix = if (tags.isEmpty()) "" else "?" + tags.joinToString(";")
        val url = "$BASE_URL/problemset.problems$suffix"
        val resp = httpClient.get<ApiResponse<GetProblemsResponse>>(url)
        return handleErrors(resp).problems
    }

    suspend fun getProblemsSolvedByUser(handle: String): List<Submission> {
        val url = "$BASE_URL/user.status?handle=$handle"
        val resp = httpClient.get<ApiResponse<List<Submission>>>(url)
        return handleErrors(resp).filter { it.verdict == Verdict.OK }
    }

    private fun <T> handleErrors(resp: ApiResponse<T>): T {
        return when (resp.status) {
            ApiResponseStatus.OK -> resp.result!!
            else -> throw Exception(resp.comment) // TODO: custom exception
        }
    }
}

private data class GetProblemsResponse(
    val problems: List<Problem>,
    val problemStatistics: List<Any>
)