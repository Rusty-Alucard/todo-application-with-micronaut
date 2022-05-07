package todo.domain.service.graphql.query

import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import jakarta.inject.Singleton
import todo.domain.model.entity.ToDo
import todo.domain.repository.ToDoRepository

@Singleton
class ToDosDataFetcher(
    private val toDoRepository: ToDoRepository
) : DataFetcher<Iterable<ToDo?>> {

    override fun get(environment: DataFetchingEnvironment): Iterable<ToDo?> {
        return toDoRepository.findAll()
    }
}
