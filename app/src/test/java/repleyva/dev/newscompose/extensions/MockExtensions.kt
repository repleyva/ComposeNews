package repleyva.dev.newscompose.extensions

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import repleyva.dev.newscompose.common.RESPONSE_200
import java.nio.charset.StandardCharsets

fun MockWebServer.enqueueResponse(filePath: String) {
    val inputStream = javaClass.classLoader?.getResourceAsStream(filePath)
    val source = inputStream?.source()?.buffer()
    source?.let {
        enqueue(
            MockResponse()
                .setResponseCode(RESPONSE_200)
                .setBody(it.readString(StandardCharsets.UTF_8))
        )
    }
}