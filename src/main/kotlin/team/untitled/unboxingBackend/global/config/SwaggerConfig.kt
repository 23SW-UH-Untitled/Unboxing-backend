package team.untitled.unboxingBackend.global.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springdoc.core.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration



@Configuration
class Swagger2Config {

    @Bean
    fun publicApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("v1-definition")
            .pathsToMatch("/**")
            .build();
    }
    @Bean
    fun springShopOpenAPI(): OpenAPI {
        return  OpenAPI()
            .info(
                Info().title("asdf API")
                .description("asdf 프로젝트 API 명세서입니다.")
                .version("v0.0.1")
            )
    }
}

