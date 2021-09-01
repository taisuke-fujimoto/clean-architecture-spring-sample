package sample.config

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.jetbrains.exposed.sql.SchemaUtils
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import sample.interfaceAdapters.gateways.db.tables.Users

@Component
class SchemaCreation : ApplicationRunner {
    private val logger: Log = LogFactory.getLog(javaClass)

    @Transactional
    override fun run(args: ApplicationArguments?) {
        logger.debug("[run] create database schema")
        SchemaUtils.create(Users)
    }
}
