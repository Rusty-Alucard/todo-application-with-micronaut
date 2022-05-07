package todo.domain.service.graphql.query

import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import jakarta.inject.Singleton
import todo.domain.model.entity.Author
import todo.domain.model.entity.ToDo
import java.util.concurrent.CompletionStage

@Singleton
class AuthorDataFetcher : DataFetcher<CompletionStage<Author>> {

    override fun get(environment: DataFetchingEnvironment): CompletionStage<Author> {
        val toDo: ToDo = environment.getSource()
        val authorDataLoader = environment.getDataLoader<Long, Author>("author")
        return authorDataLoader.load(toDo.authorId)
    }
}
