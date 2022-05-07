package todo.domain.service.graphql.mutation

import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import jakarta.inject.Singleton
import todo.domain.model.entity.ToDo
import todo.domain.repository.AuthorRepository
import todo.domain.repository.ToDoRepository
import javax.transaction.Transactional

@Singleton
open class CreateToDoDataFetcher(
    private val toDoRepository: ToDoRepository,
    private val authorRepository: AuthorRepository
) : DataFetcher<ToDo> {

    @Transactional
    override fun get(environment: DataFetchingEnvironment): ToDo {
        val title = environment.getArgument<String>("title")
        val username = environment.getArgument<String>("author")
        val author = authorRepository.findOrCreate(username)
        val toDo = ToDo(title, author.id!!)
        return toDoRepository.save(toDo)
    }
}
