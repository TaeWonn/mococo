package kr.side.mococo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MococoApplication

fun main(args: Array<String>) {
    runApplication<MococoApplication>(*args)
}
