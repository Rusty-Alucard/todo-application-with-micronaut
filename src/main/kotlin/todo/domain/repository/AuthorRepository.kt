package todo.domain.repository

import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import todo.domain.model.entity.Author

@JdbcRepository(dialect = Dialect.MYSQL)
abstract class AuthorRepository : CrudRepository<Author, Long> {

    abstract fun findByUsername(username: String): Author?

    abstract fun findByIdIn(ids: Collection<Long>): Collection<Author>

    fun findOrCreate(username: String): Author {
        return findByUsername(username) ?: save(Author(username))
    }
}
