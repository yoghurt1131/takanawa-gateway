package dev.yoghurt1131.TakanawaGatway

import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
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

	@Bean
	fun resilience4j(): CircuitBreaker {
		val config = CircuitBreakerConfig.custom()
				.slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
				.slidingWindowSize(10)
				.minimumNumberOfCalls(3)
				.build()
		return CircuitBreaker.of("resilience4j", config)
	}

}

fun main(args: Array<String>) {
	runApplication<TakanawaGatwayApplication>(*args)
}
