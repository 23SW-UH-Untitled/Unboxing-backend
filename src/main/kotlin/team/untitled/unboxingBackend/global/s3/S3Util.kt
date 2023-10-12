package team.untitled.unboxingBackend.global.s3

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.internal.Mimetypes
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import org.springframework.stereotype.Component
import java.io.File
import java.io.IOException
import java.util.*

@Component
class S3Util(
    private val s3Properties: S3Properties,
    private val amazonS3: AmazonS3
){
    fun uploadFile(file: File): String {
        val fileName = "${UUID.randomUUID()}.${file.extension}"

        return inputS3(file, fileName)
            .run { getResourceUrl(fileName = fileName) }
    }

    private fun inputS3(file: File, fileName: String) {
        val objectMetadata = ObjectMetadata()
        objectMetadata.contentLength = file.length()
        objectMetadata.contentType = Mimetypes.getInstance().getMimetype(file)
        try {
            amazonS3.putObject(
                PutObjectRequest(s3Properties.s3Bucket, fileName, file.inputStream(), objectMetadata)
                    .withCannedAcl(
                        CannedAccessControlList.PublicRead
                    )
            )
            file.delete()
        } catch (e: IOException) {
            throw e
        }
    }

    fun getResourceUrl(fileName: String): String =
        amazonS3.getUrl(s3Properties.s3Bucket, fileName).toString()
}