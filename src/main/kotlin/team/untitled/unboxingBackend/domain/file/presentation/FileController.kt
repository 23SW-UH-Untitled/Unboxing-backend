package team.untitled.unboxingBackend.domain.file.presentation

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import team.untitled.unboxingBackend.domain.file.data.res.UploadFileResData
import team.untitled.unboxingBackend.global.s3.S3Util

@RestController
@RequestMapping("/file")
class FileController(
    val s3Util: S3Util
) {

    @PostMapping
    fun uploadFile(@RequestPart("image") image:MultipartFile): ResponseEntity<UploadFileResData> =
        s3Util.uploadFile(image)
            .let { UploadFileResData(it)}
            .let { ResponseEntity.ok(it) }

}