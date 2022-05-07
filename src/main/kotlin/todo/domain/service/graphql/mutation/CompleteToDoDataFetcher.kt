package todo.domain.service.graphql.mutation

import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import jakarta.inject.Singleton
import todo.domain.model.entity.ToDo
import todo.domain.repository.ToDoRepository

@Singleton
class CompleteToDoDataFetcher(
    private val toDoRepository: ToDoRepository
) : DataFetcher<Boolean> {

    override fun get(environment: DataFetchingEnvironment): Boolean {
        val id = environment.getArgument<String>("id").toLong()

        return toDoRepository
            .findById(id)
            .map { todo -> setCompletedAndUpdate(todo!!) }
            .orElse(false)
    }

    private fun setCompletedAndUpdate(todo: ToDo): Boolean {
        todo.completed = true
        toDoRepository.update(todo)
        return true
    }
}
