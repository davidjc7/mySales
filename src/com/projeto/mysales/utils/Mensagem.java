package com.projeto.mysales.utils;

import android.app.AlertDialog;
import android.content.Context;

public class Mensagem {
	
	public static void Exibir(String titulo, String msg, String botao, Context context) {
		AlertDialog.Builder mensagem = new AlertDialog.Builder(context);
		mensagem.setTitle(titulo);
		mensagem.setMessage(msg);
		mensagem.setNeutralButton(botao, null);
		mensagem.show();
	}
	
	

}
