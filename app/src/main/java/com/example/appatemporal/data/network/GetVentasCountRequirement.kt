package com.example.appatemporal.data.network

import com.example.appatemporal.domain.Repository

class GetVentasCountRequirement {
    suspend operator fun invoke(uid: String, repository: Repository) : Pair<Int, Int> {
        return repository.ventasCount(uid)
    }
}