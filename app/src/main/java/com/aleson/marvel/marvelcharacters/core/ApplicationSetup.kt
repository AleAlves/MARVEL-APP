package com.aleson.marvel.marvelcharacters.core

class ApplicationSetup {


    companion object {

        const val mock = false
        const val TIME_STAMP = "ts"
        const val API_KEY = "apikey"
        const val HASH = "hash"
        const val LIMIT = "limit"
        const val ORDER_BY = "orderBy"
        const val OFFSET = "offset"
        const val NAME_START_WITH = "nameStartsWith"
        const val publicKey = "002326cdaa1631d23bfb6ada5a2d7515"
        const val privateKey = "12fdc64385ad40054f84c773588ee2e7c9f0d6af"
        const val digest = "MD5"
        const val room = "database"

        class Values{

            companion object {

                const val limit = 20
                const val order = "name"
            }
        }

        class HTTP{

            companion object {

                const val success = 200
            }
        }

        class API{

            companion object {

                const val baseUrl = "https://gateway.marvel.com"
                const val characters = "/v1/public/characters"
            }
        }

    }

}