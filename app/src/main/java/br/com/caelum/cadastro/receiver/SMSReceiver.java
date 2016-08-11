package br.com.caelum.cadastro.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.dao.AlunoDAO;

/**
 * Created by wanderson on 07/06/16.
 */
public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();

        Object[] mensagens = (Object[]) bundle.get("pdus");

        byte[] mensagem = (byte[]) mensagens[0];

        String formato = (String) bundle.get("format");

        SmsMessage sms = SmsMessage.createFromPdu(mensagem,formato);

        String numero = sms.getDisplayOriginatingAddress();

        AlunoDAO dao = new AlunoDAO(context);

        if (dao.isAluno(numero)){
            Toast.makeText(context,"Mensagem do aluno de telefone : "+ numero ,Toast.LENGTH_LONG).show();
        }

        MediaPlayer mp = MediaPlayer.create(context, R.raw.msg);
        mp.start();

    }
}
