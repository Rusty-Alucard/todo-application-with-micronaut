package todo.domain.factory

import graphql.GraphQL
import graphql.schema.idl.RuntimeWiring
import graphql.schema.idl.SchemaGenerator
import graphql.schema.idl.SchemaParser
import graphql.schema.idl.TypeDefinitionRegistry
import graphql.schema.idl.TypeRuntimeWiring
import graphql.schema.idl.errors.SchemaMissingError
import io.micronaut.context.annotation.Factory
import io.micronaut.core.io.ResourceResolver
import jakarta.inject.Singleton
import todo.domain.service.graphql.mutation.CompleteToDoDataFetcher
import todo.domain.service.graphql.mutation.CreateToDoDataFetcher
import todo.domain.service.graphql.query.AuthorDataFetcher
import todo.domain.service.graphql.query.ToDosDataFetcher
import java.io.BufferedReader
import java.io.InputStreamReader

@Factory
class GraphQLFactory {

    @Singleton
    fun graphQL(
        resourceResolver: ResourceResolver,
        toDosDataFetcher: ToDosDataFetcher,
        createToDoDataFetcher: CreateToDoDataFetcher,
        completeToDoDataFetcher: CompleteToDoDataFetcher,
        authorDataFetcher: AuthorDataFetcher
    ): GraphQL {
        val schemaParser = SchemaParser()
        val schemaGenerator = SchemaGenerator()

        // Load the schema
        val schemaDefinition = resourceResolver
            .getResourceAsStream("classpath:schema.graphqls")
            .orElseThrow { SchemaMissingError() }

        // Parse the schema and merge it into a type registry
        val typeRegistry = TypeDefinitionRegistry()
        typeRegistry.merge(schemaParser.parse(BufferedReader(InputStreamReader(schemaDefinition))))

        // Create the runtime wiring.
        val runtimeWiring = RuntimeWiring.newRuntimeWiring()
            .type("Query") { typeWiring: TypeRuntimeWiring.Builder ->
                typeWiring
                    .dataFetcher("toDos", toDosDataFetcher)
            }
            .type("Mutation") { typeWiring: TypeRuntimeWiring.Builder ->
                typeWiring
                    .dataFetcher("createToDo", createToDoDataFetcher)
                    .dataFetcher("completeToDo", completeToDoDataFetcher)
            }
            .type("ToDo") { typeWiring: TypeRuntimeWiring.Builder ->
                typeWiring
                    .dataFetcher("author", authorDataFetcher)
            }
            .build()

        // Create the executable schema.
        val graphQLSchema = schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring)

        // Return the GraphQL bean.
        return GraphQL.newGraphQL(graphQLSchema).build()
    }
}
