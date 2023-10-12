package team.untitled.unboxingBackend

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import java.util.*

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableFeignClients
class UnBoxingBackendApplication

fun main(args: Array<String>) {
    TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"))
    runApplication<UnBoxingBackendApplication>(*args)
}
