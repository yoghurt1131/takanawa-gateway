package dev.yoghurt1131.TakanawaGatway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@SpringBootApplication
class TakanawaGatwayApplication {


	@Bean
	fun fallback() = router {
		GET("/fallback", accept(MediaType.TEXT_PLAIN), { ServerResponse.ok().bodyValue("Fallback")})
	}

}

fun main(args: Array<String>) {
	runApplication<TakanawaGatwayApplication>(*args)
}
