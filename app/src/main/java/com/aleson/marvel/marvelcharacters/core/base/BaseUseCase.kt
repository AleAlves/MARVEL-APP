package com.aleson.marvel.marvelcharacters.core.base

import com.aleson.marvel.marvelcharacters.core.ErrorModel

interface UseCaseRequest

interface UseCaseResponse

abstract class BaseUseCase<Request : UseCaseRequest, Response : UseCaseResponse> {

    lateinit var request: Request

    abstract fun execute(onResponse: ((Response?) -> Unit), onError: (ErrorModel?) -> Unit)
}