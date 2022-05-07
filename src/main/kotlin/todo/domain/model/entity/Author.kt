package todo.domain.model.entity

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import javax.validation.constraints.NotNull

@MappedEntity
class Author(
    val username: @NotNull String?
) {

    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    var id: Long? = null
}
