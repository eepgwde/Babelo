package ai.centa.ksbeg

import mu.KotlinLogging
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ImportResource
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import java.util.*

private val logger = KotlinLogging.logger {}

// Logging
// https://www.baeldung.com/kotlin/logging

@SpringBootApplication
@ImportResource("classpath*:applicationContext.xml")
class KsbEgApplication {
	val applicationContext: ApplicationContext? = null

	@Bean
	fun commandLineRunner(ctx: ApplicationContext): CommandLineRunner? {
		return CommandLineRunner { args: Array<String> ->
			logger.info("args: " + args.contentToString() + ": bean-listing: ")
			val beanNames = ctx.beanDefinitionNames
			Arrays.sort(beanNames)
			for (beanName in beanNames) {
				logger.info(beanName)
				if (beanName.startsWith("applicationProperties")) {
					val bean = ctx.getBean(
						beanName,
						PropertySourcesPlaceholderConfigurer::class.java
					)
				}
			}
			for (x in args) {
				if (x == "--exit") {
					logger.info("exit called")
					SpringApplication.exit(ctx)
				}
			}
			ai.centa.weaves0.Wrappers.it().halt()
		}
	}
}

fun main(args: Array<String>) {

	runApplication<KsbEgApplication>(*args)


}
