package todo.domain.model.entity

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity

@MappedEntity
class ToDo(
    var title: String,
    val authorId: Long
) {

    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    var id: Long? = null

    var completed = false
}
