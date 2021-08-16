package sample.interfaceAdapters.gateways.db.tables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Users : Table() {
    val id: Column<Long> = long("id").autoIncrement()
    val account: Column<String> = varchar("account", 10).uniqueIndex()

    override val primaryKey: PrimaryKey = PrimaryKey(id)
}
