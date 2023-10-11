package team.untitled.unboxingBackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    exclude = [
        org.springframework.cloud.aws.autoconfigure.context.ContextInstanceDataAutoConfiguration::class,
        org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration::class,
        org.springframework.cloud.aws.autoconfigure.context.ContextRegionProviderAutoConfiguration::class
    ]
)
class UnBoxingBackendApplication

fun main(args: Array<String>) {
    runApplication<UnBoxingBackendApplication>(*args)
}
