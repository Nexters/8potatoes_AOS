package com.eight_potato.domain.usecase.reststop

import com.eight_potato.domain.model.address.PoiModel
import com.eight_potato.domain.model.direction.HighwayModel
import com.eight_potato.domain.model.reststop.RestStopModel
import com.eight_potato.domain.repository.RestStopRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 휴게소 리스트 요청 UseCase
 */
@Singleton
class GetRestStopsUseCase @Inject constructor(
    private val restStopRepository: RestStopRepository
){
    suspend operator fun invoke(
        from: PoiModel,
        to: PoiModel,
        highways: List<HighwayModel>
    ) : Result<List<RestStopModel>> {
        return restStopRepository.getRestStops(
            from = from,
            to = to,
            highways = highways
        )
    }
}