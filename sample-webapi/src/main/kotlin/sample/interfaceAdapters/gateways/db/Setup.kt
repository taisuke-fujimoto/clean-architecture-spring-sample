package sample.interfaceAdapters.gateways.db

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Component
import sample.interfaceAdapters.gateways.db.tables.Users
import javax.annotation.PostConstruct

@Component
class Setup {
    @PostConstruct
    fun postConstruct() {
        Database.connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver", user = "root", password = "")

        transaction {
            SchemaUtils.create(Users)
        }
    }
}
