package todo.domain.repository

import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.PageableRepository
import todo.domain.model.entity.ToDo

@JdbcRepository(dialect = Dialect.MYSQL)
interface ToDoRepository : PageableRepository<ToDo, Long>
