package com.eight_potato.domain.usecase.reststop

import com.eight_potato.domain.model.reststop.DetailRestStopModel
import com.eight_potato.domain.repository.RestStopRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 휴게소 상세 정보 요청 UseCase
 */
@Singleton
class GetDetailRestStopUseCase @Inject constructor(
    private val restStopRepository: RestStopRepository
){
    /**
     * @param restStopCode 요청하고자 하는 휴게소의 코드
     */
    suspend operator fun invoke(
        restStopCode: String
    ): Result<DetailRestStopModel> {
        return restStopRepository.getDetailRestStop(restStopCode)
    }
}