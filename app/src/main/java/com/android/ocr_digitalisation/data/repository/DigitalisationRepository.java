package com.android.ocr_digitalisation.data.repository;

import android.os.Handler;

import com.android.ocr_digitalisation.data.model.PvOcr;
import com.android.ocr_digitalisation.ui.digitalisation.DigitalisationDetailsActivity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DigitalisationRepository {

    private final int SPLASH_DISPLAY_LENGTH = 1000;

    public void getListPDF(final DigitalisationDetailsActivity activity){
        final List<PvOcr> list = new ArrayList();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        PvOcr pv1 = new PvOcr(1, "607654161621.pdf", timestamp);
        PvOcr pv2 = new PvOcr(2, "607897414615.pdf", timestamp);
        PvOcr pv3 = new PvOcr(3, "607511621321.pdf", timestamp);
        PvOcr pv4 = new PvOcr(4, "607654561679.pdf", timestamp);

        list.add(pv1);list.add(pv2);list.add(pv3);list.add(pv4);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                activity.getListPDDigital(list);
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

}
