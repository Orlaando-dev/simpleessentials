package controllers

import HttpService
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import java.util.concurrent.CompletableFuture


class PingController {
    fun getPingResponse(playerName: String) : CompletableFuture<String> {
            val httpService = HttpService()
            val url = "http://localhost:3000/ping"
            val requestBody = "{\"data\":\"${playerName}\"}"

            val future = httpService.postAsync(url, requestBody)
            return future
        }
}