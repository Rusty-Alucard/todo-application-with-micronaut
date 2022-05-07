package todo.domain.service.wiring

import io.micronaut.scheduling.TaskExecutors
import jakarta.inject.Named
import jakarta.inject.Singleton
import org.dataloader.MappedBatchLoader
import todo.domain.model.entity.Author
import todo.domain.repository.AuthorRepository
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage
import java.util.concurrent.ExecutorService

@Singleton
class AuthorDataLoader(
    private val authorRepository: AuthorRepository,
    @Named(TaskExecutors.IO) val executor: ExecutorService
) : MappedBatchLoader<Long, Author> {

    override fun load(keys: MutableSet<Long>): CompletionStage<Map<Long, Author>> =
        CompletableFuture.supplyAsync({
            authorRepository
                .findByIdIn(keys.toList())
                .associateBy { it.id!! }
        }, executor)
}
