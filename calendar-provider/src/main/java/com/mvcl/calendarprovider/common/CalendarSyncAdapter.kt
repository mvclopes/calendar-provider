package com.mvcl.calendarprovider.common

import android.net.Uri
import android.provider.CalendarContract

internal fun Uri.asSyncAdapter(
    accountName: String,
    accountType: String
): Uri {
    return this.buildUpon()
        .appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER, "true")
        .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_NAME, accountName)
        .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE, accountType)
        .build()
}