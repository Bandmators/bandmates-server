package com.dygames.bandmates.domain.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.NoRepositoryBean
import java.util.Optional

@NoRepositoryBean
interface BaseRepository<T, ID> : CrudRepository<T, ID> {

    override fun findById(id: ID): Optional<T>
    override fun existsById(id: ID): Boolean
    override fun deleteById(id: ID)
}
