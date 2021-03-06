package org.alex73.android.bel;

import android.text.util.Linkify;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Step1 extends Step {
    public Step1(AndroidBel ui) {
        super(ui);
    }

    @Override
    protected void show() {
        ui.setContentView(R.layout.step1);

        textLog = (TextView) ui.findViewById(R.id.textLog1);
        btnNext = (Button) ui.findViewById(R.id.btnNext1);
    }

    @Override
    protected void process() throws Exception {
        boolean rooted = new LocalStorage().checkSu();

        if (!rooted) {
            MyLog.log("NON-ROOTED");
            ui.runOnUiThread(new Runnable() {
                public void run() {
                    new StepFinish(ui, ui.getResources().getText(R.string.msgRoot), false).doit();
                }
            });
        } else {
            MyLog.log("ROOTED");
            ui.runOnUiThread(new Runnable() {
                public void run() {
                    Linkify.addLinks(textLog, Linkify.ALL);
                }
            });
            setNext(new OnClickListener() {
                public void onClick(View view) {
                    new Step4(ui).doit();
                }
            });
        }
    }
}
