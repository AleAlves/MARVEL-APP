package com.aleson.marvel.marvelcharacters.core.base

interface UseCaseRequest

interface UseCaseResponse

abstract class BaseUseCase<Request : UseCaseRequest, Response : UseCaseResponse> {

    lateinit var request: Request

    abstract fun execute(onResponse: ((Response?) -> Unit))
}