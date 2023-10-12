package team.untitled.unboxingBackend.global.s3

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class S3Properties(
    @Value("\${cloud.aws.credentials.access-key}")
    val accessKey:String,
    @Value("\${cloud.aws.credentials.secret-key}")
    val secretKey: String,
    @Value("\${cloud.aws.region.static}")
    val region: String,
    @Value("\${cloud.aws.s3.bucket}")
    val bucket: String,
)