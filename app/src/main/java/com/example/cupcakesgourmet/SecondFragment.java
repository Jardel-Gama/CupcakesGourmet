package com.example.cupcakesgourmet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Locale;

public class SecondFragment extends Fragment {

    private int quantidade = 0;
    private int totalCentavos = 0;

    private TextView txtCarrinho;
    private TextView txtTotal;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        return inflater.inflate(
                R.layout.fragment_second,
                container,
                false
        );
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        Button chocolate = view.findViewById(R.id.btnChocolate);
        Button morango = view.findViewById(R.id.btnMorango);
        Button baunilha = view.findViewById(R.id.btnBaunilha);
        Button finalizar = view.findViewById(R.id.btnFinalizarPedido);
        txtCarrinho = view.findViewById(R.id.txtCarrinho);
        txtTotal = view.findViewById(R.id.txtTotal);

        atualizarCarrinho();
        finalizar.setOnClickListener(v -> finalizarPedido());

        chocolate.setOnClickListener(v ->
                adicionarCupcake("Chocolate", 800));

        morango.setOnClickListener(v ->
                adicionarCupcake("Morango", 900));

        baunilha.setOnClickListener(v ->
                adicionarCupcake("Baunilha", 700));
    }

    private void adicionarCupcake(String sabor, int precoCentavos) {
        quantidade++;
        totalCentavos += precoCentavos;

        atualizarCarrinho();

        com.google.android.material.snackbar.Snackbar snackbar =
                com.google.android.material.snackbar.Snackbar.make(
                        requireView(),
                        "Cupcake de " + sabor + " adicionado!",
                        com.google.android.material.snackbar.Snackbar.LENGTH_LONG
                );

        android.widget.TextView textoMensagem =
                snackbar.getView().findViewById(
                        com.google.android.material.R.id.snackbar_text
                );

        textoMensagem.setTextSize(20);
        snackbar.show();
    }

    private void atualizarCarrinho() {
        txtCarrinho.setText("Carrinho: " + quantidade + " cupcakes");

        String valor = String.format(
                Locale.forLanguageTag("pt-BR"),
                "Total: R$ %.2f",
                totalCentavos / 100.0
        );

        txtTotal.setText(valor);
    }
    private void finalizarPedido() {
        if (quantidade == 0) {
            com.google.android.material.snackbar.Snackbar snackbar =
                    com.google.android.material.snackbar.Snackbar.make(
                            requireView(),
                            "Adicione um cupcake ao carrinho!",
                            com.google.android.material.snackbar.Snackbar.LENGTH_LONG
                    );

            android.widget.TextView textoAviso =
                    snackbar.getView().findViewById(
                            com.google.android.material.R.id.snackbar_text
                    );

            textoAviso.setTextSize(20);
            textoAviso.setMaxLines(3);
            snackbar.show();
            return;
        }

        com.google.android.material.snackbar.Snackbar snackbar =
                com.google.android.material.snackbar.Snackbar.make(
                        requireView(),
                        "Pedido finalizado com sucesso!",
                        com.google.android.material.snackbar.Snackbar.LENGTH_LONG
                );

        android.widget.TextView textoMensagem =
                snackbar.getView().findViewById(
                        com.google.android.material.R.id.snackbar_text
                );

        textoMensagem.setTextSize(20);
        snackbar.show();
        quantidade = 0;
        totalCentavos = 0;
        atualizarCarrinho();
    }

}