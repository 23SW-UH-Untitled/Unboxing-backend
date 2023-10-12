package team.untitled.unboxingBackend.domain.file.presentation

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import team.untitled.unboxingBackend.global.s3.S3Util

@RestController
@RequestMapping("/file")
class FileController(
    val s3Util: S3Util
) {

    @PostMapping
    fun uploadFile(@RequestPart("image") image:MultipartFile){
        s3Util.uploadFile(image)
    }

}