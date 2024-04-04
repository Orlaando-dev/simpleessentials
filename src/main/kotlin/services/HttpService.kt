import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors

class HttpService(private val client: OkHttpClient = OkHttpClient()) {
    private val executor = Executors.newCachedThreadPool()

    fun postAsync(url: String, bodyString: String, contentType: String = "application/json; charset=utf-8"): CompletableFuture<String> {
        val future = CompletableFuture<String>()

        executor.submit {
            try {
                val mediaType = contentType.toMediaType()
                val body = bodyString.toRequestBody(mediaType)
                val request = Request.Builder()
                    .url(url)
                    .post(body)
                    .build()

                client.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    future.complete(response.body?.string())
                }
            } catch (e: Exception) {
                future.completeExceptionally(e)
            }
        }

        return future
    }
}
