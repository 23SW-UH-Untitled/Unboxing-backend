package team.untitled.unboxingBackend.global.exception

import io.jsonwebtoken.io.IOException
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class ExceptionFilter : OncePerRequestFilter() {
    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: UntitledException) {
            writeErrorCode(response, e.status, e.message ?: "Unknown Error")
        } catch (e: Exception) {
            e.printStackTrace()
            writeErrorCode(response, 500, "Internal Server Error")
        }
    }

    @Throws(IOException::class)
    private fun writeErrorCode(response: HttpServletResponse, status: Int, message:String) {
        val errorResponse = ExceptionResponse(
            status,message
        )
        response.status = errorResponse.status
        response.characterEncoding = "UTF-8"
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.writer.write(errorResponse.toString())
    }
}