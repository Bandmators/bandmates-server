package com.dygames.bandmates.domain

import com.dygames.bandmates.domain.repository.BaseRepository
import java.util.Optional

open class FakeRepository<T : BaseEntity> : BaseRepository<T, Long> {
    private var lastId = 0
    protected val entities: MutableList<T> = mutableListOf()

    override fun <S : T> save(entity: S): S {
        entities.add(entity)
        return entity
    }

    override fun findById(id: Long): Optional<T> {
        return Optional.of(entities.first {
            it.id == id
        })
    }

    override fun <S : T> saveAll(entities: MutableIterable<S>): MutableIterable<S> {
        TODO("Not yet implemented")
    }

    override fun existsById(id: Long): Boolean {
        return entities.any {
            it.id == id
        }
    }

    override fun findAll(): Iterable<T> {
        return entities
    }

    override fun count(): Long {
        return entities.size.toLong()
    }

    override fun deleteAll() {
        entities.clear()
    }

    override fun deleteAll(entities: MutableIterable<T>) {
        TODO("Not yet implemented")
    }

    override fun deleteAllById(ids: MutableIterable<Long>) {
        TODO("Not yet implemented")
    }

    override fun delete(entity: T) {
        entities.remove(entity)
    }

    override fun findAllById(ids: MutableIterable<Long>): MutableIterable<T> {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Long) {
        entities.removeIf {
            it.id == id
        }
    }
}
