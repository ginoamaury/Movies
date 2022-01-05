package com.co.ceiba.domain.builder

import com.co.ceiba.domain.models.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Builder {

    companion object{

        fun getFlowListMovie (): Flow<List<Movie>> {
            val movies = listOf(BuilderMovie.getMovie("Spiderman"),BuilderMovie.getMovie("Deadpool"))
            return flow { emit(movies) }
        }

        fun getFlowMovie (): Flow<Movie> {
            return flow { emit(BuilderMovie.getMovie("Spiderman")) }
        }

        fun getMovie (): Movie = BuilderMovie.getMovie("Spiderman")

        fun getListMovie (): List<Movie> = listOf(BuilderMovie.getMovie("Spiderman"),BuilderMovie.getMovie("Deadpool"))

    }


    private class BuilderMovie {
        companion object {
            fun getMovie (name:String): Movie {
                if(name == "Spiderman"){
                    return Movie (
                        false,
                        "/1Rr5SrvHxMXHu5RjKpaMba8VTzi.jpg",
                        "genres",
                        123456,
                        "en",
                        "Spider-Man: No Way Home",
                        "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                        0.0,
                        "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                        "2021-12-15",
                        "Spider-Man: No Way Home",
                        false,
                        0.0,
                        0
                    )
                }else{
                    return Movie(
                        false,
                        "",
                        "genres",
                        413323,
                        "en",
                        "Deadpool: From Comics to Screen... to Screen",
                        "This documentary divided into five segments examines the source and its path to the movies, backstory, special effects story/character areas, cast and performances. It includes notes from Reynolds, Liefeld, Miller, Wernick, Reese, executive producers Aditya Sood and Stan Lee, co-creator/comics writer Fabian Nicieza, producer Simon Kinberg, comics writer Joe Kelly, specialty costume designer Russell Shinkle, makeup designer Bill Corso, production designer Sean Haworth, director of photography Ken Seng, executive producer/unit production manager John J. Kelly, previs supervisor Franck Balson, stunt coordinator Philip J. Silvera, visual effects supervisors Pauline Duvall and Jonathan Rothbart, visual effects producer Annemarie Griggs, 2nd unit director/stunt coordinator Robert Alonzo, special effects coordinator Alex Burdett, utility stunts Regis Harrington, composer Tom Holkenberg, and actors Morena Baccarin, TJ Miller, Brianna Hildebrand, Leslie Uggams, Ed Skrein, and Gina Carano.",
                        0.0,
                        "/chV0avy5ogIB2PMTInT4KpHDzwj.jpg",
                        "2016-05-10",
                        "Deadpool: From Comics to Screen... to Screen",
                        false,
                        0.0,
                        0
                    )
                }
            }
        }
    }
}