package com.chesire.orochi.api.kitsu

object KitsuEndpoint {
    object OAuth {
        const val Token = "https://kitsu.io/api/oauth/token"
    }

    object User {
        /**
         * URL for getting the library entries for a user.
         * Requires formatting the string with the user id.
         */
        const val LibraryEntries = "https://kitsu.io/api/edge/users/%s/library-entries"
    }
}
