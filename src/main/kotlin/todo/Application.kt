package todo

import io.micronaut.runtime.Micronaut.build
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("todo")
		.start()
}

