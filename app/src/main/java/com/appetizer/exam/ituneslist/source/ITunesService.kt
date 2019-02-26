package com.appetizer.exam.ituneslist.source

import com.appetizer.exam.entities.ITunesResponseWrapper
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * Created by: david on 2019-02-21
 * All rights reserved.
 * val url = "https://itunes.apple.com/search?term=star&amp;country=au&amp;media=movie"
 */
interface ITunesService {
    /**
     * gets the list of itunes data by the following parameters
     * @param [term] - search term used to search for data
     * @param [country] - country of origin of the data
     * @param [media] - media type of the data
     */
    @GET("search")
    fun list(@Query("term") term: String,
             @Query("country") country: String,
             @Query("media") media: String): Flowable<ITunesResponseWrapper>

    /**
     * gets the list of itunes data by the foloowing parameters
     * @param [options] - parameters to include in the request like term, media
     * and country, etc.
     *
     * for the complete set of parameters, please refer to this link
     * [](https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/#searching)
     */
    @GET("search")
    fun list(@QueryMap options: Map<String, String>): Flowable<ITunesResponseWrapper>
}