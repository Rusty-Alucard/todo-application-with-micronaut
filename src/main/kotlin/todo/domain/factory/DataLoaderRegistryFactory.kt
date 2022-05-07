package todo.domain.factory

import io.micronaut.context.annotation.Factory
import io.micronaut.runtime.http.scope.RequestScope
import org.dataloader.DataLoader
import org.dataloader.DataLoaderRegistry
import org.slf4j.LoggerFactory
import todo.domain.service.wiring.AuthorDataLoader

@Factory
class DataLoaderRegistryFactory {

    companion object {
        private val LOG = LoggerFactory.getLogger(DataLoaderRegistryFactory::class.java)
    }

    @Suppress("unused")
    @RequestScope
    fun dataLoaderRegistry(authorDataLoader: AuthorDataLoader): DataLoaderRegistry {
        val dataLoaderRegistry = DataLoaderRegistry()
        dataLoaderRegistry.register(
            "author",
            DataLoader.newMappedDataLoader(authorDataLoader)
        )

        LOG.trace("Create new data loader registry")

        return dataLoaderRegistry
    }
}
