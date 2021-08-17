package sample.config

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.jetbrains.exposed.spring.SpringTransactionManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
class ExposedConfig(
    private val datasource: DataSource
) {
    private val logger: Log = LogFactory.getLog(javaClass)

    @Bean
    fun springTransactionManager(): SpringTransactionManager =
        SpringTransactionManager(datasource).also {
            logger.debug("[springTransactionManager] called")
        }
}
