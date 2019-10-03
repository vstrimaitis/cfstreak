package io.cfstreak.cf.model

import java.math.BigDecimal

data class ApiResponse<T>(
    val status: ApiResponseStatus,
    val comment: String?,
    val result: T?
)

enum class ApiResponseStatus {
    OK,
    FAILED
}

enum class ProblemType {
    PROGRAMMING,
    QUESTION
}

data class Problem(
    val contestId: Int?,
    val problemSetName: String?,
    val index: String,
    val name: String,
    val type: ProblemType,
    val points: BigDecimal?,
    val rating: Int?,
    val tags: List<String>
) {
    val url = "https://codeforces.com/problemset/problem/${contestId}/${index}"
}

data class Member(
    val handle: String
)

enum class ParticipantType {
    CONTESTANT,
    PRACTICE,
    VIRTUAL,
    MANAGER,
    OUT_OF_COMPETITION
}

data class Party(
    val contestId: Int?,
    val members: List<Member>,
    val participantType: ParticipantType,
    val teamId: Int?,
    val teamName: String?,
    val ghost: Boolean,
    val room: Int?,
    val startTimeSeconds: Int?
)

enum class Verdict {
    FAILED,
    OK,
    PARTIAL,
    COMPILATION_ERROR,
    RUNTIME_ERROR,
    WRONG_ANSWER,
    PRESENTATION_ERROR,
    TIME_LIMIT_EXCEEDED,
    MEMORY_LIMIT_EXCEEDED,
    IDLENESS_LIMIT_EXCEEDED,
    SECURITY_VIOLATED,
    CRASHED,
    INPUT_PREPARATION_CRASHED,
    CHALLENGED,
    SKIPPED,
    TESTING,
    REJECTED
}

enum class TestSet {
    SAMPLES,
    PRETESTS,
    TESTS,
    CHALLENGES,
    TESTS1,
    TESTS2,
    TESTS3,
    TESTS4,
    TESTS5,
    TESTS6,
    TESTS7,
    TESTS8,
    TESTS9,
    TESTS10
}

data class Submission(
    val id: Int,
    val contestId: Int?,
    val creationTimeSeconds: Int,
    val relativeTimeSeconds: Int,
    val problem: Problem,
    val author: Party,
    val programmingLanguage: String,
    val verdict: Verdict?,
    val testset: TestSet,
    val passedTestCount: Int,
    val timeConsumedMillis: Int,
    val memoryConsumedBytes: Int
)