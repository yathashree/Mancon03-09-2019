package com.anitha.offsitefinal;

import android.icu.text.DecimalFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

class DecimalRemover implements ValueFormatter {
    protected DecimalFormat mFormat;

    public DecimalRemover(DecimalFormat format) {
        this.mFormat = format;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return mFormat.format(value);
    }
}
