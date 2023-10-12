package team.untitled.unboxingBackend.global.s3

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import team.untitled.unboxingBackend.global.exception.UntitledException
import java.io.IOException
import java.util.*

@Service
class S3Util(
    val s3Properties: S3Properties,
    val amazonS3Client: AmazonS3Client
){
    fun uploadFile(reqImg: MultipartFile): String {
        val fileName:String = createFile(reqImg.originalFilename)

        return inputS3(reqImg, fileName)
            .run { getResourceUrl(fileName) }
    }

    private fun createFile(image: String?): String {
        return UUID.randomUUID().toString() + "-" + image
    }

    private fun inputS3(file: MultipartFile, fileName: String):String {

        try {
            val request = PutObjectRequest(
                s3Properties.bucket, fileName, file.inputStream, getMetadata(file)
            )
            amazonS3Client.putObject(request)
        } catch (e: IOException) {
            throw UntitledException(400,"Invalid Image")
        }

        return amazonS3Client.getUrl(s3Properties.bucket, fileName).toString()

    }
    fun getMetadata(file: MultipartFile): ObjectMetadata {
        val metadata = ObjectMetadata()
        metadata.contentLength = file.size
        metadata.contentType = file.contentType
        return metadata
    }

    fun getResourceUrl(fileName: String): String =
        amazonS3Client.getUrl(s3Properties.bucket, fileName).toString()
}